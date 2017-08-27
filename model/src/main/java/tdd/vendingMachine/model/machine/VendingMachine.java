package tdd.vendingMachine.model.machine;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
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

    private List<String> availableCodes;

    public VendingMachine(VendingMachineCash cash, List<String> availableCodes) {
        this.cash = cash;
        this.availableCodes = availableCodes;
    }

    public VendingMachine(VendingMachineCash cash, List<String> availableCodes, Map<String, VendingMachineShelf> shelves) {
        this(cash, availableCodes);
        this.shelves = shelves;
    }
}
