package repository.custom.Impl;

import edu.icet.entity.SupplierEntity;
import edu.icet.util.CrudUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.custom.SupplierDao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierDaoImpl implements SupplierDao {

    @Override
    public boolean add(SupplierEntity supplier) {
        String SQL = "INSERT INTO supplier VALUES(?,?,?,?)";
        try {
            return  CrudUtil.execute(SQL,
                    supplier.getSupplierID(),
                    supplier.getSupplierName(),
                    supplier.getSupplierCompany(),
                    supplier.getSupplierEmail()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SupplierEntity search(String id) {
        String SQL ="SELECT * FROM supplier WHERE supplier_id=?";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL,id);

            while (resultSet.next()){
                return new SupplierEntity(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean update(SupplierEntity supplier) {

        String SQL= "UPDATE supplier SET  name=?, company=?, email=?";
        try {
            return   CrudUtil.execute(SQL,
                    supplier.getSupplierName(),
                    supplier.getSupplierCompany(),
                    supplier.getSupplierEmail()
            );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean remove(String id) {

        String SQL = "DELETE FROM supplier WHERE supplier_id=?";

        try {
            return  CrudUtil.execute(SQL,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String generateID() {

        String SQL = "SELECT supplier_id  FROM supplier ORDER BY supplier_id  DESC LIMIT 1";

        try {
            ResultSet resultSet = CrudUtil.execute(SQL);

            if (resultSet.next()) {
                String lastID = resultSet.getString("supplier_id");

                // Extract the numeric part from the employee ID
                int idNum = Integer.parseInt(lastID.replace("SID", ""));
                idNum++;
                return String.format("SID%04d", idNum);
            } else {
                return "SID0001";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<SupplierEntity> getAll() {
        ObservableList<SupplierEntity> supplierObservableList = FXCollections.observableArrayList();

        String SQL = "SELECT * FROM supplier";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL);

            while (resultSet.next()){
                supplierObservableList.add(
                        new SupplierEntity(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4)
                        )
                );
            }
            return supplierObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
