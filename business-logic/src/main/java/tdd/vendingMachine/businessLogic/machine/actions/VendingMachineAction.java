package tdd.vendingMachine.businessLogic.machine.actions;

/**
 * Vending machine action.
 *
 * @param <T> action's parameter type.
 */
public interface VendingMachineAction<T> {
    /**
     * Returns action parameters.
     *
     * @return action parameters.
     */
    T getActionParameters();

    /**
     * Returns action type.
     *
     * @return action type.
     */
    VendingMachineActionType getActionType();
}
