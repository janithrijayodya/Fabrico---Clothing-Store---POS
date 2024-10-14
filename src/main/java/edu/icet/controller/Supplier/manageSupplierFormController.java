package edu.icet.controller.Supplier;

import edu.icet.model.Supplier;
import edu.icet.util.CrudUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class manageSupplierFormController implements Initializable {

    public TableColumn colSupplierID;
    public TableColumn colSupplierName;
    public TableColumn colSupplierCompany;
    public TableColumn colSupplierEmail;
    public Button btnSupReload;
    public Button btnSupPrint;
    @FXML
    private Button btnSupAdd;

    @FXML
    private Button btnSupRemove;

    @FXML
    private Button btnSupSearch;

    @FXML
    private Button btnSupUpdate;

    @FXML
    private TextField txtSupplierCompany;

    @FXML
    private TextField txtSupplierEmail;

    @FXML
    private TextField txtSupplierId;

    @FXML
    private TextField txtSupplierName;

    supplierService service = manageSupplierController.getInstance();

    @FXML
    void btnSupAddOnClick(ActionEvent event) {
        Supplier supplier = new Supplier(
               txtSupplierId.getText(),
               txtSupplierName.getText(),
               txtSupplierCompany.getText(),
               txtSupplierEmail.getText()
        );

        if(service.addSupplier(supplier)){
            clearSupplierForm();
            setGeneratedID();
            new Alert(Alert.AlertType.INFORMATION,"Supplier added !!").show();

            String supplierID = service.generateSupplierID();
            txtSupplierId.setText(supplierID);
        }else{
            clearSupplierForm();
            setGeneratedID();
            new Alert(Alert.AlertType.INFORMATION,"Supplier NOT added !!").show();
        }
    }


    @FXML
    void btnSupRemoveOnClick(ActionEvent event) {
        String supId = txtSupplierId.getText();
        if(service.removeSupplier(supId)){
            clearSupplierForm();
            setGeneratedID();
        }else {
            clearSupplierForm();
            setGeneratedID();
            new Alert(Alert.AlertType.INFORMATION,"NOT deleted !!").show();
        }
    }

    @FXML
    void btnSupSearchOnClick(ActionEvent event) {

        String supId = txtSupplierId.getText();

        Supplier supplier =service.searchSupplier(supId);

        if(supplier != null){
            txtSupplierId.setText(supplier.getSupplierID());
            txtSupplierName.setText(supplier.getSupplierName());
            txtSupplierCompany.setText(supplier.getSupplierCompany());
            txtSupplierEmail.setText(supplier.getSupplierEmail());
        }else{
            clearSupplierForm();
            setGeneratedID();
            new Alert(Alert.AlertType.INFORMATION,"Supplier NOT found !!").show();
        }

    }

    @FXML
    void btnSupUpdateOnClick(ActionEvent event) {
        Supplier supplier = new Supplier(
                txtSupplierId.getText(),
                txtSupplierName.getText(),
                txtSupplierCompany.getText(),
                txtSupplierEmail.getText()
        );

        if(service.updateSupplier(supplier)){
            clearSupplierForm();
            setGeneratedID();
        }else {
            clearSupplierForm();
            setGeneratedID();
            new Alert(Alert.AlertType.INFORMATION,"NOT Updated !!");
        }

    }

    public void clearSupplierForm(){
        txtSupplierCompany.clear();
        txtSupplierEmail.clear();
        txtSupplierId.clear();
        txtSupplierName.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setGeneratedID();
    }

    public void setGeneratedID(){
        String supplierID = service.generateSupplierID();
        txtSupplierId.setText(supplierID);
    }


    //========================LOAD TABLE========================


    public void btnSupReloadOnClick(ActionEvent actionEvent) {
    }

    public void btnSupPrintOnClick(ActionEvent actionEvent) {
    }
}

