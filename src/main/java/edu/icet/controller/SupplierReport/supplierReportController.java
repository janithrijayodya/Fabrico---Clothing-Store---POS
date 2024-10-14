package edu.icet.controller.SupplierReport;

import edu.icet.model.Supplier;
import edu.icet.util.CrudUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class supplierReportController implements suppplierReportService{

    private static supplierReportController instance;

    private supplierReportController(){}

    public static suppplierReportService getInstance() {
        return instance == null? new supplierReportController() : instance ;
    }

    @Override
    public ObservableList<Supplier> getAll() {
        ObservableList<Supplier> supplierObservableList = FXCollections.observableArrayList();

        String SQL = "SELECT * FROM supplier";

        try {
            ResultSet resultSet = CrudUtil.execute(SQL);

            while (resultSet.next()){
               supplierObservableList.add(
                       new Supplier(
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
