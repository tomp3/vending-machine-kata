package tdd.model.vendingMachine.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import tdd.model.vendingMachine.common.Dimension;

/**
 * Product's packet.
 * Container must have it's name and dimension.
 */
@Getter
@Setter
@AllArgsConstructor
public class Container {
    /**
     * Container's name.
     */
    @NonNull
    private String name;
    /**
     * Container's dimension
     */
    @NonNull
    private Dimension dimension;
}
