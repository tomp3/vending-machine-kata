package tdd.vendingMachine.businessLogic.machine.exception;

/**
 * Exception thrown in case product is unavailable.
 */
public class ProductUnavailableException extends Exception {
    /**
     * Constructor assigning the exception message.
     *
     * @param message message.
     */
    public ProductUnavailableException(String message) {
        super(message);
    }

    /**
     * Constructor assigning the exception message and exception cause.
     *
     * @param message message.
     * @param cause   cause.
     */
    public ProductUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
