package tdd.vendingMachine.common.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tdd.vendingMachine.common.properties.exception.PropertyRequiredException;

import java.util.Properties;

/**
 * Abstract implementation of {@link PropertyManager}.
 * Provides methods reading properties and writing them in-memory.
 *
 * @param <T> property key type.
 */
public abstract class PropertyManagerAbstr<T extends PropertyKey> implements PropertyManager<T> {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyManagerAbstr.class);

    /**
     * Exception message used when required property file could not be read.
     */
    private static final String PROPERTY_REQUIRED_EXCEPTION_MESSAGE = "Could not read required properties - %s";

    /**
     * Exception message used when reading property file produced an exception.
     */
    private static final String ERROR_WHILE_READING_PROPERTIES_MSG = "An error occurred during property file read: %s";

    /**
     * Properties.
     */
    protected Properties properties;

    /**
     * Default constructor, loads properties.
     */
    public PropertyManagerAbstr() {
        loadProperties();
    }

    /**
     * Returns property file path.
     *
     * @return property file path.
     */
    protected abstract String getPropertyPath();

    /**
     * Returns the information if this property file is required for the application to run.
     *
     * @return {@code true} if this property file is required for the application to run, {@code false} otherwise.
     */
    protected abstract boolean isRequired();

    /**
     * Loads properties from file.
     * If properties' files could not be read, logs the error
     * and in case the property file is required for the application to run - throws an exception.
     */
    private void loadProperties() {
        properties = new Properties();
        try {
            properties.load(PropertyManagerAbstr.class.getClassLoader().getResourceAsStream(getPropertyPath()));
        } catch (Exception e) {
            LOGGER.error(String.format(ERROR_WHILE_READING_PROPERTIES_MSG, e.getMessage()), e);
            if (isRequired()) {
                throw new PropertyRequiredException(String.format(PROPERTY_REQUIRED_EXCEPTION_MESSAGE, getPropertyPath()), e);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getProperty(T key) {
        String property = properties.getProperty(key.getKey());
        if (Boolean.TRUE.equals(key.isRequired()) && property == null) {
            throw new PropertyRequiredException(key);
        }
        return property;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setProperty(T key, String value) {
        properties.setProperty(key.getKey(), value);
    }
}
