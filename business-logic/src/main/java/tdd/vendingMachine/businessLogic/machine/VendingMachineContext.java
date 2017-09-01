package tdd.vendingMachine.businessLogic.machine;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import tdd.vendingMachine.model.common.CoinType;
import tdd.vendingMachine.model.machine.VendingMachine;

import java.io.Serializable;
import java.util.Map;

/**
 * Simple implementation of context class.
 * Vending machine context stores context values used by multiple business logic methods.
 */
@Getter
@Setter
public final class VendingMachineContext implements Serializable {
    /**
     * Vending machine context instance.
     */
    private static final VendingMachineContext INSTANCE = new VendingMachineContext();

    /**
     * Vending machine.
     */
    private VendingMachine vendingMachine = null;
    /**
     * Coins inserted by the concrete.
     */
    private Map<CoinType, Integer> userCoinsInserted = Maps.newHashMap();
    /**
     * Message currently displayed to the concrete.
     */
    private String displayedMessage = null;

    /**
     * Default constructor.
     */
    private VendingMachineContext() {
    }

    /**
     * Method returning vending machine context instance.
     *
     * @return vending machine context instance.
     */
    public static VendingMachineContext getCurrentContext() {
        return INSTANCE;
    }

}
