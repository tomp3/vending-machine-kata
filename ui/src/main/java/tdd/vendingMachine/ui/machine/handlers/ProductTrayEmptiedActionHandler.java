package tdd.vendingMachine.ui.machine.handlers;

import tdd.vendingMachine.ui.machine.actions.VendingMachineAction;
import tdd.vendingMachine.ui.machine.view.model.VendingMachineViewModel;

/**
 * Handles action of product tray emptying.
 */
public class ProductTrayEmptiedActionHandler implements VendingMachineActionHandler<Void> {

    /**
     * Handled action.
     */
    private final VendingMachineAction action;

    /**
     * Constructor assigning  handled action.
     *
     * @param action handled action.
     */
    ProductTrayEmptiedActionHandler(VendingMachineAction action) {
        this.action = action;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Void handle() {
        VendingMachineViewModel vendingMachineView = action.getVendingMachineActionParameters().getVendingMachine();
        vendingMachineView.getVendingMachine().getProductTray().getProducts().clear();
        return null;
    }
}
