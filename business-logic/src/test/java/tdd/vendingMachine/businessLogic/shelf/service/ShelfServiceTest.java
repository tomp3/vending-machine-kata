package tdd.vendingMachine.businessLogic.shelf.service;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import tdd.vendingMachine.model.machine.VendingMachineShelf;
import tdd.vendingMachine.model.product.Product;
import tdd.vendingMachine.model.product.ProductType;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * {@link ShelfService} test.
 */
public class ShelfServiceTest {

    /**
     * Default product type.
     */
    private static final ProductType DEFAULT_PRODUCT_TYPE = ProductType.CARBONATED_WATER_0_50_BOTTLE;
    /**
     * Default product.
     */
    private static final Product DEFAULT_PRODUCT = new Product(DEFAULT_PRODUCT_TYPE);

    /**
     * Tested object.
     */
    private ShelfService testedService = ShelfService.newShelfService();
    /**
     * Vending machine's shelf.
     */
    private VendingMachineShelf shelf;

    /**
     * Initializes vending machine's shelf.
     */
    @Before
    public void beforeTest() {
        shelf = new VendingMachineShelf(DEFAULT_PRODUCT_TYPE, 10, BigDecimal.ONE);
    }

    /**
     * Tests methods inserting products to the shelf.
     */
    @Test
    public void testShelfInsertProducts() {
        List<Product> products = Lists.newArrayList(Arrays.asList(DEFAULT_PRODUCT, DEFAULT_PRODUCT, DEFAULT_PRODUCT, DEFAULT_PRODUCT, DEFAULT_PRODUCT));
        // add 5 products
        this.testedService.insertProducts(shelf, products);
        // check products size
        assertThat(shelf.getProducts().size()).isEqualTo(5);

        // add another 5 products
        this.testedService.insertProducts(shelf, products);
        // verify that another product is not added
        assertThat(this.testedService.insertProducts(shelf, Collections.singletonList(DEFAULT_PRODUCT))).hasSize(1);
    }

    /**
     * Tests methods dispensing product from the shelf.
     */
    @Test
    public void testShelfDispenseProduct() {
        List<Product> products = Lists.newArrayList(Arrays.asList(DEFAULT_PRODUCT, DEFAULT_PRODUCT));
        VendingMachineShelf shelf = new VendingMachineShelf(DEFAULT_PRODUCT_TYPE, 10, BigDecimal.ONE);
        // add 5 products
        this.testedService.insertProducts(shelf, products);

        // dispense one product, verify it's not null
        assertThat(this.testedService.dispenseProduct(shelf)).isNotNull();

        // verify products' count
        assertThat(shelf.getProducts()).hasSize(1);

        // dispense the second product
        this.testedService.dispenseProduct(shelf);

        // verify that exception is thrown while trying to dispense from an empty shelf
        assertThatThrownBy(() -> this.testedService.dispenseProduct(shelf));

    }


}
