package tdd.vendingMachine.model.product.util;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import tdd.vendingMachine.common.comparator.NaturalOrderComparator;
import tdd.vendingMachine.model.machine.VendingMachineShelf;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Product utils.
 */
public class ProductUtils {


    /**
     * Default constructor.
     */
    private ProductUtils() {
    }

    /**
     * Prepares shelves' {@link String} representation.
     *
     * @param shelves shelves.
     * @return shelves' {@link String} representation.
     */
    public static String productsToString(Map<String, VendingMachineShelf> shelves) {
        if (MapUtils.isEmpty(shelves)) {
            return StringUtils.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        List<String> codes = shelves.keySet().stream().sorted((a, b) -> NaturalOrderComparator.getInstance().compare(a, b)).collect(Collectors.toList());
        codes.forEach(code -> {
            VendingMachineShelf shelf = shelves.get(code);
            if (sb.length() > 0) {
                sb.append(System.lineSeparator());
            }
            sb.append(shelf.getProductType().getDispalyName()).append(" : ").append(shelf.getProducts().size())
                .append(" : ").append(shelf.getProductPrice());
        });
        return sb.toString();
    }


}
