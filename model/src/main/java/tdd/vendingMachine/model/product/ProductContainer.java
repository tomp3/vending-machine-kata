package tdd.vendingMachine.model.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Product's container.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductContainer {
    /**
     * ProductContainer's type.
     */
    private ProductContainerType productContainerType;
    /**
     * ProductContainer's RGB color.
     */
    private String colorRgb;
}
