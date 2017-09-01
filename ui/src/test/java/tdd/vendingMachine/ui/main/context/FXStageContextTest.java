package tdd.vendingMachine.ui.main.context;

import javafx.stage.Stage;
import org.junit.Rule;
import org.junit.Test;
import tdd.vendingMachine.ui.junit.javafx.JavaFXThreadingRule;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link FXStageContext} tests.
 */
public class FXStageContextTest {

    /**
     * JavaFX rule used to initialize JavaFX context.
     */
    @Rule
    public JavaFXThreadingRule jfxRule = new JavaFXThreadingRule();

    /**
     * Tests register stage method.
     */
    @Test
    public void testRegisterStage() {
        Stage stage = new Stage();
        FXStageContext stageCtx = FXStageContext.getInstance();
        stageCtx.registerStage(stage);
        stage.show();
        assertThat(stageCtx.getOpenStages()).contains(stage);
        stage.requestFocus();
        assertThat(stageCtx.getCurrentStage()).isEqualTo(stage);
        stage.hide();
        assertThat(stageCtx.getOpenStages()).doesNotContain(stage);
    }

}
