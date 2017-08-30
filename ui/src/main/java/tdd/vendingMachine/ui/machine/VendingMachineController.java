package tdd.vendingMachine.ui.machine;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import lombok.Getter;
import lombok.Setter;
import tdd.vendingMachine.businessLogic.machine.generator.DefaultVendingMachineGenerator;
import tdd.vendingMachine.businessLogic.machine.generator.VendingMachineGenerator;
import tdd.vendingMachine.businessLogic.machine.service.VendingMachineService;
import tdd.vendingMachine.model.machine.VendingMachine;
import tdd.vendingMachine.ui.main.context.FXContext;
import tdd.vendingMachine.ui.properties.GUIProperties;

import java.net.URL;
import java.util.ResourceBundle;

public class VendingMachineController implements Initializable {

    /**
     * GUIProperties.
     */
    private static final GUIProperties GUI_PROPERTIES = GUIProperties.getInstance();

    /**
     * FXContext.
     */
    private static final FXContext fxContext = FXContext.getInstance();

    /**
     * Vending machine service.
     */
    private final VendingMachineService vendingMachineService;

    /**
     * Default vending machine generator.
     */
    private final VendingMachineGenerator vendingMachineGenerator;

    /**
     * Shelves pane manager.
     */
    private final ShelvesPaneManager shelvesPaneManager;

    /**
     * Vending machine.
     */
    @Getter
    @Setter
    private VendingMachine vendingMachine;

    /**
     * Display text.
     */
    @Getter
    @Setter
    private String display;

    @FXML
    private GridPane shelvesPane;

    /**
     * Constructor initializing basic fields and performing initial configuration.
     * Creates default vending machine using {@link DefaultVendingMachineGenerator}.
     */
    public VendingMachineController() {
        this.vendingMachineService = VendingMachineService.newVendingMachineService();
        this.vendingMachineGenerator = new DefaultVendingMachineGenerator();
        this.shelvesPaneManager = new ShelvesPaneManager();
        this.display = GUI_PROPERTIES.getProperty(GUIProperties.PropertyKeys.DISPLAY_DEFAULT_TEXT);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.vendingMachine = createDefaultVendingMachine();
        initView();
    }


    private VendingMachine createDefaultVendingMachine() {
        return vendingMachineGenerator.generate();
    }

    private void initView() {
        shelvesPaneManager.addShelvesToPane(shelvesPane, vendingMachine.getShelves());
    }


}
