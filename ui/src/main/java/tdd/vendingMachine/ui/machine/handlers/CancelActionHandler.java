package tdd.vendingMachine.ui.machine.handlers;

import org.apache.commons.lang3.StringUtils;
import tdd.vendingMachine.businessLogic.machine.service.VendingMachineService;
import tdd.vendingMachine.ui.machine.actions.VendingMachineAction;
import tdd.vendingMachine.ui.machine.actions.concrete.CancelAction;
import tdd.vendingMachine.ui.machine.view.model.VendingMachineState;
import tdd.vendingMachine.ui.machine.view.model.VendingMachineViewModel;

/**
 * Cancel action handler.
 */
public class CancelActionHandler implements VendingMachineActionHandler<Void> {

    /**
     * Action.
     */
    private final CancelAction action;

    /**
     * Vending machine service.
     */
    private final VendingMachineService vendingMachineService;

    /**
     * Constructor assigning handled action.
     *
     * @param action handled action.
     */
    CancelActionHandler(VendingMachineAction action) {
        this.action = (CancelAction) action;
        this.vendingMachineService = VendingMachineService.newVendingMachineService();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Void handle() {
        VendingMachineViewModel vendingMachineView = action.getVendingMachineActionParameters().getVendingMachine();
        vendingMachineView.setState(VendingMachineState.IDLE);
        vendingMachineView.setSelectedCode(StringUtils.EMPTY);
        vendingMachineView.getDisplay().setText(action.getVendingMachineActionParameters().getActionParameters());
        vendingMachineService.returnUserMoney(vendingMachineView.getVendingMachine());
        return null;
    }
}
