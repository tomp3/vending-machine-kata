package tdd.vendingMachine.ui.machine.handlers;

/**
 * Class responsible for vending machine actions' handling.
 *
 * @param <K> handler return type.
 */
public interface VendingMachineActionHandler<K> {
    /**
     * Handles vending machine action.
     *
     * @return result of vending machine action handling.
     */
    K handle();
}
