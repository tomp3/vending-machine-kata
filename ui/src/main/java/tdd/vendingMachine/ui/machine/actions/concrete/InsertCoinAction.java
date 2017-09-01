package tdd.vendingMachine.ui.machine.actions.concrete;

import tdd.vendingMachine.model.common.CoinType;
import tdd.vendingMachine.ui.machine.actions.VendingMachineActionAbstr;
import tdd.vendingMachine.ui.machine.actions.VendingMachineActionParameters;
import tdd.vendingMachine.ui.machine.actions.VendingMachineActionType;

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
    public InsertCoinAction(VendingMachineActionParameters<CoinType> actionParameters) {
        super(actionParameters);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VendingMachineActionType getActionType() {
        return VendingMachineActionType.INSERT_COIN;
    }
}
