package tdd.vendingMachine.ui.machine.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import tdd.vendingMachine.businessLogic.machine.generator.DefaultVendingMachineGenerator;
import tdd.vendingMachine.businessLogic.machine.generator.VendingMachineGenerator;
import tdd.vendingMachine.businessLogic.machine.service.VendingMachineService;
import tdd.vendingMachine.model.machine.VendingMachine;
import tdd.vendingMachine.ui.machine.ShelvesPaneManager;
import tdd.vendingMachine.ui.machine.actions.VendingMachineAction;
import tdd.vendingMachine.ui.machine.actions.VendingMachineActionParameters;
import tdd.vendingMachine.ui.machine.actions.VendingMachineActionType;
import tdd.vendingMachine.ui.machine.actions.concrete.CancelAction;
import tdd.vendingMachine.ui.machine.actions.concrete.InsertCoinAction;
import tdd.vendingMachine.ui.machine.actions.concrete.NumberButtonPressedAction;
import tdd.vendingMachine.ui.machine.handlers.VendingMachineActionHandlerFactory;
import tdd.vendingMachine.ui.machine.view.converter.CoinTypeButtonConverter;
import tdd.vendingMachine.ui.machine.view.model.VendingMachineViewModel;
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
    @FXML
    private VendingMachineViewModel vendingMachineModel;

    @FXML
    private GridPane shelvesPane;

    @FXML
    private Label displayLabel;

    @FXML
    private TextArea productTrayLabel;
    @FXML
    private TextArea coinTrayLabel;

    /**
     * Constructor initializing basic fields and performing initial configuration.
     * Creates default vending machine using {@link DefaultVendingMachineGenerator}.
     */
    public VendingMachineController() {
        this.vendingMachineService = VendingMachineService.newVendingMachineService();
        this.vendingMachineGenerator = new DefaultVendingMachineGenerator();
        this.shelvesPaneManager = new ShelvesPaneManager();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.vendingMachineModel = new VendingMachineViewModel(createDefaultVendingMachine());
        vendingMachineModel.getDisplay().setText(StringUtils.EMPTY);
        initView();
    }


    private VendingMachine createDefaultVendingMachine() {
        return vendingMachineGenerator.generate();
    }

    private void initView() {
        shelvesPaneManager.addShelvesToPane(shelvesPane, vendingMachineModel.getVendingMachine().getShelves());
        displayLabel.textProperty().bind(vendingMachineModel.getDisplay().getTextProperty());
        initTrays();
    }

    private void initTrays() {
        coinTrayLabel.setDisable(true);
        coinTrayLabel.setWrapText(true);
        productTrayLabel.setDisable(true);
        productTrayLabel.setWrapText(true);
    }


    @FXML
    public void handleCodeButtonAction(ActionEvent event) {
        Button button = (Button) event.getSource();
        VendingMachineActionHandlerFactory.getInstance()
            .create(new NumberButtonPressedAction(VendingMachineActionParameters.of(vendingMachineModel, button.getText())))
            .handle();
    }

    @FXML
    public void handleCancelButtonAction(ActionEvent event) {
        VendingMachineActionHandlerFactory.getInstance()
            .create(new CancelAction(VendingMachineActionParameters.of(vendingMachineModel, StringUtils.EMPTY)))
            .handle();
        updateCoinTrayUI();
    }

    @FXML
    public void handleOkButtonAction(ActionEvent event) {
        VendingMachineActionHandlerFactory.getInstance()
            .create(VendingMachineAction.of(VendingMachineActionParameters.of(vendingMachineModel), VendingMachineActionType.OK_PRESSED))
            .handle();
    }

    @FXML
    public void handleCoinButtonAction(ActionEvent event) {
        Button button = (Button) event.getSource();
        VendingMachineActionHandlerFactory.getInstance()
            .create(new InsertCoinAction(VendingMachineActionParameters.of(vendingMachineModel, CoinTypeButtonConverter.convert(button.getText()))))
            .handle();
        updateCoinTrayUI();
        updateProductTrayUI();
    }

    @FXML
    public void handleProductTrayAction(MouseEvent event) {
        VendingMachineActionHandlerFactory.getInstance()
            .create(VendingMachineAction.of(VendingMachineActionParameters.of(vendingMachineModel), VendingMachineActionType.PRODUCT_TRAY_EMPTIED))
            .handle();
        updateProductTrayUI();
    }

    @FXML
    public void handleCoinTrayAction(MouseEvent event) {
        VendingMachineActionHandlerFactory.getInstance()
            .create(VendingMachineAction.of(VendingMachineActionParameters.of(vendingMachineModel), VendingMachineActionType.COIN_TRAY_EMPTIED))
            .handle();
        updateCoinTrayUI();
    }

    private void updateCoinTrayUI() {
        coinTrayLabel.setText(vendingMachineModel.getVendingMachine().getCashTray().coinsToString());
    }

    private void updateProductTrayUI() {
        productTrayLabel.setText(vendingMachineModel.getVendingMachine().getProductTray().productsToString());
    }

}
