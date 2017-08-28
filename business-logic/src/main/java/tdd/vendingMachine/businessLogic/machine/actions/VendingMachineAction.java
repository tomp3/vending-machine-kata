package tdd.vendingMachine.businessLogic.machine.actions;

/**
 * Vending machine action.
 *
 * @param <T> action's parameter type.
 */
@FunctionalInterface
public interface VendingMachineAction<T> {
    /**
     * Returns action parameters.
     *
     * @return action parameters.
     */
    T getActionParameters();
}
