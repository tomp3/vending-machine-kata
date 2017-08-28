package tdd.vendingMachine.businessLogic.machine.actions.internal;

import tdd.vendingMachine.businessLogic.machine.actions.VendingMachineAction;

/**
 * Action displaying the message.
 * Parameters hold the message to be displayed.
 */
@FunctionalInterface
public interface DisplayMessageAction extends VendingMachineAction<String> {
}
