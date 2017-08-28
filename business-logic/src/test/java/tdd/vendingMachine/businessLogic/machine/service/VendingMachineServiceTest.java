package tdd.vendingMachine.businessLogic.machine.service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.junit.Before;
import org.junit.Test;
import tdd.vendingMachine.businessLogic.cash.service.CashService;
import tdd.vendingMachine.businessLogic.machine.exception.UnavailableShelfCodeException;
import tdd.vendingMachine.model.common.CoinType;
import tdd.vendingMachine.model.machine.VendingMachine;
import tdd.vendingMachine.model.machine.VendingMachineCash;
import tdd.vendingMachine.model.machine.VendingMachineShelf;
import tdd.vendingMachine.model.product.ProductType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class VendingMachineServiceTest {

    private static final ImmutableMap<CoinType, Integer> MAX_COINS = ImmutableMap.<CoinType, Integer>builder()
        .put(CoinType.POINT_ONE, 20)
        .put(CoinType.POINT_TWO, 20)
        .put(CoinType.POINT_FIVE, 17)
        .put(CoinType.ONE, 15)
        .put(CoinType.TWO, 15)
        .put(CoinType.FIVE, 10)
        .build();
    private static final ImmutableMap<CoinType, Integer> INITIAL_COINS = ImmutableMap.<CoinType, Integer>builder()
        .put(CoinType.POINT_ONE, 10)
        .put(CoinType.POINT_TWO, 10)
        .put(CoinType.POINT_FIVE, 10)
        .put(CoinType.ONE, 8)
        .put(CoinType.TWO, 8)
        .put(CoinType.FIVE, 5)
        .build();

    private static final List<String> AVAILABLE_CODES = ImmutableList.of("01", "02", "03", "04", "05", "06", "07");

    private VendingMachineService testedService;

    private VendingMachine vendingMachine;
    private VendingMachineCash cash;
    private Map<String, VendingMachineShelf> shelvesMap;

    @Before
    public void beforeTest() {
        testedService = VendingMachineService.newVendingMachineService();
        cash = CashService.newCashService().createCash(MAX_COINS, INITIAL_COINS);
        shelvesMap = Maps.newHashMap();
        shelvesMap.put("01", new VendingMachineShelf(ProductType.AWESOME_CHOCOLATE_BAR, 20, new BigDecimal(1.6)));
        shelvesMap.put("02", new VendingMachineShelf(ProductType.COKE_0_33_BOTTLE, 12, BigDecimal.valueOf(2)));
        shelvesMap.put("03", new VendingMachineShelf(ProductType.COKE_0_50_BOTTLE, 8, new BigDecimal(3.5)));
        shelvesMap.put("04", new VendingMachineShelf(ProductType.PEANUTS_CAN_0_200, 10, new BigDecimal(2.2)));
        shelvesMap.put("06", new VendingMachineShelf(ProductType.STILL_WATER_0_50_BOTTLE, 10, BigDecimal.valueOf(2)));
        vendingMachine = new VendingMachine(cash, AVAILABLE_CODES, shelvesMap);
    }

    @Test
    public void testCreateVendingMachine() {
        VendingMachine vendingMachine = testedService.createVendingMachine(cash, AVAILABLE_CODES, shelvesMap);
        assertThat(vendingMachine.getCash().getCoins()).hasSize(6);
        assertThat(testedService.getUsedShelves(vendingMachine)).hasSize(shelvesMap.size());
    }

    @Test
    public void testAddShelf() throws UnavailableShelfCodeException {
        VendingMachineShelf shelf = new VendingMachineShelf(ProductType.ENERGY_DRINK_0_33_CAN, 12, BigDecimal.valueOf(3));
        assertThatThrownBy(() -> testedService.addShelve(vendingMachine, "01", shelf));
        assertThatThrownBy(() -> testedService.addShelve(vendingMachine, "10", shelf));

        String code = testedService.addShelve(vendingMachine, shelf);
        assertThat(code).isEqualTo("05");
        assertThat(testedService.getUsedShelves(vendingMachine)).hasSize(6);
    }

    @Test
    public void testGetUsedShelves() throws UnavailableShelfCodeException {
        VendingMachineShelf shelf = new VendingMachineShelf(ProductType.ENERGY_DRINK_0_33_CAN, 12, BigDecimal.valueOf(3));
        testedService.addShelve(vendingMachine, shelf);
        testedService.addShelve(vendingMachine, "07", null);

        assertThat(testedService.getUsedShelves(vendingMachine)).hasSize(6);

    }
}
