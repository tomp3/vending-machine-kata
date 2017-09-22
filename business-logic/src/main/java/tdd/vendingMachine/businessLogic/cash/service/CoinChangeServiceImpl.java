package tdd.vendingMachine.businessLogic.cash.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.tuple.MutablePair;
import tdd.vendingMachine.businessLogic.cash.exception.ChangeImpossibleException;
import tdd.vendingMachine.common.currency.CurrencyUtils;
import tdd.vendingMachine.common.number.NumberUtils;
import tdd.vendingMachine.model.common.CoinType;

import java.math.BigDecimal;
import java.util.*;

/**
 * Default implementation of CoinChangeService.
 */
class CoinChangeServiceImpl implements CoinChangeService {

    /**
     * An array containing coin types sorted ascending.
     */
    private static final CoinType[] COIN_TYPES_ASCENDING;

    /**
     * Sorting coin types in the ascending order.
     */
    static {
        CoinType[] coinTypes = CoinType.values();
        Arrays.sort(coinTypes, Comparator.comparingInt(CoinType::getValue));
        COIN_TYPES_ASCENDING = coinTypes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<CoinType, Integer> calculateChange(Map<CoinType, Integer> coins, BigDecimal price, BigDecimal payment) throws ChangeImpossibleException {
        int normPrice = CurrencyUtils.normalizeCurrency(price);
        int normPayment = CurrencyUtils.normalizeCurrency(payment);
        if (NumberUtils.isZero(normPrice) || normPrice == normPayment) {
            return Collections.emptyMap();
        }
        int changeAmount = normPayment - normPrice;
        return findCoins(coins, changeAmount);
    }

    /**
     * Method finding given amount using given coins.
     *
     * @param coins  coins available.
     * @param amount amount to be given.
     * @return change in the form of a map containing coin type - coin count pairs.
     * @throws ChangeImpossibleException exception thrown in case there is no change combination.
     */
    private Map<CoinType, Integer> findCoins(Map<CoinType, Integer> coins, int amount) throws ChangeImpossibleException {
        return findCoins(prepareCoinList(coins), amount);
    }

    /**
     * Method preparing coin list, converting given coins map.
     * List contains {@link MutablePair} objects holding coin type and coin count.
     * List is used because of the availability to access given coin types via their index on the list.
     *
     * @param coins coins to be transformed.
     * @return List of {@link MutablePair} objects holding coin type and coin count.
     */
    private List<MutablePair<CoinType, Integer>> prepareCoinList(Map<CoinType, Integer> coins) {
        List<MutablePair<CoinType, Integer>> coinList = Lists.newArrayList();
        Arrays.stream(COIN_TYPES_ASCENDING).forEach(ct -> coinList.add(MutablePair.of(ct, Optional.ofNullable(coins.get(ct)).orElse(0))));
        return coinList;
    }

    /**
     * Method implementing change finding algorithm.
     * Finds the given amount using coins available.
     *
     * @param coins  coins available.
     * @param amount amount of change to be given.
     * @return change in form of coins combination, containing coin types and their counts.
     * @throws ChangeImpossibleException exception thrown in case there is no valid change combination.
     */
    private Map<CoinType, Integer> findCoins(List<MutablePair<CoinType, Integer>> coins, int amount) throws ChangeImpossibleException {
        Map<CoinType, Integer> change = Collections.emptyMap();

        int changeAmount = amount;
        // starting from biggest coin value
        for (int i = 0; i < coins.size(); i++) {
            changeAmount = amount;
            change = resetChangeMap();
            int n = coins.size() - 1 - i;
            // while amount left
            while (changeAmount > 0) {
                // all types of coins iterated, change impossible
                if (n < 0 && i == coins.size()) {
                    throw new ChangeImpossibleException();
                } else if (n < 0) {
                    break;
                }
                MutablePair<CoinType, Integer> coinPair = coins.get(n);
                int coinVal = coinPair.getLeft().getValue();
                // coin value lesser or equal to amount and any coins of that value left
                if (coinVal <= changeAmount && coinPair.getRight() > 0) {
                    // add used coin to change result
                    change.put(coinPair.getLeft(), change.get(coinPair.getLeft()) + 1);
                    // decrease no. of coins available
                    coinPair.setRight(coinPair.getRight() - 1);
                    // decrease amount
                    changeAmount -= coinVal;
                } else {
                    n--; /// check another coin type
                }
            }
            // if change coins found starting from given coin type
            if (changeAmount == 0) {
                return change;
            }
        }
        // if change amount is different to 0 after having iterated over every combination
        // change cannot be given
        if (changeAmount != 0) {
            throw new ChangeImpossibleException();
        }
        return change;
    }

    /**
     * Method generating a new instance of a map, having all coin types as it's keys
     * with all values set to 0.
     *
     * @return instance of a map, having all coin types as it's keys
     * with all values set to 0.
     */
    private Map<CoinType, Integer> resetChangeMap() {
        Map<CoinType, Integer> changeMap = Maps.newHashMap();
        Arrays.stream(COIN_TYPES_ASCENDING).forEach(ct -> changeMap.put(ct, 0));
        return changeMap;
    }
}
