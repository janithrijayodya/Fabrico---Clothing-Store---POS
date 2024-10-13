package edu.icet.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class overviewFormController {

    @FXML
    public Label pageTitle;
    @FXML
    private Button btnCategories;

    @FXML
    private Button btnEmployeeReport;

    @FXML
    private Button btnInventoryReport;

    @FXML
    private Button btnOrders;

    @FXML
    private Button btnProductManagement;

    @FXML
    private Button btnSalesReport;

    @FXML
    private Button btnSupplierReport;

    @FXML
    private Button btnUserManagement;


    @FXML
    private AnchorPane childAnchorpane;

    @FXML
    void categoriesOnClick(ActionEvent event) {

    }

    @FXML
    void employeeReportOnClick(ActionEvent event) {

    }

    @FXML
    void inventoryReportOnClick(ActionEvent event) {

    }

    @FXML
    void ordersOnClick(ActionEvent event) {

    }

    @FXML
    void productManagementOnClick(ActionEvent event) {

    }

    @FXML
    void salesReportOnClick(ActionEvent event) {

    }

    @FXML
    void supplierReportOnClick(ActionEvent event) {

    }

    @FXML
    void userManagementOnClick(ActionEvent event) throws IOException {
        pageTitle.setText("User Management");

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/manageEmployeeForm.fxml"));
        childAnchorpane.getChildren().setAll(pane);
    }

}
