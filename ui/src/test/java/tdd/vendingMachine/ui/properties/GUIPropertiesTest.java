package tdd.vendingMachine.ui.properties;

import org.junit.Test;
import tdd.vendingMachine.common.properties.PropertyKey;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link GUIProperties} tests.
 */
public class GUIPropertiesTest {

    /**
     * Tests {@link GUIProperties#getProperty(PropertyKey)} method.
     */
    @Test
    public void testGetProperty() {
        GUIProperties props = GUIProperties.getInstance();
        assertThat(props.getProperty(GUIProperties.PropertyKeys.MAIN_FXML_PATH)).isNotEmpty();
    }
}
