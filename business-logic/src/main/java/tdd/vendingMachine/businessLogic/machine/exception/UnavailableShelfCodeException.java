package tdd.vendingMachine.businessLogic.machine.exception;

/**
 * An exception thrown in case unavailable shelf code was specified.
 */
public class UnavailableShelfCodeException extends Exception {
    /**
     * Constructor setting the message of the exception.
     *
     * @param message exception message.
     */
    public UnavailableShelfCodeException(String message) {
        super(message);
    }
}
