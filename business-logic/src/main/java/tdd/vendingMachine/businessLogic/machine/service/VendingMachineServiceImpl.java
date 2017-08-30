package tdd.vendingMachine.businessLogic.machine.service;

import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;
import tdd.vendingMachine.businessLogic.machine.exception.UnavailableShelfCodeException;
import tdd.vendingMachine.common.number.NumberUtils;
import tdd.vendingMachine.model.machine.VendingMachine;
import tdd.vendingMachine.model.machine.VendingMachineCash;
import tdd.vendingMachine.model.machine.VendingMachineShelf;
import tdd.vendingMachine.model.product.ProductType;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Default {@link VendingMachineService} implementation.
 */
class VendingMachineServiceImpl implements VendingMachineService {

    /**
     * Code already in use exception message.
     */
    private static final String CODE_ALREADY_IN_USE_EXC_MSG = "Code %s is already in use.";
    /**
     * Code not supported exception message.
     */
    private static final String CODE_NOT_SUPPORTED_EXC_MSG = "Vending machine does not support the given code (%s).";
    /**
     * All codes in use exception message.
     */
    private static final String ALL_CODES_IN_USE_EXC_MSG = "All supported codes are already in use. Cannot add another shelf.";

    /**
     * Not enough codes available exception message.
     */
    private static final String CODES_UNAVAILABLE_EXC_MSG = "Not enough codes available.";

    /**
     * {@inheritDoc}
     */
    @Override
    public VendingMachine createVendingMachine(VendingMachineCash cash, List<String> availableCodes, Map<String, VendingMachineShelf> shelves) {
        return new VendingMachine(cash, availableCodes, shelves != null ? shelves : Maps.newHashMap());
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, VendingMachineShelf> getUsedShelves(VendingMachine vendingMachine) {
        return vendingMachine.getShelves().entrySet().stream().filter(e -> e.getValue() != null)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
