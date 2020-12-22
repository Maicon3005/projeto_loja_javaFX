package br.com.loja.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.com.loja.DAO.ClientDAO;
import br.com.loja.model.AddressModel;
import br.com.loja.model.AlertPaneModel;
import br.com.loja.model.ClientModel;
import br.com.loja.model.ContactModel;
import br.com.loja.model.StateListModel;
import br.com.loja.model.TableClientModel;
import br.com.loja.utilities.ClearFields;
import br.com.loja.utilities.ConvertToTable;
import br.com.loja.utilities.TextFieldFormatter;
import br.com.loja.utilities.ZipCodeSearch;
import br.com.loja.validation.Validate;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.hibernate.HibernateException;

/**
 * FXML Controller class
 *
 * @author maico
 */
public class FXMLClientController implements Initializable {

    //FX components ************************************************************
    @FXML
    private TabPane tabPaneClient;
    @FXML
    private AnchorPane anpRegisterClient;
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
    private ComboBox<String> cbmOptionOfSearch;
    @FXML
    private Button btnSearchList;
    @FXML
    private TableView<TableClientModel> tblClient;
    @FXML
    private TableColumn<TableClientModel, Long> clmCode;
    @FXML
    private TableColumn<TableClientModel, String> clmName;
    @FXML
    private TableColumn<TableClientModel, String> clmCpf;
    @FXML
    private TableColumn<TableClientModel, String> clmEmail;
    @FXML
    private TableColumn<TableClientModel, String> clmTelephone;
    @FXML
    private TableColumn<TableClientModel, String> clmCellPhone;
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
    @FXML
    private Button btnClearTable;
    @FXML
    private AnchorPane anpSearchClient;

    //Variable -----------------------------------------------------------------
    private ClientModel clientModel;
    private ContactModel contactModel;
    private AddressModel addressModel;
    private final ClientDAO clientDAO = new ClientDAO();
    private ObservableList<TableClientModel> observableClient;
    private List<TableClientModel> listTableAllClients;

    //initializa controller ----------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AlertPaneModel.getInstance().setAlertPaneDefault(paneAlertRegisterClient, lblAlertRegisterClient, "Preencha os campos a seguir para cadastrar um cliente");
        cbmStateFill();
        cbmSearchOptionFill();
        tblClientAutoFillAllClients(clientDAO.searchAllClients());

        //fill registration form with double click *****************************
        tblClient.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                TableClientModel tableClientModel = tblClient.getSelectionModel().getSelectedItem();
                if (tableClientModel != null) {
                    fillForm(tableClientModel.getId());
                } else {
                    AlertPaneModel.getInstance().setAlertPaneFail(paneAlertSearchClient, lblMsgSearchClient, "A tabela está vazia!");

                }
            }
        });
    }

    //presents standard message ------------------------------------------------
    @FXML
    public void selectTab() {
        if (tabRegisterClient.isSelected()) {
            AlertPaneModel.getInstance().setAlertPaneDefault(paneAlertRegisterClient, lblAlertRegisterClient, "Preencha os campos a seguir para cadastrar um cliente");
        } else if (tabListClient.isSelected()) {
            AlertPaneModel.getInstance().setAlertPaneDefault(paneAlertSearchClient, lblMsgSearchClient, "Preencha o campo a seguir para pesquisar um cliente:");
        }
    }

    //CRUD ---------------------------------------------------------------------
    // save client *************************************************************
    @FXML
    public void btnSaveClientOnAction() {
        tabPaneClient.getSelectionModel().select(0);
        if (validateClient() & validateAddress() & validateContact()) {
            //contact model*****************************************************
            String email = txtEmail.getText();
            String cellPhone = txtCellPhone.getText();
            String telephone = txtTelephone.getText();

            contactModel = new ContactModel(email, cellPhone, telephone);

            //address model*****************************************************
            String zipCode = txtZipCode.getText();
            String street = txtStreet.getText();
            String district = txtDistrict.getText();
            String city = txtCity.getText();
            int number = Integer.parseInt(txtNumber.getText());
            String state = cbmState.getValue();
            String complement = txtComplement.getText();

            addressModel = new AddressModel(zipCode, street, district, city, number, state, complement);

            //client model******************************************************
            String name = txtName.getText();
            String cpf = txtCpf.getText();
            String rg = txtRg.getText();
            LocalDate date = cbmDateOfBith.getValue();
            char genre = btnGenreMale.isSelected() ? 'm' : 'f';

            clientModel = new ClientModel(name, cpf, rg, date, genre, addressModel, contactModel);

            //save client*******************************************************
            try {
                Long idClient = clientDAO.SaveClient(clientModel);
                txtCode.setText(String.valueOf(idClient));
                tblClientAutoFillAllClients(clientDAO.searchAllClients());
                AlertPaneModel.getInstance().setAlertPaneSuccess(paneAlertRegisterClient, lblAlertRegisterClient, "Cliente salvo com sucesso!");
            } catch (HibernateException e) {
                AlertPaneModel.getInstance().setAlertPaneFail(paneAlertRegisterClient, lblAlertRegisterClient, "Não foi possível salvar o cliente! \nErro: " + e);
            } catch (Exception e) {
                AlertPaneModel.getInstance().setAlertPaneFail(paneAlertRegisterClient, lblAlertRegisterClient, "Não foi possível salvar o cliente! \nErro: " + e);
            }
        } else {
            AlertPaneModel.getInstance().setAlertPaneFail(paneAlertRegisterClient, lblAlertRegisterClient, "Por favor, verifique os campos do formulário!");
        }
    }

    //update client ************************************************************
    @FXML
    public void btnEditClientOnAction() {
        tabPaneClient.getSelectionModel().select(0);
        if (validateClient() & validateAddress() & validateContact()) {

            //contact model*****************************************************
            Long contactCode = clientModel.getContact().getId();
            String email = txtEmail.getText();
            String cellPhone = txtCellPhone.getText();
            String telephone = txtTelephone.getText();

            contactModel = new ContactModel(contactCode, email, cellPhone, telephone);

            //address model*****************************************************
            Long addressCode = clientModel.getAddress().getId();
            String zipCode = txtZipCode.getText();
            String street = txtStreet.getText();
            String district = txtDistrict.getText();
            String city = txtCity.getText();
            int number = Integer.parseInt(txtNumber.getText());
            String state = cbmState.getValue();
            String complement = txtComplement.getText();

            addressModel = new AddressModel(addressCode, zipCode, street, district, city, number, state, complement);

            //client model******************************************************
            Long clientCode = Long.parseLong(txtCode.getText());
            String name = txtName.getText();
            String cpf = txtCpf.getText();
            String rg = txtRg.getText();
            LocalDate date = cbmDateOfBith.getValue();
            char genre = btnGenreMale.isSelected() ? 'm' : 'f';

            clientModel = new ClientModel(clientCode, name, cpf, rg, date, genre, addressModel, contactModel);

            //update client*******************************************************
            try {
                if (AlertPaneModel.getInstance().alertUpdateConfirmation("Aviso de Alteração", "Alerta de Alteração", "Tem certeza que deseja Atualizar esse cliente?")) {
                    clientDAO.updateClient(clientModel);
                    tblClientAutoFillAllClients(clientDAO.searchAllClients());
                    AlertPaneModel.getInstance().setAlertPaneSuccess(paneAlertRegisterClient, lblAlertRegisterClient, "Cliente atualizado com sucesso!");
                }
            } catch (HibernateException e) {
                AlertPaneModel.getInstance().setAlertPaneFail(paneAlertRegisterClient, lblAlertRegisterClient, "Não foi possível atualizar o cliente! \nErro: " + e);
            } catch (Exception e) {
                AlertPaneModel.getInstance().setAlertPaneFail(paneAlertRegisterClient, lblAlertRegisterClient, "Não foi possível atualizar o cliente! \nErro: " + e);
            }
        } else {
            AlertPaneModel.getInstance().setAlertPaneFail(paneAlertRegisterClient, lblAlertRegisterClient, "Por favor, verifique os campos do formulário!");
        }

    }

    //delete client ************************************************************
    @FXML
    public void btnDeleteClientOnAction() {
        tabPaneClient.getSelectionModel().select(0);
        if (validateClient() & validateContact() & validateAddress()) {
            try {
                if (AlertPaneModel.getInstance().alertDeleteConfirmation("Aviso de Exclusão", "Alerta de Exclusão", "Tem certeza que deseja excluir esse cliente?")) {
                    clientDAO.removeClient(clientModel);
                    tblClientAutoFillAllClients(clientDAO.searchAllClients());
                    btnNewClientOnAction();
                    AlertPaneModel.getInstance().setAlertPaneSuccess(paneAlertRegisterClient, lblAlertRegisterClient, "Cliente Excluído com sucesso!");
                }
            } catch (HibernateException e) {
                AlertPaneModel.getInstance().setAlertPaneFail(paneAlertRegisterClient, lblAlertRegisterClient, "Não foi possível excluir o cliente! \nErro: " + e);
            }
        } else {
            AlertPaneModel.getInstance().setAlertPaneFail(paneAlertRegisterClient, lblAlertRegisterClient, "Por favor, selecione um cliente para excluir!");
        }
    }

    //search -------------------------------------------------------------------
    //search by name ***********************************************************
    @FXML
    public void btnSearchClientOnAction() {
        String name = txtName.getText();
        if (Validate.getInstance().validateName(name)) {
            clientModel = clientDAO.searchByName(name);
            if (clientModel != null) {
                //client fields
                txtCode.setText(String.valueOf(clientModel.getId()));
                txtName.setText(clientModel.getName());
                txtCpf.setText(clientModel.getCpf());
                txtRg.setText(clientModel.getRg());
                cbmDateOfBith.setValue(clientModel.getDateOfBirth());

                //contact fields
                txtEmail.setText(clientModel.getContact().getEmail());
                txtTelephone.setText(clientModel.getContact().getTelephone());
                txtCellPhone.setText(clientModel.getContact().getCellPhone());

                //address fields
                txtZipCode.setText(clientModel.getAddress().getZipCode());
                txtStreet.setText(clientModel.getAddress().getStreet());
                txtDistrict.setText(clientModel.getAddress().getDistricty());
                txtCity.setText(clientModel.getAddress().getCity());
                cbmState.getSelectionModel().select(clientModel.getAddress().getState());
                txtComplement.setText(clientModel.getAddress().getComplement());
                txtNumber.setText(String.valueOf(clientModel.getAddress().getNumber()));

                if (clientModel.getGender() == 'm') {
                    btnGenreMale.setSelected(true);
                } else {
                    btnGenreFeminine.setSelected(true);
                }

            } else {
                AlertPaneModel.getInstance().setAlertPaneFail(paneAlertRegisterClient, lblAlertRegisterClient, "Nenhum Cliente encontrado com esse nome!");
            }
        } else {
            AlertPaneModel.getInstance().setAlertPaneFail(paneAlertRegisterClient, lblAlertRegisterClient, "Por favor, preencha corretamente\no campo busca!");
        }
    }

    //client search by name or cpf
    @FXML
    public void searchClientListBtnSearchList() {
        AlertPaneModel.getInstance().setAlertPaneDefault(paneAlertSearchClient, lblMsgSearchClient, "Preencha o campo a seguir para pesquisar um cliente:");
        String text = txtSearchList.getText();

        if (cbmOptionOfSearch.getSelectionModel().isSelected(0)) {
            tblClientSearchNameResult(text);
        } else if (cbmOptionOfSearch.getSelectionModel().isSelected(1)) {
            tblClientSearchCPFResult(text);
        } else {
            AlertPaneModel.getInstance().alertSearchConfirmation("Selecionar Busca", "Seleção de Busca", "Por favor selecione o campo a ser encontrado!");
        }

    }

    //table search result by name **********************************************
    public void tblClientSearchNameResult(String text) {
        if (Validate.getInstance().validateName(text)) {
            clmCode.setCellValueFactory(new PropertyValueFactory<>("id"));
            clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
            clmCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
            clmEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            clmTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
            clmCellPhone.setCellValueFactory(new PropertyValueFactory<>("cellPhone"));

            List<TableClientModel> listTableSearchResults = new ArrayList<>();

            listTableAllClients.stream().filter((tableClientModel) -> (tableClientModel.getName().equals(text))).forEachOrdered((tableClientModel) -> {
                listTableSearchResults.add(tableClientModel);
            });

            observableClient = FXCollections.observableArrayList(listTableSearchResults);
            tblClient.setItems(observableClient);
        } else {
            AlertPaneModel.getInstance().setAlertPaneFail(paneAlertSearchClient, lblMsgSearchClient, "Por favor, digite um nome válido!");
        }

    }

    //table search result by cpf ***********************************************
    public void tblClientSearchCPFResult(String text) {
        if (Validate.getInstance().validateCpf(text)) {
            clmCode.setCellValueFactory(new PropertyValueFactory<>("id"));
            clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
            clmCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
            clmEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            clmTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
            clmCellPhone.setCellValueFactory(new PropertyValueFactory<>("cellPhone"));

            List<TableClientModel> listTableSearchResults = new ArrayList<>();

            listTableAllClients.stream().filter((tableClientModel) -> (tableClientModel.getCpf().equals(text))).forEachOrdered((tableClientModel) -> {
                listTableSearchResults.add(tableClientModel);
            });

            observableClient = FXCollections.observableArrayList(listTableSearchResults);
            tblClient.setItems(observableClient);
        } else {
            AlertPaneModel.getInstance().setAlertPaneFail(paneAlertSearchClient, lblMsgSearchClient, "Por favor, digite um CPF válido!");
        }

    }

    //auto fill ----------------------------------------------------------------
    //Fill search option combox box ********************************************
    public void cbmSearchOptionFill() {
        List<String> listOptions = new ArrayList<>();
        listOptions.add("Por nome");
        listOptions.add("Por CPF");
        ObservableList<String> observableSearch = FXCollections.observableArrayList(listOptions);
        cbmOptionOfSearch.setPromptText("Selecione ...");
        cbmOptionOfSearch.setItems(observableSearch);
    }

    //table autofill ***********************************************************
    public void tblClientAutoFillAllClients(List<ClientModel> listFillOfTable) {

        clmCode.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        clmEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        clmTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        clmCellPhone.setCellValueFactory(new PropertyValueFactory<>("cellPhone"));

        listTableAllClients = ConvertToTable.getInstance().convertObject(listFillOfTable);

        observableClient = FXCollections.observableArrayList(listTableAllClients);
        tblClient.setItems(observableClient);
    }

    //fill in registration tab *************************************************
    public void fillForm(Long idClient) {
        if (idClient != null) {
            clientModel = clientDAO.searchById(idClient);

            //client fields
            txtCode.setText(String.valueOf(clientModel.getId()));
            txtName.setText(clientModel.getName());
            txtCpf.setText(clientModel.getCpf());
            txtRg.setText(clientModel.getRg());
            cbmDateOfBith.setValue(clientModel.getDateOfBirth());

            //contact fields
            txtEmail.setText(clientModel.getContact().getEmail());
            txtTelephone.setText(clientModel.getContact().getTelephone());
            txtCellPhone.setText(clientModel.getContact().getCellPhone());

            //address fields
            txtZipCode.setText(clientModel.getAddress().getZipCode());
            txtStreet.setText(clientModel.getAddress().getStreet());
            txtDistrict.setText(clientModel.getAddress().getDistricty());
            txtCity.setText(clientModel.getAddress().getCity());
            cbmState.getSelectionModel().select(clientModel.getAddress().getState());
            txtComplement.setText(clientModel.getAddress().getComplement());
            txtNumber.setText(String.valueOf(clientModel.getAddress().getNumber()));

            tabPaneClient.getSelectionModel().select(0);
        }
    }

    //clear table and search field the tab list ********************************
    @FXML
    public void clearTabList() {
        txtSearchList.setText("");
        tblClientAutoFillAllClients(clientDAO.searchAllClients());
    }

    //Address auto-fill ********************************************************
    @FXML
    public void zipCodeAutoFill() {
        try {
            String zipCode = txtZipCode.getText();
            ZipCodeSearch zipCodeSearch = new ZipCodeSearch();
            AddressModel addressModelZipCode = zipCodeSearch.buscarCep(zipCode);
            txtStreet.setText(addressModelZipCode.getStreet());
            txtComplement.setText(addressModelZipCode.getComplement());
            txtDistrict.setText(addressModelZipCode.getDistricty());
            txtCity.setText(addressModelZipCode.getCity());
            cbmState.getSelectionModel().select(addressModelZipCode.getState());
        } catch (Exception e) {
            AlertPaneModel.getInstance().setAlertPaneFail(paneAlertRegisterClient, lblAlertRegisterClient, "O CEP informado não foi encontrado!");
        }
    }

    //Fill state combobox ******************************************************
    @FXML
    public void cbmStateFill() {
        List<String> listStates = StateListModel.getInstance().getAllStates();
        ObservableList<String> observableStates = FXCollections.observableArrayList(listStates);
        cbmState.setPromptText(observableStates.get(0));
        cbmState.setItems(observableStates);
    }

    //clear fields *************************************************************
    @FXML
    public void btnNewClientOnAction() {
        AlertPaneModel.getInstance().setAlertPaneDefault(paneAlertRegisterClient, lblAlertRegisterClient, "Preencha os campos a seguir para cadastrar um cliente");
        ClearFields.getInstance().clearScreen(this.anpRegisterClient);
        tabPaneClient.getSelectionModel().select(0);
    }

    //Masking ------------------------------------------------------------------
    //CPF mask *****************************************************************
    @FXML
    public void addMaskTxtCpfKeyReleased() {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("###.###.###-##");
        tff.setTf(txtCpf);
        tff.formatter();
    }

    //RG mask ******************************************************************
    @FXML
    public void addMaskTxtRgKeyReleased() {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("##.###.###-#");
        tff.setTf(txtRg);
        tff.formatter();
    }

    //ZipCode mask *************************************************************
    @FXML
    public void addMaskTxtZipCodeKeyReleased() {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("#####-###");
        tff.setTf(txtZipCode);
        tff.formatter();
    }

    //cellphone mask ***********************************************************
    @FXML
    public void addMaskTxtCellPhoneKeyReleased() {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("(##)#####-####");
        tff.setTf(txtCellPhone);
        tff.formatter();
    }

    //telephone mask ***********************************************************
    @FXML
    public void addMaskTxtTelephoneKeyReleased() {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("(##)####-####");
        tff.setTf(txtTelephone);
        tff.formatter();
    }

    //validations --------------------------------------------------------------
    // validate client *********************************************************
    public boolean validateClient() {
        boolean checkName = Validate.getInstance().validateName(txtName);
        boolean checkCpf = Validate.getInstance().validateCpf(txtCpf);
        boolean checkRg = Validate.getInstance().validateRg(txtRg);
        boolean checkDate = Validate.getInstance().validateDate(cbmDateOfBith);
        return checkName && checkCpf && checkRg && checkDate;
    }

    //validate contact *********************************************************
    public boolean validateContact() {
        boolean checkEmail = Validate.getInstance().validateEmail(txtEmail);
        boolean checkCellPhone = Validate.getInstance().validateCellPhone(txtCellPhone);
        boolean checkTelephone = Validate.getInstance().validateTelephone(txtTelephone);

        return checkEmail && checkCellPhone && checkTelephone;
    }

    //validate adress **********************************************************
    public boolean validateAddress() {
        boolean checkZipCode = Validate.getInstance().validateZipCode(txtZipCode);
        boolean checkStreet = Validate.getInstance().validateText(txtStreet);
        boolean checkDistrict = Validate.getInstance().validateText(txtDistrict);
        boolean checkCity = Validate.getInstance().validateText(txtCity);
        boolean checkNumber = Validate.getInstance().validateNumber(txtNumber);

        return checkZipCode && checkStreet && checkDistrict && checkCity && checkNumber;
    }
}
