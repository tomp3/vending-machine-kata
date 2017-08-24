package tdd.model.vendingMachine;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import tdd.model.vendingMachine.common.Dimension;
import tdd.model.vendingMachine.machine.Shelve;
import tdd.model.vendingMachine.product.Container;
import tdd.model.vendingMachine.product.ProductType;
import tdd.model.vendingMachine.product.api.Product;

import java.math.BigDecimal;
import java.util.stream.IntStream;

public class ShelveTest {

    private Container shelveProductContainer;
    private ProductType shelveProductType;
    private Shelve testedShelve;

    @Before
    public void initParams() {
        this.shelveProductContainer = new Container("Container", new Dimension(new BigDecimal(3), new BigDecimal(2), new BigDecimal(3)));
        this.shelveProductType = new ProductType("ProductType", this.shelveProductContainer);
        this.testedShelve =
            new Shelve(this.shelveProductType, new Dimension(new BigDecimal(4), new BigDecimal(40), new BigDecimal(5)));
    }


    @Test
    public void testShelveSize() {
        final Product product = Product.builder()
            .name("Test product")
            .productType(this.shelveProductType)
            .build();
        IntStream.rangeClosed(1, 10).forEach((i) -> this.testedShelve.addProduct(product));

        Assertions.assertThat(this.testedShelve.getProducts().size()).isEqualTo(10);
    }

    @Test
    public void testShelveProductOrder() {
        final ProductType productType =
            new ProductType("ProductType", new Container("Container", new Dimension(new BigDecimal(3), new BigDecimal(2), new BigDecimal(3))));

        IntStream.rangeClosed(1, 3).forEach((i) -> this.testedShelve.addProduct(Product.builder()
            .name(String.format("Test product %d", i))
            .productType(productType).build()));

        this.testedShelve.getProduct();
        this.testedShelve.getProduct();
        Assertions.assertThat(this.testedShelve.peekProduct().isPresent());
        Assertions.assertThat(this.testedShelve.peekProduct().get().getName()).isEqualTo("Test product 3");

        this.testedShelve.getProduct();
        Assertions.assertThat(this.testedShelve.getProduct().isPresent()).isFalse();
    }

    @Test
    public void testShelveProductType() {
        final ProductType otherProductType = new ProductType("OtherProductType", this.shelveProductContainer);
        final Product otherTypeProduct = Product.builder().name("Product 2").productType(otherProductType).build();
        Assertions.assertThatThrownBy(() -> this.testedShelve.addProduct(otherTypeProduct));
        Assertions.assertThatThrownBy(() -> this.testedShelve.addProducts(otherTypeProduct));
    }
}
