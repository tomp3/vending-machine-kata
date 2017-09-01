package tdd.vendingMachine.ui.machine.actions.concrete;

import tdd.vendingMachine.ui.machine.actions.VendingMachineActionAbstr;
import tdd.vendingMachine.ui.machine.actions.VendingMachineActionParameters;
import tdd.vendingMachine.ui.machine.actions.VendingMachineActionType;

public class OkAction extends VendingMachineActionAbstr<Void> {
    /**
     * Constructor assigning action parameters.
     *
     * @param actionParameters action parameters.
     */
    public OkAction(VendingMachineActionParameters<Void> actionParameters) {
        super(actionParameters);
    }

    @Override
    public VendingMachineActionType getActionType() {
        return VendingMachineActionType.OK_PRESSED;
    }
}
