package tdd.vendingMachine.ui.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tdd.vendingMachine.ui.main.context.FXStageContext;

/**
 * Java FX Application.
 */
public class JavaFXApplication extends Application {

    /**
     * Vending machine FXML.
     */
    private static final String MAIN_FXML = "/tdd/vendingMachine/ui/main/machine/VendingMachine.fxml";

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXStageContext stageCtx = FXStageContext.getInstance();
        stageCtx.registerStage(primaryStage);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(MAIN_FXML));
        Pane root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Vending machine");
        primaryStage.show();
    }

    /**
     * Main method launching the Java FX Application.
     *
     * @param args run parameters.
     */
    public static void main(String... args) {
        Application.launch(args);
    }
}
