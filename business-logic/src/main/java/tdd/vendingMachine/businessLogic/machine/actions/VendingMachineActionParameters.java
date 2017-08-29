package tdd.vendingMachine.businessLogic.machine.actions;

import tdd.vendingMachine.model.machine.VendingMachine;

/**
 * Default vending machine action parameters.
 *
 * @param <T> custom action parameters type.
 */
public interface VendingMachineActionParameters<T> {
    /**
     * Returns serviced vending machine instance.
     *
     * @return vending machine instance.
     */
    VendingMachine getVendingMachine();

    /**
     * Returns custom action parameters.
     *
     * @return custom action parameters.
     */
    T getActionParameters();
}
