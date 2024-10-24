package service.custom;

import edu.icet.entity.OwnerEntity;
import service.SuperService;

public interface OwnerService extends SuperService {
    boolean addOwner(OwnerEntity owner);
    boolean updateOwner(OwnerEntity owner);
    String generateOwnerID();
    boolean OwnerSignInValidation(String userID, String userPassword);
    boolean updatePassword(String userID, String userPassword);
}
