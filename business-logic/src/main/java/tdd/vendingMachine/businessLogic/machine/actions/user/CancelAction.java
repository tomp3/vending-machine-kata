package tdd.vendingMachine.businessLogic.machine.actions.user;

import tdd.vendingMachine.businessLogic.machine.actions.VendingMachineAction;

import java.math.BigDecimal;

/**
 * Action of transaction cancellation.
 * Parameters hold the amount paid by the user.
 */
@FunctionalInterface
public interface CancelAction extends VendingMachineAction<BigDecimal> {
}
