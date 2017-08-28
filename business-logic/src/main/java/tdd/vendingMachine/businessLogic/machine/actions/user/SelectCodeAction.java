package tdd.vendingMachine.businessLogic.machine.actions.user;


import tdd.vendingMachine.businessLogic.machine.actions.VendingMachineAction;

/**
 * Action of selecting the code.
 * Parameters hold the selected code.
 */
@FunctionalInterface
public interface SelectCodeAction extends VendingMachineAction<String> {
}
