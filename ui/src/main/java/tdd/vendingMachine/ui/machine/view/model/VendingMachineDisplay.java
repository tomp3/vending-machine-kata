package tdd.vendingMachine.ui.machine.view.model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

/**
 * Object representing vending machine display.
 */
public class VendingMachineDisplay implements Serializable {
    /**
     * Display text property.
     */
    private StringProperty text = new SimpleStringProperty();

    /**
     * Gets displayed text.
     *
     * @return displayed text.
     */
    public String getText() {
        return text.getValue();
    }

    /**
     * Sets displayed text.
     *
     * @param text text to be displayed.
     */
    public void setText(String text) {
        this.text.setValue(text);
    }

    /**
     * Returns display text property.
     *
     * @return dispaly text property.
     */
    public StringProperty getTextProperty() {
        return text;
    }


}
