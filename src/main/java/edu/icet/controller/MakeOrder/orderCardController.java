package edu.icet.controller.MakeOrder;

import edu.icet.entity.OrderDetailsEntity;
import edu.icet.entity.OrderEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class orderCardController {
    @FXML
    public VBox perCard;
    @FXML
    public VBox allItems;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblOrderID;
    @FXML
    private Label lblTotal;

    public void setOrderData(OrderEntity order, List<OrderDetailsEntity> orderDetails) {
        lblOrderID.setText(order.getOrder_id());
        lblDate.setText(order.getDate());
        Double total =0.0;

        for(OrderDetailsEntity details : orderDetails){
            HBox itemRow = new HBox(60);
            Label itemCode =  new Label(details.getProduct_id());
            Label quantity =  new Label(String.valueOf(details.getQuantity()));
            Label price =  new Label(String.valueOf(details.getTotal_amount()));

            itemRow.getChildren().addAll(itemCode, quantity, price);
            allItems.getChildren().add(itemRow);

            double subTotal=total+details.getTotal_amount();
            total = subTotal-(subTotal*0.05);
        }
        lblTotal.setText(String.format("Rs: %.2f", total));
    }
}
