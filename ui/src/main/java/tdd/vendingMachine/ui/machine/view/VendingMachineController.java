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

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML vendingMachine.fxml controller.
 */
public class VendingMachineController implements Initializable {

    /**
     * Default vending machine generator.
     */
    private final VendingMachineGenerator vendingMachineGenerator;

    /**
     * Shelves pane manager.
     */
    private final ShelvesPaneManager shelvesPaneManager;

    /**
     * Vending machine action handler factory.
     */
    private final VendingMachineActionHandlerFactory actionHandlerFactory;

    /**
     * Vending machine.
     */
    @Getter
    @Setter
    @FXML
    private VendingMachineViewModel vendingMachineModel;

    /**
     * Pane containing vending machine shelves.
     */
    @FXML
    private GridPane shelvesPane;

    /**
     * Label representing vending machine display.
     */
    @FXML
    private Label displayLabel;

    /**
     * Product tray area.
     */
    @FXML
    private TextArea productTrayTextArea;
    /**
     * Coin tray area.
     */
    @FXML
    private TextArea coinTrayTextArea;

    /**
     * Constructor initializing basic fields and performing initial configuration.
     * Creates default vending machine using {@link DefaultVendingMachineGenerator}.
     */
    public VendingMachineController() {
        this.vendingMachineGenerator = new DefaultVendingMachineGenerator();
        this.shelvesPaneManager = new ShelvesPaneManager();
        this.actionHandlerFactory = new VendingMachineActionHandlerFactory(VendingMachineService.newVendingMachineService());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.vendingMachineModel = new VendingMachineViewModel(createDefaultVendingMachine());
        vendingMachineModel.getDisplay().setText(StringUtils.EMPTY);
        initView();
    }


    /**
     * Creates default vending machine presented and managed using this controller.
     *
     * @return default vending machine presented and managed using this controller.
     */
    private VendingMachine createDefaultVendingMachine() {
        return vendingMachineGenerator.generate();
    }

    /**
     * Initialises view/graphical components.
     */
    private void initView() {
        shelvesPaneManager.addShelvesToPane(shelvesPane, vendingMachineModel.getVendingMachine().getShelves());
        displayLabel.textProperty().bind(vendingMachineModel.getDisplay().getTextProperty());
        initTrays();
    }

    /**
     * Initializes product and coin trays.
     */
    private void initTrays() {
        coinTrayTextArea.setDisable(true);
        coinTrayTextArea.setWrapText(true);
        productTrayTextArea.setDisable(true);
        productTrayTextArea.setWrapText(true);
    }


    /**
     * Method bound to machine code number button press action.
     *
     * @param event action event.
     */
    @FXML
    public void handleCodeButtonAction(ActionEvent event) {
        Button button = (Button) event.getSource();
        actionHandlerFactory.create(new NumberButtonPressedAction(VendingMachineActionParameters.of(vendingMachineModel, button.getText()))).handle();
    }

    /**
     * Method bound to cancel button press action.
     *
     * @param event action event.
     */
    @FXML
    public void handleCancelButtonAction(ActionEvent event) {
        actionHandlerFactory
            .create(new CancelAction(VendingMachineActionParameters.of(vendingMachineModel, StringUtils.EMPTY)))
            .handle();
        updateCoinTrayUI();
    }

    /**
     * Method bound to OK button press action.
     *
     * @param event action event.
     */
    @FXML
    public void handleOkButtonAction(ActionEvent event) {
        actionHandlerFactory
            .create(VendingMachineAction.of(VendingMachineActionParameters.of(vendingMachineModel), VendingMachineActionType.OK_PRESSED))
            .handle();
    }

    /**
     * Method bound to coin button press action.
     *
     * @param event action event.
     */
    @FXML
    public void handleCoinButtonAction(ActionEvent event) {
        Button button = (Button) event.getSource();
        actionHandlerFactory
            .create(new InsertCoinAction(VendingMachineActionParameters.of(vendingMachineModel, CoinTypeButtonConverter.convert(button.getText()))))
            .handle();
        updateCoinTrayUI();
        updateProductTrayUI();
    }

    /**
     * Method bound to product tray emptying action (click on the component).
     *
     * @param event mouse event.
     */
    @FXML
    public void handleProductTrayAction(MouseEvent event) {
        actionHandlerFactory
            .create(VendingMachineAction.of(VendingMachineActionParameters.of(vendingMachineModel), VendingMachineActionType.PRODUCT_TRAY_EMPTIED))
            .handle();
        updateProductTrayUI();
    }

    /**
     * Method bound to coin tray emptying action (click on the component).
     *
     * @param event mouse event.
     */
    @FXML
    public void handleCoinTrayAction(MouseEvent event) {
        actionHandlerFactory
            .create(VendingMachineAction.of(VendingMachineActionParameters.of(vendingMachineModel), VendingMachineActionType.COIN_TRAY_EMPTIED))
            .handle();
        updateCoinTrayUI();
    }

    /**
     * Updates coin tray text area.
     */
    private void updateCoinTrayUI() {
        coinTrayTextArea.setText(vendingMachineModel.getVendingMachine().getCashTray().coinsToString());
    }

    /**
     * Updates product tray text area.
     */
    private void updateProductTrayUI() {
        productTrayTextArea.setText(vendingMachineModel.getVendingMachine().getProductTray().productsToString());
    }

}
