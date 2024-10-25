package repository.custom.Impl;

import edu.icet.db.DBConnection;
import edu.icet.entity.OrderEntity;
import edu.icet.util.CrudUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.custom.OrderDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean add(OrderEntity order) {
        String SQL = "INSERT INTO orders VALUES (?,?,?)";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(SQL);

            pstm.setObject(1, order.getOrder_id());
            pstm.setObject(2,order.getDate());
            pstm.setObject(3,order.getEmp_id());

            return  pstm.executeUpdate()>0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OrderEntity search(String id) {
        return null;
    }

    @Override
    public boolean update(OrderEntity order) {
        return false;
    }

    @Override
    public boolean remove(String id) {
        return false;
    }

    @Override
    public String generateID() {
        String SQL = "SELECT order_id  FROM orders ORDER BY order_id  DESC LIMIT 1";

        try {
            ResultSet resultSet = CrudUtil.execute(SQL);

            if (resultSet.next()) {
                String lastID = resultSet.getString("order_id");

                // Extract the numeric part from the employee ID
                int idNum = Integer.parseInt(lastID.replace("OID", ""));
                idNum++;

                // Format the new ID with leading zeros (e.g., "EID0006")
                return String.format("OID%04d", idNum);

            } else {
                return "OID0001";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<OrderEntity> getAll() {
        ObservableList<OrderEntity> orderEntityObservableList = FXCollections.observableArrayList();

        String SQL = "SELECT * FROM orders";

        try {
            ResultSet resultSet = CrudUtil.execute(SQL);

            while (resultSet.next()){
                orderEntityObservableList.add(
                            new OrderEntity(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3)
                        )
                );
            }

            return orderEntityObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
