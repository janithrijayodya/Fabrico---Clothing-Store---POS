package repository.custom.Impl;

import edu.icet.db.DBConnection;
import edu.icet.entity.OrderDetailsEntity;
import edu.icet.util.CrudUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.custom.OrderDetailsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailsDaoImpl implements OrderDetailsDao {
    @Override
    public boolean add(OrderDetailsEntity orderDetailsEntity) {
        String SQL = "INSERT INTO order_details VALUES(?,?,?,?,?)";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(SQL);

            pstm.setObject(1, orderDetailsEntity.getOrder_id());
            pstm.setObject(2,orderDetailsEntity.getProduct_id());
            pstm.setObject(3,orderDetailsEntity.getQuantity());
            pstm.setObject(4,orderDetailsEntity.getTotal_amount());
            pstm.setObject(5,orderDetailsEntity.getPayment_type());

            return  pstm.executeUpdate()>0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OrderDetailsEntity search(String id) {
        return null;
    }

    @Override
    public boolean update(OrderDetailsEntity orderDetailsEntity) {
        return false;
    }

    @Override
    public boolean remove(String id) {
        return false;
    }

    @Override
    public String generateID() {
        return "";
    }

    @Override
    public ObservableList<OrderDetailsEntity> getAll() {
        return null;
    }

    @Override
    public List<OrderDetailsEntity> getAllOrderDetails(String orderId) {
        ObservableList<OrderDetailsEntity> orderDetailsEntityObservableList = FXCollections.observableArrayList();

        String SQL = "SELECT * FROM order_details WHERE order_id=?";

        try {
            ResultSet resultSet = CrudUtil.execute(SQL,orderId);

            while (resultSet.next()){
                orderDetailsEntityObservableList.add(
                        new OrderDetailsEntity(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getInt(3),
                                resultSet.getDouble(4),
                                resultSet.getString(5)
                        )
                );
            }

            return orderDetailsEntityObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
