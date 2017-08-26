package tdd.vendingMachine.model.product;

/**
 * Product interface.
 * Every product has it's productContainer and type.
 */
public interface Product {
    /**
     * Returns product's type.
     *
     * @return product's type.
     */
    ProductType getProductType();

    /**
     * Returns product's container..
     *
     * @return product's container.
     */
    ProductContainer getProductContainer();

    /**
     * Returns default product builder.
     *
     * @return default product builder.
     */
    static ProductBuilder builder() {
        return new ProductBuilder();
    }
}
