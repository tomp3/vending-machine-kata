package tdd.vendingMachine.ui.machine.view.converter;

import com.google.common.collect.ImmutableMap;
import tdd.vendingMachine.common.converter.Converter;
import tdd.vendingMachine.model.common.CoinType;

import java.util.Map;

/**
 * Coin Type - Button text converter.
 */
public final class CoinTypeButtonConverter {

    /**
     * Default constructor.
     */
    private CoinTypeButtonConverter() {
    }

    /**
     * Map used for converting button text to coin type.
     */
    private static final Map<String, CoinType> COIN_BUTTON_TYPE_MAP = ImmutableMap.<String, CoinType>builder()
        .put("5", CoinType.FIVE)
        .put("2", CoinType.TWO)
        .put("1", CoinType.ONE)
        .put("0.5", CoinType.POINT_FIVE)
        .put("0.2", CoinType.POINT_TWO)
        .put("0.1", CoinType.POINT_ONE)
        .build();
    /**
     * Map used for converting coin type to button text.
     */
    private static final Map<CoinType, String> INVERTED_COIN_BUTTON_TYPE_MAP = ImmutableMap.<CoinType, String>builder()
        .put(CoinType.FIVE, "5")
        .put(CoinType.TWO, "2")
        .put(CoinType.ONE, "1")
        .put(CoinType.POINT_FIVE, "0.5")
        .put(CoinType.POINT_TWO, "0.2")
        .put(CoinType.POINT_ONE, "0.1")
        .build();

    /**
     * Converter implementation.
     */
    private static final Converter<CoinType, String> COIN_TYPE_CONVERTER = Converter.of(
        INVERTED_COIN_BUTTON_TYPE_MAP::get,
        COIN_BUTTON_TYPE_MAP::get
    );

    /**
     * Converts coin type to button text.
     *
     * @param coinType coin type.
     * @return button text.
     */
    public static String convert(CoinType coinType) {
        return COIN_TYPE_CONVERTER.from(coinType);
    }

    /**
     * Converts button text to coin type.
     *
     * @param buttonText button text.
     * @return coin type.
     */
    public static CoinType convert(String buttonText) {
        return COIN_TYPE_CONVERTER.to(buttonText);
    }
}
