package tdd.vendingMachine.model.product;

import tdd.vendingMachine.common.builder.Builder;

/**
 * Default product builder.
 * Uses {@link ProductImpl} to instantiate {@link Product} objects.
 */
public class ProductBuilder implements Builder<Product> {

    private ProductType productType;
    private ProductContainer productContainer;

    public ProductBuilder productType(ProductType productType) {
        this.productType = productType;
        return this;
    }

    public ProductBuilder productContainer(ProductContainer productContainer) {
        this.productContainer = productContainer;
        return this;
    }

    @Override
    public Product build() {
        return new ProductImpl(productType, productContainer);
    }
}
