package tdd.vendingMachine.businessLogic.shelf.service;

import org.assertj.core.util.Lists;
import org.junit.Test;
import tdd.vendingMachine.model.machine.VendingMachineShelf;
import tdd.vendingMachine.model.product.Product;
import tdd.vendingMachine.model.product.ProductContainer;
import tdd.vendingMachine.model.product.ProductContainerType;
import tdd.vendingMachine.model.product.ProductType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ShelfServiceTest {

    private static final ProductType DEFAULT_PRODUCT_TYPE = ProductType.CARBONATED_WATER_0_50_BOTTLE;
    private static final ProductContainer DEFAULT_PRODUCT_CONTAINER = new ProductContainer(ProductContainerType.BOTTLE_0_50, "FF33FF");

    private ShelfService testedService = ShelfService.newShelfService();


    @Test
    public void testShelfInsertProducts() {
        Product product = Product.builder().productContainer(DEFAULT_PRODUCT_CONTAINER).productType(DEFAULT_PRODUCT_TYPE).build();
        List<Product> products = Lists.newArrayList(Arrays.asList(product, product, product, product, product));
        VendingMachineShelf shelf = new VendingMachineShelf(DEFAULT_PRODUCT_TYPE, 10);
        // add 5 products
        this.testedService.insertProducts(shelf, products);
        // check products size
        assertThat(shelf.getProducts().size()).isEqualTo(5);

        // add another 5 products
        this.testedService.insertProducts(shelf, products);
        // verify that another product is not added
        assertThat(this.testedService.insertProducts(shelf, Collections.singletonList(product))).hasSize(1);
    }

    @Test
    public void testShelfDispenseProduct() {
        Product product = Product.builder().productContainer(DEFAULT_PRODUCT_CONTAINER).productType(DEFAULT_PRODUCT_TYPE).build();
        List<Product> products = Lists.newArrayList(Arrays.asList(product, product));
        VendingMachineShelf shelf = new VendingMachineShelf(DEFAULT_PRODUCT_TYPE, 10);
        // add 5 products
        this.testedService.insertProducts(shelf, products);

        // dispense one product, verify it's not null
        assertThat(this.testedService.dispenseProduct(shelf)).isNotNull();

        // verify products' amount
        assertThat(shelf.getProducts()).hasSize(1);

        // dispense the second product
        this.testedService.dispenseProduct(shelf);

        // verify that exception is thrown while trying to dispense from an empty shelf
        assertThatThrownBy(() -> this.testedService.dispenseProduct(shelf));

    }


}
