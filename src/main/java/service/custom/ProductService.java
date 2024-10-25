package service.custom;

import edu.icet.entity.ProductEntity;
import javafx.collections.ObservableList;
import service.SuperService;

import java.util.List;

public interface ProductService extends SuperService {
    boolean addProduct(ProductEntity product);
    String generateProductID();
    List<String> getSuppliers();
    ObservableList<ProductEntity> getAll();
    ObservableList<ProductEntity> getGentsAll();
    ObservableList<ProductEntity> getLadiesAll();
    ObservableList<ProductEntity> getKidsAll();
}
