package tdd.vendingMachine.ui.machine.actions.concrete;

import tdd.vendingMachine.ui.machine.actions.VendingMachineActionAbstr;
import tdd.vendingMachine.ui.machine.actions.VendingMachineActionParameters;
import tdd.vendingMachine.ui.machine.actions.VendingMachineActionType;

/**
 * Action of transaction cancellation.
 * Parameters hold the amount paid by the concrete.
 */
public class CancelAction extends VendingMachineActionAbstr<Void> {
    /**
     * Constructor assigning action parameters.
     *
     * @param actionParameters action parameters.
     */
    public CancelAction(VendingMachineActionParameters<Void> actionParameters) {
        super(actionParameters);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VendingMachineActionType getActionType() {
        return VendingMachineActionType.CANCEL_PRESSED;
    }
}
