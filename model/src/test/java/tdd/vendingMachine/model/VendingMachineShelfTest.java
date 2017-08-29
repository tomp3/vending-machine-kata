package tdd.vendingMachine.model;

import org.junit.Before;
import org.junit.Test;
import tdd.vendingMachine.model.machine.VendingMachineShelf;
import tdd.vendingMachine.model.product.Product;
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
     * Default product type.
     */
    private static final ProductType PRODUCT_TYPE = ProductType.AWESOME_CHOCOLATE_BAR;

    /**
     * Default product.
     */
    private static final Product PRODUCT = new Product(PRODUCT_TYPE);

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
        IntStream.rangeClosed(1, 4).forEach((i) -> this.testedShelf.addProduct(PRODUCT));

        assertThat(testedShelf.getProducts().size()).isEqualTo(4);
    }

    /**
     * Tests methods performing operations on products returned by the shelf via {@link VendingMachineShelf#getProducts()} method.
     */
    @Test
    public void testShelfProductsModification() {
        IntStream.rangeClosed(1, 4).forEach((i) -> this.testedShelf.addProduct(PRODUCT));

        Queue<Product> products = (Queue<Product>) this.testedShelf.getProducts();
        products.poll();
        assertThat(testedShelf.getProducts().size()).isEqualTo(4);
    }
}
