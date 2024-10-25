
package edu.icet.controller.MakeOrder;

import edu.icet.entity.OrderDetailsEntity;
import edu.icet.entity.OrderEntity;
import edu.icet.entity.ProductEntity;
import edu.icet.util.ServiceType;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;
import service.ServiceFactory;
import service.custom.OrderDetailsService;
import service.custom.OrderService;
import service.custom.ProductService;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class makeOrderFormController implements Initializable {

    @FXML
    public CheckBox checkBoxCash;
    @FXML
    public CheckBox checkBoxCard;
    @FXML
    public Label lblSubTotal;
    @FXML
    public Label lblEmpIdinOrder;
    @FXML
    public Rectangle orderItems;
    @FXML
    public Label lblItem;
    @FXML
    public Label lblPrice;
    @FXML
    public TableView<ProductEntity> tblProducts;
    @FXML
    public TilePane loadProducts;
    @FXML
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
    private TableColumn<ProductEntity, String> colName;
    @FXML
    private TableColumn<ProductEntity, Double> colPrice;
    @FXML
    private TableColumn<ProductEntity, String> colProductId;
    @FXML
    private TableColumn<ProductEntity, Integer> colQty;
    @FXML
    private TableColumn<ProductEntity, String> colSize;
    @FXML
    private Label item;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblOrderID;
    @FXML
    private Label lblTotal;
    @FXML
    public String userIDInOrder = "EID0001";

    private Double subTotal = 0.0;

    // =================== LIST TO STORE SELECTED RECORDS===============
    private List<ProductEntity> selectedProducts = new ArrayList<>();

    OrderService orderService = ServiceFactory.getInstance().getServiceType(ServiceType.ORDER);
    OrderDetailsService orderDetailsService = ServiceFactory.getInstance().getServiceType(ServiceType.ORDERDETAILS);
    ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getGeneratedID();
        lblDate.setText(String.valueOf(LocalDate.now()));

        //===========LOAD ALL PRODUCTS TO THE TABLE====================
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        loadTable();

        // =============EVENT HANDLING => WHEN CLICK ON A RECORD, THE RECORD ADD TO ORDER DETAILS BAR===========
        tblProducts.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                addProductToOrderDetails(newSelection);
            }
        });
    }

    // ===========PASS SELECTED RECORD TO THIS METHOD==========
    private void addProductToOrderDetails(ProductEntity product) {
        // ========ADD SELECTED RECORDS TO THE ARRAY LIST=========
        selectedProducts.add(product);

        // ========= SHOW SELECTED RECORDS IN ORDER DETAILS BAR=======
        String currentText = lblItem.getText();
        String currentPrice = lblPrice.getText();
        lblItem.setText(currentText + "\n" + product.getProductID() + "\t" + product.getProductName());
        lblPrice.setText(currentPrice + "\n" + "Rs: " + product.getPrice());

        // ========= UPDATE SUB TOTAL AND TOTAL AMOUNT========
        subTotal += product.getPrice();
        lblSubTotal.setText("Rs: " + subTotal);
        lblTotal.setText("Rs: " + (subTotal - (subTotal * 0.05))); // Assuming 5% discount
    }

    @FXML
    void btnProceedOnClick(ActionEvent event) {
        // ENSURE SERVICES ARE INITIALIZED==========
        if (orderService == null || orderDetailsService == null) {
            new Alert(Alert.AlertType.ERROR, "Services are not initialized!").show();
            return;
        }

        // ======== CREATE NEW ORDER========
        OrderEntity order = new OrderEntity(
                lblOrderID.getText(),
                lblDate.getText(),
                userIDInOrder
        );
        orderService.addOrder(order);


        String paymentMethod = checkBoxCash.isSelected() ? "cash" : "credit_card";
        Integer qty = 1;
        // ======CREATE NEW ORDER DETAILS===========
        for (ProductEntity product : selectedProducts) {
            OrderDetailsEntity orderDetails = new OrderDetailsEntity(
                    lblOrderID.getText(),
                    product.getProductID(),
                    qty,
                    product.getPrice()*qty ,
                    paymentMethod
            );

            if (!orderDetailsService.addOrderDetails(orderDetails)) {
                new Alert(Alert.AlertType.ERROR, "Order details could not be saved!").show();
                return;
            }
        }

        clearForm();
        getGeneratedID();
        new Alert(Alert.AlertType.INFORMATION, "Order is completed!").show();
    }

    public void btnClearOnClick(ActionEvent actionEvent) {
        clearForm();
        getGeneratedID();
    }

    public void getGeneratedID() {
        String setOrderId = orderService.generateOrderID();
        lblOrderID.setText(setOrderId);
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    public void clearForm() {
        lblOrderID.setText("");
        lblEmpId.setText("");
        lblDate.setText("");
        lblItem.setText("");
        lblPrice.setText("");
        checkBoxCard.setSelected(false);
        checkBoxCash.setSelected(false);
        lblSubTotal.setText("");
        lblTotal.setText("");
        selectedProducts.clear();
    }

    public void loadTable() {
        ObservableList<ProductEntity> load = productService.getAll();
        tblProducts.setItems(load);
    }

    public void btnGentsOnClick(ActionEvent actionEvent) {}

    public void btnLadiesOnClick(ActionEvent actionEvent) {}

    public void btnKidsOnClick(ActionEvent actionEvent) {}

}

