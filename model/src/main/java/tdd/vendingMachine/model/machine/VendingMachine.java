package tdd.vendingMachine.model.machine;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Vending machine representation.
 */
@Getter
@Setter
public class VendingMachine implements Serializable {
    /**
     * Vending machine cash.
     */
    private VendingMachineCash cash;
    /**
     * Vending machine shelves.
     * Shelves are stored as a map with shelf code as a {@code key} and shelf as a {@code value}.
     */
    private Map<String, VendingMachineShelf> shelves = Maps.newHashMap();

    /**
     * Available shelves' codes.
     */
    private List<String> availableCodes;

    /**
     * Constructor assigning given vending machine cash, available shelves' codes and shelves.
     *
     * @param cash           vending machine cash.
     * @param availableCodes available shelves' codes.
     * @param shelves        shelves.
     */
    public VendingMachine(VendingMachineCash cash, List<String> availableCodes, Map<String, VendingMachineShelf> shelves) {
        this.cash = cash;
        this.availableCodes = availableCodes;
        this.shelves = shelves;
    }
}
