package tdd.vendingMachine.model.product;

import lombok.Getter;

/**
 * Some product types.
 * In the real world application, would be based on some kind of parametrized data.
 */
public enum ProductType {
    COKE_0_33_CAN("Coke 0.33L can"),
    COKE_0_33_BOTTLE("Coke 0.33L bottle"),
    COKE_0_50_BOTTLE("Coke 0.5L bottle"),
    AWESOME_CHOCOLATE_BAR("Awesome Chocolate Bar"),
    ENERGY_DRINK_0_33_CAN("Energy drink 0.33L can"),
    STILL_WATER_0_50_BOTTLE("Still water 0.5L bottle"),
    CARBONATED_WATER_0_50_BOTTLE("Carbonated water 0.5L bottle"),
    POTATO_CHIPS_0_200("Potato chips 0.200g"),
    PEANUTS_CAN_0_200("Peanuts 0.200g can");

    @Getter
    private final String dispalyName;

    ProductType(String displayName) {
        this.dispalyName = displayName;
    }
}
