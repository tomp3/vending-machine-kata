package tdd.vendingMachine.model.machine;

import com.google.common.collect.Lists;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableInt;
import tdd.vendingMachine.model.product.Product;

import java.util.List;

/**
 * Vending machine product tray.
 */
public class VendingMachineProductTray {

    /**
     * Space string representation.
     */
    private static final String SPACE = " ";
    /**
     * Products in the tray.
     */
    @Getter
    private final List<Product> products;

    /**
     * Constructor instantiating final fields.
     */
    public VendingMachineProductTray() {
        this.products = Lists.newLinkedList();
    }

    /**
     * Prepares products tray string representation.
     *
     * @return products tray string representation.
     */
    public String productsToString() {
        StringBuilder sb = new StringBuilder();
        MutableInt idx = new MutableInt(0);
        products.forEach(p -> sb.append(sb.length() > 0 ? SPACE : StringUtils.EMPTY).append("(").append(idx.getAndIncrement()).append(") ")
            .append(p.getProductType().getDispalyName()));
        return sb.toString();
    }
}
