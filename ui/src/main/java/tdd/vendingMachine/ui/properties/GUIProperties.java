package tdd.vendingMachine.ui.properties;

import lombok.Getter;
import tdd.vendingMachine.common.properties.PropertyKey;
import tdd.vendingMachine.common.properties.PropertyManagerAbstr;

/**
 * Util class for storing gui properties.
 */
public final class GUIProperties extends PropertyManagerAbstr<GUIProperties.PropertyKeys> {

    /**
     * GUI Property keys.
     */
    public enum PropertyKeys implements PropertyKey {
        MAIN_FXML_PATH("vending.machine.main.fxml", "Path to the main FXML.", true),
        WINDOW_TITLE("window.title", "Application window title."),
        WINDOW_ICON("window.icon.path", "Application window icon."),
        DISPLAY_INSERT_COINS_TEXT("display.insert.coins.text", "Display insert coins text."),
        COMPONENT_CODE_TEXT_CLASS("component.code.text.class", "Code text filed css class."),
        FULL_OPACITY_CLASS("component.full.opacity.class", "Full opacity css class.");

        @Getter
        private final String key;
        @Getter
        private final String description;
        private final Boolean required;

        PropertyKeys(String key, String description) {
            this.key = key;
            this.description = description;
            this.required = Boolean.FALSE;
        }

        PropertyKeys(String key, String description, Boolean required) {
            this.key = key;
            this.description = description;
            this.required = required;
        }

        @Override
        public Boolean isRequired() {
            return required;
        }
    }

    /**
     * {@link GUIProperties} instance.
     */
    private static final GUIProperties INSTANCE = new GUIProperties();

    /**
     * Default constructor calling constructor of super class, which reads properties from file.
     */
    private GUIProperties() {
        super();
    }

    /**
     * Returns {@link GUIProperties} instance.
     *
     * @return {@link GUIProperties} instance.
     */
    public static GUIProperties getInstance() {
        return INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPropertyPath() {
        return "gui.properties";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isRequired() {
        return true;
    }
}
