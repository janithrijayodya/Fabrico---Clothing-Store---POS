package edu.icet.controller;

import edu.icet.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import service.ServiceFactory;
import service.custom.EmployeeService;
import service.custom.OwnerService;

public class forgotPasswordController {

    @FXML
    private TextField txtNewPassword;

    @FXML
    private TextField txtNewPassword2;

    @FXML
    private TextField txtUserID;

    EmployeeService employeeService = ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);
    OwnerService ownerService = ServiceFactory.getInstance().getServiceType(ServiceType.OWNER);

    @FXML
    void btnEmpUpdateOnClick(ActionEvent event) {
        String userID = txtUserID.getText();
        String userPassword = txtNewPassword.getText();
        String reEnterUserPassword = txtNewPassword2.getText();
        String pattern = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{8,}$";

        if(userID.charAt(0) == 'M' && userPassword.equals(reEnterUserPassword) && userPassword.matches(pattern)){
            if(ownerService.updatePassword(userID,userPassword)){
                clearForm();
                new Alert(Alert.AlertType.INFORMATION,"Updated!").show();
            }else {
                clearForm();
                new Alert(Alert.AlertType.INFORMATION,"NOT updated!").show();
            }
        } else if (userID.charAt(0) == 'E' && userPassword.equals(reEnterUserPassword) && userPassword.matches(pattern)){
            if(employeeService.updatePassword(userID,userPassword)){
                clearForm();
                new Alert(Alert.AlertType.INFORMATION,"Updated!").show();
            }else {
                clearForm();
                new Alert(Alert.AlertType.INFORMATION,"NOT updated!").show();
            }
        }else{
            clearForm();
            new Alert(Alert.AlertType.INFORMATION,"Invalid UserID or Password !").show();
        }
    }

    public void clearForm(){
        txtUserID.clear();
        txtNewPassword.clear();
        txtNewPassword2.clear();
    }

}

