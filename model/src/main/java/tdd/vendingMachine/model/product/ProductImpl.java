package tdd.vendingMachine.model.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Default product implementation.
 */
@Getter
@Setter
@AllArgsConstructor
class ProductImpl implements Product {
    /**
     * Product's type.
     */
    private ProductType productType;
    /**
     * Product's container.
     */
    private ProductContainer productContainer;
}
