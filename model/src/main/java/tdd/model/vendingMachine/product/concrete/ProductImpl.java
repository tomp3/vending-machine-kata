package tdd.model.vendingMachine.product.concrete;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import tdd.model.vendingMachine.product.ProductType;
import tdd.model.vendingMachine.product.api.Product;

/**
 * Default product implementation.
 */
@Getter
@Setter
@AllArgsConstructor
class ProductImpl implements Product {
    /**
     * Product's name.
     */
    private String name;
    /**
     * Product's type.
     */
    private ProductType productType;
}
