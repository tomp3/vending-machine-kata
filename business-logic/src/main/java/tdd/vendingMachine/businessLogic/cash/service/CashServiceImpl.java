package tdd.vendingMachine.businessLogic.cash.service;


import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tdd.vendingMachine.businessLogic.cash.exception.ChangeImpossibleException;
import tdd.vendingMachine.businessLogic.cash.exception.CoinInsertionImpossibleException;
import tdd.vendingMachine.common.currency.CurrencyUtils;
import tdd.vendingMachine.model.common.CoinType;
import tdd.vendingMachine.model.common.util.CoinUtils;
import tdd.vendingMachine.model.machine.VendingMachineCash;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

/**
 * Default cash service implementation.
 */
class CashServiceImpl implements CashService {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CashServiceImpl.class);

    /**
     * Instance of service responsible for change calculations.
     */
    private final CoinChangeService coinChangeService;

    /**
     * Constructor assigning coin change service.
     *
     * @param coinChangeService coin change service;
     */
    CashServiceImpl(CoinChangeService coinChangeService) {
        this.coinChangeService = coinChangeService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertCoins(VendingMachineCash cash, Map<CoinType, Integer> coins) throws CoinInsertionImpossibleException {
        Map<CoinType, Integer> cashCoins = tryInsertCoins(cash, coins);
        cash.getCoins().clear();
        cash.getCoins().putAll(cashCoins);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Cash coins: ");
            LOGGER.debug(System.lineSeparator() + CoinUtils.coinsToString(cash.getCoins()));
        }
    }

    /**
     * Tries inserting coins. If successful, returns new coins to be stored by the cash.
     * If unsuccessful, throws an exception.
     *
     * @param cash  vending machine cash.
     * @param coins coins to be inserted.
     * @return cash coins including inserted coins.
     */
    private Map<CoinType, Integer> tryInsertCoins(VendingMachineCash cash, Map<CoinType, Integer> coins) throws CoinInsertionImpossibleException {
        Map<CoinType, Integer> cashCoins = Maps.newHashMap(cash.getCoins());
        for (Map.Entry<CoinType, Integer> entry : coins.entrySet()) {
            cashCoins.put(entry.getKey(), insertCoins(entry.getKey(), entry.getValue(), cash));
        }
        return cashCoins;
    }

    /**
     * Insert given amount of coins of the given type.
     *
     * @param coinType coin type.
     * @param count    amount of coins to be inserted.
     * @param cash     vending machine cash.
     * @return amount of coins left after having inserted coins reaching the cash limit.
     */
    private int insertCoins(CoinType coinType, Integer count, VendingMachineCash cash) throws CoinInsertionImpossibleException {
        int coinsCount = cash.getCoins().get(coinType);
        int maxCoinCount = cash.getMaxCoins().get(coinType);
        int freeCoinSlots = maxCoinCount - coinsCount;
        // any free coin slots exist
        if (freeCoinSlots < count) {
            throw new CoinInsertionImpossibleException();
        }
        return coinsCount + count;
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
    public Map<CoinType, Integer> prepareChange(VendingMachineCash cash, BigDecimal price, BigDecimal payment)
        throws ChangeImpossibleException {
        Map<CoinType, Integer> coinsAvailable = Maps.newHashMap(cash.getCoins());
        coinsAvailable.putAll(cash.getUserInsertedMoney());
        return coinChangeService.calculateChange(coinsAvailable, price, payment);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<CoinType, Integer> giveCoins(VendingMachineCash cash, Map<CoinType, Integer> coins) {
        Map<CoinType, Integer> coinsToBeGiven = Maps.newHashMap(coins);
        if (cannotGiveCoins(cash, coinsToBeGiven)) {
            return Collections.emptyMap();
        }
        // decrease coins number in cash
        coinsToBeGiven.forEach((k, v) -> cash.getCoins().put(k, cash.getCoins().get(k) - v));
        // clear concrete inserted money
        cash.getUserInsertedMoney().clear();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Cash coins: ");
            LOGGER.debug(System.lineSeparator() + CoinUtils.coinsToString(cash.getCoins()));
        }
        return coinsToBeGiven;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VendingMachineCash createCash(Map<CoinType, Integer> maxCoinCount, Map<CoinType, Integer> coins) {
        VendingMachineCash cash = new VendingMachineCash(maxCoinCount);
        coins.forEach((k, v) -> addCoins(cash, k, v));
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
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Cash coins: ");
            LOGGER.debug(System.lineSeparator() + CoinUtils.coinsToString(cash.getCoins()));
        }
        for (Map.Entry<CoinType, Integer> coinEntry : coins.entrySet()) {
            Integer cashCoinCount = cash.getCoins().get(coinEntry.getKey());
            if (cashCoinCount < coinEntry.getValue()) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public BigDecimal getUserInsertedAmountSum(VendingMachineCash cash) {
        return cash.getUserInsertedMoney().entrySet().stream().map(e -> CurrencyUtils.denormalizeCurrency(e.getKey().getValue() * e.getValue()))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
