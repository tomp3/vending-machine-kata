package tdd.vendingMachine.businessLogic.machine.actions.internal;


import tdd.vendingMachine.businessLogic.machine.actions.VendingMachineAction;

/**
 * Action disabling product disposal.
 * Parameters hold the cause message.
 */
@FunctionalInterface
public interface DisableDisposalAction extends VendingMachineAction<String> {
}
