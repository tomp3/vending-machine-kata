package tdd.vendingMachine.model.machine;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Vending machine representation.
 */
@Getter
@Setter
public class VendingMachine {
    /**
     * Vending machine cash.
     */
    private VendingMachineCash cash;
    /**
     * Vending machine shelves.
     * Shelves are stored as a map with shelf code as a {@code key} and shelf as a {@code value}.
     */
    private Map<String, VendingMachineShelf> shelves = Maps.newHashMap();

    public VendingMachine(VendingMachineCash cash) {
        this.cash = cash;
    }

    public VendingMachine(VendingMachineCash cash, Map<String, VendingMachineShelf> shelves) {
        this(cash);
        this.shelves = shelves;
    }
}
