package tdd.vendingMachine.businessLogic.machine.actions;

/**
 * Abstract implementation of vending machine action.
 * Implements {@link VendingMachineAction#getActionParameters()} method
 * enforcing passing parameters to the constructor be extending classes.
 *
 * @param <T> parameters class.
 */
public abstract class VendingMachineActionAbstr<T> implements VendingMachineAction<T> {

    /**
     * Action parameters.
     */
    private final T actionParameters;

    /**
     * Constructor assigning action parameters.
     *
     * @param actionParameters action parameters.
     */
    public VendingMachineActionAbstr(T actionParameters) {
        this.actionParameters = actionParameters;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getActionParameters() {
        return actionParameters;
    }
}
