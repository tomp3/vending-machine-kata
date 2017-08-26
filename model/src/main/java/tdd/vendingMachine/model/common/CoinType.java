package tdd.vendingMachine.model.common;

import lombok.Getter;

import java.util.Arrays;

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

    @Getter
    private final int value;

    CoinType(int value) {
        this.value = value;
    }

    public static CoinType find(int value) {
        return Arrays.stream(CoinType.values()).filter(ct -> ct.getValue() == value).findFirst().get();
    }
}
