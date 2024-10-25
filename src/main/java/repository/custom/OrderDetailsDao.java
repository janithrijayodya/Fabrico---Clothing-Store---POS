package repository.custom;

import edu.icet.entity.OrderDetailsEntity;
import repository.CrudDao;

import java.util.List;

public interface OrderDetailsDao extends CrudDao<OrderDetailsEntity> {
    List<OrderDetailsEntity> getAllOrderDetails(String orderId);
}
