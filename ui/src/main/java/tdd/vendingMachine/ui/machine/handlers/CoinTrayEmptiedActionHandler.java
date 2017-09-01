package tdd.vendingMachine.ui.machine.handlers;

import tdd.vendingMachine.ui.machine.actions.VendingMachineAction;
import tdd.vendingMachine.ui.machine.view.model.VendingMachineViewModel;

/**
 * Object handling action of coin tray emptying.
 */
public class CoinTrayEmptiedActionHandler implements VendingMachineActionHandler<Void> {

    /**
     * Handled action.
     */
    private final VendingMachineAction action;

    /**
     * Constructor assigning handled action.
     * @param action handled action.
     */
    CoinTrayEmptiedActionHandler(VendingMachineAction action) {
        this.action = action;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Void handle() {
        VendingMachineViewModel vendingMachineView = action.getVendingMachineActionParameters().getVendingMachine();
        vendingMachineView.getVendingMachine().getCashTray().getCoins().clear();
        return null;
    }
}
