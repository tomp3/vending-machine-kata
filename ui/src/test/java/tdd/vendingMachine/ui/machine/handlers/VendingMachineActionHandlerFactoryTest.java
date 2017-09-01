package tdd.vendingMachine.ui.machine.handlers;

import org.junit.Test;
import tdd.vendingMachine.model.machine.VendingMachine;
import tdd.vendingMachine.ui.machine.actions.VendingMachineAction;
import tdd.vendingMachine.ui.machine.actions.VendingMachineActionParameters;
import tdd.vendingMachine.ui.machine.actions.VendingMachineActionType;
import tdd.vendingMachine.ui.machine.view.model.VendingMachineViewModel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * {@link VendingMachineActionHandlerFactory} test.
 */
public class VendingMachineActionHandlerFactoryTest {

    /**
     * Tested object.
     */
    private VendingMachineActionHandlerFactory testedFactory = VendingMachineActionHandlerFactory.getInstance();

    private VendingMachine vendingMachine = mock(VendingMachine.class);

    /**
     * Tests creation of handlers.
     */
    @Test
    public void testCreate() {
        VendingMachineActionHandler handler = testedFactory.create(
            VendingMachineAction.of(
                VendingMachineActionParameters.of(
                    new VendingMachineViewModel(vendingMachine)), VendingMachineActionType.OK_PRESSED));
        assertThat(handler).isInstanceOf(OkActionHandler.class);
    }
}
