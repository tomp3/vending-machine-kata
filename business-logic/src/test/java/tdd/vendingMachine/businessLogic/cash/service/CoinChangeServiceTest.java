package tdd.vendingMachine.businessLogic.cash.service;

import com.google.common.collect.ImmutableMap;
import org.assertj.core.data.MapEntry;
import org.junit.Test;
import tdd.vendingMachine.businessLogic.cash.exception.ChangeImpossibleException;
import tdd.vendingMachine.model.common.CoinType;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class CoinChangeServiceTest {

    private CoinChangeService testedService = CoinChangeService.newCoinChangeService();

    @Test
    public void testCalculateChange() {
        Map<CoinType, Integer> coins = ImmutableMap.<CoinType, Integer>builder()
            .put(CoinType.FIVE, 1)
            .put(CoinType.TWO, 2)
            .put(CoinType.ONE, 1)
            .put(CoinType.POINT_FIVE, 1)
            .put(CoinType.POINT_TWO, 2)
            .put(CoinType.POINT_ONE, 0)
            .build();

        try {
            Map<CoinType, Integer> change = testedService.calculateChange(coins, new BigDecimal(0.1), new BigDecimal(5));
            assertThat(change).contains(MapEntry.entry(CoinType.TWO, 2), MapEntry.entry(CoinType.POINT_FIVE, 1),
                MapEntry.entry(CoinType.POINT_TWO, 2));
        } catch (ChangeImpossibleException e) {
            fail(e.getMessage(), e);
        }
        assertThatThrownBy(() -> testedService.calculateChange(coins, new BigDecimal(0.7), BigDecimal.ONE));

        Map<CoinType, Integer> otherCoins = ImmutableMap.<CoinType, Integer>builder()
            .put(CoinType.FIVE, 1)
            .put(CoinType.TWO, 3)
            .put(CoinType.ONE, 0)
            .put(CoinType.POINT_FIVE, 1)
            .put(CoinType.POINT_TWO, 2)
            .put(CoinType.POINT_ONE, 0)
            .build();

        try {
            Map<CoinType, Integer> change = testedService.calculateChange(otherCoins, BigDecimal.valueOf(4), BigDecimal.TEN);
            change.forEach((k,v) -> System.out.println(k.getValue() + "\t" + v));
        } catch (ChangeImpossibleException e) {
            fail(e.getMessage(), e);
        }

    }
}
