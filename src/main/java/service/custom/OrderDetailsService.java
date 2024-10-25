package service.custom;

import edu.icet.entity.OrderDetailsEntity;
import service.SuperService;

import java.util.List;

public interface OrderDetailsService extends SuperService {

    boolean addOrderDetails(OrderDetailsEntity orderDetails);
    List<OrderDetailsEntity> getOrderDetailsByOrderId(String orderId);
}
