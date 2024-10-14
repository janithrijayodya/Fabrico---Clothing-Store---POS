package edu.icet.controller.EmployeeReport;

import edu.icet.model.Employee;
import edu.icet.model.Supplier;
import edu.icet.util.CrudUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class employeeReportController implements employeeReportService{

    private static employeeReportController instance;

    private employeeReportController(){}

    public static employeeReportController getInstance(){
        return instance == null? new employeeReportController() : instance;
    }

    @Override
    public ObservableList<Employee> getAll() {
        ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList();

        String SQL = "SELECT * FROM employee";

        try {
            ResultSet resultSet = CrudUtil.execute(SQL);

            while (resultSet.next()){
                employeeObservableList.add(
                        new Employee(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4)
                        )
                );
            }

            return employeeObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
