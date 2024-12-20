package repository.custom.Impl;

import edu.icet.entity.ProductEntity;
import edu.icet.util.CrudUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.custom.ProductDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public boolean add(ProductEntity product) {

        String SQL = "INSERT INTO product VALUES(?,?,?,?,?,?,?)";

        try {
            return  CrudUtil.execute(SQL,
                    product.getProductID(),
                    product.getProductName(),
                    product.getSize(),
                    product.getPrice(),
                    product.getQuantity(),
                    product.getType(),
                    product.getSupID()
//                    product.getImage()

            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String generateID() {

        String SQL = "SELECT product_id  FROM product ORDER BY product_id  DESC LIMIT 1";

        try {
            ResultSet resultSet = CrudUtil.execute(SQL);

            if (resultSet.next()) {
                String lastID = resultSet.getString("product_id");

                // Extract the numeric part from the employee ID
                int idNum = Integer.parseInt(lastID.replace("PID", ""));
                idNum++;

                // Format the new ID with leading zeros (e.g., "EID0006")
                return String.format("PID%04d", idNum);

            } else {
                return "PID0001";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<ProductEntity> getAll() {

        ObservableList<ProductEntity> productEntityObservableList = FXCollections.observableArrayList();

        String SQL = "SELECT * FROM product ";

        try {
            ResultSet resultSet = CrudUtil.execute(SQL);

            while (resultSet.next()){
                productEntityObservableList.add(
                        new ProductEntity(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getDouble(4),
                                resultSet.getInt(5),
                                resultSet.getString(6),
                                resultSet.getString(7)
                        )
                );
            }

            return productEntityObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getSuppliers() {
        List<String> supplierIDs =  new ArrayList<>();

        String SQL = "SELECT supplier_id FROM supplier";

        try {
            ResultSet resultSet = CrudUtil.execute(SQL);

            while (resultSet.next()){
                supplierIDs.add(resultSet.getString(1));
            }
            return supplierIDs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<ProductEntity> getGentsAll() {
        ObservableList<ProductEntity> productEntityObservableList = FXCollections.observableArrayList();

        String SQL = "SELECT * FROM product WHERE type= 'Gents' ";

        try {
            ResultSet resultSet = CrudUtil.execute(SQL);

            while (resultSet.next()){
                productEntityObservableList.add(
                        new ProductEntity(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getDouble(4),
                                resultSet.getInt(5),
                                resultSet.getString(6),
                                resultSet.getString(7)
                        )
                );
            }

            return productEntityObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<ProductEntity> getLadiesAll() {
        ObservableList<ProductEntity> productEntityObservableList = FXCollections.observableArrayList();

        String SQL = "SELECT * FROM product WHERE type= 'Ladies' ";

        try {
            ResultSet resultSet = CrudUtil.execute(SQL);

            while (resultSet.next()){
                productEntityObservableList.add(
                        new ProductEntity(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getDouble(4),
                                resultSet.getInt(5),
                                resultSet.getString(6),
                                resultSet.getString(7)
                        )
                );
            }

            return productEntityObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<ProductEntity> getKidsAll() {
        ObservableList<ProductEntity> productEntityObservableList = FXCollections.observableArrayList();

        String SQL = "SELECT * FROM product WHERE type= 'Kids' ";

        try {
            ResultSet resultSet = CrudUtil.execute(SQL);

            while (resultSet.next()){
                productEntityObservableList.add(
                        new ProductEntity(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getDouble(4),
                                resultSet.getInt(5),
                                resultSet.getString(6),
                                resultSet.getString(7)
                        )
                );
            }

            return productEntityObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public ProductEntity search(String id) {
        return null;
    }

    @Override
    public boolean update(ProductEntity product) {
        return false;
    }

    @Override
    public boolean remove(String id) {
        return false;
    }

}
