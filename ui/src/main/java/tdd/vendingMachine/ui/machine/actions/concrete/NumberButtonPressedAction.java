package tdd.vendingMachine.ui.machine.actions.concrete;

import tdd.vendingMachine.ui.machine.actions.VendingMachineActionAbstr;
import tdd.vendingMachine.ui.machine.actions.VendingMachineActionParameters;
import tdd.vendingMachine.ui.machine.actions.VendingMachineActionType;

/**
 * Action fired when number button is pressed.
 */
public class NumberButtonPressedAction extends VendingMachineActionAbstr<String> {

    /**
     * Constructor assigning action parameters.
     *
     * @param actionParameters action parameters.
     */
    public NumberButtonPressedAction(VendingMachineActionParameters<String> actionParameters) {
        super(actionParameters);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VendingMachineActionType getActionType() {
        return VendingMachineActionType.NUMBER_BUTTON_PRESSED;
    }
}
