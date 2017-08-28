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
public class VendingMachineContext implements Serializable {
    private static final VendingMachineContext INSTANCE = new VendingMachineContext();

    private VendingMachine vendingMachine = null;
    private Map<CoinType, Integer> userCoinsInserted = Maps.newHashMap();
    private String displayedMessage = null;

    private VendingMachineContext() {
    }

    public static VendingMachineContext getCurrentContext() {
        return INSTANCE;
    }

}
