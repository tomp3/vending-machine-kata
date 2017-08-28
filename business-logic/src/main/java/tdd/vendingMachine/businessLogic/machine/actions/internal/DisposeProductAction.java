package tdd.vendingMachine.businessLogic.machine.actions.internal;

import tdd.vendingMachine.businessLogic.machine.actions.VendingMachineAction;

/**
 * Action of disposing the product.
 * Parameters hold the code of the product's shelf.
 */
@FunctionalInterface
public interface DisposeProductAction extends VendingMachineAction<String> {
}
