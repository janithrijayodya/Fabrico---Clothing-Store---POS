package service.custom;

import edu.icet.entity.EmployeeEntity;
import javafx.collections.ObservableList;
import service.SuperService;

public interface EmployeeService extends SuperService {

    boolean addEmployee(EmployeeEntity employee);
    EmployeeEntity searchEmployee(String EmpId);
    boolean updateEmployee(EmployeeEntity employee);
    boolean removeEmployee(String EmpId);
    String generateEmployeeID();
    ObservableList<EmployeeEntity> getAll();
    boolean EmployeeSignInValidation(String userID, String userPassword);
    boolean updatePassword(String userID, String userPassword);
}
