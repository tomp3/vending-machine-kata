package tdd.model.vendingMachine.product.api;

import tdd.model.vendingMachine.product.ProductType;
import tdd.model.vendingMachine.product.concrete.ProductBuilder;

/**
 * Product interface.
 * Every product has it's name, container and type.
 */
public interface Product {
    /**
     * Returns product's name.
     *
     * @return product's name.
     */
    String getName();

    /**
     * Returns product's type.
     *
     * @return product's type.
     */
    ProductType getProductType();

    /**
     * Returns default product builder.
     *
     * @return default product builder.
     */
    static ProductBuilder builder() {
        return new ProductBuilder();
    }
}
