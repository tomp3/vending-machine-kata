package tdd.vendingMachine.ui.machine.actions;

/**
 * Abstract implementation of vending machine action.
 * Implements {@link VendingMachineAction#getVendingMachineActionParameters()} method
 * enforcing passing parameters to the constructor be extending classes.
 *
 * @param <T> parameters class.
 */
public abstract class VendingMachineActionAbstr<T> implements VendingMachineAction<T> {

    /**
     * Action parameters.
     */
    private final VendingMachineActionParameters<T> actionParameters;

    /**
     * Constructor assigning action parameters.
     *
     * @param actionParameters action parameters.
     */
    public VendingMachineActionAbstr(VendingMachineActionParameters<T> actionParameters) {
        this.actionParameters = actionParameters;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VendingMachineActionParameters<T> getVendingMachineActionParameters() {
        return actionParameters;
    }
}
