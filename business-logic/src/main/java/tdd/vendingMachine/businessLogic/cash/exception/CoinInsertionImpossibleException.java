package tdd.vendingMachine.businessLogic.cash.exception;

/**
 * Exception thrown in case coins cannot be inserted to the cash.
 */
public class CoinInsertionImpossibleException extends Exception {

    private static final String EXCEPTION_MESSAGE = "Not enough free coin slots.";

    /**
     * Constructor assigning default exception message as defined in {@link #EXCEPTION_MESSAGE}.
     */
    public CoinInsertionImpossibleException() {
        super(EXCEPTION_MESSAGE);
    }

    /**
     * Constructor assigning the exception message.
     *
     * @param message exception message.
     */
    public CoinInsertionImpossibleException(String message) {
        super(message);
    }
}
