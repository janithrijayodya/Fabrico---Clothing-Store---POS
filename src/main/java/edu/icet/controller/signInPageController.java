package edu.icet.controller;

import edu.icet.controller.MakeOrder.makeOrderFormController;
import edu.icet.entity.OwnerEntity;
import edu.icet.util.ServiceType;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import repository.custom.OwnerDao;
import service.ServiceFactory;
import service.custom.EmployeeService;
import service.custom.OwnerService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class signInPageController implements Initializable {

    @FXML
    public Rectangle layer1;
    @FXML
    public Rectangle layer2;
    @FXML
    public TextField txtManSignUpReEnterPswrd;
    @FXML
    public Label lblPasswordTitle;
    @FXML
    public Label lblPswrdError1;
    @FXML
    public Label lblPswrdError2;
    @FXML
    public TextField txtManSignUpEmail;
    public Label lblForgotPassword;
    @FXML
    private Button btnEmpSignIn;
    @FXML
    private Button btnManSignUp;
    @FXML
    private Button btnManSignUp2;
    @FXML
    private Label lblLogIn;
    @FXML
    private Label lblManTitle;
    @FXML
    private Label lblSignUp;
    @FXML
    private TextField txtEmpSignInName;
    @FXML
    private TextField txtEmpSignInpswrd;
    @FXML
    private TextField txtManSignUpPswrd;

    OwnerService ownerService = ServiceFactory.getInstance().getServiceType(ServiceType.OWNER);
    EmployeeService employeeService  = ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblSignUp.setVisible(false);
        txtManSignUpEmail.setVisible(false);
        txtManSignUpPswrd.setVisible(false);
        btnManSignUp.setVisible(false);
        txtManSignUpReEnterPswrd.setVisible(false);
        lblPasswordTitle.setVisible(false);
        lblPswrdError1.setVisible(false);
        lblPswrdError2.setVisible(false);
    }


    @FXML
    void btnSignInOnClick(ActionEvent event) {
//
//        makeOrderFormController id =  new makeOrderFormController();
//        id.getUserID(txtEmpSignInName.getText());
//        System.out.println(txtEmpSignInName.getText());

        String  userID = txtEmpSignInName.getText();
        String userPassword = txtEmpSignInpswrd.getText();
        if(userID.charAt(0)=='M'){
            if(ownerService.OwnerSignInValidation(userID,userPassword)){
                Stage stage =  new Stage();
                try {
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/overviewForm.fxml"))));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.show();
            }else {
                new Alert(Alert.AlertType.INFORMATION,"Incorrect user ID or password !").show();
            }
        } else if (userID.charAt(0) == 'E') {
            if(employeeService.EmployeeSignInValidation(userID,userPassword)){
                Stage stage =  new Stage();
                try {
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/overviewForm.fxml"))));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.show();
            }else {
                new Alert(Alert.AlertType.INFORMATION,"Incorrect user ID or password !").show();
            }
        }
    }

    @FXML
    void btnSignUp2OnClick(ActionEvent event) {
        //======== TRANSFER LAYERS=======
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(layer1);

        slide.play();

        layer2.setTranslateX(344);

        lblLogIn.setVisible(false);
        txtEmpSignInName.setVisible(false);
        txtEmpSignInpswrd.setVisible(false);
        btnEmpSignIn.setVisible(false);
        lblManTitle.setVisible(false);
        btnManSignUp2.setVisible(false);
        lblForgotPassword.setVisible(false);

        lblSignUp.setVisible(true);
        txtManSignUpEmail.setVisible(true);
        txtManSignUpPswrd.setVisible(true);
        btnManSignUp.setVisible(true);
        txtManSignUpReEnterPswrd.setVisible(true);
        lblPasswordTitle.setVisible(true);

    }

    @FXML
    void btnSignUpOnClick(ActionEvent event) {

        String pattern = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{8,}$";

        //===========password validation================
        if(!(txtManSignUpPswrd.getText().equals(txtManSignUpReEnterPswrd.getText()) && txtManSignUpPswrd.getText().matches(pattern))){
            lblPswrdError1.setVisible(true);

            txtManSignUpEmail.setText("");
            txtManSignUpPswrd.setText("");
            txtManSignUpReEnterPswrd.setText("");
        }else {
            lblPswrdError1.setVisible(false);
            lblForgotPassword.setVisible(true);

            //======================PASS OWNER'S DETAILS TO SERVICE LAYER=====================
            String ID =  ownerService.generateOwnerID();

            OwnerEntity owner = new OwnerEntity(
                    ID,
                    txtManSignUpEmail.getText(),
                    txtManSignUpPswrd.getText()
            );
           if(ownerService.addOwner(owner)){
               new Alert(Alert.AlertType.INFORMATION,"Added successfully ");
           }else{
               new Alert(Alert.AlertType.INFORMATION,"NOT added ! ");
           }


            txtManSignUpEmail.setText("");
            txtManSignUpPswrd.setText("");
            txtManSignUpReEnterPswrd.setText("");

            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.7));
            slide.setNode(layer1);

//        slide.setToX(0);
            slide.play();

            layer2.setTranslateX(0);

            lblLogIn.setVisible(true);
            txtEmpSignInName.setVisible(true);
            txtEmpSignInName.setText(ID);
            txtEmpSignInpswrd.setVisible(true);
            btnEmpSignIn.setVisible(true);
            lblManTitle.setVisible(true);
            btnManSignUp2.setVisible(true);

            lblSignUp.setVisible(false);
            txtManSignUpEmail.setVisible(false);
            txtManSignUpPswrd.setVisible(false);
            btnManSignUp.setVisible(false);
            txtManSignUpReEnterPswrd.setVisible(false);
            lblPasswordTitle.setVisible(false);
        }
    }

    public void passwordUpdateOnClick(MouseEvent mouseEvent) {
        Stage stage =  new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/forgotPassword.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }
}
