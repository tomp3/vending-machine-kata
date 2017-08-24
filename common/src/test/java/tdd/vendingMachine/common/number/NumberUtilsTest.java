package tdd.vendingMachine.common.number;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NumberUtilsTest {

    private static final MathContext SCALE_2_ROUND_HALF_UP = new MathContext(2, RoundingMode.HALF_UP);

    private static final MathContext SCALE_4_FLOOR = new MathContext(4, RoundingMode.FLOOR);

    private List<BigDecimal> multiplicationNumbers = Arrays.asList(
        BigDecimal.ONE, new BigDecimal(2), new BigDecimal(3), new BigDecimal(4)
    );

    @Test
    public void testBigDecimalDefaultMultiplication() {
        assertThatThrownBy(() -> NumberUtils.multiply(null));
        assertThat(multiplicationNumbers.stream().reduce(BigDecimal.ONE, (a, b) -> a.multiply(b, SCALE_2_ROUND_HALF_UP)))
            .isEqualTo(NumberUtils.multiply(multiplicationNumbers.get(0), multiplicationNumbers.stream().skip(1).toArray(BigDecimal[]::new)));
    }

    @Test
    public void testBigDecimalContextMultiplication() {
        assertThat(multiplicationNumbers.stream().reduce(BigDecimal.ONE, (a, b) -> a.multiply(b, SCALE_4_FLOOR)))
            .isEqualTo(NumberUtils.multiply(SCALE_4_FLOOR, multiplicationNumbers.get(0), multiplicationNumbers.stream().skip(1).toArray(BigDecimal[]::new)));
    }
}
