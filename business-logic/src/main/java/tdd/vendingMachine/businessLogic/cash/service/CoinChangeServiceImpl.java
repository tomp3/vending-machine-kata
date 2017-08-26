package tdd.vendingMachine.businessLogic.cash.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.tuple.MutablePair;
import tdd.vendingMachine.businessLogic.cash.exception.ChangeImpossibleException;
import tdd.vendingMachine.common.currency.CurrencyUtils;
import tdd.vendingMachine.common.number.NumberUtils;
import tdd.vendingMachine.model.common.CoinType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Default implementation of CoinChangeService.
 */
public class CoinChangeServiceImpl implements CoinChangeService {

    private static final CoinType[] COIN_TYPES_ASCENDING;

    static {
        CoinType[] coinTypes = CoinType.values();
        Arrays.sort(coinTypes, Comparator.comparingInt(CoinType::getValue));
        COIN_TYPES_ASCENDING = coinTypes;
    }

    private static final int COIN_COUNT_SCALE = 0;
    private static final RoundingMode COIN_COUNT_ROUNDING_MODE = RoundingMode.FLOOR;

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

    private Map<CoinType, Integer> findCoins(Map<CoinType, Integer> coins, int amount) throws ChangeImpossibleException {
        return findCoins(prepareCoinList(coins), amount);
    }

    private List<MutablePair<CoinType, Integer>> prepareCoinList(Map<CoinType, Integer> coins) {
        List<MutablePair<CoinType, Integer>> coinList = Lists.newLinkedList();
        Arrays.stream(COIN_TYPES_ASCENDING).forEach(ct -> coinList.add(MutablePair.of(ct, Optional.ofNullable(coins.get(ct)).orElse(0))));
        return coinList;
    }

    private Map<CoinType, Integer> findCoins(List<MutablePair<CoinType, Integer>> coins, int amount) throws ChangeImpossibleException {
        Map<CoinType, Integer> change = Maps.newHashMap();
        int n = coins.size() - 1;
        // while amount left
        while (amount > 0) {
            // all types of coins iterated, change impossible
            if (n < 0) {
                throw new ChangeImpossibleException();
            }
            MutablePair<CoinType, Integer> coinPair = coins.get(n);
            int coinVal = coinPair.getLeft().getValue();
            // coin value lesser or equal to amount and any coins of that value left
            if (coinVal <= amount && coinPair.getRight() > 0) {
                // add used coin to change result
                change.merge(coinPair.getLeft(), 1, (a, b) -> a + b);
                // decrease no. of coins available
                coinPair.setRight(coinPair.getRight() - 1);
                // decrease amount
                amount -= coinVal;
            } else {
                n--; /// check another coin type
            }
        }
        return change;
    }
}
