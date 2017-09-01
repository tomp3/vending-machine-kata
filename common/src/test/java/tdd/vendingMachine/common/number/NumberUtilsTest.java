package tdd.vendingMachine.common.number;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * {@link NumberUtils} test.
 */
public class NumberUtilsTest {

    /**
     * Tests multiplication methods.
     */
    @Test
    public void testBigDecimalDefaultMultiplication() {
        assertThatThrownBy(() -> NumberUtils.multiply(null, BigDecimal.ONE));
        BigDecimal first = BigDecimal.valueOf(5);
        BigDecimal second = BigDecimal.valueOf(2.5);
        assertThat(NumberUtils.multiply(first, second))
            .isEqualTo(first.multiply(second).setScale(2, RoundingMode.HALF_UP));
        first = BigDecimal.valueOf(0.1235);
        second = BigDecimal.valueOf(3);
        assertThat(NumberUtils.multiply(first, second, 1, RoundingMode.FLOOR))
            .isEqualTo(BigDecimal.valueOf(0.3).setScale(1, RoundingMode.HALF_UP));
    }

    /**
     * Tests the following methods:
     * <ul>
     * <li>{@link NumberUtils#divide(BigDecimal, BigDecimal)}</li>
     * <li>{@link NumberUtils#divide(BigDecimal, BigDecimal, Integer, RoundingMode)}</li>
     * </ul>
     */
    @Test
    public void testBigDecimalDivision() {
        assertThatThrownBy(() -> NumberUtils.divide(null, BigDecimal.ONE));
        assertThatThrownBy(() -> NumberUtils.divide(BigDecimal.ONE, BigDecimal.ZERO));
        assertThat(NumberUtils.divide(BigDecimal.valueOf(4.5), BigDecimal.valueOf(2))).isEqualTo(BigDecimal.valueOf(2.25));
        assertThat(NumberUtils.divide(BigDecimal.ONE, BigDecimal.valueOf(3), 3, RoundingMode.HALF_UP))
            .isEqualTo(BigDecimal.valueOf(0.333).setScale(3, RoundingMode.HALF_UP));
    }

    /**
     * {@link NumberUtils#isZero(Integer)} test.
     */
    @Test
    public void testIsZero() {
        assertThat(NumberUtils.isZero(0));
        assertThat(NumberUtils.isZero(2)).isFalse();
    }

    /**
     * Tests {@link NumberUtils#getRandomInt(int, int)} method.
     */
    @Test
    public void testGetRandomInt() {
        assertThat(NumberUtils.getRandomInt(2, 7)).isLessThan(7).isGreaterThanOrEqualTo(2);
    }

    /**
     * Tests {@link NumberUtils#format(BigDecimal)} method.
     */
    @Test
    public void testFormat(){
        assertThat(NumberUtils.format(BigDecimal.ONE)).isEqualTo("1.0");
        assertThat(NumberUtils.format(BigDecimal.valueOf(23.15))).isEqualTo("23.2");
    }
}
