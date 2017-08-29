package tdd.vendingMachine.ui.main.context;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Singleton class containing stage context - open stages, current stage.
 */
public final class FXStageContext {

    /**
     * Singleton instance.
     */
    private static final FXStageContext INSTANCE = new FXStageContext();

    /**
     * Open stages.
     */
    private final ObservableList<Stage> openStages = FXCollections.observableArrayList();

    /**
     * Default constructor.
     */
    private FXStageContext() {
    }

    /**
     * Returns FXStageContext instance.
     *
     * @return FXStageContext instance.
     */
    public static FXStageContext getInstance() {
        return INSTANCE;
    }

    /**
     * Returns open stages.
     *
     * @return open stages.
     */
    public ObservableList<Stage> getOpenStages() {
        return openStages;
    }

    /**
     * Current stage in form of ObjectProperty.
     */
    private final ObjectProperty<Stage> currentStage = new SimpleObjectProperty<>(null);

    /**
     * Returns current stage in form of {@link ObjectProperty}.
     *
     * @return current stage in form of {@link ObjectProperty}.
     */
    public final ObjectProperty<Stage> currentStageProperty() {
        return this.currentStage;
    }

    /**
     * Returns current stage in form of {@link Stage}.
     *
     * @return current stage in form of {@link Stage}.
     */
    public final Stage getCurrentStage() {
        return this.currentStageProperty().get();
    }

    /**
     * Sets current stage.
     *
     * @param currentStage current stage.
     */
    public final void setCurrentStage(Stage currentStage) {
        this.currentStageProperty().set(currentStage);
    }

    /**
     * Registers new stage - adds {@link WindowEvent#WINDOW_SHOWN} and {@link WindowEvent#WINDOW_HIDDEN} event listeners
     * responsible for maintaining open stages list {@link #openStages}.
     *
     * @param stage new stage.
     */
    public void registerStage(Stage stage) {
        stage.addEventHandler(WindowEvent.WINDOW_SHOWN, e -> openStages.add(stage));
        stage.addEventHandler(WindowEvent.WINDOW_HIDDEN, e -> openStages.remove(stage));
        stage.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> currentStage.set(isNowFocused ? stage : null));
    }

    /**
     * Creates a new stage, registering it using {@link #registerStage(Stage)} method.
     *
     * @return new stage instance.
     */
    public Stage createStage() {
        Stage stage = new Stage();
        registerStage(stage);
        return stage;
    }
}
