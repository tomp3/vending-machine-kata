package tdd.vendingMachine.model.common;

import lombok.Getter;

/**
 * Coin types with it's values.
 * In the real world application would be based on some kind of parametrized data.
 */
public enum CoinType {
    FIVE(50),
    TWO(20),
    ONE(10),
    POINT_FIVE(5),
    POINT_TWO(2),
    POINT_ONE(1);

    /**
     * Coin value.
     */
    @Getter
    private final int value;

    /**
     * Coin type constructor assigning the given value.
     *
     * @param value coin value.
     */
    CoinType(int value) {
        this.value = value;
    }
}
