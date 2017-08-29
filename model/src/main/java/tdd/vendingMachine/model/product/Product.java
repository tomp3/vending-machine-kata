package tdd.vendingMachine.model.product;

import lombok.Getter;

import java.io.Serializable;

/**
 * Product implementation.
 */
public class Product implements Serializable {

    /**
     * Product type.
     */
    @Getter
    private final ProductType productType;

    /**
     * Product constructor assigning product type.
     *
     * @param productType product type.
     */
    public Product(ProductType productType) {
        this.productType = productType;
    }
}
