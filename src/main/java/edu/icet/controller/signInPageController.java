package edu.icet.controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

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
    private TextField txtManSignUpName;

    @FXML
    private TextField txtManSignUpPswrd;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblSignUp.setVisible(false);
        txtManSignUpName.setVisible(false);
        txtManSignUpPswrd.setVisible(false);
        btnManSignUp.setVisible(false);
        txtManSignUpReEnterPswrd.setVisible(false);
        lblPasswordTitle.setVisible(false);
        lblPswrdError1.setVisible(false);
        lblPswrdError2.setVisible(false);
    }


    @FXML
    void btnSignInOnClick(ActionEvent event) {
        

    }

    @FXML
    void btnSignUp2OnClick(ActionEvent event) {

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

        lblSignUp.setVisible(true);
        txtManSignUpName.setVisible(true);
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

            txtManSignUpName.setText("");
            txtManSignUpPswrd.setText("");
            txtManSignUpReEnterPswrd.setText("");
        }else {
            lblPswrdError1.setVisible(false);

            txtManSignUpName.setText("");
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
            txtEmpSignInpswrd.setVisible(true);
            btnEmpSignIn.setVisible(true);
            lblManTitle.setVisible(true);
            btnManSignUp2.setVisible(true);

            lblSignUp.setVisible(false);
            txtManSignUpName.setVisible(false);
            txtManSignUpPswrd.setVisible(false);
            btnManSignUp.setVisible(false);
            txtManSignUpReEnterPswrd.setVisible(false);
            lblPasswordTitle.setVisible(false);
        }



    }


}
