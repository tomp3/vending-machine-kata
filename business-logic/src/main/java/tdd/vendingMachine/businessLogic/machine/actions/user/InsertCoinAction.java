package tdd.vendingMachine.businessLogic.machine.actions.user;

import tdd.vendingMachine.businessLogic.machine.actions.VendingMachineActionAbstr;
import tdd.vendingMachine.businessLogic.machine.actions.VendingMachineActionType;
import tdd.vendingMachine.model.common.CoinType;

/**
 * Action of coin insertion.
 * Parameters hold the inserted coin.
 */
public class InsertCoinAction extends VendingMachineActionAbstr<CoinType> {
    /**
     * Constructor assigning action parameters.
     *
     * @param actionParameters action parameters.
     */
    public InsertCoinAction(CoinType actionParameters) {
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
