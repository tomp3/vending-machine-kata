package tdd.vendingMachine.ui.machine.actions;

import tdd.vendingMachine.ui.machine.view.model.VendingMachineViewModel;

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
    VendingMachineViewModel getVendingMachine();

    /**
     * Returns custom action parameters.
     *
     * @return custom action parameters.
     */
    T getActionParameters();

    /**
     * Returns new instance of {@link VendingMachineActionParameters} assigning given parameters.
     *
     * @param vendingMachine vending machine.
     * @param params         custom action parameters.
     * @param <T>            custom action parameters type.
     * @return new instance of {@link VendingMachineActionParameters}
     */
    static <T> VendingMachineActionParameters<T> of(VendingMachineViewModel vendingMachine, T params) {
        return new VendingMachineActionParameters<T>() {
            @Override
            public VendingMachineViewModel getVendingMachine() {
                return vendingMachine;
            }

            @Override
            public T getActionParameters() {
                return params;
            }
        };
    }

    /**
     * Returns new instance of {@link VendingMachineActionParameters} assigning given vending machine
     * without any custom action parameters.
     *
     * @param vendingMachine vending machine.
     * @return new instance of {@link VendingMachineActionParameters}
     */
    static VendingMachineActionParameters<Void> of(VendingMachineViewModel vendingMachine) {
        return new VendingMachineActionParameters<Void>() {
            @Override
            public VendingMachineViewModel getVendingMachine() {
                return vendingMachine;
            }

            @Override
            public Void getActionParameters() {
                return null;
            }
        };
    }
}
