package tdd.vendingMachine.businessLogic.machine;

import tdd.vendingMachine.businessLogic.machine.exception.UnavailableShelfCodeException;
import tdd.vendingMachine.model.machine.VendingMachine;
import tdd.vendingMachine.model.machine.VendingMachineCash;
import tdd.vendingMachine.model.machine.VendingMachineShelf;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Default {@link VendingMachineService} implementation.
 */
class VendingMachineServiceImpl implements VendingMachineService {

    private static final String CODE_ALREADY_IN_USE_EXC_MSG = "Code %s is already in use.";
    private static final String CODE_NOT_SUPPORTED_EXC_MSG = "Vending machine does not support the given code (%s).";
    private static final String ALL_CODES_IN_USE_EXC_MSG = "All supported codes are already in use. Cannot add another shelf.";

    /**
     * {@inheritDoc}
     */
    @Override
    public VendingMachine createVendingMachine(VendingMachineCash cash, List<String> availableCodes, Map<String, VendingMachineShelf> shelves) {
        return new VendingMachine(cash, availableCodes, shelves);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addShelve(VendingMachine vendingMachine, String code, VendingMachineShelf shelf) throws UnavailableShelfCodeException {
        if (!vendingMachine.getAvailableCodes().contains(code)) {
            throw new UnavailableShelfCodeException(String.format(CODE_NOT_SUPPORTED_EXC_MSG, code));
        }
        VendingMachineShelf machineShelf = vendingMachine.getShelves().get(code);
        if (machineShelf != null) {
            throw new UnavailableShelfCodeException(String.format(CODE_ALREADY_IN_USE_EXC_MSG, code));
        }
        vendingMachine.getShelves().put(code, shelf);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String addShelve(VendingMachine vendingMachine, VendingMachineShelf shelf) throws UnavailableShelfCodeException {
        Optional<String> first = vendingMachine.getAvailableCodes().stream().filter(code -> vendingMachine.getShelves().get(code) == null).findFirst();
        if (!first.isPresent()) {
            throw new UnavailableShelfCodeException(ALL_CODES_IN_USE_EXC_MSG);
        }
        String code = first.get();
        vendingMachine.getShelves().put(code, shelf);
        return code;
    }
}
