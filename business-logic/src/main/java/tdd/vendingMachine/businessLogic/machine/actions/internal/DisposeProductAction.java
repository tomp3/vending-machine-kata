package tdd.vendingMachine.businessLogic.machine.actions.internal;

import tdd.vendingMachine.businessLogic.machine.actions.VendingMachineActionAbstr;
import tdd.vendingMachine.businessLogic.machine.actions.VendingMachineActionType;

/**
 * Action of disposing the product.
 * Parameters hold the code of the product's shelf.
 */
public class DisposeProductAction extends VendingMachineActionAbstr<String> {
    /**
     * Constructor assigning action parameters.
     *
     * @param actionParameters action parameters.
     */
    public DisposeProductAction(String actionParameters) {
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
