
package edu.icet.controller.MakeOrder;

import edu.icet.entity.OrderDetailsEntity;
import edu.icet.entity.OrderEntity;
import edu.icet.entity.ProductEntity;
import edu.icet.util.ServiceType;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;
import service.ServiceFactory;
import service.custom.OrderDetailsService;
import service.custom.OrderService;
import service.custom.ProductService;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class makeOrderFormController implements Initializable {

    @FXML
    public CheckBox checkBoxCash;
    public CheckBox checkBoxCard;
    public Label lblSubTotal;
    public Label lblEmpIdinOrder;
    @FXML
    public Rectangle orderItems;
    @FXML
    public Label lblItem;
    @FXML
    public Label lblPrice;
    public TableView<ProductEntity> tblProducts;
    @FXML
    public TilePane loadProducts;
    public Label lblEmpId;

    @FXML
    private Button btnGents;
    @FXML
    private Button btnKids;
    @FXML
    private Button btnLadies;
    @FXML
    private Button btnProceed;

    @FXML
    private TableColumn<?, ?> colName;
    @FXML
    private TableColumn<?, ?> colPrice;
    @FXML
    private TableColumn<?, ?> colProductId;
    @FXML
    private TableColumn<?, ?> colQty;
    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private Label item;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblOrderID;
    @FXML
    private Label lblTotal;

    OrderService orderService = ServiceFactory.getInstance().getServiceType(ServiceType.ORDER);
    OrderDetailsService orderDetailsService = ServiceFactory.getInstance().getServiceType(ServiceType.ORDERDETAILS);
    ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);

    @FXML
    public String userIDInOrder="EID0001";

    Double subTotal = 0.0;

    @FXML
    public void getUserID(String userID) {
        this.userIDInOrder = userID;
    }

    @FXML
    void btnProceedOnClick(ActionEvent event) {

        // Ensure services are initialized
        if (orderService == null || orderDetailsService == null) {
            new Alert(Alert.AlertType.ERROR, "Services are not initialized!").show();
            return;
        }

        OrderEntity order = new OrderEntity(
                lblOrderID.getText(),
                lblDate.getText(),
                userIDInOrder
        );

         orderService.addOrder(order);
        // Prepare order details
        String paymentMethod = checkBoxCash.isSelected() ? "Cash" : "Credit_card";

        for (ProductEntity product : tblProducts.getSelectionModel().getSelectedItems()) {

                OrderDetailsEntity orderDetails = new OrderDetailsEntity(
                        lblOrderID.getText(),
                        product.getProductID(),
                        product.getQuantity(),
                        Double.parseDouble(lblTotal.getText()),
                        paymentMethod
                );

                // Attempt to add order details and order
                if (orderDetailsService.addOrderDetails(orderDetails) ) {
                    clearForm();
                    getGeneratedID();
                    new Alert(Alert.AlertType.INFORMATION, "Order is completed !!").show();
                } else {
                    clearForm();
                    getGeneratedID();
                    new Alert(Alert.AlertType.INFORMATION, "Order is NOT completed !!").show();
                }

        }

    }

    public void getGeneratedID() {
        String setOrderid = orderService.generateOrderID();
        lblOrderID.setText(setOrderid);
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    public void clearForm(){
        lblOrderID.setText("");
        lblEmpId.setText("");
        lblDate.setText("");
        lblItem.setText("");
        lblPrice.setText("");
        checkBoxCard.setSelected(false);
        checkBoxCash.setSelected(false);
        lblSubTotal.setText("");
        lblTotal.setText("");
    }

    public void loadTable() {
        ObservableList<ProductEntity> load = productService.getAll();
        tblProducts.setItems(load);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getGeneratedID();
        lblDate.setText(String.valueOf(LocalDate.now()));

        colProductId.setCellValueFactory(new PropertyValueFactory<>("productID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        loadTable();

//        lblEmpIdinOrder.setText(userIDInOrder);

        tblProducts.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                String currentText = lblItem.getText();
                String currentPrice = lblPrice.getText();

                lblItem.setText(currentText + "\n" + newSelection.getProductID() + "\t" + newSelection.getProductName());
                lblPrice.setText(currentPrice + "\n" + "Rs: " + newSelection.getPrice());

                subTotal += newSelection.getPrice();
                lblSubTotal.setText("Rs: " + subTotal);
                lblTotal.setText(String.valueOf(subTotal - (subTotal * 0.05)));
            }
        });
    }

    public void btnGentsOnClick(ActionEvent actionEvent) {
    }

    public void btnLadiesOnClick(ActionEvent actionEvent) {
    }

    public void btnKidsOnClick(ActionEvent actionEvent) {
    }
}
