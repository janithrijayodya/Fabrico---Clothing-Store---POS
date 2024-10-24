package service.custom.Impl;

import edu.icet.entity.EmployeeEntity;
import edu.icet.util.DaoType;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.EmployeeDao;
import service.custom.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

    EmployeeDao employeeDao =  DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);

    @Override
    public boolean addEmployee(EmployeeEntity employee) {
        EmployeeEntity entity = new ModelMapper().map(employee, EmployeeEntity.class);

        employeeDao.add(entity);

        System.out.println(employee);
        return true;
    }

    @Override
    public EmployeeEntity searchEmployee(String EmpId) {
       return  employeeDao.search(EmpId);

    }

    @Override
    public boolean updateEmployee(EmployeeEntity employee) {
        return  employeeDao.update(employee);
    }

    @Override
    public boolean removeEmployee(String EmpId) {
         employeeDao.remove(EmpId);
         return true;

    }

    @Override
    public String generateEmployeeID() {
        return employeeDao.generateID();
    }

    @Override
    public ObservableList<EmployeeEntity> getAll() {
      return employeeDao.getAll();
    }

    @Override
    public boolean EmployeeSignInValidation(String userID, String userPassword) {
        return  employeeDao.validateEmployeeSignIn(userID,userPassword);
    }

    @Override
    public boolean updatePassword(String userID, String userPassword) {
        return employeeDao.updateEmployeePassword(userID,userPassword);
    }
}
