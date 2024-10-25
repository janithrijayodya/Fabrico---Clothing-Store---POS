package service.custom;

import edu.icet.entity.OrderEntity;
import service.SuperService;

import java.util.List;

public interface OrderService extends SuperService {
    boolean addOrder(OrderEntity order);
    String generateOrderID();
    List<OrderEntity> getAll();
}
