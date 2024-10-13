package edu.icet.controller.Employee;

import edu.icet.model.Employee;

public interface employeeService {

    boolean addEmployee(Employee employee);
    Employee searchEmployee(String EmpId);
    boolean updateEmployee(Employee employee);
    boolean removeEmployee(String EmpId);
    String generateEmployeeID();
}
