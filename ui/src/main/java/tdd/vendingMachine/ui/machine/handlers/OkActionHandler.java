package tdd.vendingMachine.ui.machine.handlers;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import tdd.vendingMachine.businessLogic.machine.service.VendingMachineService;
import tdd.vendingMachine.common.number.NumberUtils;
import tdd.vendingMachine.ui.machine.actions.VendingMachineAction;
import tdd.vendingMachine.ui.machine.view.model.VendingMachineDisplay;
import tdd.vendingMachine.ui.machine.view.model.VendingMachineState;
import tdd.vendingMachine.ui.machine.view.model.VendingMachineViewModel;
import tdd.vendingMachine.ui.properties.GUIProperties;

/**
 * Handles "OK" button press action.
 */
public class OkActionHandler implements VendingMachineActionHandler<Void> {

    /**
     * GUIProperties instance.
     */
    private static final GUIProperties GUI_PROPERTIES = GUIProperties.getInstance();

    /**
     * Display message shown when invalid product code is specified.
     */
    private static final String INVALID_CODE_MSG = "Invalid product code.";

    /**
     * Display message shown when product referenced by the given code is unavailable.
     */
    private static final String PRODUCT_UNAVAILABLE_MSG = "Selected product is unavailable.";

    /**
     * Vending machine service.
     */
    private final VendingMachineService service;
    /**
     * Handled action.
     */
    private final VendingMachineAction action;

    /**
     * Constructor assigning handled action.
     *
     * @param action handled action.
     */
    OkActionHandler(VendingMachineAction action) {
        this.action = action;
        this.service = VendingMachineService.newVendingMachineService();
    }

    /**
     * Handles "OK" button press action.
     * Displays result message on the vending machine display.
     *
     * @return nothing ({@code null}).
     */
    @Override
    public Void handle() {
        VendingMachineViewModel vendingMachine = action.getVendingMachineActionParameters().getVendingMachine();
        VendingMachineDisplay display = vendingMachine.getDisplay();
        // Code selection has not started yet
        if (VendingMachineState.SELECTION_STARTED != vendingMachine.getState()) {
            display.setText(StringUtils.EMPTY);
            return null;
        }

        final String code = vendingMachine.getSelectedCode();
        // Invalid code
        if (BooleanUtils.isFalse(vendingMachine.getVendingMachine().getAvailableCodes().contains(code))) {
            vendingMachine.getDisplay()
                .setText(INVALID_CODE_MSG);
            vendingMachine.setState(VendingMachineState.IDLE);
            return null;
        }
        // Product unavailable
        if (BooleanUtils.isFalse(service.isProductAvailable(vendingMachine.getVendingMachine(), code))) {
            vendingMachine.getDisplay()
                .setText(PRODUCT_UNAVAILABLE_MSG);
            vendingMachine.setState(VendingMachineState.IDLE);
            return null;
        }

        vendingMachine.getDisplay().setText(
            String.format(GUI_PROPERTIES.getProperty(GUIProperties.PropertyKeys.DISPLAY_INSERT_COINS_TEXT),
                NumberUtils.format(vendingMachine.getVendingMachine().getShelves().get(code).getProductPrice())
            ));
        vendingMachine.setState(VendingMachineState.SELECTED);

        return null;
    }
}
