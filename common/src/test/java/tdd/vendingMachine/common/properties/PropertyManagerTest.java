package tdd.vendingMachine.common.properties;

import lombok.Getter;
import org.junit.Test;
import tdd.vendingMachine.common.properties.exception.PropertyRequiredException;

import static org.assertj.core.api.Assertions.*;

/**
 * {@link PropertyManager} test.
 */
public class PropertyManagerTest {

    /**
     * Test property path.
     */
    private static final String PROPERTY_PATH = "test.properties";

    /**
     * Test enum implementing {@link PropertyKey}.
     */
    private enum PropertyKeys implements PropertyKey {
        DATE_PATTERN("date.pattern", "Date pattern (day)", true),
        DATE_PATTERN_SECONDS("date.pattern.seconds", "Date pattern (hours, mins, secs)"),
        NON_EXISTING("non.existing", "Non-existing property"),
        NON_EXISTING_REQUIRED("non.existing.required", "Non-existing required property", true);;

        /**
         * Key.
         */
        @Getter
        private final String key;
        /**
         * Description.
         */
        @Getter
        private final String description;
        /**
         * Required flag.
         */
        private final Boolean required;

        /**
         * Constructor assigning given key and description, required is set to {@link Boolean#FALSE}.
         *
         * @param key         key.
         * @param description description.
         */
        PropertyKeys(String key, String description) {
            this.key = key;
            this.description = description;
            this.required = Boolean.FALSE;
        }

        /**
         * Constructor assigning given key, description and required .
         *
         * @param key         key.
         * @param description description.
         * @param required    required.
         */
        PropertyKeys(String key, String description, Boolean required) {
            this.key = key;
            this.description = description;
            this.required = required;
        }

        /**
         * Returns required flag.
         *
         * @return requierd flag.
         */
        public Boolean isRequired() {
            return required;
        }
    }


    /**
     * Tested object.
     */
    private PropertyManager<PropertyKeys> propertyManager;

    /**
     * Tests load properties method.
     */
    @Test
    public void testLoadProperties() {
        propertyManager = new PropertyManagerAbstr<PropertyKeys>() {
            @Override
            protected String getPropertyPath() {
                return PROPERTY_PATH;
            }

            @Override
            protected boolean isRequired() {
                return false;
            }
        };
        assertThat(propertyManager.getProperty(PropertyKeys.DATE_PATTERN)).isEqualTo("dd-MM-yyyy");

        assertThatCode(() -> new PropertyManagerAbstr<PropertyKeys>() {
            @Override
            protected String getPropertyPath() {
                return "non-existing/path.properties";
            }

            @Override
            protected boolean isRequired() {
                return false;
            }
        }).doesNotThrowAnyException();

        assertThatThrownBy(() -> new PropertyManagerAbstr<PropertyKeys>() {
            @Override
            protected String getPropertyPath() {
                return "non-existing/path.properties";
            }

            @Override
            protected boolean isRequired() {
                return true;
            }
        });
    }

    @Test
    public void testGetProperty() {
        propertyManager = new PropertyManagerAbstr<PropertyKeys>() {
            @Override
            protected String getPropertyPath() {
                return PROPERTY_PATH;
            }

            @Override
            protected boolean isRequired() {
                return false;
            }
        };

        assertThatCode(() -> propertyManager.getProperty(PropertyKeys.NON_EXISTING)).doesNotThrowAnyException();
        assertThatThrownBy(() -> propertyManager.getProperty(PropertyKeys.NON_EXISTING_REQUIRED)).isInstanceOf(PropertyRequiredException.class);

    }
}
