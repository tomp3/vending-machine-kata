package tdd.vendingMachine.businessLogic.machine.service;

import org.apache.commons.lang3.tuple.Pair;
import tdd.vendingMachine.businessLogic.cash.exception.ChangeImpossibleException;
import tdd.vendingMachine.businessLogic.cash.exception.CoinInsertionImpossibleException;
import tdd.vendingMachine.businessLogic.cash.service.CashService;
import tdd.vendingMachine.businessLogic.machine.exception.ProductUnavailableException;
import tdd.vendingMachine.businessLogic.machine.exception.UnavailableShelfCodeException;
import tdd.vendingMachine.businessLogic.shelf.service.ShelfService;
import tdd.vendingMachine.model.common.CoinType;
import tdd.vendingMachine.model.machine.VendingMachine;
import tdd.vendingMachine.model.machine.VendingMachineCash;
import tdd.vendingMachine.model.machine.VendingMachineShelf;
import tdd.vendingMachine.model.product.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Service providing methods for using vendhing machine.
 */
public interface VendingMachineService {
    /**
     * Creates the instance of the service using it's default implementation defined in {@link VendingMachineServiceImpl}.
     * Assigns shelf service and cash service used by the vending machine service.
     *
     * @param cashService  cash service instance.
     * @param shelfService shelf service instance.
     * @return service instance.
     */
    static VendingMachineService newVendingMachineService(ShelfService shelfService, CashService cashService) {
        return new VendingMachineServiceImpl(shelfService, cashService);
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

    /**
     * Performs validation whether the product of the given code is available or not
     *
     * @param vendingMachine vending machine.
     * @param code           product code.
     * @return {@code true} if product identified by the given code is available, {@code false} otherwise.
     */
    boolean isProductAvailable(VendingMachine vendingMachine, String code);

    /**
     * Inserts concrete coin to the vending machine cash.
     *
     * @param vendingMachine vending machine.
     * @param coin           inserted coin.
     */
    void insertUserCoin(VendingMachine vendingMachine, CoinType coin) throws CoinInsertionImpossibleException;

    /**
     * Returns inserted money sum amount in form of {@link BigDecimal}.
     *
     * @param vendingMachine vending machine.
     * @return inserted money sum amount in form of {@link BigDecimal}.
     */
    BigDecimal getInsertedMoneyAmount(VendingMachine vendingMachine);

    /**
     * Disposes product from the vending machine giving the change.
     * Returns pair containing both change and disposed product.
     * Dispenses product and change to the trays.
     *
     * @param vendingMachine vending machine.
     * @param code           selected product code.
     * @return given product and change coins.
     * @throws ChangeImpossibleException   exception thrown in case change cannot be given.
     * @throws ProductUnavailableException thrown in case product is unavailable and cannot be given.
     */
    Pair<Product, Map<CoinType, Integer>> giveProduct(VendingMachine vendingMachine, String code)
        throws ChangeImpossibleException, ProductUnavailableException;

    /**
     * Returns user inserted money. Dispenses coins to the vending machine tray.
     *
     * @param vendingMachine vending machine.
     * @return returned user inserted money.
     */
    Map<CoinType, Integer> returnUserMoney(VendingMachine vendingMachine);
}
