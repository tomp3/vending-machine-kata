package tdd.vendingMachine.businessLogic.machine.actions.user;

import tdd.vendingMachine.businessLogic.machine.actions.VendingMachineActionAbstr;
import tdd.vendingMachine.businessLogic.machine.actions.VendingMachineActionType;

import java.math.BigDecimal;

/**
 * Action of transaction cancellation.
 * Parameters hold the amount paid by the user.
 */
public class CancelAction extends VendingMachineActionAbstr<BigDecimal> {
    /**
     * Constructor assigning action parameters.
     *
     * @param actionParameters action parameters.
     */
    public CancelAction(BigDecimal actionParameters) {
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
