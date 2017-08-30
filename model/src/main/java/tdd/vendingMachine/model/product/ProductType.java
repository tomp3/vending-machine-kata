package tdd.vendingMachine.model.product;

import lombok.Getter;

/**
 * Some product types.
 * In the real world application, would be based on some kind of parametrized data.
 */
public enum ProductType {
    COKE_0_33_CAN("Coke 0.33L can","ff0000"),
    COKE_0_33_BOTTLE("Coke 0.33L bottle","800000"),
    COKE_0_50_BOTTLE("Coke 0.5L bottle","800000"),
    AWESOME_CHOCOLATE_BAR("Awesome Chocolate Bar","996633"),
    ENERGY_DRINK_0_33_CAN("Energy drink 0.33L can","00cc00"),
    STILL_WATER_0_50_BOTTLE("Still water 0.5L bottle","66ccff"),
    CARBONATED_WATER_0_50_BOTTLE("Carbonated water 0.5L bottle","66ccff"),
    POTATO_CHIPS_0_200("Potato chips 0.200g","ffcc00"),
    PEANUTS_CAN_0_200("Peanuts 0.200g can","ffaa00");

    /**
     * Product type display name.
     */
    @Getter
    private final String dispalyName;
    /**
     * Container color.
     */
    @Getter
    private final String containerColor;

    /**
     * Constructor assigning product type display name.
     *
     * @param displayName display name.
     */
    ProductType(String displayName, String containerColor) {
        this.dispalyName = displayName;
        this.containerColor = containerColor;
    }
}
