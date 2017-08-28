package tdd.vendingMachine.common.currency;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link CurrencyUtils} test.
 */
public class CurrencyUtilsTest {

    /**
     * Tests normalization methods.
     */
    @Test
    public void testNormalizeValue() {
        BigDecimal value = BigDecimal.valueOf(12.50);
        assertThat(CurrencyUtils.normalizeCurrency(value)).isEqualTo(125);
    }

    /**
     * Tests denormalization methods.
     */
    @Test
    public void testDenormalizeValue() {
        int value = 39;
        assertThat(CurrencyUtils.denormalizeCurrency(value))
            .isEqualTo(BigDecimal.valueOf(3.9).setScale(1, RoundingMode.HALF_UP));
    }
}
