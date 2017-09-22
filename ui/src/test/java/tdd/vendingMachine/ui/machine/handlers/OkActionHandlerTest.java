package tdd.vendingMachine.ui.machine.handlers;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.junit.Before;
import org.junit.Test;
import tdd.vendingMachine.businessLogic.machine.service.VendingMachineService;
import tdd.vendingMachine.model.machine.VendingMachine;
import tdd.vendingMachine.model.machine.VendingMachineShelf;
import tdd.vendingMachine.model.product.Product;
import tdd.vendingMachine.model.product.ProductType;
import tdd.vendingMachine.ui.machine.actions.VendingMachineAction;
import tdd.vendingMachine.ui.machine.actions.VendingMachineActionParameters;
import tdd.vendingMachine.ui.machine.actions.VendingMachineActionType;
import tdd.vendingMachine.ui.machine.view.model.VendingMachineDisplay;
import tdd.vendingMachine.ui.machine.view.model.VendingMachineState;
import tdd.vendingMachine.ui.machine.view.model.VendingMachineViewModel;
import tdd.vendingMachine.ui.properties.GUIProperties;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * {@link OkActionHandler} tests.
 */
public class OkActionHandlerTest {

    /**
     * Action handled by the action handler.
     */
    private VendingMachineAction action;

    /**
     * Vending machine service.
     */
    private VendingMachineService vendingMachineService = VendingMachineService.newVendingMachineService();

    /**
     * Vending machine view model.
     */
    private VendingMachineViewModel vendingMachineViewModel;

    /**
     * Vending machine.
     */
    private VendingMachine vendingMachine;

    /**
     * Vending machine display.
     */
    private VendingMachineDisplay display;

    /**
     * Tested object instance.
     */
    private OkActionHandler testedObject;

    /**
     * Initializes and mocks required attributes.
     */
    @Before
    public void beforeTest() {
        vendingMachineViewModel = mock(VendingMachineViewModel.class);
        vendingMachine = mock(VendingMachine.class);
        display = new VendingMachineDisplay();
        action = VendingMachineAction.of(
            VendingMachineActionParameters.of(vendingMachineViewModel),
            VendingMachineActionType.OK_PRESSED
        );
        when(vendingMachineViewModel.getDisplay()).thenReturn(display);
        when(vendingMachineViewModel.getVendingMachine()).thenReturn(vendingMachine);
        testedObject = new OkActionHandler(action, vendingMachineService);
    }

    /**
     * {@link OkActionHandler#handle()} test.
     */
    @Test
    public void testHandle() {
        String code = "01";
        Product product = new Product(ProductType.ENERGY_DRINK_0_33_CAN);
        VendingMachineShelf shelf = new VendingMachineShelf(ProductType.ENERGY_DRINK_0_33_CAN, 10, BigDecimal.ONE);
        shelf.addProduct(product);
        when(vendingMachineViewModel.getSelectedCode()).thenReturn(code);
        when(vendingMachineViewModel.getState()).thenReturn(VendingMachineState.SELECTION_STARTED);
        when(vendingMachine.getAvailableCodes()).thenReturn(ImmutableList.of(code));
        when(vendingMachine.getShelves()).thenReturn(
            ImmutableMap.of(code, shelf)
        );

        testedObject.handle();
        assertThat(display.getText())
            .isEqualTo(String.format(GUIProperties.getInstance().getProperty(GUIProperties.PropertyKeys.DISPLAY_INSERT_COINS_TEXT), "1.0"));
    }
}
