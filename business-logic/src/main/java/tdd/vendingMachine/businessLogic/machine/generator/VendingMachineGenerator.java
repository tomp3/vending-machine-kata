package tdd.vendingMachine.businessLogic.machine.generator;

import tdd.vendingMachine.model.machine.VendingMachine;

/**
 * Vending machine generator interface.
 */
public interface VendingMachineGenerator {
    /**
     * Generates vending machine.
     *
     * @return generated vending machine.
     */
    VendingMachine generate();
}
