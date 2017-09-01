package tdd.vendingMachine.model.machine;

import com.google.common.collect.Maps;
import lombok.Getter;
import tdd.vendingMachine.model.common.CoinType;
import tdd.vendingMachine.model.common.util.CoinUtils;

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
        return CoinUtils.coinsToString(coins);
    }
}
