package service.custom;

import edu.icet.entity.SupplierEntity;
import javafx.collections.ObservableList;
import service.SuperService;

public interface SupplierService extends SuperService {
    boolean addSupplier(SupplierEntity supplier);
    SupplierEntity searchSupplier(String SupplierID);
    boolean removeSupplier(String SupplierID);
    boolean updateSupplier(SupplierEntity supplier);
    String generateSupplierID();
    ObservableList<SupplierEntity> getAll();
}
