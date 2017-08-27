package tdd.vendingMachine.common.currency;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;

public class CurrencyUtilsTest {

    @Test
    public void testNormalizeValue() {
        BigDecimal value = new BigDecimal(12.50);
        assertThat(CurrencyUtils.normalizeCurrency(value)).isEqualTo(1250);
    }

    @Test
    public void testDenormalizeValue() {
        int value = 399;
        assertThat(CurrencyUtils.denormalizeCurrency(value))
            .isEqualTo(new BigDecimal(3.99).setScale(2, RoundingMode.HALF_UP));
    }
}
