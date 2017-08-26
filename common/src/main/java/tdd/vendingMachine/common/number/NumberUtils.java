package tdd.vendingMachine.common.number;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Class with number util methods.
 */
public class NumberUtils {

    /**
     * Default scale.
     */
    public static final int SCALE_2 = 2;
    /**
     * Default rounding mode.
     */
    public static final RoundingMode ROUND_HALF_UP = RoundingMode.HALF_UP;
    private static final BigDecimal MULTIPLICATION_NEUTRAL = BigDecimal.ONE;


    public static final BigDecimal BD_TEN = BigDecimal.valueOf(10);
    public static final Integer INT_ZERO = 0;

    private NumberUtils() {
    }

    /**
     * Multiplies {@link BigDecimal} objects and returns multiplication product.
     * If no scale is provided, uses default scale defined as {@link #SCALE_2}.
     * If no rounding mode is provided, uses default rounding mode defined as {@link #ROUND_HALF_UP}.
     *
     * @param first        first multiplication factor.
     * @param second       second multiplication factor.
     * @param scale        multiplication scale.
     * @param roundingMode rounding mode.
     * @return multiplication result.
     */
    public static BigDecimal multiply(BigDecimal first, BigDecimal second, Integer scale, RoundingMode roundingMode) {
        if (first == null || second == null) {
            throw new IllegalArgumentException("Both factors required.");
        }
        int mScale = scale == null ? SCALE_2 : scale;
        RoundingMode mRoundingMode = roundingMode == null ? ROUND_HALF_UP : roundingMode;
        return first.multiply(second).setScale(mScale, mRoundingMode);
    }

    /**
     * Multiplies {@link BigDecimal} objects and returns multiplication product.
     *
     * @param first  first multiplication factor.
     * @param second second multiplication factor.
     * @return multiplication product.
     */
    public static BigDecimal multiply(BigDecimal first, BigDecimal second) {
        return multiply(first, second, SCALE_2, ROUND_HALF_UP);
    }

    /**
     * Checks if the given number is zero.
     *
     * @param value tested value.
     * @return {@code true} if given value is zero, {@code false} otherwise.
     */
    public static boolean isZero(Integer value) {
        return value == 0;
    }

    /**
     * Checks if the given number is different to zero.
     *
     * @param value tested value.
     * @return {@code true} if given value is not zero, {@code false} otherwise.
     */
    public static boolean isNotZero(Integer value) {
        return value != 0;
    }

    /**
     * Divides given dividend by the given divisor setting given scale and rounding mode.
     * If no scale is provided, uses default scale defined as {@link #SCALE_2}.
     * If no rounding mode is provided, uses default rounding mode defined as {@link #ROUND_HALF_UP}.
     *
     * @param dividend dividend.
     * @param divisor  divisor.
     * @return division result.
     */
    public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor, Integer scale, RoundingMode roundingMode) {
        if (dividend == null) {
            throw new IllegalArgumentException("Dividend required.");
        }
        if (divisor == null) {
            throw new IllegalArgumentException("Divisor required.");
        }
        if (BigDecimal.ZERO.compareTo(divisor) == 0) {
            throw new IllegalArgumentException("Division by 0 is disallowed.");
        }
        int mScale = scale == null ? SCALE_2 : scale;
        RoundingMode mRoundingMode = roundingMode == null ? ROUND_HALF_UP : roundingMode;
        return dividend.divide(divisor, mScale, mRoundingMode);
    }

    /**
     * Divides given dividend by the given divisor setting default scale ({@link #SCALE_2} and rounding mode ({@link #ROUND_HALF_UP}).
     * Calls {@link #divide(BigDecimal, BigDecimal, Integer, RoundingMode)} method providing default scale and rounding mode.
     *
     * @param dividend dividend.
     * @param divisor  divisor.
     * @return division result.
     */
    public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor) {
        return divide(dividend, divisor, SCALE_2, ROUND_HALF_UP);
    }
}
