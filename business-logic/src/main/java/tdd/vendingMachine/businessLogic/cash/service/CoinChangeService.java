package tdd.vendingMachine.businessLogic.cash.service;

import tdd.vendingMachine.businessLogic.cash.exception.ChangeImpossibleException;
import tdd.vendingMachine.model.common.CoinType;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Service used to calculate coin change possibilities.
 */
public interface CoinChangeService {
    static CoinChangeService newCoinChangeService() {
        return new CoinChangeServiceImpl();
    }

    /**
     * Calculates change for the product of given price while paid with the given payment amount,
     * having given coins available.
     * Returns change in form of coin type - count map.
     * If no change is required, returns empty {@link Map} object.
     *
     * @param coins   available coins.
     * @param price   product price.
     * @param payment payment.
     * @return change in form of coin type - count map. If no change is required, returns empty {@link Map} object.
     * @throws ChangeImpossibleException exception in case there is no possible change combination for the given parameters.
     */
    Map<CoinType, Integer> calculateChange(Map<CoinType, Integer> coins, BigDecimal price, BigDecimal payment) throws ChangeImpossibleException;
}
