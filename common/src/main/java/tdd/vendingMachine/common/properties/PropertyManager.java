package tdd.vendingMachine.common.properties;

import java.io.Serializable;

/**
 * Object responsible for accessing and holding properties from *.properties files.
 *
 * @param <T>
 */
public interface PropertyManager<T extends PropertyKey> extends Serializable {
    /**
     * Returns property value for the given key.
     * Throws {@link tdd.vendingMachine.common.properties.exception.PropertyRequiredException} runtime exception
     * in case the property was not found but is required.
     *
     * @param key property key.
     * @return property value for the given key.
     */
    String getProperty(T key);

    /**
     * Sets the in-memory property value for the given key.
     * All values set that way are lost after the application shutdown.
     *
     * @param key   property key.
     * @param value property value.
     */
    void setProperty(T key, String value);

}
