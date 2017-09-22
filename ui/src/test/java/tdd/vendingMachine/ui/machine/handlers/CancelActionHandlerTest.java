package tdd.vendingMachine.ui.machine.handlers;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.data.MapEntry;
import org.junit.Before;
import org.junit.Test;
import tdd.vendingMachine.businessLogic.cash.service.CashService;
import tdd.vendingMachine.businessLogic.cash.service.CoinChangeService;
import tdd.vendingMachine.businessLogic.machine.service.VendingMachineService;
import tdd.vendingMachine.businessLogic.shelf.service.ShelfService;
import tdd.vendingMachine.model.common.CoinType;
import tdd.vendingMachine.model.machine.VendingMachine;
import tdd.vendingMachine.model.machine.VendingMachineCash;
import tdd.vendingMachine.model.machine.VendingMachineShelf;
import tdd.vendingMachine.model.product.Product;
import tdd.vendingMachine.model.product.ProductType;
import tdd.vendingMachine.ui.machine.actions.VendingMachineActionParameters;
import tdd.vendingMachine.ui.machine.actions.concrete.CancelAction;
import tdd.vendingMachine.ui.machine.actions.concrete.InsertCoinAction;
import tdd.vendingMachine.ui.machine.view.model.VendingMachineState;
import tdd.vendingMachine.ui.machine.view.model.VendingMachineViewModel;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link CancelActionHandler} tests.
 */
public class CancelActionHandlerTest {

    /**
     * Vending machine view model.
     */
    private VendingMachineViewModel vendingMachineViewModel;

    /**
     * Vending machine service.
     */
    private VendingMachineService vendingMachineService;

    /**
     * Vending machine action handler factory.
     */
    private VendingMachineActionHandlerFactory actionHandlerFactory;

    /**
     * Initializes vending machine view model before tests.
     */
    @Before
    public void beforeTest() {
        VendingMachineCash cash = new VendingMachineCash(ImmutableMap.<CoinType, Integer>builder()
            .put(CoinType.POINT_ONE, 10)
            .put(CoinType.POINT_TWO, 10)
            .put(CoinType.POINT_FIVE, 10)
            .put(CoinType.ONE, 10)
            .put(CoinType.TWO, 10)
            .put(CoinType.FIVE, 10)
            .build());
        cash.getCoins().putAll(ImmutableMap.of(CoinType.POINT_ONE, 1, CoinType.POINT_FIVE, 1));
        final String code = "01";
        Map<String, VendingMachineShelf> shelves = Maps.newHashMap();
        shelves.put(code, new VendingMachineShelf(ProductType.AWESOME_CHOCOLATE_BAR, 10, BigDecimal.valueOf(2.4)));
        final Product product = new Product(ProductType.AWESOME_CHOCOLATE_BAR);
        shelves.get(code).addProduct(product);
        shelves.get(code).addProduct(product);
        VendingMachine vendingMachine = new VendingMachine(cash, Lists.newArrayList(code), shelves);
        vendingMachineViewModel = new VendingMachineViewModel(vendingMachine);
        vendingMachineViewModel.setSelectedCode(code);
        vendingMachineViewModel.setState(VendingMachineState.SELECTED);

        vendingMachineService = VendingMachineService.newVendingMachineService(ShelfService.newShelfService(), CashService.newCashService(
            CoinChangeService.newCoinChangeService()));
        actionHandlerFactory = new VendingMachineActionHandlerFactory(vendingMachineService);
    }

    /**
     * {@link CancelActionHandler#handle()} test.
     */
    @Test
    public void testHandle() {
        // Insert coins
        new InsertCoinActionHandler(new InsertCoinAction(VendingMachineActionParameters.of(vendingMachineViewModel, CoinType.TWO)), vendingMachineService,
            actionHandlerFactory).handle();
        // Cancel
        new CancelActionHandler(new CancelAction(VendingMachineActionParameters.of(vendingMachineViewModel, StringUtils.EMPTY)), vendingMachineService)
            .handle();
        assertThat(vendingMachineViewModel.getVendingMachine().getCashTray().getCoins()).containsOnly(MapEntry.entry(CoinType.TWO, 1));
    }
}
