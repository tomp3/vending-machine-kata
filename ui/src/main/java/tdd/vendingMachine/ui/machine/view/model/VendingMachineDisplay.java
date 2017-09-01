package tdd.vendingMachine.ui.machine.view.model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class VendingMachineDisplay implements Serializable {
    private StringProperty text = new SimpleStringProperty();

    public String getText() {
        return text.getValue();
    }

    public void setText(String text) {
        this.text.setValue(text);
    }

    public StringProperty getTextProperty() {
        return text;
    }


}
