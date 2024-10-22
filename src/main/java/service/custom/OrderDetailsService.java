package service.custom;

import edu.icet.entity.OrderDetailsEntity;
import service.SuperService;

public interface OrderDetailsService extends SuperService {

    boolean addOrderDetails(OrderDetailsEntity orderDetails);
}
