package tdd.vendingMachine.businessLogic.machine.actions.internal;

import tdd.vendingMachine.businessLogic.machine.actions.VendingMachineActionAbstr;
import tdd.vendingMachine.businessLogic.machine.actions.VendingMachineActionType;

/**
 * Action displaying the message.
 * Parameters hold the message to be displayed.
 */
public class DisplayMessageAction extends VendingMachineActionAbstr<String> {
    /**
     * Constructor assigning action parameters.
     *
     * @param actionParameters action parameters.
     */
    public DisplayMessageAction(String actionParameters) {
        super(actionParameters);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VendingMachineActionType getActionType() {
        return null;
    }
}
