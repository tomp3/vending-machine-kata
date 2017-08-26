package tdd.vendingMachine.common.number;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NumberUtilsTest {

    @Test
    public void testBigDecimalDefaultMultiplication() {
        assertThatThrownBy(() -> NumberUtils.multiply(null, BigDecimal.ONE));
        BigDecimal first = BigDecimal.valueOf(5);
        BigDecimal second = new BigDecimal(2.5);
        assertThat(NumberUtils.multiply(first, second))
            .isEqualTo(first.multiply(second).setScale(2, RoundingMode.HALF_UP));
        first = new BigDecimal(0.1235);
        second = BigDecimal.valueOf(3);
        assertThat(NumberUtils.multiply(first, second, 1, RoundingMode.FLOOR))
            .isEqualTo(new BigDecimal(0.3).setScale(1, RoundingMode.HALF_UP));
    }

    @Test
    public void testBigDecimalDivision() {
        assertThatThrownBy(() -> NumberUtils.divide(null, BigDecimal.ONE));
        assertThatThrownBy(() -> NumberUtils.divide(BigDecimal.ONE, BigDecimal.ZERO));
        assertThat(NumberUtils.divide(new BigDecimal(4.5), BigDecimal.valueOf(2))).isEqualTo(new BigDecimal(2.25));
        assertThat(NumberUtils.divide(BigDecimal.ONE, BigDecimal.valueOf(3), 3, RoundingMode.HALF_UP))
            .isEqualTo(new BigDecimal(0.333).setScale(3, RoundingMode.HALF_UP));
    }
}
