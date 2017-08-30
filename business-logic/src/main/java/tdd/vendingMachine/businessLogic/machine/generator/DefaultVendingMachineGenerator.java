package tdd.vendingMachine.businessLogic.machine.generator;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.mutable.MutableInt;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import tdd.vendingMachine.businessLogic.machine.service.VendingMachineService;
import tdd.vendingMachine.common.number.NumberUtils;
import tdd.vendingMachine.model.common.CoinType;
import tdd.vendingMachine.model.machine.VendingMachine;
import tdd.vendingMachine.model.machine.VendingMachineCash;
import tdd.vendingMachine.model.machine.VendingMachineShelf;
import tdd.vendingMachine.model.product.ProductType;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Default vending machine generator.
 */
public class DefaultVendingMachineGenerator implements VendingMachineGenerator {

    /**
     * Default max coins for vending machine cash.
     */
    private static final Map<CoinType, Integer> MAX_COINS = ImmutableMap.<CoinType, Integer>builder()
        .put(CoinType.POINT_ONE, 20)
        .put(CoinType.POINT_TWO, 20)
        .put(CoinType.POINT_FIVE, 20)
        .put(CoinType.ONE, 20)
        .put(CoinType.TWO, 20)
        .put(CoinType.FIVE, 20)
        .build();

    /**
     * Default initial coins in vending machine's cash.
     */
    private static final Map<CoinType, Integer> INITIAL_COINS = ImmutableMap.<CoinType, Integer>builder()
        .put(CoinType.POINT_ONE, 10)
        .put(CoinType.POINT_TWO, 10)
        .put(CoinType.POINT_FIVE, 8)
        .put(CoinType.ONE, 6)
        .put(CoinType.TWO, 6)
        .put(CoinType.FIVE, 3)
        .build();

    /**
     * Products with price ranges.
     */
    private static final Map<ProductType, Pair<BigDecimal, BigDecimal>> PRODUCTS_WITH_PRICE_RANGES =
        ImmutableMap.<ProductType, Pair<BigDecimal, BigDecimal>>builder()
            .put(ProductType.AWESOME_CHOCOLATE_BAR, ImmutablePair.of(BigDecimal.valueOf(1.4), BigDecimal.valueOf(2.2)))
            .put(ProductType.COKE_0_33_CAN, ImmutablePair.of(BigDecimal.valueOf(2), BigDecimal.valueOf(2.5)))
            .put(ProductType.COKE_0_33_BOTTLE, ImmutablePair.of(BigDecimal.valueOf(2), BigDecimal.valueOf(3)))
            .put(ProductType.COKE_0_50_BOTTLE, ImmutablePair.of(BigDecimal.valueOf(2.5), BigDecimal.valueOf(4)))
            .put(ProductType.ENERGY_DRINK_0_33_CAN, ImmutablePair.of(BigDecimal.valueOf(3), BigDecimal.valueOf(5)))
            .put(ProductType.STILL_WATER_0_50_BOTTLE, ImmutablePair.of(BigDecimal.valueOf(1.5), BigDecimal.valueOf(2.5)))
            .put(ProductType.CARBONATED_WATER_0_50_BOTTLE, ImmutablePair.of(BigDecimal.valueOf(1.5), BigDecimal.valueOf(2.5)))
            .put(ProductType.POTATO_CHIPS_0_200, ImmutablePair.of(BigDecimal.valueOf(2), BigDecimal.valueOf(3)))
            .put(ProductType.PEANUTS_CAN_0_200, ImmutablePair.of(BigDecimal.valueOf(1.4), BigDecimal.valueOf(2.0)))
            .build();

    /**
     * Default shelf size.
     */
    private static final int DEFAULT_SHELF_SIZE = 20;

    /**
     * {@link VendingMachineService} instance.
     */
    private VendingMachineService service = VendingMachineService.newVendingMachineService();

    /**
     * Generates {@link VendingMachine} instance.
     *
     * @return {@link VendingMachine} instance.
     */
    public VendingMachine generate() {
        Map<String, VendingMachineShelf> shelves = createShelves();
        return service.createVendingMachine(createCash(), shelves.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList()), shelves);
    }

    /**
     * Creates {@link VendingMachineCash} instance setting it's max coin sizes to {@link #MAX_COINS}
     * and inserting default coins as defined by {@link #INITIAL_COINS}.
     *
     * @return {@link VendingMachineCash} instance.
     */
    private VendingMachineCash createCash() {
        VendingMachineCash cash = new VendingMachineCash(MAX_COINS);
        cash.getCoins().putAll(INITIAL_COINS);
        return cash;
    }

    /**
     * Creates default shelves.
     *
     * @return default shelves.
     */
    private Map<String, VendingMachineShelf> createShelves() {
        Map<String, VendingMachineShelf> shelves = Maps.newHashMap();
        MutableInt mutableInt = new MutableInt(1);
        PRODUCTS_WITH_PRICE_RANGES
            .forEach((key, value) -> shelves.put(String.format("%02d", mutableInt.getAndIncrement()),
                new VendingMachineShelf(key, DEFAULT_SHELF_SIZE, getRandomProductPrice(value.getLeft(), value.getRight()))));
        return shelves;
    }

    /**
     * Gets random product price within the given range (both {@code min} and {@code max} inclusive).
     *
     * @param min min price (inclusive).
     * @param max max price (inclusive).
     * @return random product price within the given range (both {@code min} and {@code max} inclusive).
     */
    private BigDecimal getRandomProductPrice(BigDecimal min, BigDecimal max) {
        return NumberUtils.getRandomBigDecimalInclusive(min, max, 4);
    }
}
