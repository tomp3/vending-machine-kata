package tdd.vendingMachine.businessLogic.machine.actions.internal;

import tdd.vendingMachine.businessLogic.machine.actions.VendingMachineAction;
import tdd.vendingMachine.model.common.CoinType;

import java.util.Map;

/**
 * Action giving the change.
 * Parameters hold the change to be given.
 */
@FunctionalInterface
public interface GiveChangeAction extends VendingMachineAction<Map<CoinType, Integer>> {
}
