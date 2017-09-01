package tdd.vendingMachine.ui.machine.actions.concrete;

import tdd.vendingMachine.ui.machine.actions.VendingMachineActionAbstr;
import tdd.vendingMachine.ui.machine.actions.VendingMachineActionParameters;
import tdd.vendingMachine.ui.machine.actions.VendingMachineActionType;

public class NumberButtonPressedAction extends VendingMachineActionAbstr<String> {

    /**
     * Constructor assigning action parameters.
     *
     * @param actionParameters action parameters.
     */
    public NumberButtonPressedAction(VendingMachineActionParameters<String> actionParameters) {
        super(actionParameters);
    }

    @Override
    public VendingMachineActionType getActionType() {
        return VendingMachineActionType.NUMBER_BUTTON_PRESSED;
    }
}
