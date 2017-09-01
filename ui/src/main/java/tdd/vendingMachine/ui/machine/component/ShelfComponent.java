package tdd.vendingMachine.ui.machine.component;

import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import lombok.Getter;
import tdd.vendingMachine.model.machine.VendingMachineShelf;
import tdd.vendingMachine.ui.properties.GUIProperties;

/**
 * Shelf UI component.
 */
public class ShelfComponent extends GridPane {

    /**
     * GUIProperties instance.
     */
    private static final GUIProperties GUI_PROPERTIES = GUIProperties.getInstance();

    /**
     * Pane containing product container with product name.
     */
    @Getter
    private StackPane productContainerPane;
    /**
     * Product container pane.
     */
    @Getter
    private Pane productContainer;
    /**
     * Text area containing product name.
     */
    @Getter
    private TextArea nameTextArea;
    /**
     * Text area containing shelf code.
     */
    @Getter
    private TextField codeTextField;

    /**
     * Constructor creating new shelf component with product container and it's name, as well as shelf code.
     *
     * @param code           shelf code.
     * @param productName    product name.
     * @param containerColor product container color.
     * @param shelf          product shelf.
     */
    public ShelfComponent(String code, String productName, String containerColor, VendingMachineShelf shelf) {
        super();
        setSize(this);
        this.productContainer = createProductContainer(containerColor);
        this.nameTextArea = createNameTextArea(productName);
        this.codeTextField = createCodeTextField(code);
        this.productContainerPane = createStackPane(productContainer, nameTextArea);
        this.add(productContainerPane, 0, 0);
        this.add(codeTextField, 0, 1);
        setRowAndColConstraints();
    }

    /**
     * Creates text field containing shelf code.
     *
     * @param code shelf code
     * @return text field containing shelf code.
     */
    private TextField createCodeTextField(String code) {
        TextField textField = new TextField();
        setSize(textField);
        textField.setText(code);
        textField.setDisable(true);
        textField.getStyleClass().add(GUI_PROPERTIES.getProperty(GUIProperties.PropertyKeys.COMPONENT_CODE_TEXT_CLASS));
        return textField;
    }

    /**
     * Creates text area containing product name.
     *
     * @param name product name.
     * @return text area containing product name.
     */
    private TextArea createNameTextArea(String name) {
        TextArea ta = new TextArea();
        setSize(ta);
        ta.setText(name);
        ta.setDisable(true);
        ta.setWrapText(true);
        ta.setStyle("-fx-font-weight: bold");
        return ta;
    }

    /**
     * Creates pane representing product container with the given color.
     *
     * @param containerColor container color.
     * @return pane representing product container with the given color.
     */
    private Pane createProductContainer(String containerColor) {
        Pane pane = new Pane();
        setSize(pane);
        pane.getStyleClass().add(GUI_PROPERTIES.getProperty(GUIProperties.PropertyKeys.FULL_OPACITY_CLASS));
        pane.setBackground(new Background(new BackgroundFill(Color.valueOf(containerColor), null, null)));
        return pane;
    }

    /**
     * Creates stack pane packing product container and it's name.
     *
     * @param productContainer product container
     * @param productName      product name.
     * @return stack pane packing product container and it's name.
     */
    private StackPane createStackPane(Node productContainer, Node productName) {
        StackPane stackPane = new StackPane();
        setSize(stackPane);
        stackPane.getChildren().addAll(productContainer, productName);
        return stackPane;
    }

    /**
     * Sets default size parameters - min height and width to 1 px, max width and height to {@link Double#MAX_VALUE}.
     *
     * @param node node whose parameters are set.
     */
    private void setSize(Node node) {
        node.minHeight(1);
        node.minWidth(1);
        node.maxHeight(Double.MAX_VALUE);
        node.maxWidth(Double.MAX_VALUE);
    }

    /**
     * Sets default row and column constrains of this object.
     */
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
