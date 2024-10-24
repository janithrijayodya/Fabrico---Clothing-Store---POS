package repository.custom;

import edu.icet.entity.OwnerEntity;
import repository.CrudDao;

public interface OwnerDao extends CrudDao<OwnerEntity> {
    boolean validateOwnerSignIn(String userID, String userPassword);
    boolean updateOwnerPassword(String userID, String userPassword);
}
