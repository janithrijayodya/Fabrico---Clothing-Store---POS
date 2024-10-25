package edu.icet.controller.Supplier;

import edu.icet.entity.SupplierEntity;
import edu.icet.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import service.ServiceFactory;
import service.SuperService;
import service.custom.EmployeeService;
import service.custom.SupplierService;

import java.net.URL;
import java.util.ResourceBundle;

public class manageSupplierFormController implements Initializable {

    @FXML
    public Button btnSupReload;
    @FXML
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

    SupplierService supplierService = ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setGeneratedID();
    }

    public void setGeneratedID(){
        String supplierID = supplierService.generateSupplierID();
        txtSupplierId.setText(supplierID);
    }

    @FXML
    void btnSupAddOnClick(ActionEvent event) {
        SupplierEntity supplier = new SupplierEntity(
               txtSupplierId.getText(),
               txtSupplierName.getText(),
               txtSupplierCompany.getText(),
               txtSupplierEmail.getText()
        );
        if(supplierService.addSupplier(supplier)){
            clearSupplierForm();
            setGeneratedID();
            new Alert(Alert.AlertType.INFORMATION,"Supplier added !!").show();

        }else{
            clearSupplierForm();
            setGeneratedID();
            new Alert(Alert.AlertType.INFORMATION,"Supplier NOT added !!").show();
        }
    }


    @FXML
    void btnSupRemoveOnClick(ActionEvent event) {
        String supId = txtSupplierId.getText();
        if(supplierService.removeSupplier(supId)){
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
        SupplierEntity supplier =supplierService.searchSupplier(supId);
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
        SupplierEntity supplier = new SupplierEntity(
                txtSupplierId.getText(),
                txtSupplierName.getText(),
                txtSupplierCompany.getText(),
                txtSupplierEmail.getText()
        );
        if(supplierService.updateSupplier(supplier)){
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

}

