package tdd.vendingMachine.ui.machine.actions;

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
    VendingMachineActionParameters<T> getVendingMachineActionParameters();

    /**
     * Returns action type.
     *
     * @return action type.
     */
    VendingMachineActionType getActionType();

    /**
     * Creates new instance of {@link VendingMachineAction}.
     *
     * @param parameters action parameters.
     * @param actionType action type.
     * @param <T>        custom action parameters type.
     * @return new instance of {@link VendingMachineAction}.
     */
    static <T> VendingMachineAction<T> of(VendingMachineActionParameters<T> parameters, VendingMachineActionType actionType) {
        return new VendingMachineAction<T>() {

            @Override
            public VendingMachineActionParameters<T> getVendingMachineActionParameters() {
                return parameters;
            }

            @Override
            public VendingMachineActionType getActionType() {
                return actionType;
            }
        };
    }
}
