package tdd.vendingMachine.businessLogic.cash.service;

import tdd.vendingMachine.businessLogic.cash.exception.ChangeImpossibleException;
import tdd.vendingMachine.businessLogic.cash.exception.CoinInsertionImpossibleException;
import tdd.vendingMachine.model.common.CoinType;
import tdd.vendingMachine.model.machine.VendingMachineCash;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Cash service responsible for managing vending machine cash.
 */
public interface CashService {

    /**
     * Creates default implementation of cash service ({@link CashServiceImpl}),
     * applying given coin change service.
     *
     * @param coinChangeService coin change service.
     * @return default implementation of cash service ({@link CashServiceImpl}).
     */
    static CashService newCashService(CoinChangeService coinChangeService) {
        return new CashServiceImpl(coinChangeService);
    }

    /**
     * Inserts coins into the cash as long as cash has free slots for given coin types.
     *
     * @param cash  vending machine cash.
     * @param coins coins to be inserted.
     * @throws CoinInsertionImpossibleException thrown in case all coin slots are taken.
     */
    void insertCoins(VendingMachineCash cash, Map<CoinType, Integer> coins) throws CoinInsertionImpossibleException;

    /**
     * Prepares change for the product of the given price with the given payment amount.
     *
     * @param cash    vending machine cash.
     * @param price   product price.
     * @param payment payment amount.
     * @return map representation of change - coin type and their counts.
     * @throws ChangeImpossibleException exception in case there is no possible change combination for the given parameters.
     */
    Map<CoinType, Integer> prepareChange(VendingMachineCash cash, BigDecimal price, BigDecimal payment)
        throws ChangeImpossibleException;

    /**
     * Disposes given coins from given vending machine cash.
     * Updates coins remaining in the cash.
     * Returns no coins in case cash has less coins than required.
     *
     * @param coins coins to be disposed.
     * @return disposed coins. Returns no coins in case cash has less coins than required.
     */
    Map<CoinType, Integer> giveCoins(VendingMachineCash cash, Map<CoinType, Integer> coins);

    /**
     * Creates {@link VendingMachineCash} instance with given coin limit and inserts given coins into it.
     *
     * @param maxCoinCount coins limit.
     * @param coins        initial coins.
     * @return vending machine cash instance.
     */
    VendingMachineCash createCash(Map<CoinType, Integer> maxCoinCount, Map<CoinType, Integer> coins);

    /**
     * Returns concrete inserted amount sum in form of {@link BigDecimal}.
     *
     * @param cash vending machine cash.
     * @return concrete inserted amount sum in form of {@link BigDecimal}.
     */
    BigDecimal getUserInsertedAmountSum(VendingMachineCash cash);
}
