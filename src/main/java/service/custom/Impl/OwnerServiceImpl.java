package service.custom.Impl;

import edu.icet.entity.OwnerEntity;
import edu.icet.util.DaoType;
import repository.DaoFactory;
import repository.custom.OwnerDao;
import repository.custom.ProductDao;
import service.custom.OwnerService;

public class OwnerServiceImpl implements OwnerService {
    OwnerDao ownerDao =  DaoFactory.getInstance().getDaoType(DaoType.OWNER);

    @Override
    public boolean addOwner(OwnerEntity owner) {
        return ownerDao.add(owner);
    }

    @Override
    public boolean updateOwner(OwnerEntity owner) {
        return false;
    }

    @Override
    public String generateOwnerID() {
        return ownerDao.generateID();
    }

    @Override
    public boolean OwnerSignInValidation(String userID, String userPassword) {
        return ownerDao.validateOwnerSignIn(userID,userPassword);
    }

    @Override
    public boolean updatePassword(String userID, String userPassword) {
        return ownerDao.updateOwnerPassword(userID,userPassword);
    }
}
