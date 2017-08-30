package tdd.vendingMachine.common.number;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 * Class with number util methods.
 */
public final class NumberUtils {

    /**
     * Default scale.
     */
    private static final int SCALE_2 = 2;
    /**
     * Default rounding mode.
     */
    private static final RoundingMode ROUND_HALF_UP = RoundingMode.HALF_UP;

    /**
     * Big decimal 10 value.
     */
    public static final BigDecimal BD_TEN = BigDecimal.valueOf(10);
    /**
     * Integer 0 value.
     */
    public static final Integer INT_ZERO = 0;

    /**
     * {@link Random} instance.
     */
    private static final Random RANDOM = new Random();

    /**
     * Default private constructor.
     */
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
            throw new IllegalArgumentException("Both dividend and divisor are required.");
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

    /**
     * Generates new random int of value between {@code min} (inclusive) and {@code max} (exclusive).
     *
     * @param min min value (inclusive).
     * @param max max value (exclusive).
     * @return random int of value between {@code min} (inclusive) and {@code max} (exclusive).
     */
    public static int getRandomInt(int min, int max) {
        return min + RANDOM.nextInt((max - min));
    }

    /**
     * Generates new random {@link BigDecimal} of value between {@code min} (inclusive) and {@code max} (inclusive).
     * Returned object is scaled with the default scale ({@link #SCALE_2} using {@link #ROUND_HALF_UP} rounding mode.
     *
     * @param min min value (inclusive)
     * @param max max value (inclusive).
     * @return random {@link BigDecimal} of value between {@code min} (inclusive) and {@code max} (inclusive).
     * Returned object is scaled with the default scale ({@link #SCALE_2} using {@link #ROUND_HALF_UP} rounding mode.
     */
    public static BigDecimal getRandomBigDecimalInclusive(BigDecimal min, BigDecimal max, int precision) {
        BigDecimal precisionDecimals = BigDecimal.TEN.pow(precision - 1);
        BigDecimal minPrec = min.multiply(precisionDecimals);
        BigDecimal maxPrec = max.multiply(precisionDecimals);
        BigDecimal range = maxPrec.subtract(minPrec).add(BigDecimal.ONE);

        return BigDecimal.valueOf(RANDOM.nextDouble())
            .multiply(range)
            .subtract(precisionDecimals)
            .divide(precisionDecimals, SCALE_2, ROUND_HALF_UP);
    }
}
