package tdd.vendingMachine.ui.machine.handlers;

import tdd.vendingMachine.ui.machine.actions.VendingMachineAction;
import tdd.vendingMachine.ui.machine.view.model.VendingMachineViewModel;

public class CoinTrayEmptiedActionHandler implements VendingMachineActionHandler<Void> {

    private final VendingMachineAction action;

    CoinTrayEmptiedActionHandler(VendingMachineAction action) {
        this.action = action;
    }

    @Override
    public Void handle() {
        VendingMachineViewModel vendingMachineView = action.getVendingMachineActionParameters().getVendingMachine();
        vendingMachineView.getVendingMachine().getCashTray().getCoins().clear();
        return null;
    }
}
