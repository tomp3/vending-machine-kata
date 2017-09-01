package tdd.vendingMachine.ui.machine.component;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableObjectValue;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import lombok.Getter;
import tdd.vendingMachine.model.machine.VendingMachineShelf;
import tdd.vendingMachine.ui.properties.GUIProperties;

public class ShelfComponent extends GridPane {

    private static final GUIProperties GUI_PROPERTIES = GUIProperties.getInstance();
    private static final String EMPTY_SHELF_COLOR = GUI_PROPERTIES.getProperty(GUIProperties.PropertyKeys.EMPTY_SHELF_COLOR);
    private static final Background EMPTY_SHELF_BACKGROUND = new Background(new BackgroundFill(Color.valueOf(EMPTY_SHELF_COLOR), null, null));

    @Getter
    private StackPane productContainerPane;
    @Getter
    private Pane productContainer;
    @Getter
    private TextArea nameTextArea;
    @Getter
    private TextField codeTextField;
    @Getter
    private ObjectProperty<Background> backgroundProperty;

    public ShelfComponent(String code, String productName, String containerColor, VendingMachineShelf shelf) {
        super();
        setSize(this);
        this.backgroundProperty = new SimpleObjectProperty<>(shelf.getProducts().isEmpty() ?
            EMPTY_SHELF_BACKGROUND :
            new Background(new BackgroundFill(Color.valueOf(containerColor), null, null))
        );
        this.productContainer = createProductContainer(backgroundProperty);
        this.nameTextArea = createNameTextArea(productName);
        this.codeTextField = createCodeTextField(code);
        this.productContainerPane = createStackPane(productContainer, nameTextArea);
        this.add(productContainerPane, 0, 0);
        this.add(codeTextField, 0, 1);
        setRowAndColConstraints();
    }

    private TextField createCodeTextField(String code) {
        TextField textField = new TextField();
        setSize(textField);
        textField.setText(code);
        textField.setDisable(true);
        textField.getStyleClass().add(GUI_PROPERTIES.getProperty(GUIProperties.PropertyKeys.COMPONENT_CODE_TEXT_CLASS));
        return textField;
    }

    private TextArea createNameTextArea(String name) {
        TextArea ta = new TextArea();
        setSize(ta);
        ta.setText(name);
        ta.setDisable(true);
        ta.setWrapText(true);
        ta.setStyle("-fx-font-weight: bold");
        return ta;
    }

    private Pane createProductContainer(ObservableObjectValue<Background> backgroundProperty) {
        Pane pane = new Pane();
        setSize(pane);
        pane.backgroundProperty().bind(backgroundProperty);
        return pane;
    }

    private StackPane createStackPane(Node productContainerRectangle, Node nameTextArea) {
        StackPane stackPane = new StackPane();
        setSize(stackPane);
        stackPane.getChildren().addAll(productContainerRectangle, nameTextArea);
        return stackPane;
    }

    private void setSize(Node node) {
        node.minHeight(1);
        node.minWidth(1);
        node.maxHeight(Double.MAX_VALUE);
        node.maxWidth(Double.MAX_VALUE);
    }

    private void setRowAndColConstraints() {
        this.getRowConstraints().forEach(r -> {
            r.setFillHeight(true);
            r.setPercentHeight(100);
            r.setVgrow(Priority.ALWAYS);
        });
        this.getColumnConstraints().forEach(c -> {
            c.setFillWidth(true);
            c.setPercentWidth(100);
            c.setHgrow(Priority.ALWAYS);
        });
    }
}
