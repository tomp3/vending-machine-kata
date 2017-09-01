package tdd.vendingMachine.businessLogic.machine.generator;

import org.assertj.core.data.MapEntry;
import org.junit.Test;
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
    private VendingMachineGenerator generator = new DefaultVendingMachineGenerator();

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
