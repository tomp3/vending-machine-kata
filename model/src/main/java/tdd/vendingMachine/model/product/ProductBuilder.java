package tdd.vendingMachine.model.product;

import tdd.vendingMachine.common.builder.Builder;

/**
 * Default product builder.
 * Uses {@link ProductImpl} to instantiate {@link Product} objects.
 */
public class ProductBuilder implements Builder<Product> {

    /**
     * Product type.
     */
    private ProductType productType;
    /**
     * Product container.
     */
    private ProductContainer productContainer;

    /**
     * Sets product type.
     *
     * @param productType product type.
     * @return builder.
     */
    public ProductBuilder productType(ProductType productType) {
        this.productType = productType;
        return this;
    }

    /**
     * Sets product container.
     *
     * @param productContainer product container.
     * @return builder.
     */
    public ProductBuilder productContainer(ProductContainer productContainer) {
        this.productContainer = productContainer;
        return this;
    }

    /**
     * Builds product instance.
     *
     * @return product instance.
     */
    @Override
    public Product build() {
        return new ProductImpl(productType, productContainer);
    }
}
