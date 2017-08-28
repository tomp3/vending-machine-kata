package tdd.vendingMachine.businessLogic.machine.actions;

/**
 * Vending machine action type.
 */
public enum VendingMachineActionType {
    INT_DISABLE_DISPOSAL,
    INT_DISPLAY_MESSAGE,
    INT_DISPOSE_PRODUCT,
    INT_GIVE_CHANGE,
    USR_CANCEL,
    USR_INSERT_COIN,
    USR_SELECT_CODE
}
