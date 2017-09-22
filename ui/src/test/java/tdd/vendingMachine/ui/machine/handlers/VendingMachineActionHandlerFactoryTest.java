package tdd.vendingMachine.ui.machine.handlers;

import org.junit.Before;
import org.junit.Test;
import tdd.vendingMachine.businessLogic.cash.service.CashService;
import tdd.vendingMachine.businessLogic.cash.service.CoinChangeService;
import tdd.vendingMachine.businessLogic.machine.service.VendingMachineService;
import tdd.vendingMachine.businessLogic.shelf.service.ShelfService;
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
    private VendingMachineActionHandlerFactory testedFactory;

    /**
     * Vending machine.
     */
    private VendingMachine vendingMachine = mock(VendingMachine.class);

    /**
     * Initializes tested object.
     */
    @Before
    public void beforeTests() {
        testedFactory =
            new VendingMachineActionHandlerFactory(VendingMachineService.newVendingMachineService(ShelfService.newShelfService(), CashService.newCashService(
                CoinChangeService.newCoinChangeService())));
    }

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
