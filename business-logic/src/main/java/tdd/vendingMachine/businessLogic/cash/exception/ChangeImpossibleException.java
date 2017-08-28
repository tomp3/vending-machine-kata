package tdd.vendingMachine.businessLogic.cash.exception;

/**
 * Exception thrown in case there is no possible change combination.
 */
public class ChangeImpossibleException extends Exception {
    /**
     * Default exception message.
     */
    private static final String DEFAULT_MESSAGE = "Not enough coins to give change!";

    /**
     * Constructor creating an instance of an exception with the default message.
     */
    public ChangeImpossibleException() {
        super(DEFAULT_MESSAGE);
    }
}
