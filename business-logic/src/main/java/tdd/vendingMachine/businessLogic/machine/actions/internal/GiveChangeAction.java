package tdd.vendingMachine.businessLogic.machine.actions.internal;

import tdd.vendingMachine.businessLogic.machine.actions.VendingMachineActionAbstr;
import tdd.vendingMachine.businessLogic.machine.actions.VendingMachineActionType;
import tdd.vendingMachine.model.common.CoinType;

import java.util.Map;

/**
 * Action giving the change.
 * Parameters hold the change to be given.
 */
public class GiveChangeAction extends VendingMachineActionAbstr<Map<CoinType, Integer>> {
    /**
     * Constructor assigning action parameters.
     *
     * @param actionParameters action parameters.
     */
    public GiveChangeAction(Map<CoinType, Integer> actionParameters) {
        super(actionParameters);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VendingMachineActionType getActionType() {
        return null;
    }
}
