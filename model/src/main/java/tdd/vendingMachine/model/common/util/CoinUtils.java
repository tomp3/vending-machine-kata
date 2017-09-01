package tdd.vendingMachine.model.common.util;

import org.apache.commons.lang3.StringUtils;
import tdd.vendingMachine.common.currency.CurrencyUtils;
import tdd.vendingMachine.common.number.NumberUtils;
import tdd.vendingMachine.model.common.CoinType;

import java.util.Arrays;
import java.util.Map;

/**
 * Coin utils.
 */
public final class CoinUtils {

    /**
     * Default constructor.
     */
    private CoinUtils() {
    }


    /**
     * Prepares coins' {@link String} representation.
     *
     * @param coins coins.
     * @return coins' {@link String} representation.
     */
    public static String coinsToString(Map<CoinType, Integer> coins) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(CoinType.coinsAscending()).forEach(coinType -> {
            Integer count = coins.get(coinType);
            if (count != null) {
                sb.append(sb.length() > 0 ? System.lineSeparator() : StringUtils.EMPTY).append("(").append(
                    NumberUtils.format(CurrencyUtils.denormalizeCurrency(coinType.getValue()))).append(") : ").append(count);
            }
        });
        return sb.toString();
    }
}
