package tdd.vendingMachine.ui.machine;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import org.apache.commons.lang3.mutable.MutableInt;
import tdd.vendingMachine.model.machine.VendingMachineShelf;
import tdd.vendingMachine.ui.machine.component.ShelfComponent;

import java.util.Map;

public class ShelvesPaneManager {

    private static final int MAX_ROW_SIZE = 5;

    /**
     * Adds given shelves in form of JavaFX components to the given {@link GridPane shelvesPane}.
     *
     * @param shelvesPane shelves pane.
     * @param shelves     shelves to be added.
     */
    public void addShelvesToPane(GridPane shelvesPane, Map<String, VendingMachineShelf> shelves) {
        MutableInt rowIdx = new MutableInt(0);
        MutableInt colIdx = new MutableInt(0);
        shelves.forEach((key, value) -> {
            if (colIdx.getValue() == MAX_ROW_SIZE) {
                colIdx.setValue(0);
                rowIdx.increment();
            }
            shelvesPane.add(createShelf(key, value), colIdx.getAndIncrement(), rowIdx.getValue());
        });
        setRowAndColConstraints(shelvesPane);
    }

    private Node createShelf(String code, VendingMachineShelf shelf) {
        return new ShelfComponent(code, shelf.getProductType().getDispalyName(), shelf.getProductType().getContainerColor());
    }

    private void setRowAndColConstraints(GridPane shelvesPane) {
        shelvesPane.getRowConstraints().forEach(r -> {
            r.setFillHeight(true);
            r.setPercentHeight(100);
            r.setVgrow(Priority.ALWAYS);
            r.setPrefHeight(20);
        });
        shelvesPane.getColumnConstraints().forEach(c -> {
            c.setFillWidth(true);
            c.setPercentWidth(100);
            c.setHgrow(Priority.ALWAYS);
        });
    }
}
