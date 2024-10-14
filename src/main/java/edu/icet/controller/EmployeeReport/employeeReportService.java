package edu.icet.controller.EmployeeReport;

import edu.icet.model.Employee;
import javafx.collections.ObservableList;

public interface employeeReportService {
    ObservableList<Employee> getAll();
}
