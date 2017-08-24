package tdd.model.vendingMachine.product;

import lombok.*;

/**
 * Class representing product's type.
 * Product's type has it's name and container.
 */
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ProductType {
    private String name;
    private Container container;
}
