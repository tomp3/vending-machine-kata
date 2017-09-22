package tdd.vendingMachine.businessLogic.machine.generator;

import org.assertj.core.data.MapEntry;
import org.junit.Before;
import org.junit.Test;
import tdd.vendingMachine.businessLogic.cash.service.CashService;
import tdd.vendingMachine.businessLogic.cash.service.CoinChangeService;
import tdd.vendingMachine.businessLogic.machine.service.VendingMachineService;
import tdd.vendingMachine.businessLogic.shelf.service.ShelfService;
import tdd.vendingMachine.model.common.CoinType;
import tdd.vendingMachine.model.machine.VendingMachine;
import tdd.vendingMachine.model.product.ProductType;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link DefaultVendingMachineGenerator} tests.
 */
public class DefaultVendingMachineGeneratorTest {

    /**
     * Tested object.
     */
    private VendingMachineGenerator generator;

    /**
     * Initializes tested object.
     */
    @Before
    public void beforeTest() {
        ShelfService shelfService = ShelfService.newShelfService();
        generator = new DefaultVendingMachineGenerator(
            VendingMachineService.newVendingMachineService(shelfService, CashService.newCashService(CoinChangeService.newCoinChangeService())), shelfService);
    }

    /**
     * Tests {@link DefaultVendingMachineGenerator#generate()} method.
     */
    @Test
    public void testGenerate() {
        VendingMachine vendingMachine = generator.generate();
        assertThat(vendingMachine.getAvailableCodes().size()).isEqualTo(ProductType.values().length);
        assertThat(vendingMachine.getCash().getMaxCoins()).contains(MapEntry.entry(CoinType.POINT_ONE, 20));
    }
}
