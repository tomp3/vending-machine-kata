package tdd.vendingMachine.ui.machine.view.model;

import lombok.Getter;
import lombok.Setter;
import tdd.vendingMachine.model.machine.VendingMachine;

@Getter
@Setter
public class VendingMachineViewModel {

    /**
     * Vending machine.
     */
    private final VendingMachine vendingMachine;

    /**
     * Vending machine display.
     */
    private final VendingMachineDisplay display;

    /**
     * Selected code.
     */
    private String selectedCode;

    /**
     * Vending machine state.
     */
    private VendingMachineState state;

    public VendingMachineViewModel(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
        this.display = new VendingMachineDisplay();
        this.state = VendingMachineState.IDLE;
    }

}
