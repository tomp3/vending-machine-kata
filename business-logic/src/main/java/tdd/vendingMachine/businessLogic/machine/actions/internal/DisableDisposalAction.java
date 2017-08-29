package tdd.vendingMachine.businessLogic.machine.actions.internal;


import tdd.vendingMachine.businessLogic.machine.actions.VendingMachineActionAbstr;
import tdd.vendingMachine.businessLogic.machine.actions.VendingMachineActionType;

/**
 * Action disabling product disposal.
 * Parameters hold the cause message.
 */
public class DisableDisposalAction extends VendingMachineActionAbstr<String> {

    /**
     * Constructor assigning action parameters.
     *
     * @param actionParameters action parameters.
     */
    public DisableDisposalAction(String actionParameters) {
        super(actionParameters);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VendingMachineActionType getActionType() {
        return VendingMachineActionType.INT_DISABLE_DISPOSAL;
    }
}
