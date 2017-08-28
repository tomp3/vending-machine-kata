package tdd.vendingMachine.common.currency;

import tdd.vendingMachine.common.number.NumberUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Class with currency util methods.
 */
public final class CurrencyUtils {

    /**
     * Scale used in normalization operations.
     */
    private static final int NORMALIZE_SCALE_0 = 0;
    /**
     * Scale used in denormalization operations.
     */
    private static final int DENORMALIZE_SCALE_1 = 1;
    /**
     * Rounding mode - {@link RoundingMode#HALF_UP}.
     */
    private static final RoundingMode ROUND_HALF_UP = RoundingMode.HALF_UP;

    /**
     * Converts currency to normalized value - multiplies currency by 10 to create the decimal value.
     *
     * @param value currency with fractions.
     * @return normalized currency value.
     */
    public static Integer normalizeCurrency(BigDecimal value) {
        return NumberUtils.multiply(value, NumberUtils.BD_TEN, NORMALIZE_SCALE_0, ROUND_HALF_UP).intValue();
    }

    /**
     * Converts normalized currency value to regular currency value with fractions.
     * Divides currency by 10 rounding half up.
     *
     * @param value normalized currency (in decimal only form).
     * @return denormalized currency.
     */
    public static BigDecimal denormalizeCurrency(Integer value) {
        return NumberUtils.divide(new BigDecimal(value), NumberUtils.BD_TEN, DENORMALIZE_SCALE_1, ROUND_HALF_UP);
    }

    /**
     * Default private constructor.
     */
    private CurrencyUtils() {
    }
}
