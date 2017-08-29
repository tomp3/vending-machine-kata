package tdd.vendingMachine.businessLogic.machine.actions.user;


import tdd.vendingMachine.businessLogic.machine.actions.VendingMachineActionAbstr;
import tdd.vendingMachine.businessLogic.machine.actions.VendingMachineActionType;

/**
 * Action of selecting the code.
 * Parameters hold the selected code.
 */
public class SelectCodeAction extends VendingMachineActionAbstr<String> {
    /**
     * Constructor assigning action parameters.
     *
     * @param actionParameters action parameters.
     */
    public SelectCodeAction(String actionParameters) {
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
