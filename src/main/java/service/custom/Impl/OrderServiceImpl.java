package service.custom.Impl;

import edu.icet.entity.OrderEntity;
import edu.icet.util.DaoType;
import repository.DaoFactory;
import repository.custom.OrderDao;
import service.custom.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    OrderDao orderDao  =  DaoFactory.getInstance().getDaoType(DaoType.ORDER);

    @Override
    public boolean addOrder(OrderEntity order) {
        return orderDao.add(order);
    }

    @Override
    public String generateOrderID() {
        return orderDao.generateID();
    }

    @Override
    public List<OrderEntity> getAll() {
        return orderDao.getAll();
    }

}
