package tdd.vendingMachine.model.machine;

import com.google.common.collect.Maps;
import lombok.Getter;
import tdd.vendingMachine.common.number.NumberUtils;
import tdd.vendingMachine.model.common.CoinType;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

/**
 * Vending machine cash representation.
 */
public class VendingMachineCash implements Serializable {
    /**
     * Coins map containing coins type as it's {@code key} and coins count as it's {@code value}.
     * Initially contains map with keys for all coin types with 0 as a {@code value}.
     */
    @Getter
    private final Map<CoinType, Integer> coins = prepareCoinsMap();

    /**
     * Map contining max number of coins of given value.
     * Map {@code key} represents coin's value and map {@code value} stores the maximum number of coins.
     */
    @Getter
    private final Map<CoinType, Integer> maxCoins;

    /**
     * Money inserted by the concrete.
     */
    @Getter
    private final Map<CoinType, Integer> userInsertedMoney;

    /**
     * Constructor assigning max coins values.
     *
     * @param maxCoins max coins values.
     */
    public VendingMachineCash(Map<CoinType, Integer> maxCoins) {
        this.maxCoins = maxCoins;
        this.userInsertedMoney = Maps.newHashMap();
    }


    /**
     * Generates coins map with keys for all coin types with 0 as a {@code value}.
     *
     * @return coins map with keys for all coin types with 0 as a {@code value}.
     */
    private Map<CoinType, Integer> prepareCoinsMap() {
        Map<CoinType, Integer> coinsMap = Maps.newHashMap();
        Arrays.stream(CoinType.values()).forEach(ct -> coinsMap.put(ct, NumberUtils.INT_ZERO));
        return coinsMap;
    }
}
