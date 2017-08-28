package tdd.vendingMachine.businessLogic.machine.actions;

import tdd.vendingMachine.model.machine.VendingMachine;

public interface VendingMachineActionParameters<T> {
    VendingMachine getVendingMachine();

    T getActionParameters();
}
