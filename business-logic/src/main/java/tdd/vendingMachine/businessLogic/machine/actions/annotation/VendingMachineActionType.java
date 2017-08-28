package tdd.vendingMachine.businessLogic.machine.actions.annotation;

import java.lang.annotation.*;

/**
 * Vending machine action type.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.LOCAL_VARIABLE})
public @interface VendingMachineActionType {

    ActionType value();

    enum ActionType {
        INT_DISABLE_DISPOSAL,
        INT_DISPLAY_MESSAGE,
        INT_DISPOSE_PRODUCT,
        INT_GIVE_CHANGE,
        USR_CANCEL,
        USR_INSERT_COIN,
        USR_SELECT_CODE
    }
}
