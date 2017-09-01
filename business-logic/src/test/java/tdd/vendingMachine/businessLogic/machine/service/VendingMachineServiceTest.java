package tdd.vendingMachine.businessLogic.machine.service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.tuple.Pair;
import org.assertj.core.data.MapEntry;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import tdd.vendingMachine.businessLogic.cash.exception.ChangeImpossibleException;
import tdd.vendingMachine.businessLogic.cash.exception.CoinInsertionImpossibleException;
import tdd.vendingMachine.businessLogic.cash.service.CashService;
import tdd.vendingMachine.businessLogic.machine.exception.ProductUnavailableException;
import tdd.vendingMachine.businessLogic.machine.exception.UnavailableShelfCodeException;
import tdd.vendingMachine.businessLogic.shelf.service.ShelfService;
import tdd.vendingMachine.common.number.NumberUtils;
import tdd.vendingMachine.model.common.CoinType;
import tdd.vendingMachine.model.machine.VendingMachine;
import tdd.vendingMachine.model.machine.VendingMachineCash;
import tdd.vendingMachine.model.machine.VendingMachineShelf;
import tdd.vendingMachine.model.product.Product;
import tdd.vendingMachine.model.product.ProductType;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * {@link VendingMachineService} test.
 */
public class VendingMachineServiceTest {

    /**
     * Max coins map.
     */
    private static final ImmutableMap<CoinType, Integer> MAX_COINS = ImmutableMap.<CoinType, Integer>builder()
        .put(CoinType.POINT_ONE, 20)
        .put(CoinType.POINT_TWO, 20)
        .put(CoinType.POINT_FIVE, 17)
        .put(CoinType.ONE, 15)
        .put(CoinType.TWO, 15)
        .put(CoinType.FIVE, 10)
        .build();
    /**
     * Default initial coins map.
     */
    private static final ImmutableMap<CoinType, Integer> INITIAL_COINS = ImmutableMap.<CoinType, Integer>builder()
        .put(CoinType.POINT_ONE, 10)
        .put(CoinType.POINT_TWO, 10)
        .put(CoinType.POINT_FIVE, 10)
        .put(CoinType.ONE, 8)
        .put(CoinType.TWO, 8)
        .put(CoinType.FIVE, 5)
        .build();

    /**
     * Default available shelves' codes list.
     */
    private static final List<String> AVAILABLE_CODES = ImmutableList.of("01", "02", "03", "04", "05", "06", "07");

    /**
     * Tested object.
     */
    private VendingMachineService testedService;

    /**
     * Vending machine instance.
     */
    private VendingMachine vendingMachine;
    /**
     * Vending machine's cash instance.
     */
    private VendingMachineCash cash;
    /**
     * Vending machine's shelves map.
     */
    private Map<String, VendingMachineShelf> shelvesMap;

    /**
     * Shelf service.
     */
    private final ShelfService shelfService = ShelfService.newShelfService();

    /**
     * Initializes tested service and vending machine instances.
     */
    @Before
    public void beforeTest() {
        testedService = VendingMachineService.newVendingMachineService();
        cash = CashService.newCashService().createCash(MAX_COINS, INITIAL_COINS);
        shelvesMap = Maps.newHashMap();
        shelvesMap.put("01", new VendingMachineShelf(ProductType.AWESOME_CHOCOLATE_BAR, 20, BigDecimal.valueOf(1.6)));
        shelvesMap.put("02", new VendingMachineShelf(ProductType.COKE_0_33_BOTTLE, 12, BigDecimal.valueOf(2)));
        shelvesMap.put("03", new VendingMachineShelf(ProductType.COKE_0_50_BOTTLE, 8, BigDecimal.valueOf(3.5)));
        shelvesMap.put("04", new VendingMachineShelf(ProductType.PEANUTS_CAN_0_200, 10, BigDecimal.valueOf(2.2)));
        shelvesMap.put("06", new VendingMachineShelf(ProductType.STILL_WATER_0_50_BOTTLE, 10, BigDecimal.valueOf(2)));
        vendingMachine = new VendingMachine(cash, AVAILABLE_CODES, shelvesMap);
    }

    /**
     * Tests methods creating new vending machine instance.
     */
    @Test
    public void testCreateVendingMachine() {
        VendingMachine vendingMachine = testedService.createVendingMachine(cash, AVAILABLE_CODES, shelvesMap);
        assertThat(vendingMachine.getCash().getCoins()).hasSize(6);
        assertThat(testedService.getUsedShelves(vendingMachine)).hasSize(shelvesMap.size());
    }

    /**
     * Tests methods adding shelf to the vending machine.
     *
     * @throws UnavailableShelfCodeException exception thrown in case invalid shelf code was specified.
     */
    @Test
    public void testAddShelf() throws UnavailableShelfCodeException {
        VendingMachineShelf shelf = new VendingMachineShelf(ProductType.ENERGY_DRINK_0_33_CAN, 12, BigDecimal.valueOf(3));
        assertThatThrownBy(() -> testedService.addShelve(vendingMachine, "01", shelf));
        assertThatThrownBy(() -> testedService.addShelve(vendingMachine, "10", shelf));

        String code = testedService.addShelve(vendingMachine, shelf);
        assertThat(code).isEqualTo("05");
        assertThat(testedService.getUsedShelves(vendingMachine)).hasSize(6);
    }

    /**
     * Tests methods returning shelves used by the vending machine.
     *
     * @throws UnavailableShelfCodeException exception thrown in case invalid shelf code was specified.
     */
    @Test
    public void testGetUsedShelves() throws UnavailableShelfCodeException {
        VendingMachineShelf shelf = new VendingMachineShelf(ProductType.ENERGY_DRINK_0_33_CAN, 12, BigDecimal.valueOf(3));
        testedService.addShelve(vendingMachine, shelf);
        testedService.addShelve(vendingMachine, "07", null);
        assertThat(testedService.getUsedShelves(vendingMachine)).hasSize(6);
    }

    /**
     * Tests {@link VendingMachineService#isProductAvailable(VendingMachine, String)} method.
     */
    @Test
    public void testIsProductAvailable() throws ProductUnavailableException, ChangeImpossibleException {
        final String code = "03";
        shelfService.insertProducts(vendingMachine.getShelves().get(code), Lists.newArrayList(new Product(ProductType.COKE_0_50_BOTTLE)));
        assertThat(testedService.isProductAvailable(vendingMachine, code));
        vendingMachine.getCash().getUserInsertedMoney().putAll(ImmutableMap.of(CoinType.ONE, 3, CoinType.POINT_FIVE, 1));
        testedService.giveProduct(vendingMachine, code);
        assertThat(testedService.isProductAvailable(vendingMachine, code)).isFalse();
    }

    /**
     * Tests {@link VendingMachineService#insertUserCoin(VendingMachine, CoinType)} method.
     */
    @Test
    public void testInsertUserCoin() throws CoinInsertionImpossibleException {
        testedService.insertUserCoin(vendingMachine, CoinType.TWO);
        assertThat(vendingMachine.getCash().getUserInsertedMoney()).containsExactly(MapEntry.entry(CoinType.TWO, 1));
    }

    /**
     * Tests {@link VendingMachineService#getInsertedMoneyAmount(VendingMachine)} method.
     */
    @Test
    public void testGetInsertedMoneyAmount() {
        vendingMachine.getCash().getUserInsertedMoney().putAll(ImmutableMap.of(CoinType.POINT_ONE, 5, CoinType.POINT_TWO, 1, CoinType.POINT_FIVE, 1));
        BigDecimal insertedAmount = testedService.getInsertedMoneyAmount(vendingMachine);
        assertThat(insertedAmount).isEqualByComparingTo(BigDecimal.valueOf(1.2));
    }

    /**
     * Tests {@link VendingMachineService#giveProduct(VendingMachine, String)} method.
     */
    @Test
    public void testGiveProduct() throws ChangeImpossibleException, ProductUnavailableException {
        // Prepare cash coins
        Map<CoinType, Integer> coinsMap = Maps.newHashMap();
        Arrays.stream(CoinType.values()).forEach(ct -> coinsMap.put(ct, NumberUtils.INT_ZERO));
        VendingMachineCash cash = vendingMachine.getCash();
        cash.getCoins().clear();
        cash.getCoins().putAll(coinsMap);
        cash.getCoins().putAll(ImmutableMap.of(CoinType.POINT_ONE, 1, CoinType.POINT_FIVE, 2));
        // Prepare shelves
        vendingMachine.getShelves().clear();
        final String code = "01";
        vendingMachine.getShelves().put(code, new VendingMachineShelf(ProductType.AWESOME_CHOCOLATE_BAR, 10, BigDecimal.valueOf(1.4)));
        Product product = new Product(ProductType.AWESOME_CHOCOLATE_BAR);
        vendingMachine.getShelves().get(code).addProduct(product);
        vendingMachine.getShelves().get(code).addProduct(product);

        // Insert 2 and try getting product
        cash.getUserInsertedMoney().put(CoinType.ONE, 2);
        Pair<Product, Map<CoinType, Integer>> productAndChange = testedService.giveProduct(vendingMachine, code);
        Map<CoinType, Integer> change = productAndChange.getRight();
        // change is given
        assertThat(change).contains(MapEntry.entry(CoinType.POINT_FIVE, 1), MapEntry.entry(CoinType.POINT_ONE, 1));

        // change cannot be given
        cash.getUserInsertedMoney().put(CoinType.ONE, 2);
        assertThatThrownBy(() -> testedService.giveProduct(vendingMachine, code)).isInstanceOf(ChangeImpossibleException.class);

        // Insert exact amount
        cash.getUserInsertedMoney().putAll(ImmutableMap.of(CoinType.ONE, 1, CoinType.POINT_TWO, 2));
        // Product is given
        assertThat(testedService.giveProduct(vendingMachine, code).getLeft()).isNotNull();

        // Product is unavailable
        cash.getUserInsertedMoney().putAll(ImmutableMap.of(CoinType.ONE, 1, CoinType.POINT_TWO, 2));
        assertThatThrownBy(() -> testedService.giveProduct(vendingMachine, code)).isInstanceOf(ProductUnavailableException.class);
    }

    /**
     * {@link VendingMachineService#returnUserMoney(VendingMachine)} test.
     */
    @Test
    public void testReturnUserMoney() throws CoinInsertionImpossibleException {
        // Prepare cash coins
        Map<CoinType, Integer> coinsMap = Maps.newHashMap();
        Arrays.stream(CoinType.values()).forEach(ct -> coinsMap.put(ct, NumberUtils.INT_ZERO));
        VendingMachineCash cash = vendingMachine.getCash();
        cash.getCoins().clear();
        cash.getCoins().putAll(coinsMap);
        cash.getCoins().putAll(ImmutableMap.of(CoinType.POINT_ONE, 1, CoinType.POINT_FIVE, 2));

        testedService.insertUserCoin(vendingMachine, CoinType.TWO);
        testedService.insertUserCoin(vendingMachine, CoinType.POINT_TWO);

        Map<CoinType, Integer> userCoins = testedService.returnUserMoney(vendingMachine);
        assertThat(userCoins).containsOnly(MapEntry.entry(CoinType.TWO, 1), MapEntry.entry(CoinType.POINT_TWO, 1));
    }
}
