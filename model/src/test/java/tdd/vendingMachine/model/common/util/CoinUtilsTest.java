package tdd.vendingMachine.model.common.util;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import tdd.vendingMachine.model.common.CoinType;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link CoinUtils} tests.
 */
public class CoinUtilsTest {

    /**
     * {@link CoinUtils#coinsToString(Map)} test.
     */
    @Test
    public void testCoinsToString() {
        Map<CoinType, Integer> coins = ImmutableMap.of(CoinType.ONE, 1, CoinType.POINT_FIVE, 2);
        String string = CoinUtils.coinsToString(coins);
        assertThat(string).startsWith("(0.5)");
    }
}
