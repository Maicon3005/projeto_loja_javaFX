package br.com.loja.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.com.loja.DAO.ProviderDAO;
import br.com.loja.model.AddressModel;
import br.com.loja.model.AlertPaneModel;
import br.com.loja.model.ContactModel;
import br.com.loja.model.ProviderModel;
import br.com.loja.model.StateListModel;
import br.com.loja.model.TableProviderModel;
import br.com.loja.utilities.ClearFields;
import br.com.loja.utilities.ConvertToTable;
import br.com.loja.utilities.TextFieldFormatter;
import br.com.loja.utilities.ZipCodeSearch;
import br.com.loja.validation.Validate;
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
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
public class FXMLProviderController implements Initializable {

    //FX components ************************************************************
    @FXML
    private TabPane tabPaneProvider;

    @FXML
    private Tab tabRegisterProvider;

    @FXML
    private AnchorPane anpRegisterProvider;

    @FXML
    private Label lblCompanyName;

    @FXML
    private Label lblCnpj;

    @FXML
    private Label lblTelephone;

    @FXML
    private Label lblFantasyName;

    @FXML
    private Label lblCellPhone;

    @FXML
    private Label lblEmail;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtFantasyName;

    @FXML
    private Button btnSearchProviderByName;

    @FXML
    private TextField txtCompanyName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtCellPhone;

    @FXML
    private TextField txtTelephone;

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
    private Button btnSearchZipCode;

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
    private Label lblCode;

    @FXML
    private Pane paneAlertRegisterProvider;

    @FXML
    private Label lblAlertRegisterProvider;

    @FXML
    private TextField txtCnpj;

    @FXML
    private Tab tabListProvider;

    @FXML
    private AnchorPane anpSearchProvider;

    @FXML
    private Label lblSearchList;

    @FXML
    private TextField txtSearchList;

    @FXML
    private ComboBox<String> cbmOptionOfSearch;

    @FXML
    private Button btnSearchList;

    @FXML
    private TableView<TableProviderModel> tblProvider;

    @FXML
    private TableColumn<Long, TableProviderModel> clmCode;

    @FXML
    private TableColumn<String, TableProviderModel> clmFantasyName;

    @FXML
    private TableColumn<String, TableProviderModel> clmCnpj;

    @FXML
    private TableColumn<String, TableProviderModel> clmEmail;

    @FXML
    private TableColumn<String, TableProviderModel> clmTelephone;

    @FXML
    private TableColumn<String, TableProviderModel> clmCellPhone;

    @FXML
    private Pane paneAlertSearchProvider;

    @FXML
    private Label lblMsgSearchProvider;

    @FXML
    private Button btnClearTable;

    @FXML
    private Button btnNewProvider;

    @FXML
    private Button btnSaveProvider;

    @FXML
    private Button btnEditProvider;

    @FXML
    private Button btnDeleteProvider;

    //Variable -----------------------------------------------------------------
    private ProviderModel providerModel;
    private ContactModel contactModel;
    private AddressModel addressModel;
    private final ProviderDAO providerDAO = new ProviderDAO();
    private ObservableList<TableProviderModel> observableProvider;
    private List<TableProviderModel> listTableAllProviders;

    //initializa controller ----------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AlertPaneModel.getInstance().setAlertPaneDefault(paneAlertRegisterProvider, lblAlertRegisterProvider, "Preencha os campos a seguir para cadastrar um fornecedor");
        cbmStateFill();
        cbmSearchOptionFill();
        tblProviderAutoFillAllProviders(providerDAO.searchAllProviders());

        //fill registration form with double click *****************************
        tblProvider.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                TableProviderModel tableProviderModel = tblProvider.getSelectionModel().getSelectedItem();
                if (tableProviderModel != null) {
                    fillForm(tableProviderModel.getId());
                } else {
                    AlertPaneModel.getInstance().setAlertPaneFail(paneAlertSearchProvider, lblMsgSearchProvider, "A tabela está vazia!");

                }
            }
        });
    }

    //presents standard message ------------------------------------------------
    @FXML
    public void selectTab() {
        if (tabRegisterProvider.isSelected()) {
            AlertPaneModel.getInstance().setAlertPaneDefault(paneAlertRegisterProvider, lblAlertRegisterProvider, "Preencha os campos a seguir para cadastrar um fornecedor");
        } else if (tabListProvider.isSelected()) {
            AlertPaneModel.getInstance().setAlertPaneDefault(paneAlertSearchProvider, lblMsgSearchProvider, "Preencha o campo a seguir para pesquisar um fornecedor:");
        }
    }

    //CRUD ---------------------------------------------------------------------
    // save provider *************************************************************
    @FXML
    public void btnSaveProviderOnAction() {
        tabPaneProvider.getSelectionModel().select(0);
        if (validateProvider() & validateAddress() & validateContact()) {
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

            //provider model******************************************************
            String fantasyName = txtFantasyName.getText();
            String companyName = txtCompanyName.getText();
            String cnpj = txtCnpj.getText();

            providerModel = new ProviderModel(fantasyName, companyName, cnpj, addressModel, contactModel);

            //save provider*******************************************************
            try {
                Long idProvider = providerDAO.SaveProvider(providerModel);
                txtCode.setText(String.valueOf(idProvider));
                tblProviderAutoFillAllProviders(providerDAO.searchAllProviders());
                AlertPaneModel.getInstance().setAlertPaneSuccess(paneAlertRegisterProvider, lblAlertRegisterProvider, "Fornecedor salvo com sucesso!");
            } catch (HibernateException e) {
                AlertPaneModel.getInstance().setAlertPaneFail(paneAlertRegisterProvider, lblAlertRegisterProvider, "Não foi possível salvar o fornecedor! \nErro: " + e);
            } catch (Exception e) {
                AlertPaneModel.getInstance().setAlertPaneFail(paneAlertRegisterProvider, lblAlertRegisterProvider, "Não foi possível salvar o fornecedor! \nErro: " + e);
            }
        } else {
            AlertPaneModel.getInstance().setAlertPaneFail(paneAlertRegisterProvider, lblAlertRegisterProvider, "Por favor, verifique os campos do formulário!");
        }
    }

    //update provider ************************************************************
    @FXML
    public void btnEditProviderOnAction() {
        tabPaneProvider.getSelectionModel().select(0);
        if (validateProvider() & validateAddress() & validateContact()) {

            //contact model*****************************************************
            Long contactCode = providerModel.getContact().getId();
            String email = txtEmail.getText();
            String cellPhone = txtCellPhone.getText();
            String telephone = txtTelephone.getText();

            contactModel = new ContactModel(contactCode, email, cellPhone, telephone);

            //address model*****************************************************
            Long addressCode = providerModel.getAddress().getId();
            String zipCode = txtZipCode.getText();
            String street = txtStreet.getText();
            String district = txtDistrict.getText();
            String city = txtCity.getText();
            int number = Integer.parseInt(txtNumber.getText());
            String state = cbmState.getValue();
            String complement = txtComplement.getText();

            addressModel = new AddressModel(addressCode, zipCode, street, district, city, number, state, complement);

            //provider model******************************************************
            Long providerCode = Long.parseLong(txtCode.getText());
            String fantasyName = txtFantasyName.getText();
            String companyName = txtCompanyName.getText();
            String cnpj = txtCnpj.getText();

            providerModel = new ProviderModel(providerCode, fantasyName, companyName, cnpj, addressModel, contactModel);

            //update provider*******************************************************
            try {
                if (AlertPaneModel.getInstance().alertUpdateConfirmation("Aviso de Alteração", "Alerta de Alteração", "Tem certeza que deseja Atualizar esse fornecedor?")) {
                    providerDAO.updateProvider(providerModel);
                    tblProviderAutoFillAllProviders(providerDAO.searchAllProviders());
                    AlertPaneModel.getInstance().setAlertPaneSuccess(paneAlertRegisterProvider, lblAlertRegisterProvider, "Fornecedor atualizado com sucesso!");
                }
            } catch (HibernateException e) {
                AlertPaneModel.getInstance().setAlertPaneFail(paneAlertRegisterProvider, lblAlertRegisterProvider, "Não foi possível atualizar o fornecedor! \nErro: " + e);
            } catch (Exception e) {
                AlertPaneModel.getInstance().setAlertPaneFail(paneAlertRegisterProvider, lblAlertRegisterProvider, "Não foi possível atualizar o fornecedor! \nErro: " + e);
            }
        } else {
            AlertPaneModel.getInstance().setAlertPaneFail(paneAlertRegisterProvider, lblAlertRegisterProvider, "Por favor, verifique os campos do formulário!");
        }

    }

    //delete provider ************************************************************
    @FXML
    public void btnDeleteProviderOnAction() {
        tabPaneProvider.getSelectionModel().select(0);
        if (validateProvider() & validateContact() & validateAddress()) {
            try {
                if (AlertPaneModel.getInstance().alertDeleteConfirmation("Aviso de Exclusão", "Alerta de Exclusão", "Tem certeza que deseja excluir esse fornecedor?")) {
                    providerDAO.removeProvider(providerModel);
                    tblProviderAutoFillAllProviders(providerDAO.searchAllProviders());
                    btnNewProviderOnAction();
                    AlertPaneModel.getInstance().setAlertPaneSuccess(paneAlertRegisterProvider, lblAlertRegisterProvider, "fornecedor Excluído com sucesso!");
                }
            } catch (HibernateException e) {
                AlertPaneModel.getInstance().setAlertPaneFail(paneAlertRegisterProvider, lblAlertRegisterProvider, "Não foi possível excluir o fornecedor! \nErro: " + e);
            }
        } else {
            AlertPaneModel.getInstance().setAlertPaneFail(paneAlertRegisterProvider, lblAlertRegisterProvider, "Por favor, selecione um fornecedor para excluir!");
        }
    }

    //search -------------------------------------------------------------------
    //search by name ***********************************************************
    @FXML
    public void btnSearchProviderOnAction() {
        String name = txtFantasyName.getText();
        if (Validate.getInstance().validateName(name)) {
            providerModel = providerDAO.searchByName(name);
            if (providerModel != null) {
                //provider fields
                txtCode.setText(String.valueOf(providerModel.getId()));
                txtCompanyName.setText(providerModel.getCompanyName());
                txtFantasyName.setText(providerModel.getFantasyName());
                txtCnpj.setText(providerModel.getCnpj());

                //contact fields
                txtEmail.setText(providerModel.getContact().getEmail());
                txtTelephone.setText(providerModel.getContact().getTelephone());
                txtCellPhone.setText(providerModel.getContact().getCellPhone());

                //address fields
                txtZipCode.setText(providerModel.getAddress().getZipCode());
                txtStreet.setText(providerModel.getAddress().getStreet());
                txtDistrict.setText(providerModel.getAddress().getDistricty());
                txtCity.setText(providerModel.getAddress().getCity());
                cbmState.getSelectionModel().select(providerModel.getAddress().getState());
                txtComplement.setText(providerModel.getAddress().getComplement());
                txtNumber.setText(String.valueOf(providerModel.getAddress().getNumber()));

            } else {
                AlertPaneModel.getInstance().setAlertPaneFail(paneAlertRegisterProvider, lblAlertRegisterProvider, "Nenhum fornecedor encontrado com esse nome!");
            }
        } else {
            AlertPaneModel.getInstance().setAlertPaneFail(paneAlertRegisterProvider, lblAlertRegisterProvider, "Por favor, preencha corretamente\no campo busca!");
        }
    }

    //provider search by name or cnpj
    @FXML
    public void searchProviderListBtnSearchList() {
        AlertPaneModel.getInstance().setAlertPaneDefault(paneAlertSearchProvider, lblMsgSearchProvider, "Preencha o campo a seguir para pesquisar um fornecedor:");
        String text = txtSearchList.getText();

        if (cbmOptionOfSearch.getSelectionModel().isSelected(0)) {
            tblProviderSearchNameResult(text);
        } else if (cbmOptionOfSearch.getSelectionModel().isSelected(1)) {
            tblProviderSearchCNPJResult(text);
        } else {
            AlertPaneModel.getInstance().alertSearchConfirmation("Selecionar Busca", "Seleção de Busca", "Por favor selecione o campo a ser encontrado!");
        }

    }

    //table search result by name **********************************************
    public void tblProviderSearchNameResult(String text) {
        if (Validate.getInstance().validateName(text)) {
            clmCode.setCellValueFactory(new PropertyValueFactory<>("id"));
            clmFantasyName.setCellValueFactory(new PropertyValueFactory<>("fantasyName"));
            clmCnpj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
            clmEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            clmTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
            clmCellPhone.setCellValueFactory(new PropertyValueFactory<>("cellPhone"));

            List<TableProviderModel> listTableSearchResults = new ArrayList<>();

            listTableAllProviders.stream().filter((tableProviderModel) -> (tableProviderModel.getFantasyName().equals(text))).forEachOrdered((tableProviderModel) -> {
                listTableSearchResults.add(tableProviderModel);
            });

            observableProvider = FXCollections.observableArrayList(listTableSearchResults);
            tblProvider.setItems(observableProvider);
        } else {
            AlertPaneModel.getInstance().setAlertPaneFail(paneAlertSearchProvider, lblMsgSearchProvider, "Por favor, digite um nome fantasia válido!");
        }

    }

    //table search result by cnpj ***********************************************
    public void tblProviderSearchCNPJResult(String text) {
        if (Validate.getInstance().validateCnpj(text)) {
            clmCode.setCellValueFactory(new PropertyValueFactory<>("id"));
            clmFantasyName.setCellValueFactory(new PropertyValueFactory<>("fantasyName"));
            clmCnpj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
            clmEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            clmTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
            clmCellPhone.setCellValueFactory(new PropertyValueFactory<>("cellPhone"));

            List<TableProviderModel> listTableSearchResults = new ArrayList<>();

            listTableAllProviders.stream().filter((tableProviderModel) -> (tableProviderModel.getCnpj().equals(text))).forEachOrdered((tableProviderModel) -> {
                listTableSearchResults.add(tableProviderModel);
            });

            observableProvider = FXCollections.observableArrayList(listTableSearchResults);
            tblProvider.setItems(observableProvider);
        } else {
            AlertPaneModel.getInstance().setAlertPaneFail(paneAlertSearchProvider, lblMsgSearchProvider, "Por favor, digite um CNPJ válido!");
        }

    }

    //auto fill ----------------------------------------------------------------
    //Fill search option combox box ********************************************
    public void cbmSearchOptionFill() {
        List<String> listOptions = new ArrayList<>();
        listOptions.add("Por nome fantasia");
        listOptions.add("Por CNPJ");
        ObservableList<String> observableSearch = FXCollections.observableArrayList(listOptions);
        cbmOptionOfSearch.setPromptText("Selecione ...");
        cbmOptionOfSearch.setItems(observableSearch);
    }

    //table autofill ***********************************************************
    public void tblProviderAutoFillAllProviders(List<ProviderModel> listFillOfTable) {

        clmCode.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmFantasyName.setCellValueFactory(new PropertyValueFactory<>("fantasyName"));
        clmCnpj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
        clmEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        clmTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        clmCellPhone.setCellValueFactory(new PropertyValueFactory<>("cellPhone"));

        listTableAllProviders = ConvertToTable.getInstance().convertObjectProvider(listFillOfTable);

        observableProvider = FXCollections.observableArrayList(listTableAllProviders);
        tblProvider.setItems(observableProvider);
    }

    //fill in registration tab *************************************************
    public void fillForm(Long idProvider) {
        if (idProvider != null) {
            providerModel = providerDAO.searchById(idProvider);

            //provider fields
            txtCode.setText(String.valueOf(providerModel.getId()));
            txtFantasyName.setText(providerModel.getFantasyName());
            txtCompanyName.setText(providerModel.getCompanyName());
            txtCnpj.setText(providerModel.getCnpj());

            //contact fields
            txtEmail.setText(providerModel.getContact().getEmail());
            txtTelephone.setText(providerModel.getContact().getTelephone());
            txtCellPhone.setText(providerModel.getContact().getCellPhone());

            //address fields
            txtZipCode.setText(providerModel.getAddress().getZipCode());
            txtStreet.setText(providerModel.getAddress().getStreet());
            txtDistrict.setText(providerModel.getAddress().getDistricty());
            txtCity.setText(providerModel.getAddress().getCity());
            cbmState.getSelectionModel().select(providerModel.getAddress().getState());
            txtComplement.setText(providerModel.getAddress().getComplement());
            txtNumber.setText(String.valueOf(providerModel.getAddress().getNumber()));

            tabPaneProvider.getSelectionModel().select(0);
        }
    }

    //clear table and search field the tab list ********************************
    @FXML
    public void clearTabList() {
        txtSearchList.setText("");
        tblProviderAutoFillAllProviders(providerDAO.searchAllProviders());
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
            AlertPaneModel.getInstance().setAlertPaneFail(paneAlertRegisterProvider, lblAlertRegisterProvider, "O CEP informado não foi encontrado!");
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
    public void btnNewProviderOnAction() {
        AlertPaneModel.getInstance().setAlertPaneDefault(paneAlertRegisterProvider, lblAlertRegisterProvider, "Preencha os campos a seguir para cadastrar um fornecedor");
        ClearFields.getInstance().clearScreen(this.anpRegisterProvider);
        tabPaneProvider.getSelectionModel().select(0);
    }

    //Masking ------------------------------------------------------------------
    //CPF mask *****************************************************************
    @FXML
    public void addMaskTxtCpfKeyReleased() {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("##.###.###/####-##");
        tff.setTf(txtCnpj);
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
    // validate provider *********************************************************
    public boolean validateProvider() {
        boolean checkFantasyName = Validate.getInstance().validateText(txtFantasyName);
        boolean checkCompanyName = Validate.getInstance().validateText(txtCompanyName);
        boolean checkCnpj = Validate.getInstance().validateCnpj(txtCnpj);
        return checkFantasyName && checkCompanyName && checkCnpj;
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
