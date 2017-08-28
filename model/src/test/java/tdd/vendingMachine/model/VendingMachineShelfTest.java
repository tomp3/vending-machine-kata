package tdd.vendingMachine.model;

import org.junit.Before;
import org.junit.Test;
import tdd.vendingMachine.model.machine.VendingMachineShelf;
import tdd.vendingMachine.model.product.Product;
import tdd.vendingMachine.model.product.ProductContainer;
import tdd.vendingMachine.model.product.ProductContainerType;
import tdd.vendingMachine.model.product.ProductType;

import java.math.BigDecimal;
import java.util.Queue;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link VendingMachineShelf} test.
 */
public class VendingMachineShelfTest {

    /**
     * Default product container.
     */
    private static final ProductContainer PRODUCT_CONTAINER =
        new ProductContainer(ProductContainerType.PACKET_BAR, "FFFFFF");
    /**
     * Default product type.
     */
    private static final ProductType PRODUCT_TYPE = ProductType.AWESOME_CHOCOLATE_BAR;

    /**
     * Tested object.
     */
    private VendingMachineShelf testedShelf;

    /**
     * Initializes tested object.
     */
    @Before
    public void beforeTests() {
        this.testedShelf =
            new VendingMachineShelf(PRODUCT_TYPE, 10, BigDecimal.ONE);
    }


    /**
     * Tests methods adding products.
     */
    @Test
    public void testProductAddition() {
        final Product product = Product.builder()
            .productContainer(PRODUCT_CONTAINER)
            .build();
        IntStream.rangeClosed(1, 4).forEach((i) -> this.testedShelf.addProduct(product));

        assertThat(testedShelf.getProducts().size()).isEqualTo(4);
    }

    /**
     * Tests methods performing operations on products returned by the shelf via {@link VendingMachineShelf#getProducts()} method.
     */
    @Test
    public void testShelfProductsModification() {
        final Product product = Product.builder()
            .productContainer(PRODUCT_CONTAINER)
            .productType(PRODUCT_TYPE)
            .build();
        IntStream.rangeClosed(1, 4).forEach((i) -> this.testedShelf.addProduct(product));

        Queue<Product> products = (Queue<Product>) this.testedShelf.getProducts();
        products.poll();
        assertThat(testedShelf.getProducts().size()).isEqualTo(4);
    }
}
