package tdd.vendingMachine.common.properties.exception;

import tdd.vendingMachine.common.properties.PropertyKey;

/**
 * Exception thrown in case required property was not found.
 */
public class PropertyRequiredException extends RuntimeException {
    /**
     * Default exception message.
     */
    private static final String EXCEPTION_MESSAGE = "Could not find the required %s (\"%s\") property.";

    /**
     * Constructor preparing the exception message with property key and description.
     *
     * @param propertyKey property key.
     */
    public PropertyRequiredException(PropertyKey propertyKey) {
        super(String.format(EXCEPTION_MESSAGE, propertyKey.getKey(), propertyKey.getDescription()));
    }

    /**
     * Constructor assigning the exception message.
     *
     * @param message exception message.
     */
    public PropertyRequiredException(String message) {
        super(message);
    }
}
