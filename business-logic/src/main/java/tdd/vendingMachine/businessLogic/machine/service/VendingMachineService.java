package tdd.vendingMachine.businessLogic.machine.service;

import tdd.vendingMachine.businessLogic.machine.exception.UnavailableShelfCodeException;
import tdd.vendingMachine.model.machine.VendingMachine;
import tdd.vendingMachine.model.machine.VendingMachineCash;
import tdd.vendingMachine.model.machine.VendingMachineShelf;

import java.util.List;
import java.util.Map;

/**
 * Service providing methods for using vendhing machine.
 */
public interface VendingMachineService {
    /**
     * Creates the instance of the service using it's default implementation defined in {@link VendingMachineServiceImpl}.
     *
     * @return service instance.
     */
    static VendingMachineService newVendingMachineService() {
        return new VendingMachineServiceImpl();
    }

    /**
     * Creates vending machine instance with the given cash and shelves.
     *
     * @param cash           vending machine cash.
     * @param availableCodes codes supported by the machine.
     * @param shelves        vending machine shelves.
     * @return vending machine instance.
     */
    VendingMachine createVendingMachine(VendingMachineCash cash, List<String> availableCodes, Map<String, VendingMachineShelf> shelves);

    /**
     * Adds given shelf to the vending machine assigning given code.
     *
     * @param vendingMachine vending machine.
     * @param code           product / shelf code.
     * @param shelf          vending machine shelf.
     * @throws UnavailableShelfCodeException exception thrown in case given code is unavailable (already taken or machine does not support such code).
     */
    void addShelve(VendingMachine vendingMachine, String code, VendingMachineShelf shelf) throws UnavailableShelfCodeException;

    /**
     * Adds given shelf to the vending machine assigning the first unused, supported code.
     * Returns the code assigned to the given shelf.
     *
     * @param vendingMachine vending machine.
     * @param shelf          vending machine shelf.
     * @return code assigned to the given shelf.
     * @throws UnavailableShelfCodeException exception thrown in case all supported codes are in use.
     */
    String addShelve(VendingMachine vendingMachine, VendingMachineShelf shelf) throws UnavailableShelfCodeException;

    /**
     * Returns shelves used by the given vending machine.
     *
     * @return shelves used by the given vending machine.
     */
    Map<String, VendingMachineShelf> getUsedShelves(VendingMachine vendingMachine);
}
