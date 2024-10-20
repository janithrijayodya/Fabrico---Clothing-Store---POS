
package edu.icet.controller.MakeOrder;

import edu.icet.entity.OrderEntity;
import edu.icet.entity.ProductEntity;
import edu.icet.entity.SupplierEntity;
import edu.icet.util.ServiceType;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import service.ServiceFactory;
import service.custom.OrderDetailsService;
import service.custom.OrderService;
import service.custom.ProductService;
import service.custom.SupplierService;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class makeOrderFormController implements Initializable {

    public CheckBox checkBoxCash;
    public CheckBox checkBoxCard;
    public Label lblSubTotal;
    public Label lblEmpIdinOrder;
    public Rectangle orderItems;
    public Label lblItem;
    public Label lblPrice;


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

    @FXML
    private TableView<ProductEntity> tblProducts;


    OrderService orderService  = ServiceFactory.getInstance().getServiceType(ServiceType.ORDER);
    OrderDetailsService orderDetailsService = ServiceFactory.getInstance().getServiceType(ServiceType.ORDERDETAILS);
    ProductService productService  = ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);

    @FXML
    public String userIDInOrder;


    public void getUserID(String userID){
//        System.out.println(userID);
        this.userIDInOrder = userID;
//        System.out.println(userIDInOrder);
    }


    @FXML
    void btnGentsOnClick(ActionEvent event) {

    }

    @FXML
    void btnKidsOnClick(ActionEvent event) {

    }

    @FXML
    void btnLadiesOnClick(ActionEvent event) {

    }

    @FXML
    void btnProceedOnClick(ActionEvent event) {

        OrderEntity order = new OrderEntity(
                                        lblOrderID.getText(),
                                        lblDate.getText(),
                                        lblEmpIdinOrder.getText()
                                );

        orderService.addOrder(order);

    }

    public void getGeneratedID(){
        String setOrderid = orderService.generateOrderID();
        lblOrderID.setText(setOrderid);
    }

    public void loadTable(){
        ObservableList<ProductEntity> load = productService.getAll();
        tblProducts.setItems(load);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getGeneratedID();

        colProductId.setCellValueFactory(new PropertyValueFactory<>("productID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        loadTable();

        lblDate.setText(String.valueOf(LocalDate.now()));

//        lblEmpIdinOrder.setText(userIDInOrder);

        tblProducts.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                String currentText = lblItem.getText(); // Get the current label text
                String currentPrice = lblPrice.getText();

                // Add the new product details to the current text
                lblItem.setText(currentText + "\n" + newSelection.getProductName());
                lblPrice.setText(currentPrice + "\n" + "Rs: " + newSelection.getPrice());
            }
        });

    }


}

