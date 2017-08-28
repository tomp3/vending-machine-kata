package tdd.vendingMachine.businessLogic.machine.actions.user;

import tdd.vendingMachine.businessLogic.machine.actions.VendingMachineAction;
import tdd.vendingMachine.model.common.CoinType;

/**
 * Action of coin insertion.
 * Parameters hold the inserted coin.
 */
@FunctionalInterface
public interface InsertCoinAction extends VendingMachineAction<CoinType> {
}
