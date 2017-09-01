package tdd.vendingMachine.common.properties;

import java.io.Serializable;

/**
 * Property key interface.
 */
public interface PropertyKey extends Serializable {
    /**
     * Gets property key.
     *
     * @return property key.
     */
    String getKey();

    /**
     * Gets property description.
     *
     * @return property description.
     */
    String getDescription();

    /**
     * Returns information whether the property specified by this key is required or not.
     *
     * @return {@code true} if the property is required, {@code false} otherwise.
     */
    Boolean isRequired();
}
