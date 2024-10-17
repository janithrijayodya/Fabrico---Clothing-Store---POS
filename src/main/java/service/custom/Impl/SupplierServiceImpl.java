package service.custom.Impl;

import edu.icet.entity.EmployeeEntity;
import edu.icet.entity.SupplierEntity;
import edu.icet.util.DaoType;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.EmployeeDao;
import repository.custom.SupplierDao;
import service.custom.SupplierService;

public class SupplierServiceImpl implements SupplierService {

    SupplierDao supplierDao=  DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);


    @Override
    public boolean addSupplier(SupplierEntity supplier) {

        return supplierDao.add(supplier);

    }

    @Override
    public SupplierEntity searchSupplier(String SupplierID) {
        return supplierDao.search(SupplierID);
    }

    @Override
    public boolean removeSupplier(String SupplierID) {
        return supplierDao.remove(SupplierID);
    }

    @Override
    public boolean updateSupplier(SupplierEntity supplier) {
        return supplierDao.update(supplier);
    }

    @Override
    public String generateSupplierID() {
      return   supplierDao.generateID();
    }

    @Override
    public ObservableList<SupplierEntity> getAll() {
       return supplierDao.getAll();
    }
}
