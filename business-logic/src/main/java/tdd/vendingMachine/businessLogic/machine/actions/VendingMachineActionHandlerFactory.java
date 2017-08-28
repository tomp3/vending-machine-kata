package tdd.vendingMachine.businessLogic.machine.actions;

import com.google.common.collect.Maps;
import tdd.vendingMachine.businessLogic.machine.actions.annotation.VendingMachineActionType;
import tdd.vendingMachine.businessLogic.machine.actions.internal.DisableDisposalAction;
import tdd.vendingMachine.businessLogic.machine.actions.internal.DisplayMessageAction;
import tdd.vendingMachine.common.factory.Factory;

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
    private static final Map<VendingMachineActionType.ActionType, VendingMachineActionHandler> HANDLER_MAP = Maps.newHashMap();

    /**
     * Null handler (null object pattern) - used while no handler is defined for given {@link VendingMachineActionType.ActionType action type}.
     */
    private static final VendingMachineActionHandler<Void, Void> NULL_HANDLER = v -> null;
    /**
     * Disable disposal action handler instance.
     */
    @VendingMachineActionType(VendingMachineActionType.ActionType.INT_DISABLE_DISPOSAL)
    private static final VendingMachineActionHandler<DisplayMessageAction, DisableDisposalAction> DISABLE_DISPOSAL_ACTION_HANDLER = (action) ->
        (DisableDisposalAction) action::getActionParameters;

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
        VendingMachineActionType.ActionType actionType = findActionType(action);
        VendingMachineActionHandler handler = HANDLER_MAP.get(actionType);
        if (handler != null) {
            return handler;
        }

        switch (actionType) {
            case INT_DISABLE_DISPOSAL: {
                handler = DISABLE_DISPOSAL_ACTION_HANDLER;
                break;
            }
            case INT_DISPLAY_MESSAGE: {
                handler = DISABLE_DISPOSAL_ACTION_HANDLER;
                break;
            }
            case INT_DISPOSE_PRODUCT: {
                handler = DISABLE_DISPOSAL_ACTION_HANDLER;
                break;
            }
            case INT_GIVE_CHANGE: {
                handler = DISABLE_DISPOSAL_ACTION_HANDLER;
                break;
            }
            case USR_CANCEL: {
                handler = DISABLE_DISPOSAL_ACTION_HANDLER;
                break;
            }
            case USR_INSERT_COIN: {
                handler = DISABLE_DISPOSAL_ACTION_HANDLER;
                break;
            }
            case USR_SELECT_CODE: {
                handler = DISABLE_DISPOSAL_ACTION_HANDLER;
                break;
            }
            default: {
                handler = NULL_HANDLER;
                break;
            }
        }
        HANDLER_MAP.put(actionType, handler);
        return handler;
    }

    /**
     * Gets action type from it's class annotation.
     *
     * @param action action.
     * @return action type.
     */
    private VendingMachineActionType.ActionType findActionType(VendingMachineAction action) {
        return action.getClass().getDeclaredAnnotation(VendingMachineActionType.class).value();
    }
}
