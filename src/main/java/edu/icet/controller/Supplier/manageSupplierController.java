package edu.icet.controller.Supplier;

import edu.icet.model.Supplier;
import edu.icet.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class manageSupplierController implements supplierService{

    private static manageSupplierController instance;

    private manageSupplierController(){}

    public static manageSupplierController getInstance(){
        return  instance == null ? instance = new manageSupplierController() : instance;
    }


    @Override
    public boolean addSupplier(Supplier supplier) {

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
    public Supplier searchSupplier(String SupplierID) {

        String SQL ="SELECT * FROM supplier WHERE supplier_id=?";

        try {
            ResultSet resultSet = CrudUtil.execute(SQL,SupplierID);

            while (resultSet.next()){
                return new Supplier(
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
    public boolean removeSupplier(String SupplierID) {
        String SQL = "DELETE FROM supplier WHERE supplier_id=?";

        try {
            return  CrudUtil.execute(SQL,SupplierID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {

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
    public String generateSupplierID() {
        String SQL = "SELECT supplier_id  FROM supplier ORDER BY supplier_id  DESC LIMIT 1";

        try {
            ResultSet resultSet = CrudUtil.execute(SQL);

            if (resultSet.next()) {
                String lastID = resultSet.getString("supplier_id");

                // Extract the numeric part from the employee ID
                int idNum = Integer.parseInt(lastID.replace("SID", ""));
                idNum++;

                // Format the new ID with leading zeros (e.g., "EID0006")
                return String.format("SID%04d", idNum);

            } else {
                return "SID0001";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
