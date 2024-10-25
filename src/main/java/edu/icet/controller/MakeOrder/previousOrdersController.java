package edu.icet.controller.MakeOrder;

import edu.icet.entity.OrderDetailsEntity;
import edu.icet.entity.OrderEntity;
import edu.icet.util.ServiceType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.TilePane;
import service.ServiceFactory;
import service.custom.OrderDetailsService;
import service.custom.OrderService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class previousOrdersController implements Initializable {

    @FXML
    private TilePane AllCardsLoader;

    OrderService orderService = ServiceFactory.getInstance().getServiceType(ServiceType.ORDER);
    OrderDetailsService orderDetailsService = ServiceFactory.getInstance().getServiceType(ServiceType.ORDERDETAILS);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadCards();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadCards() throws IOException {
        List<OrderEntity> orders = orderService.getAll();
        for (OrderEntity order : orders) {
            // =======GET ORDER DETAILS, RELATED TO ORDER ID==========
            List<OrderDetailsEntity> orderDetails = orderDetailsService.getOrderDetailsByOrderId(order.getOrder_id());
            // ===== LOAD CARD========
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/orderCard.fxml"));
            Node orderCard = loader.load();
            // GET THE CONTROLLER OF THE ORDERCARDCONTROLLER AND PASS ORDER AND ORDER DETAILS TO THE CARD CONTROLLER======
            orderCardController controller = loader.getController();
            controller.setOrderData(order, orderDetails);
            //========ADD CARD TO THE TILE PANE==========
            AllCardsLoader.getChildren().add(orderCard);
        }
    }
}
