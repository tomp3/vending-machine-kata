package tdd.vendingMachine.ui.main.context;


import lombok.Getter;
import lombok.Setter;
import tdd.vendingMachine.model.machine.VendingMachine;

import java.io.Serializable;

/**
 * FX Application context singleton.
 */
public final class FXContext implements Serializable {

    /**
     * Singleton instance.
     */
    private static final FXContext INSTANCE = new FXContext();

    /**
     * Vending machine.
     */
    @Getter
    @Setter
    private VendingMachine vendingMachine;


    /**
     * Returns current instance.
     *
     * @return current instance.
     */
    public static FXContext getInstance() {
        return INSTANCE;
    }


}
