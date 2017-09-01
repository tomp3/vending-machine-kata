package tdd.vendingMachine.ui.machine.handlers;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import tdd.vendingMachine.ui.machine.actions.VendingMachineAction;
import tdd.vendingMachine.ui.machine.actions.concrete.NumberButtonPressedAction;
import tdd.vendingMachine.ui.machine.view.model.VendingMachineDisplay;
import tdd.vendingMachine.ui.machine.view.model.VendingMachineState;
import tdd.vendingMachine.ui.machine.view.model.VendingMachineViewModel;

import java.util.List;

/**
 * Handles action of pressing any of the numbers on vending machine selection panel.
 */
public class NumberButtonPressedActionHandler implements VendingMachineActionHandler<Void> {

    /**
     * States of the vending machine required to handle the action.
     */
    private static final List<VendingMachineState> ALLOWED_STATES = ImmutableList.of(VendingMachineState.IDLE, VendingMachineState.SELECTION_STARTED);

    /**
     * Handled action.
     */
    private final NumberButtonPressedAction action;

    /**
     * Constructor assigning handled action.
     * @param action handled action.
     */
    NumberButtonPressedActionHandler(VendingMachineAction action) {
        this.action = (NumberButtonPressedAction) action;
    }

    /**
     * Handles action of pressing any of the numbers on vending machine selection panel.
     * Displays result message on the vending machine display.
     * @return nothing ({@code null}).
     */
    @Override
    public Void handle() {
        VendingMachineViewModel vendingMachine = action.getVendingMachineActionParameters().getVendingMachine();
        if (BooleanUtils.isFalse(ALLOWED_STATES.contains(vendingMachine.getState()))) {
            return null;
        }
        // If pressed when machine was idle - reset the selected code
        if (VendingMachineState.IDLE == vendingMachine.getState()) {
            vendingMachine.setSelectedCode(StringUtils.EMPTY);
        }

        String buttonCode = action.getVendingMachineActionParameters().getActionParameters();
        vendingMachine.setSelectedCode(StringUtils.isEmpty(vendingMachine.getSelectedCode()) ? buttonCode : vendingMachine.getSelectedCode() + buttonCode);
        VendingMachineDisplay display = vendingMachine.getDisplay();
        display.setText(vendingMachine.getSelectedCode());
        vendingMachine.setState(VendingMachineState.SELECTION_STARTED);
        return null;
    }
}
