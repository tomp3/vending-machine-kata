package tdd.vendingMachine.ui.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tdd.vendingMachine.ui.main.context.FXStageContext;
import tdd.vendingMachine.ui.properties.GUIProperties;

import java.io.IOException;

/**
 * Java FX Application.
 */
public class JavaFXApplication extends Application {

    private static final GUIProperties GUI_PROPERTIES = GUIProperties.getInstance();

    /**
     * Vending machine FXML.
     */
    private static final String MAIN_FXML = GUI_PROPERTIES.getProperty(GUIProperties.PropertyKeys.MAIN_FXML_PATH);

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        configureStage(primaryStage);
        primaryStage.setScene(prepareScene());
        primaryStage.show();
    }

    /**
     * Configures stage before rendering.
     *
     * @param stage primary stage.
     */
    private void configureStage(Stage stage) {
        FXStageContext stageCtx = FXStageContext.getInstance();
        stageCtx.registerStage(stage);
        stage.setTitle(GUI_PROPERTIES.getProperty(GUIProperties.PropertyKeys.WINDOW_TITLE));
        stage.getIcons().add(new Image(GUI_PROPERTIES.getProperty(GUIProperties.PropertyKeys.WINDOW_ICON)));
    }

    /**
     * Prepares main scene to be displayed in the primary stage.
     *
     * @return main scene to be displayed in the primary stage.
     * @throws IOException thrown in case main scene could not be loaded.
     */
    private Scene prepareScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(MAIN_FXML));
        Pane root = loader.load();
        return new Scene(root);
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
