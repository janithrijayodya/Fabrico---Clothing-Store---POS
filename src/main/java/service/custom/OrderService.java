package service.custom;

import edu.icet.entity.OrderEntity;
import edu.icet.entity.ProductEntity;
import service.SuperService;

import java.util.List;

public interface OrderService extends SuperService {
    boolean addOrder(OrderEntity order);
    String generateOrderID();
}
