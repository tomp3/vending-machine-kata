package tdd.vendingMachine.ui.machine.handlers;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import tdd.vendingMachine.businessLogic.cash.exception.ChangeImpossibleException;
import tdd.vendingMachine.businessLogic.cash.exception.CoinInsertionImpossibleException;
import tdd.vendingMachine.businessLogic.machine.exception.ProductUnavailableException;
import tdd.vendingMachine.businessLogic.machine.service.VendingMachineService;
import tdd.vendingMachine.common.number.NumberUtils;
import tdd.vendingMachine.model.common.CoinType;
import tdd.vendingMachine.model.machine.VendingMachineShelf;
import tdd.vendingMachine.ui.machine.actions.VendingMachineAction;
import tdd.vendingMachine.ui.machine.actions.VendingMachineActionParameters;
import tdd.vendingMachine.ui.machine.actions.concrete.CancelAction;
import tdd.vendingMachine.ui.machine.actions.concrete.InsertCoinAction;
import tdd.vendingMachine.ui.machine.view.model.VendingMachineState;
import tdd.vendingMachine.ui.machine.view.model.VendingMachineViewModel;
import tdd.vendingMachine.ui.properties.GUIProperties;

import java.math.BigDecimal;

/**
 * Handles action of inserting coins to the vending machine.
 */
public class InsertCoinActionHandler implements VendingMachineActionHandler<Void> {

    /**
     * GUIProperties instance.
     */
    private static final GUIProperties GUI_PROPERTIES = GUIProperties.getInstance();

    /**
     * Handled action.
     */
    private final InsertCoinAction action;

    /**
     * Vending machine service.
     */
    private final VendingMachineService service;

    /**
     * Vending machine action handler factory.
     */
    private final VendingMachineActionHandlerFactory actionHandlerFactory;

    /**
     * Constructor assigning handled action.
     *
     * @param action handled action.
     */
    InsertCoinActionHandler(VendingMachineAction action, VendingMachineService vendingMachineService, VendingMachineActionHandlerFactory actionHandlerFactory) {
        this.action = (InsertCoinAction) action;
        this.service = vendingMachineService;
        this.actionHandlerFactory = actionHandlerFactory;
    }

    /**
     * Handles action of inserting coins to the vending machine.
     * Displays result message on the vending machine display.
     * In case enough money is inserted, dispenses the product and gives change.
     *
     * @return nothing ({@code null}).
     */
    @Override
    public Void handle() {
        VendingMachineViewModel vendingMachineView = action.getVendingMachineActionParameters().getVendingMachine();
        // Do nothing if state different to SELECTED
        if (vendingMachineView.getState() != VendingMachineState.SELECTED) {
            return null;
        }

        CoinType coin = action.getVendingMachineActionParameters().getActionParameters();
        // if could not insert the coin stop handling the action
        if (BooleanUtils.isFalse(insertCoin(vendingMachineView, coin))) {
            return null;
        }


        BigDecimal userInsertedAmount = service.getInsertedMoneyAmount(vendingMachineView.getVendingMachine());
        String selectedCode = vendingMachineView.getSelectedCode();
        VendingMachineShelf shelf = vendingMachineView.getVendingMachine().getShelves().get(selectedCode);
        BigDecimal productPrice = shelf.getProductPrice();
        // concrete inserted money greater or equal to the product price
        if (userInsertedAmount.compareTo(productPrice) >= 0) {
            // try giving product and change change
            // cancels in case product could not bie given
            giveProductOrCancel(vendingMachineView, selectedCode);
        } else {
            BigDecimal amountLeft = productPrice.subtract(userInsertedAmount);
            vendingMachineView.getDisplay()
                .setText(String.format(GUI_PROPERTIES.getProperty(GUIProperties.PropertyKeys.DISPLAY_INSERT_COINS_TEXT), NumberUtils.format(amountLeft)));
        }


        return null;
    }

    /**
     * Tries inserting given coin to the vending machine cash.
     * If unsuccessful calls {@link CancelActionHandler CancelActionHandler's} handle method.
     *
     * @param vendingMachineView vending machine view.
     * @param coin               inserted coin.
     * @return {@code true} if insertion was successful, {@code false} otherwise.
     */
    private boolean insertCoin(VendingMachineViewModel vendingMachineView, CoinType coin) {
        try {
            service.insertUserCoin(vendingMachineView.getVendingMachine(), coin);
            return true;
        } catch (CoinInsertionImpossibleException e) {
            callCancelHandler(vendingMachineView, e.getMessage());
        }
        return false;
    }

    /**
     * Tries giving product and change.
     * If unsuccessful calls {@link CancelActionHandler CancelActionHandler's} handle method.
     *
     * @param vendingMachineView vending machine view.
     * @param code               selected product code.
     */
    private void giveProductOrCancel(VendingMachineViewModel vendingMachineView, String code) {
        try {
            service.giveProduct(vendingMachineView.getVendingMachine(), code);

            vendingMachineView.setSelectedCode(StringUtils.EMPTY);
            vendingMachineView.setState(VendingMachineState.IDLE);
            vendingMachineView.getDisplay().setText(StringUtils.EMPTY);
        } catch (ChangeImpossibleException | ProductUnavailableException e) {
            callCancelHandler(vendingMachineView, e.getMessage());
        }
    }

    /**
     * Calls {@link CancelActionHandler#handle()} method to cancel the entire transaction.
     *
     * @param vendingMachineView vending machine view model.
     * @param message            to be displayed.
     */
    private void callCancelHandler(VendingMachineViewModel vendingMachineView, String message) {
        // Create cancel action handler and call it's handle method
        actionHandlerFactory
            .create(new CancelAction(VendingMachineActionParameters.of(vendingMachineView, message)))
            .handle();
    }
}
