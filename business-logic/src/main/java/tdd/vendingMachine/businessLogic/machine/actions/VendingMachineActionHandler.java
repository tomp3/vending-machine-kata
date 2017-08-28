package tdd.vendingMachine.businessLogic.machine.actions;

/**
 * Class responsible for vending machine actions' handling.
 *
 * @param <K> handler return type.
 * @param <T> vending machine action type.
 */
public interface VendingMachineActionHandler<T, K> {
    /**
     * Handles vending machine action.
     *
     * @param action vending machine action.
     * @return result of vending machine action handling.
     */
    K handle(T action);
}
