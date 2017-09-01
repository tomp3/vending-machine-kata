package tdd.vendingMachine.businessLogic.machine.service;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tdd.vendingMachine.businessLogic.cash.exception.ChangeImpossibleException;
import tdd.vendingMachine.businessLogic.cash.exception.CoinInsertionImpossibleException;
import tdd.vendingMachine.businessLogic.cash.service.CashService;
import tdd.vendingMachine.businessLogic.machine.exception.ProductUnavailableException;
import tdd.vendingMachine.businessLogic.machine.exception.UnavailableShelfCodeException;
import tdd.vendingMachine.businessLogic.shelf.service.ShelfService;
import tdd.vendingMachine.model.common.CoinType;
import tdd.vendingMachine.model.common.util.CoinUtils;
import tdd.vendingMachine.model.machine.VendingMachine;
import tdd.vendingMachine.model.machine.VendingMachineCash;
import tdd.vendingMachine.model.machine.VendingMachineShelf;
import tdd.vendingMachine.model.product.Product;
import tdd.vendingMachine.model.product.util.ProductUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Default {@link VendingMachineService} implementation.
 */
class VendingMachineServiceImpl implements VendingMachineService {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(VendingMachineServiceImpl.class);

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
     * Specified product unavailable exception message.
     */
    private static final String SPECIFIED_PRODUCT_UNAVAILABLE_EXC_MSG = "Specified product unavailable (code: %s)";

    /**
     * Shelf service.
     */
    private final ShelfService shelfService = ShelfService.newShelfService();

    /**
     * Cash service.
     */
    private final CashService cashService = CashService.newCashService();

    /**
     * {@inheritDoc}
     */
    @Override
    public VendingMachine createVendingMachine(VendingMachineCash cash, List<String> availableCodes, Map<String, VendingMachineShelf> shelves) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Cash coins: ");
            LOGGER.debug(System.lineSeparator() + CoinUtils.coinsToString(cash.getCoins()));
            LOGGER.debug("Products: ");
            LOGGER.debug(System.lineSeparator() + ProductUtils.productsToString(shelves));
        }
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

    /**
     * Dispenses VendingMachine product stored on the shelf of the given code.
     *
     * @param vendingMachine vending machine.
     * @param code           product code.
     * @return dispensed product.
     * @throws ProductUnavailableException thrown in case product is unavailable (e.g. empty shelf).
     */
    private Product dispenseProduct(VendingMachine vendingMachine, String code) throws ProductUnavailableException {
        if (BooleanUtils.isFalse(vendingMachine.getAvailableCodes().contains(code))) {
            throw new ProductUnavailableException(String.format(SPECIFIED_PRODUCT_UNAVAILABLE_EXC_MSG, code),
                new UnavailableShelfCodeException(String.format(CODE_NOT_SUPPORTED_EXC_MSG, code)));
        }
        if (BooleanUtils.isFalse(isProductAvailable(vendingMachine, code))) {
            throw new ProductUnavailableException(String.format(SPECIFIED_PRODUCT_UNAVAILABLE_EXC_MSG, code));
        }
        return shelfService.dispenseProduct(vendingMachine.getShelves().get(code));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isProductAvailable(VendingMachine vendingMachine, String code) {
        return vendingMachine.getAvailableCodes().contains(code) &&
            vendingMachine.getShelves().get(code).peekProduct().isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertUserCoin(VendingMachine vendingMachine, CoinType coin) throws CoinInsertionImpossibleException {
        VendingMachineCash cash = vendingMachine.getCash();
        Integer coinCount = cash.getUserInsertedMoney().get(coin);
        coinCount = coinCount == null ? 1 : coinCount + 1;
        cash.getUserInsertedMoney().put(coin, coinCount);
        cashService.insertCoins(cash, Collections.singletonMap(coin, 1));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BigDecimal getInsertedMoneyAmount(VendingMachine vendingMachine) {
        return cashService.getUserInsertedAmountSum(vendingMachine.getCash());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Product, Map<CoinType, Integer>> giveProduct(VendingMachine vendingMachine, String code)
        throws ChangeImpossibleException, ProductUnavailableException {
        BigDecimal userInsertedAmount = cashService.getUserInsertedAmountSum(vendingMachine.getCash());
        VendingMachineShelf shelf = vendingMachine.getShelves().get(code);
        BigDecimal productPrice = shelf.getProductPrice();
        Map<CoinType, Integer> preparedChange = cashService.prepareChange(vendingMachine.getCash(), productPrice, userInsertedAmount);
        Map<CoinType, Integer> change = cashService.giveCoins(vendingMachine.getCash(), preparedChange);
        Product product = dispenseProduct(vendingMachine, code);


        // Add change to cash tray.
        addToCashTray(vendingMachine, change);
        // Add product to product tray.
        vendingMachine.getProductTray().getProducts().add(product);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Cash coins: ");
            LOGGER.debug(System.lineSeparator() + CoinUtils.coinsToString(vendingMachine.getCash().getCoins()));
            LOGGER.debug("Products: ");
            LOGGER.debug(System.lineSeparator() + ProductUtils.productsToString(vendingMachine.getShelves()));
        }
        return ImmutablePair.of(product, change);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<CoinType, Integer> returnUserMoney(VendingMachine vendingMachine) {
        Map<CoinType, Integer> userMoney = vendingMachine.getCash().getUserInsertedMoney();
        Map<CoinType, Integer> userCoins = cashService.giveCoins(vendingMachine.getCash(), userMoney);
        addToCashTray(vendingMachine, userCoins);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Cash coins: ");
            LOGGER.debug(System.lineSeparator() + CoinUtils.coinsToString(vendingMachine.getCash().getCoins()));
        }
        return userCoins;
    }

    /**
     * Adds coins to vending machine cash tray.
     *
     * @param vendingMachine vending machine.
     * @param coins          coins to be added to the cash tray.
     */
    private void addToCashTray(VendingMachine vendingMachine, Map<CoinType, Integer> coins) {
        coins.forEach((k, v) -> {
            Map<CoinType, Integer> tray = vendingMachine.getCashTray().getCoins();
            Integer count = tray.get(k);
            count = count == null ? v : count + v;
            tray.put(k, count);
        });
    }


}
