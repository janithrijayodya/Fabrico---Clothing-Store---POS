package service.custom.Impl;

import edu.icet.entity.OrderDetailsEntity;
import edu.icet.util.DaoType;
import repository.DaoFactory;
import repository.custom.OrderDetailsDao;
import service.custom.OrderDetailsService;

public class OrderDetailsServiceImpl implements OrderDetailsService {

    OrderDetailsDao orderDetailsDao   =  DaoFactory.getInstance().getDaoType(DaoType.ORDERDETAILS);

    @Override
    public boolean addOrderDetails(OrderDetailsEntity orderDetails) {
        return orderDetailsDao.add(orderDetails);
    }
}
