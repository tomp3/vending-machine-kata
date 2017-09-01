package tdd.vendingMachine.model.machine;

import com.google.common.collect.Maps;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import tdd.vendingMachine.common.currency.CurrencyUtils;
import tdd.vendingMachine.common.number.NumberUtils;
import tdd.vendingMachine.model.common.CoinType;

import java.util.Arrays;
import java.util.Map;

/**
 * Vending machine cash tray.
 */
public class VendingMachineCashTray {

    /**
     * Coins in the vending machine cash tray.
     */
    @Getter
    private final Map<CoinType, Integer> coins;


    /**
     * Constructor instantiating final fields.
     */
    VendingMachineCashTray() {
        this.coins = Maps.newHashMap();
    }

    /**
     * Prepares tray coins string representation.
     *
     * @return tray coins string representation.
     */
    public String coinsToString() {
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
