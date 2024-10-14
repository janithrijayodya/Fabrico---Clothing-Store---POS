package edu.icet.controller.Supplier;

import edu.icet.model.Supplier;

public interface supplierService {
    boolean addSupplier(Supplier supplier);
    Supplier searchSupplier(String SupplierID);
    boolean removeSupplier(String SupplierID);
    boolean updateSupplier(Supplier supplier);
    String generateSupplierID();
}
