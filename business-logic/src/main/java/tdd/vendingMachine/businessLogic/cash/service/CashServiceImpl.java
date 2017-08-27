package tdd.vendingMachine.businessLogic.cash.service;


import com.google.common.collect.Maps;
import tdd.vendingMachine.businessLogic.cash.exception.ChangeImpossibleException;
import tdd.vendingMachine.common.number.NumberUtils;
import tdd.vendingMachine.model.common.CoinType;
import tdd.vendingMachine.model.machine.VendingMachineCash;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

/**
 * Default cash service implementation.
 */
class CashServiceImpl implements CashService {

    private CoinChangeService coinChangeService = CoinChangeService.newCoinChangeService();

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<CoinType, Integer> insertCoins(VendingMachineCash cash, Map<CoinType, Integer> coins) {
        // map to be returned with coins left
        Map<CoinType, Integer> coinsLeft = Maps.newHashMap(coins);
        coins.forEach((k, v) -> coinsLeft.put(k, insertCoins(k, v, cash)));
        return coinsLeft;
    }

    /**
     * Insert given amount of coins of the given type.
     * Returns amount of coins left after having inserted coins reaching the cash limit.
     *
     * @param coinType coin type.
     * @param count    amount of coins to be inserted.
     * @param cash     vending machine cash.
     * @return amount of coins left after having inserted coins reaching the cash limit.
     */
    private int insertCoins(CoinType coinType, Integer count, VendingMachineCash cash) {
        int coinsLeft = count;
        int coinsCount = cash.getCoins().get(coinType);
        int maxCoinCount = cash.getMaxCoins().get(coinType);
        int freeCoinSlots = maxCoinCount - coinsCount;
        // any free coin slots exist
        if (freeCoinSlots > NumberUtils.INT_ZERO) {
            // amount of coins to be inserted
            int coinsToInsert = count < freeCoinSlots ? count : freeCoinSlots;
            // add coins to the cash
            addCoins(cash, coinType, coinsToInsert);
            coinsLeft -= coinsToInsert; // decrease left coins amount
        }
        return coinsLeft;
    }

    /**
     * Adds given amount of coins of the given coin type to the vending machine cash.
     *
     * @param cash      vending machine cash.
     * @param coinType  coin type.
     * @param coinCount coin count.
     */
    private void addCoins(VendingMachineCash cash, CoinType coinType, int coinCount) {
        cash.getCoins().put(coinType, cash.getCoins().get(coinType) + coinCount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<CoinType, Integer> prepareChange(VendingMachineCash cash, BigDecimal price, BigDecimal payment) throws ChangeImpossibleException {
        return coinChangeService.calculateChange(cash.getCoins(), price, payment);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<CoinType, Integer> giveCoins(VendingMachineCash cash, Map<CoinType, Integer> coins) {
        if (cannotGiveCoins(cash, coins)) {
            return Collections.emptyMap();
        }
        // decrease coins number in cash
        coins.forEach((k, v) -> cash.getCoins().put(k, cash.getCoins().get(k) - v));
        return coins;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VendingMachineCash createCash(Map<CoinType, Integer> maxCoinCount, Map<CoinType, Integer> coins) {
        VendingMachineCash cash = new VendingMachineCash(maxCoinCount);
        insertCoins(cash, coins);
        return cash;
    }

    /**
     * Checks if cash has less coins than required to dispose given coins.
     *
     * @param cash  vending machine cash.
     * @param coins coins to be disposed.
     * @return {@code true} if cash has less coins than required to dispose given coins, {@code false} otherwise.
     */
    private boolean cannotGiveCoins(VendingMachineCash cash, Map<CoinType, Integer> coins) {
        for (Map.Entry<CoinType, Integer> coinEntry : coins.entrySet()) {
            Integer cashCoinCount = cash.getCoins().get(coinEntry.getKey());
            if (cashCoinCount < coinEntry.getValue()) {
                return true;
            }
        }
        return false;
    }
}
