package tdd.model.vendingMachine.product.concrete;

import tdd.model.vendingMachine.product.ProductType;
import tdd.model.vendingMachine.product.api.Product;
import tdd.vendingMachine.common.builder.Builder;

/**
 * Default product builder.
 * Uses {@link ProductImpl} to instantiate {@link Product} objects.
 */
public class ProductBuilder implements Builder<Product> {

    private String name;
    private ProductType productType;

    public ProductBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder productType(ProductType productType) {
        this.productType = productType;
        return this;
    }

    @Override
    public Product build() {
        return new ProductImpl(name, productType);
    }
}
