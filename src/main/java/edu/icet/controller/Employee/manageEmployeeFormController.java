package edu.icet.controller.Employee;

import edu.icet.entity.EmployeeEntity;
import edu.icet.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import service.ServiceFactory;
import service.custom.EmployeeService;

import java.net.URL;
import java.util.ResourceBundle;

public class manageEmployeeFormController implements Initializable {
    @FXML
    public ComboBox combo;
    @FXML
    public TextField txtEmpAddress;
    @FXML
    public TextField txtEmpPassword;
    @FXML
    private TextField txtEmpCompany;
    @FXML
    private TextField txtEmpEmail;
    @FXML
    private TextField txtEmpId;
    @FXML
    private TextField txtEmpName;


    EmployeeService employeeService = ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getGeneratedID();
    }

    @FXML
    void btnEmpAddOnClick(ActionEvent event) {
        String EmpId = txtEmpId.getText();
        String EmpName = txtEmpName.getText();
        String EmpCompany = txtEmpCompany.getText();
        String EmpEmail = txtEmpEmail.getText();
        String EmpAddress = txtEmpAddress.getText();
        String EmpPassword = txtEmpPassword.getText();

        EmployeeEntity employee = new EmployeeEntity(EmpId, EmpName, EmpCompany, EmpEmail,EmpAddress,EmpPassword);

//        System.out.println(service);

        if(employeeService.addEmployee(employee)){
            clearForm();
            getGeneratedID();
            new Alert(Alert.AlertType.INFORMATION,"Employee added successfully !! ").show();
        }else {
            clearForm();
            getGeneratedID();
            new Alert(Alert.AlertType.INFORMATION,"Employee is NOT added !! ").show();
        }
    }

    @FXML
    void btnEmpRemoveOnClick(ActionEvent event) {
        String EmpId = txtEmpId.getText();

        if (employeeService.removeEmployee(EmpId)){
            clearForm();
            getGeneratedID();
        }else {
            clearForm();
            getGeneratedID();
            new Alert(Alert.AlertType.INFORMATION,"NOT deleted !!").show();
        }
    }

    @FXML
    void btnEmpSearchOnClick(ActionEvent event) {

        String EmpId = txtEmpId.getText();
        EmployeeEntity employee = employeeService.searchEmployee(EmpId);
        if( employee != null){
            txtEmpId.setText(employee.getEmpId());
            txtEmpName.setText(employee.getEmpName());
            txtEmpCompany.setText(employee.getEmpCompany());
            txtEmpEmail.setText(employee.getEmpEmail());
            txtEmpAddress.setText(employee.getEmpAddress());
        }else {
            clearForm();
            getGeneratedID();
            new Alert(Alert.AlertType.INFORMATION,"Employee NOT found !!").show();
        }
    }

    @FXML
    void btnEmpUpdateOnClick(ActionEvent event) {
        EmployeeEntity employee= new EmployeeEntity(
                txtEmpId.getText(),
                txtEmpName.getText(),
                txtEmpCompany.getText(),
                txtEmpEmail.getText(),
                txtEmpAddress.getText(),
                txtEmpPassword.getText()
        );
        if (employeeService.updateEmployee(employee)){
            clearForm();
            getGeneratedID();
        }else {
            clearForm();
            getGeneratedID();
            new Alert(Alert.AlertType.INFORMATION,"NOT Updated !!");
        }
    }


    public void clearForm(){
        txtEmpEmail.clear();
        txtEmpId.clear();
        txtEmpCompany.clear();
        txtEmpName.clear();
        txtEmpAddress.clear();
        txtEmpPassword.clear();
    }

    public void getGeneratedID(){
        String setEmployeeid = employeeService.generateEmployeeID();
        txtEmpId.setText(setEmployeeid);
    }

}
