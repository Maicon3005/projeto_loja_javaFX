package br.com.loja.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.com.loja.model.AddressModel;
import br.com.loja.model.StateList;
import br.com.loja.utilities.TextFieldFormatter;
import br.com.loja.utilities.ZipCodeSearch;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author maico
 */
public class FXMLClientController implements Initializable {

    //FX components
    @FXML
    private Tab tabRegisterClient;
    @FXML
    private Label lblCpf;
    @FXML
    private Label lblDateOfBirth;
    @FXML
    private Label lblTelephone;
    @FXML
    private Label lblName;
    @FXML
    private Label lblCellPhone;
    @FXML
    private Label lblGenre;
    @FXML
    private Label lblEmail;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtCpf;
    @FXML
    private TextField txtRg;
    @FXML
    private TextField txtCellPhone;
    @FXML
    private TextField txtTelephone;
    @FXML
    private DatePicker cbmDateOfBith;
    @FXML
    private RadioButton btnGenreMale;
    @FXML
    private ToggleGroup groupGenre;
    @FXML
    private RadioButton btnGenreFeminine;
    @FXML
    private TextField txtEmail;
    @FXML
    private Label lblStreet;
    @FXML
    private Label lblDistrict;
    @FXML
    private Label lblCity;
    @FXML
    private Label lblZipCode;
    @FXML
    private Label lblNumber;
    @FXML
    private Label lblState;
    @FXML
    private Label lblComplement;
    @FXML
    private TextField txtZipCode;
    @FXML
    private TextField txtStreet;
    @FXML
    private TextField txtNumber;
    @FXML
    private TextField txtDistrict;
    @FXML
    private TextField txtComplement;
    @FXML
    private TextField txtCity;
    @FXML
    private ComboBox<String> cbmState;
    @FXML
    private Button btnSearchZipCode;
    @FXML
    private TextField txtCode;
    @FXML
    private Label lblCode;
    @FXML
    private Button btnSearchClientByName;
    @FXML
    private Pane paneAlertRegisterClient;
    @FXML
    private Label lblAlertRegisterClient;
    @FXML
    private Tab tabListClient;
    @FXML
    private TextField txtSearchList;
    @FXML
    private Label lblSearchList;
    @FXML
    private ComboBox<?> cbmOptionOfSearch;
    @FXML
    private Button btnSerachList;
    @FXML
    private TableView<?> tblClient;
    @FXML
    private TableColumn<?, ?> clmCode;
    @FXML
    private TableColumn<?, ?> clmName;
    @FXML
    private TableColumn<?, ?> clmCpf;
    @FXML
    private TableColumn<?, ?> clmEmail;
    @FXML
    private TableColumn<?, ?> clmTelephone;
    @FXML
    private TableColumn<?, ?> clmCellPhone;
    @FXML
    private Pane paneAlertSearchClient;
    @FXML
    private Label lblMsgSearchClient;
    @FXML
    private Button btnNewClient;
    @FXML
    private Button btnSaveClient;
    @FXML
    private Button btnEditClient;
    @FXML
    private Button btnDeleteClient;

    //Variable
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbmStateFill();
    }

    @FXML
    public void addMaskTxtCpfKeyReleased() {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("###.###.###-##");
        tff.setTf(txtCpf);
        tff.formatter();
    }

    @FXML
    public void addMaskTxtZipCodeKeyReleased() {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("#####-###");
        tff.setTf(txtZipCode);
        tff.formatter();
    }

    @FXML
    public void addMaskTxtCellPhoneKeyReleased() {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("(##)#####-####");
        tff.setTf(txtCellPhone);
        tff.formatter();
    }

    @FXML
    public void addMaskTxtTelephoneKeyReleased() {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("(##)####-####");
        tff.setTf(txtTelephone);
        tff.formatter();
    }

    @FXML
    public void cbmStateFill() {
        List<String> listStates = StateList.getInstance().getAllStates();
        ObservableList<String> observableStates = FXCollections.observableArrayList(listStates);
        cbmState.setPromptText(observableStates.get(0));
        cbmState.setItems(observableStates);
    }

    @FXML
    public void zipCodeAutoFill() {
        String zipCode = txtZipCode.getText();
        ZipCodeSearch zipCodeSearch = new ZipCodeSearch();
        AddressModel addressModel = zipCodeSearch.buscarCep(zipCode);
        txtStreet.setText(addressModel.getStreet());
        txtComplement.setText(addressModel.getComplement());
        txtDistrict.setText(addressModel.getDistricty());
        txtCity.setText(addressModel.getCity());
        cbmState.getSelectionModel().select(addressModel.getState());

    }
}
