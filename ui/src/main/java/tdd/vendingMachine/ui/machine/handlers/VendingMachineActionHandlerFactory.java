package tdd.vendingMachine.ui.machine.handlers;

import com.google.common.collect.Maps;
import tdd.vendingMachine.common.factory.Factory;
import tdd.vendingMachine.ui.machine.actions.VendingMachineAction;
import tdd.vendingMachine.ui.machine.actions.VendingMachineActionType;

import java.util.Map;

/**
 * Vending machine action handlers' factory.
 */
public final class VendingMachineActionHandlerFactory implements Factory<VendingMachineAction, VendingMachineActionHandler> {

    /**
     * Vending machine action handler factory instance.
     */
    private static final VendingMachineActionHandlerFactory INSTANCE = new VendingMachineActionHandlerFactory();

    /**
     * Vending machine action handler's cache (helps simulating handlers being used in a similar manner to stateless beans).
     */
    private static final Map<VendingMachineActionType, VendingMachineActionHandler> HANDLER_MAP = Maps.newHashMap();

    /**
     * Null handler (null object pattern) - used while no handler is defined for given {@link VendingMachineActionType action type}.
     */
    private static final VendingMachineActionHandler<Void> NULL_HANDLER = () -> null;

    /**
     * Default constructor.
     */
    private VendingMachineActionHandlerFactory() {
    }

    /**
     * Returns instance of the factory.
     *
     * @return instance of the factory.
     */
    public static VendingMachineActionHandlerFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Creates an instance of the action handler.
     *
     * @param action action.
     * @return action handler.
     */
    public VendingMachineActionHandler create(VendingMachineAction action) {
        VendingMachineActionType actionType = action.getActionType();
        switch (actionType) {
            case CANCEL_PRESSED: {
                return new CancelActionHandler(action);
            }
            case OK_PRESSED: {
                return new OkActionHandler(action);
            }
            case INSERT_COIN: {
                return new InsertCoinActionHandler(action);
            }
            case NUMBER_BUTTON_PRESSED: {
                return new NumberButtonPressedActionHandler(action);
            }
            case COIN_TRAY_EMPTIED: {
                return new CoinTrayEmptiedActionHandler(action);
            } case PRODUCT_TRAY_EMPTIED: {
                return new ProductTrayEmptiedActionHandler(action);
            }
            default: {
                return NULL_HANDLER;
            }
        }
    }
}
