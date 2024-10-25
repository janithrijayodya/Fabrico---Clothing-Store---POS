package repository.custom;

import edu.icet.entity.ProductEntity;
import javafx.collections.ObservableList;
import repository.CrudDao;

import java.util.List;

public interface ProductDao extends CrudDao<ProductEntity> {
    List<String> getSuppliers();
    ObservableList<ProductEntity> getGentsAll();
    ObservableList<ProductEntity> getLadiesAll();
    ObservableList<ProductEntity> getKidsAll();
}
