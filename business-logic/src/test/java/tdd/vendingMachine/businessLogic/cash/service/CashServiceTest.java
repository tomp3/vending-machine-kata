package tdd.vendingMachine.businessLogic.cash.service;

import com.google.common.collect.ImmutableMap;
import org.assertj.core.data.MapEntry;
import org.junit.Test;
import tdd.vendingMachine.businessLogic.cash.exception.ChangeImpossibleException;
import tdd.vendingMachine.businessLogic.cash.exception.CoinInsertionImpossibleException;
import tdd.vendingMachine.model.common.CoinType;
import tdd.vendingMachine.model.machine.VendingMachineCash;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

/**
 * {@link CashService} test.
 */
public class CashServiceTest {

    /**
     * Max coin count map.
     */
    private static final Map<CoinType, Integer> MAX_COIN_COUNT = ImmutableMap.<CoinType, Integer>builder()
        .put(CoinType.POINT_ONE, 200)
        .put(CoinType.POINT_TWO, 200)
        .put(CoinType.POINT_FIVE, 170)
        .put(CoinType.ONE, 150)
        .put(CoinType.TWO, 150)
        .put(CoinType.FIVE, 100)
        .build();

    /**
     * Default coins map.
     */
    private static final Map<CoinType, Integer> DEFAULT_COINS = ImmutableMap.<CoinType, Integer>builder()
        .put(CoinType.FIVE, 1)
        .put(CoinType.TWO, 2)
        .put(CoinType.ONE, 1)
        .put(CoinType.POINT_FIVE, 1)
        .put(CoinType.POINT_TWO, 2)
        .put(CoinType.POINT_ONE, 0)
        .build();

    /**
     * Tested object instance.
     */
    private CashService testedService = CashService.newCashService(CoinChangeService.newCoinChangeService());

    /**
     * Method testing methods inserting coins to the cash.
     */
    @Test
    public void testInsertCoins() throws CoinInsertionImpossibleException {
        VendingMachineCash cash = new VendingMachineCash(MAX_COIN_COUNT);
        ImmutableMap<CoinType, Integer> coins = ImmutableMap.<CoinType, Integer>builder()
            .put(CoinType.POINT_ONE, 200)
            .put(CoinType.POINT_TWO, 200)
            .put(CoinType.POINT_FIVE, 200)
            .put(CoinType.ONE, 200)
            .put(CoinType.TWO, 200)
            .put(CoinType.FIVE, 200)
            .build();
        assertThatThrownBy(() -> testedService.insertCoins(cash, coins));
        ImmutableMap<CoinType, Integer> insertCoins = ImmutableMap.<CoinType, Integer>builder()
            .put(CoinType.POINT_ONE, 200)
            .put(CoinType.POINT_TWO, 200)
            .put(CoinType.POINT_FIVE, 170)
            .put(CoinType.ONE, 150)
            .put(CoinType.TWO, 150)
            .put(CoinType.FIVE, 100)
            .build();
        testedService.insertCoins(cash, insertCoins);
        assertThat(cash.getCoins()).contains(
            MapEntry.entry(CoinType.POINT_ONE, 200), MapEntry.entry(CoinType.POINT_TWO, 200),
            MapEntry.entry(CoinType.POINT_FIVE, 170), MapEntry.entry(CoinType.ONE, 150),
            MapEntry.entry(CoinType.TWO, 150), MapEntry.entry(CoinType.FIVE, 100)
        );
    }

    /**
     * Method testing methods responsible for calculating the change.
     */
    @Test
    public void testPrepareChange() throws CoinInsertionImpossibleException {
        VendingMachineCash cash = new VendingMachineCash(MAX_COIN_COUNT);
        testedService.insertCoins(cash, DEFAULT_COINS);

        try {
            Map<CoinType, Integer> change = testedService.prepareChange(cash, BigDecimal.valueOf(0.1), BigDecimal.valueOf(5));
            assertThat(change).contains(MapEntry.entry(CoinType.TWO, 2), MapEntry.entry(CoinType.POINT_FIVE, 1),
                MapEntry.entry(CoinType.POINT_TWO, 2));
        } catch (ChangeImpossibleException e) {
            fail(e.getMessage(), e);
        }
        assertThatThrownBy(() -> testedService.prepareChange(cash, BigDecimal.valueOf(0.7), BigDecimal.ONE));
    }

    /**
     * Method testing methods giving / disposing coins.
     */
    @Test
    public void testGiveCoins() throws CoinInsertionImpossibleException {
        VendingMachineCash cash = new VendingMachineCash(MAX_COIN_COUNT);
        testedService.insertCoins(cash, DEFAULT_COINS);

        ImmutableMap<CoinType, Integer> toBeRetrievedCoins = ImmutableMap.<CoinType, Integer>builder()
            .put(CoinType.TWO, 2)
            .put(CoinType.POINT_FIVE, 1)
            .put(CoinType.POINT_TWO, 2)
            .build();
        Map<CoinType, Integer> retrievedCoins = testedService.giveCoins(cash, toBeRetrievedCoins);
        assertThat(retrievedCoins).contains(MapEntry.entry(CoinType.TWO, 2), MapEntry.entry(CoinType.POINT_FIVE, 1),
            MapEntry.entry(CoinType.POINT_TWO, 2));

        assertThat(cash.getCoins()).contains(MapEntry.entry(CoinType.TWO, 0));

        retrievedCoins = testedService.giveCoins(cash, ImmutableMap.<CoinType, Integer>builder().put(CoinType.TWO, 1).build());
        assertThat(retrievedCoins).hasSize(0);
    }

    /**
     * Method testing methods creating cash instance.
     */
    @Test
    public void testCreateCashInstance() {
        VendingMachineCash cash = testedService.createCash(MAX_COIN_COUNT, DEFAULT_COINS);
        assertThat(cash.getCoins()).contains(
            MapEntry.entry(CoinType.FIVE, 1),
            MapEntry.entry(CoinType.TWO, 2),
            MapEntry.entry(CoinType.ONE, 1),
            MapEntry.entry(CoinType.POINT_FIVE, 1),
            MapEntry.entry(CoinType.POINT_TWO, 2),
            MapEntry.entry(CoinType.POINT_ONE, 0)
        );
        assertThat(cash.getMaxCoins()).contains(
            MapEntry.entry(CoinType.POINT_ONE, 200),
            MapEntry.entry(CoinType.POINT_TWO, 200),
            MapEntry.entry(CoinType.POINT_FIVE, 170),
            MapEntry.entry(CoinType.ONE, 150),
            MapEntry.entry(CoinType.TWO, 150),
            MapEntry.entry(CoinType.FIVE, 100)
        );
    }

    /**
     * {@link CashService#getUserInsertedAmountSum(VendingMachineCash)} test.
     */
    @Test
    public void testGetUserInsertedAmountSum() {
        VendingMachineCash cash = testedService.createCash(MAX_COIN_COUNT, DEFAULT_COINS);
        cash.getUserInsertedMoney().putAll(ImmutableMap.of(CoinType.POINT_ONE, 5, CoinType.POINT_FIVE, 1));
        BigDecimal insertedAmount = testedService.getUserInsertedAmountSum(cash);
        assertThat(insertedAmount).isEqualByComparingTo(BigDecimal.ONE);
    }
}
