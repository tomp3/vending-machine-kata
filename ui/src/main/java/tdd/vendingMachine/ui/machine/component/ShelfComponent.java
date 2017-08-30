package tdd.vendingMachine.ui.machine.component;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

public class ShelfComponent extends GridPane {

    private StackPane productContainerPane;
    private Pane productContainer;
    private TextArea nameTextArea;
    private TextField codeTextField;

    public ShelfComponent(String code, String productName, String containerColor) {
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

    private TextField createCodeTextField(String code) {
        TextField tf = new TextField();
        setSize(tf);
        tf.setText(code);
        tf.setDisable(true);
        tf.setAlignment(Pos.CENTER);
        return tf;
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

    private Pane createProductContainer(String containerColor) {
        Pane pane = new Pane();
        setSize(pane);
        pane.setStyle(String.format("-fx-background-color:#%s;", containerColor));
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
