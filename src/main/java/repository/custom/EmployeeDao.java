package repository.custom;

import edu.icet.entity.EmployeeEntity;
import repository.CrudDao;

public interface EmployeeDao extends CrudDao<EmployeeEntity> {
    boolean validateEmployeeSignIn(String userID, String userPassword);
    boolean updateEmployeePassword(String userID, String userPassword);
}
