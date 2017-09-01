package tdd.vendingMachine.ui.machine.handlers;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.assertj.core.data.MapEntry;
import org.junit.Before;
import org.junit.Test;
import tdd.vendingMachine.model.common.CoinType;
import tdd.vendingMachine.model.machine.VendingMachine;
import tdd.vendingMachine.model.machine.VendingMachineCash;
import tdd.vendingMachine.model.machine.VendingMachineShelf;
import tdd.vendingMachine.model.product.Product;
import tdd.vendingMachine.model.product.ProductType;
import tdd.vendingMachine.ui.machine.actions.VendingMachineAction;
import tdd.vendingMachine.ui.machine.actions.VendingMachineActionParameters;
import tdd.vendingMachine.ui.machine.actions.concrete.InsertCoinAction;
import tdd.vendingMachine.ui.machine.handlers.InsertCoinActionHandler;
import tdd.vendingMachine.ui.machine.handlers.VendingMachineActionHandler;
import tdd.vendingMachine.ui.machine.view.model.VendingMachineState;
import tdd.vendingMachine.ui.machine.view.model.VendingMachineViewModel;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link InsertCoinActionHandler} tests.
 */
public class InsertCoinActionHandlerTest {

    /**
     * Vending machine view model.
     */
    private VendingMachineViewModel vendingMachineViewModel;

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
    }

    /**
     * {@link InsertCoinActionHandler#handle()} test.
     */
    @Test
    public void testHandle() {
        VendingMachineAction action = new InsertCoinAction(VendingMachineActionParameters.of(vendingMachineViewModel, CoinType.TWO));
        VendingMachineActionHandler handler = new InsertCoinActionHandler(action);
        handler.handle();

        assertThat(vendingMachineViewModel.getVendingMachine().getCash().getUserInsertedMoney()).containsExactly(MapEntry.entry(CoinType.TWO, 1));
        new InsertCoinActionHandler(new InsertCoinAction(VendingMachineActionParameters.of(vendingMachineViewModel, CoinType.ONE))).handle();
        assertThat(vendingMachineViewModel.getVendingMachine().getProductTray().getProducts()).hasSize(1);
        assertThat(vendingMachineViewModel.getVendingMachine().getCashTray().getCoins())
            .containsOnly(MapEntry.entry(CoinType.POINT_ONE, 1),
                MapEntry.entry(CoinType.POINT_FIVE, 1),
                MapEntry.entry(CoinType.POINT_TWO, 0),
                MapEntry.entry(CoinType.ONE, 0),
                MapEntry.entry(CoinType.TWO, 0),
                MapEntry.entry(CoinType.FIVE, 0)
            );


    }
}
