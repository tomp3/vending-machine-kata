package tdd.vendingMachine.businessLogic.machine.actions;

import org.junit.Test;
import tdd.vendingMachine.businessLogic.machine.actions.internal.DisableDisposalAction;
import tdd.vendingMachine.businessLogic.machine.actions.internal.DisplayMessageAction;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link VendingMachineActionHandlerFactory} test.
 */
public class VendingMachineActionHandlerFactoryTest {

    /**
     * Tested object.
     */
    private VendingMachineActionHandlerFactory testedFactory = VendingMachineActionHandlerFactory.getInstance();

    /**
     * Tests creation of handlers.
     */
    @Test
    public void testCreate() {
        final VendingMachineAction vendingMachineAction = new DisableDisposalAction("Disposal disabled.");

        VendingMachineActionHandler handler = testedFactory.create(vendingMachineAction);
        assertThat(handler.handle(vendingMachineAction)).isInstanceOf(DisplayMessageAction.class);
    }
}
