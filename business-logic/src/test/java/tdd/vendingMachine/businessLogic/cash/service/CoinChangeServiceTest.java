package tdd.vendingMachine.businessLogic.cash.service;

import com.google.common.collect.ImmutableMap;
import org.assertj.core.data.MapEntry;
import org.junit.Test;
import tdd.vendingMachine.businessLogic.cash.exception.ChangeImpossibleException;
import tdd.vendingMachine.model.common.CoinType;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * {@link CoinChangeService} test.
 */
public class CoinChangeServiceTest {

    /**
     * Tested object.
     */
    private CoinChangeService testedService = CoinChangeService.newCoinChangeService();

    /**
     * Tests change calculation.
     *
     * @throws ChangeImpossibleException exception thrown in case there is no possible change combination.
     */
    @Test
    public void testCalculateChange() throws ChangeImpossibleException {
        Map<CoinType, Integer> coins = ImmutableMap.<CoinType, Integer>builder()
            .put(CoinType.FIVE, 1)
            .put(CoinType.TWO, 2)
            .put(CoinType.ONE, 1)
            .put(CoinType.POINT_FIVE, 1)
            .put(CoinType.POINT_TWO, 2)
            .put(CoinType.POINT_ONE, 0)
            .build();

        Map<CoinType, Integer> change = testedService.calculateChange(coins, BigDecimal.valueOf(0.1), BigDecimal.valueOf(5));
        assertThat(change).contains(MapEntry.entry(CoinType.TWO, 2), MapEntry.entry(CoinType.POINT_FIVE, 1),
            MapEntry.entry(CoinType.POINT_TWO, 2));
        assertThatThrownBy(() -> testedService.calculateChange(coins, BigDecimal.valueOf(0.7), BigDecimal.ONE));

        Map<CoinType, Integer> otherCoins = ImmutableMap.<CoinType, Integer>builder()
            .put(CoinType.FIVE, 1)
            .put(CoinType.TWO, 3)
            .put(CoinType.ONE, 0)
            .put(CoinType.POINT_FIVE, 1)
            .put(CoinType.POINT_TWO, 2)
            .put(CoinType.POINT_ONE, 0)
            .build();

        change = testedService.calculateChange(otherCoins, BigDecimal.valueOf(4), BigDecimal.TEN);
        assertThat(change).contains(MapEntry.entry(CoinType.TWO, 3));

    }
}
