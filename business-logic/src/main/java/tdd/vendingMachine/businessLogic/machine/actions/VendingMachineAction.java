package tdd.vendingMachine.businessLogic.machine.actions;

/**
 * Vending machine action.
 *
 * @param <T> action's parameter type.
 */
@FunctionalInterface
public interface VendingMachineAction<T> {
    T getActionParameters();
}
