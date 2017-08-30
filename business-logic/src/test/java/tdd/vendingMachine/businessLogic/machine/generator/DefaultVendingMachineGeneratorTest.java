package tdd.vendingMachine.businessLogic.machine.generator;

import org.assertj.core.data.MapEntry;
import org.junit.Test;
import tdd.vendingMachine.model.common.CoinType;
import tdd.vendingMachine.model.machine.VendingMachine;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link DefaultVendingMachineGenerator} tests.
 */
public class DefaultVendingMachineGeneratorTest {

    VendingMachineGenerator generator = new DefaultVendingMachineGenerator();

    /**
     * Tests {@link DefaultVendingMachineGenerator#generate()} method.
     */
    @Test
    public void testGenerate() {
        VendingMachine vendingMachine = generator.generate();
        assertThat(vendingMachine.getAvailableCodes().size()).isEqualTo(20);
        assertThat(vendingMachine.getCash().getMaxCoins()).contains(MapEntry.entry(CoinType.POINT_ONE, 20));
    }
}