package edu.icet.controller.Employee;

import edu.icet.model.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class manageEmployeeFormController implements Initializable {

    public ComboBox combo;
    @FXML
    private Button btnEmpAdd;

    @FXML
    private Button btnEmpRemove;

    @FXML
    private Button btnEmpSearch;

    @FXML
    private Button btnEmpUpdate;

    @FXML
    private Button btnEmployee;

    @FXML
    private Button btnSupplier;

    @FXML
    private TextField txtEmpCompany;

    @FXML
    private TextField txtEmpEmail;

    @FXML
    private TextField txtEmpId;

    @FXML
    private TextField txtEmpName;

    employeeService service = manageEmployeeController.getInstance();


    @FXML
    void btnEmpAddOnClick(ActionEvent event) {
        String EmpId = txtEmpId.getText();
        String EmpName = txtEmpName.getText();
        String EmpCompany = txtEmpCompany.getText();
        String EmpEmail = txtEmpEmail.getText();

        Employee employee = new Employee(EmpId, EmpName, EmpCompany, EmpEmail);

//        System.out.println(service);

        if(service.addEmployee(employee)){
            clearForm();
            new Alert(Alert.AlertType.INFORMATION,"Employee added successfully !! ").show();
        }else {
            clearForm();
            new Alert(Alert.AlertType.INFORMATION,"Employee is NOT added !! ").show();
        }


        // when added a employe ,regenerate the ID

        String setEmployeeid = service.generateEmployeeID();
        txtEmpId.setText(setEmployeeid);

    }

    @FXML
    void btnEmpRemoveOnClick(ActionEvent event) {
        String EmpId = txtEmpId.getText();

        if (service.removeEmployee(EmpId)){
            clearForm();
        }else {
            new Alert(Alert.AlertType.INFORMATION,"NOT deleted !!").show();
        }
    }

    @FXML
    void btnEmpSearchOnClick(ActionEvent event) {
        String EmpId = txtEmpId.getText();
        Employee employee = service.searchEmployee(EmpId);

        if( employee != null){
            txtEmpId.setText(employee.getEmpId());
            txtEmpName.setText(employee.getEmpName());
            txtEmpCompany.setText(employee.getEmpCompany());
            txtEmpEmail.setText(employee.getEmpEmail());
        }else {
            clearForm();
            new Alert(Alert.AlertType.INFORMATION,"Employee NOT found !!").show();
        }
    }

    @FXML
    void btnEmpUpdateOnClick(ActionEvent event) {
        Employee employee= new Employee(
                txtEmpId.getText(),
                txtEmpName.getText(),
                txtEmpCompany.getText(),
                txtEmpEmail.getText()
        );
        if (service.updateEmployee(employee)){
            clearForm();
        }else {
            clearForm();
            new Alert(Alert.AlertType.INFORMATION,"NOT Updated !!");
        }
    }


    public void clearForm(){
        txtEmpEmail.clear();
        txtEmpId.clear();
        txtEmpCompany.clear();
        txtEmpName.clear();
    }

//    private int num = 1;
//
//    public void generateEmployeeID(){
//
//        String EmployeeID = "EID"+ String.format("%04d", num) ;
//
//        txtEmpId.setText(EmployeeID);
//
//        num++;
//    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String setEmployeeid = service.generateEmployeeID();
        txtEmpId.setText(setEmployeeid);
    }


}
