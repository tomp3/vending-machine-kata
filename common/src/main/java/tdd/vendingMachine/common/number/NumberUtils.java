package tdd.vendingMachine.common.number;

import org.apache.commons.lang3.ArrayUtils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class with number util methods.
 */
public class NumberUtils {

    /**
     * Default MathContext for number operations.
     */
    public static final MathContext SCALE_2_ROUND_HALF_UP = new MathContext(2, RoundingMode.HALF_UP);
    private static final BigDecimal MULTIPLICATION_NEUTRAL = BigDecimal.ONE;

    private NumberUtils() {
    }

    /**
     * Multiplies {@link BigDecimal} objects and returns multiplication product.
     * First multiplication factor is required.
     *
     * @param mathContext multiplication {@link MathContext}.
     * @param first       first multiplication factor.
     * @param numbers     other multiplication factors.
     * @return multiplication product.
     */
    public static BigDecimal multiply(MathContext mathContext, BigDecimal first, BigDecimal... numbers) {
        if (first == null) {
            throw new IllegalArgumentException("Factors required.");
        }
        if (mathContext == null) {
            return multiply(first, numbers);
        }
        if (ArrayUtils.isEmpty(numbers)) {
            return first;
        }

        List<BigDecimal> factors = new ArrayList<>(Arrays.asList(numbers));
        factors.add(first);
        return factors.stream().reduce(MULTIPLICATION_NEUTRAL, (a, b) -> a.multiply(b, mathContext));
    }

    /**
     * Multiplies {@link BigDecimal} objects and returns multiplication product.
     * First multiplication factor is required.
     * Calls {@link NumberUtils#multiply(MathContext, BigDecimal, BigDecimal...)} method
     * providing default math context defined as {@link NumberUtils#SCALE_2_ROUND_HALF_UP}.
     *
     * @param first   first multiplication factor.
     * @param numbers other multiplication factors.
     * @return multiplication product.
     */
    public static BigDecimal multiply(BigDecimal first, BigDecimal... numbers) {
        return multiply(SCALE_2_ROUND_HALF_UP, first, numbers);
    }
}
