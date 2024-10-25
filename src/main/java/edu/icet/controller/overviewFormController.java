package edu.icet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class overviewFormController {

    @FXML
    public Label pageTitle;
    @FXML
    public ComboBox comboBpx;
    @FXML
    public Button btnSupplierManagement;
    @FXML
    private AnchorPane childAnchorpane;

    @FXML
    void categoriesOnClick(ActionEvent event) throws IOException {
        pageTitle.setText("Place Orders");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/MakeOrder.fxml"));
        childAnchorpane.getChildren().setAll(pane);
    }

    @FXML
    void employeeReportOnClick(ActionEvent event) throws IOException {
        pageTitle.setText("Employee Report");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/employeeReportForm.fxml"));
        childAnchorpane.getChildren().setAll(pane);
    }

    @FXML
    void inventoryReportOnClick(ActionEvent event) {

    }

    @FXML
    void ordersOnClick(ActionEvent event) throws IOException {
        pageTitle.setText("Previous Orders");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/previousOrders.fxml"));
        childAnchorpane.getChildren().setAll(pane);
    }

    @FXML
    void productManagementOnClick(ActionEvent event) throws IOException {
        pageTitle.setText("Product Management");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/manageProduct.fxml"));
        childAnchorpane.getChildren().setAll(pane);
    }

    @FXML
    void salesReportOnClick(ActionEvent event) {

    }

    @FXML
    void supplierReportOnClick(ActionEvent event) throws IOException {
        pageTitle.setText("Supplier Report");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/supplierReportForm.fxml"));
        childAnchorpane.getChildren().setAll(pane);
    }

    @FXML
    void userManagementOnClick(ActionEvent event) throws IOException {
        pageTitle.setText("User Management");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/manageEmployeeForm.fxml"));
        childAnchorpane.getChildren().setAll(pane);
    }


    public void supplierManagementOnClick(ActionEvent actionEvent) throws IOException {
        pageTitle.setText("User Management");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/manageSupplierForm.fxml"));
        childAnchorpane.getChildren().setAll(pane);
    }
}
