package tdd.vendingMachine.ui.machine.handlers;

import org.junit.Before;
import org.junit.Test;
import tdd.vendingMachine.ui.machine.actions.VendingMachineAction;
import tdd.vendingMachine.ui.machine.actions.VendingMachineActionParameters;
import tdd.vendingMachine.ui.machine.actions.concrete.NumberButtonPressedAction;
import tdd.vendingMachine.ui.machine.handlers.NumberButtonPressedActionHandler;
import tdd.vendingMachine.ui.machine.handlers.VendingMachineActionHandler;
import tdd.vendingMachine.ui.machine.view.model.VendingMachineDisplay;
import tdd.vendingMachine.ui.machine.view.model.VendingMachineState;
import tdd.vendingMachine.ui.machine.view.model.VendingMachineViewModel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * {@link NumberButtonPressedActionHandler} tests.
 */
public class NumberButtonPressedActionHandlerTest {

    private VendingMachineViewModel vendingMachineViewModel;


    private VendingMachineDisplay display;

    @Before
    public void beforeTest() {
        vendingMachineViewModel = mock(VendingMachineViewModel.class);
        display = new VendingMachineDisplay();

        when(vendingMachineViewModel.getDisplay()).thenReturn(display);
    }

    /**
     * {@link NumberButtonPressedActionHandler#handle()} test.
     */
    @Test
    public void testHandle() {
        String code = "1";
        when(vendingMachineViewModel.getState()).thenReturn(VendingMachineState.IDLE);
        when(vendingMachineViewModel.getSelectedCode()).thenReturn(code);
        VendingMachineAction action = new NumberButtonPressedAction(VendingMachineActionParameters.of(vendingMachineViewModel, code));
        VendingMachineActionHandler handler = new NumberButtonPressedActionHandler(action);
        handler.handle();
        assertThat(display.getText()).isEqualTo(code);
    }
}
