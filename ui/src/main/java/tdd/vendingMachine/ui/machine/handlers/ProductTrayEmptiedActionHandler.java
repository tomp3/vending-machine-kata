package tdd.vendingMachine.ui.machine.handlers;

import tdd.vendingMachine.ui.machine.actions.VendingMachineAction;
import tdd.vendingMachine.ui.machine.view.model.VendingMachineViewModel;

public class ProductTrayEmptiedActionHandler implements VendingMachineActionHandler<Void> {

    private final VendingMachineAction action;

    ProductTrayEmptiedActionHandler(VendingMachineAction action) {
        this.action = action;
    }

    @Override
    public Void handle() {
        VendingMachineViewModel vendingMachineView = action.getVendingMachineActionParameters().getVendingMachine();
        vendingMachineView.getVendingMachine().getProductTray().getProducts().clear();
        return null;
    }
}
