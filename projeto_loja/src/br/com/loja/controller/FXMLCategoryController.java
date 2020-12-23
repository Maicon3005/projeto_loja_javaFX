package br.com.loja.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.com.loja.DAO.CategoryDAO;
import br.com.loja.model.AddressModel;
import br.com.loja.model.AlertPaneModel;
import br.com.loja.model.ContactModel;
import br.com.loja.model.CategoryModel;
import br.com.loja.utilities.ClearFields;
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
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
public class FXMLCategoryController implements Initializable {

    //FX components ************************************************************
    @FXML
    private TabPane tabPaneCategory;

    @FXML
    private Tab tabRegisterCategory;

    @FXML
    private AnchorPane anpRegisterCategory;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblCategory;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtCategory;

    @FXML
    private Button btnSearchCategoryByName;

    @FXML
    private Label lblCode;

    @FXML
    private Pane paneAlertRegisterCategory;

    @FXML
    private Label lblAlertRegisterCategory;

    @FXML
    private TextArea txtDescription;

    @FXML
    private Tab tabListCategory;

    @FXML
    private AnchorPane anpSearchCategory;

    @FXML
    private Label lblSearchList;

    @FXML
    private TextField txtSearchList;

    @FXML
    private Button btnSearchList;

    @FXML
    private TableView<CategoryModel> tblCategory;

    @FXML
    private TableColumn<Long, CategoryModel> clmCode;

    @FXML
    private TableColumn<String, CategoryModel> clmCategory;

    @FXML
    private TableColumn<String, CategoryModel> clmDescription;

    @FXML
    private Pane paneAlertSearchCategory;

    @FXML
    private Label lblMsgSearchCategory;

    @FXML
    private Button btnClearTable;

    @FXML
    private Button btnNewCategory;

    @FXML
    private Button btnSaveCategory;

    @FXML
    private Button btnEditCategory;

    @FXML
    private Button btnDeleteCategory;

    //Variable -----------------------------------------------------------------
    private CategoryModel categoryModel;
    private ContactModel contactModel;
    private AddressModel addressModel;
    private final CategoryDAO categoryDAO = new CategoryDAO();
    private ObservableList<CategoryModel> observableCategory;
    private List<CategoryModel> listTableAllCategorys = new ArrayList<>();

    //initializa controller ----------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AlertPaneModel.getInstance().setAlertPaneDefault(paneAlertRegisterCategory, lblAlertRegisterCategory, "Preencha os campos a seguir para cadastrar uma categoria");
        tblCategoryAutoFillAllCategorys(categoryDAO.searchAllCategorys());

        //fill registration form with double click *****************************
        tblCategory.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                CategoryModel category = tblCategory.getSelectionModel().getSelectedItem();
                if (category != null) {
                    fillForm(category.getId());
                } else {
                    AlertPaneModel.getInstance().setAlertPaneFail(paneAlertSearchCategory, lblMsgSearchCategory, "A tabela está vazia!");

                }
            }
        });
    }

    //presents standard message ------------------------------------------------
    @FXML
    public void selectTab() {
        if (tabRegisterCategory.isSelected()) {
            AlertPaneModel.getInstance().setAlertPaneDefault(paneAlertRegisterCategory, lblAlertRegisterCategory, "Preencha os campos a seguir para cadastrar uma categoria");
        } else if (tabListCategory.isSelected()) {
            AlertPaneModel.getInstance().setAlertPaneDefault(paneAlertSearchCategory, lblMsgSearchCategory, "Preencha o campo a seguir para pesquisar uma categoria:");
        }
    }

    //CRUD ---------------------------------------------------------------------
    // save category *************************************************************
    @FXML
    public void btnSaveCategoryOnAction() {
        tabPaneCategory.getSelectionModel().select(0);
        if (validateCategory()) {
            //category model*****************************************************

            String category = txtCategory.getText();
            String description = txtDescription.getText();

            categoryModel = new CategoryModel(category, description);

            //save category*******************************************************
            try {
                Long idCategory = categoryDAO.SaveCategory(categoryModel);
                txtCode.setText(String.valueOf(idCategory));
                tblCategoryAutoFillAllCategorys(categoryDAO.searchAllCategorys());
                AlertPaneModel.getInstance().setAlertPaneSuccess(paneAlertRegisterCategory, lblAlertRegisterCategory, "Categoria salvo com sucesso!");
            } catch (HibernateException e) {
                AlertPaneModel.getInstance().setAlertPaneFail(paneAlertRegisterCategory, lblAlertRegisterCategory, "Não foi possível salvar a categoria! \nErro: " + e);
            } catch (Exception e) {
                AlertPaneModel.getInstance().setAlertPaneFail(paneAlertRegisterCategory, lblAlertRegisterCategory, "Não foi possível salvar a categoria! \nErro: " + e);
            }
        } else {
            AlertPaneModel.getInstance().setAlertPaneFail(paneAlertRegisterCategory, lblAlertRegisterCategory, "Por favor, verifique os campos do formulário!");
        }
    }

    //update category ************************************************************
    @FXML
    public void btnEditCategoryOnAction() {
        tabPaneCategory.getSelectionModel().select(0);
        if (validateCategory()) {

            //category model*****************************************************
            Long code = Long.parseLong(txtCode.getText());
            String category = txtCategory.getText();
            String description = txtDescription.getText();

            categoryModel = new CategoryModel(code, category, description);

            //update category*******************************************************
            try {
                if (AlertPaneModel.getInstance().alertUpdateConfirmation("Aviso de Alteração", "Alerta de Alteração", "Tem certeza que deseja Atualizar essa categoria?")) {
                    categoryDAO.updateCategory(categoryModel);
                    tblCategoryAutoFillAllCategorys(categoryDAO.searchAllCategorys());
                    AlertPaneModel.getInstance().setAlertPaneSuccess(paneAlertRegisterCategory, lblAlertRegisterCategory, "Categoria atualizado com sucesso!");
                }
            } catch (HibernateException e) {
                AlertPaneModel.getInstance().setAlertPaneFail(paneAlertRegisterCategory, lblAlertRegisterCategory, "Não foi possível atualizar a categoria! \nErro: " + e);
            } catch (Exception e) {
                AlertPaneModel.getInstance().setAlertPaneFail(paneAlertRegisterCategory, lblAlertRegisterCategory, "Não foi possível atualizar a categoria! \nErro: " + e);
            }
        } else {
            AlertPaneModel.getInstance().setAlertPaneFail(paneAlertRegisterCategory, lblAlertRegisterCategory, "Por favor, verifique os campos da categoria!");
        }

    }

    //delete category ************************************************************
    @FXML
    public void btnDeleteCategoryOnAction() {
        tabPaneCategory.getSelectionModel().select(0);
        if (validateCategory()) {
            try {
                if (AlertPaneModel.getInstance().alertDeleteConfirmation("Aviso de Exclusão", "Alerta de Exclusão", "Tem certeza que deseja excluir essa categoria?")) {
                    categoryDAO.removeCategory(categoryModel);
                    tblCategoryAutoFillAllCategorys(categoryDAO.searchAllCategorys());
                    btnNewCategoryOnAction();
                    AlertPaneModel.getInstance().setAlertPaneSuccess(paneAlertRegisterCategory, lblAlertRegisterCategory, "Categoria Excluída com sucesso!");
                }
            } catch (HibernateException e) {
                AlertPaneModel.getInstance().setAlertPaneFail(paneAlertRegisterCategory, lblAlertRegisterCategory, "Não foi possível excluir a categoria! \nErro: " + e);
            }
        } else {
            AlertPaneModel.getInstance().setAlertPaneFail(paneAlertRegisterCategory, lblAlertRegisterCategory, "Por favor, selecione uma categoria para excluir!");
        }
    }

    //search -------------------------------------------------------------------
    //search by name ***********************************************************
    @FXML
    public void btnSearchCategoryOnAction() {
        String name = txtCategory.getText();
        if (Validate.getInstance().validateName(name)) {
            categoryModel = categoryDAO.searchByName(name);
            if (categoryModel != null) {
                //category fields
                txtCode.setText(String.valueOf(categoryModel.getId()));
                txtCategory.setText(categoryModel.getCategory());
                txtDescription.setText(categoryModel.getDescription());

            } else {
                AlertPaneModel.getInstance().setAlertPaneFail(paneAlertRegisterCategory, lblAlertRegisterCategory, "Nenhuma categoria encontrada com esse nome!");
            }
        } else {
            AlertPaneModel.getInstance().setAlertPaneFail(paneAlertRegisterCategory, lblAlertRegisterCategory, "Por favor, preencha corretamente\no campo busca!");
        }
    }

    //table search result by name **********************************************
    @FXML
    public void tblCategorySearchCategoryResult() {
        String text = txtSearchList.getText();

        if (Validate.getInstance().validateName(text)) {
            clmCode.setCellValueFactory(new PropertyValueFactory<>("id"));
            clmCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
            clmDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

            List<CategoryModel> listTableSearchResults = new ArrayList<>();

            listTableAllCategorys.stream().filter((category) -> (category.getCategory().equals(text))).forEachOrdered((category) -> {
                listTableSearchResults.add(category);
            });

            observableCategory = FXCollections.observableArrayList(listTableSearchResults);

            tblCategory.setItems(observableCategory);
        } else {
            AlertPaneModel.getInstance().setAlertPaneFail(paneAlertSearchCategory, lblMsgSearchCategory, "Por favor, digite um nome válido válido!");
        }
    }

    //auto fill ----------------------------------------------------------------
    //table autofill ***********************************************************
    public void tblCategoryAutoFillAllCategorys(List<CategoryModel> listFillOfTable) {

        clmCode.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        clmDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        listTableAllCategorys = listFillOfTable;
        observableCategory = FXCollections.observableArrayList(listTableAllCategorys);

        tblCategory.setItems(observableCategory);
    }

    //fill in registration tab *************************************************
    public void fillForm(Long idCategory) {
        if (idCategory != null) {
            categoryModel = categoryDAO.searchById(idCategory);

            //category fields
            txtCode.setText(String.valueOf(categoryModel.getId()));
            txtCategory.setText(categoryModel.getCategory());
            txtDescription.setText(categoryModel.getDescription());

            tabPaneCategory.getSelectionModel().select(0);
        }
    }

    //clear table and search field the tab list ********************************
    @FXML
    public void clearTabList() {
        txtSearchList.setText("");
        tblCategoryAutoFillAllCategorys(categoryDAO.searchAllCategorys());
    }

    //clear fields *************************************************************
    @FXML
    public void btnNewCategoryOnAction() {
        AlertPaneModel.getInstance().setAlertPaneDefault(paneAlertRegisterCategory, lblAlertRegisterCategory, "Preencha os campos a seguir para cadastrar uma categoria");
        ClearFields.getInstance().clearScreen(this.anpRegisterCategory);
        tabPaneCategory.getSelectionModel().select(0);
    }

    //validations --------------------------------------------------------------
    // validate category *********************************************************
    public boolean validateCategory() {
        boolean checkCategory = Validate.getInstance().validateText(txtCategory);
        boolean checkDescription = Validate.getInstance().validateText(txtDescription);
        return checkCategory && checkDescription;
    }

}
