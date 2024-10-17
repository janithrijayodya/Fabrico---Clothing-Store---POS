package service.custom;

import edu.icet.entity.ProductEntity;
import service.SuperService;

import java.util.List;

public interface ProductService extends SuperService {
    boolean addProduct(ProductEntity product);
    String generateProductID();
    List<String> getSuppliers();
}
