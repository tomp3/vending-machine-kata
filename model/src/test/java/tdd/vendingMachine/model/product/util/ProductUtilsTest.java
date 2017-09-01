package tdd.vendingMachine.model.product.util;

import com.google.common.collect.ImmutableMap;
import org.assertj.core.util.Lists;
import org.junit.Test;
import tdd.vendingMachine.model.machine.VendingMachineShelf;
import tdd.vendingMachine.model.product.Product;
import tdd.vendingMachine.model.product.ProductType;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link ProductUtils} tests.
 */
public class ProductUtilsTest {

    /**
     * {@link ProductUtils#productsToString(Map)} test.
     */
    @Test
    public void testProductsToString() {
        Map<String, VendingMachineShelf> shelves = ImmutableMap.of(
            "03", new VendingMachineShelf(ProductType.AWESOME_CHOCOLATE_BAR, 10, BigDecimal.ONE),
            "02", new VendingMachineShelf(ProductType.COKE_0_33_BOTTLE, 10, BigDecimal.valueOf(2.2)),
            "01", new VendingMachineShelf(ProductType.ENERGY_DRINK_0_33_CAN, 10, BigDecimal.valueOf(3.6))
        );

        Product product = new Product(ProductType.AWESOME_CHOCOLATE_BAR);
        shelves.get("03").addProducts(Lists.newArrayList(product, product, product));

        product = new Product(ProductType.COKE_0_33_BOTTLE);
        shelves.get("01").addProduct(product);

        String string = ProductUtils.productsToString(shelves);
        assertThat(string).startsWith(ProductType.ENERGY_DRINK_0_33_CAN.getDispalyName())
            .endsWith("1");
    }
}
