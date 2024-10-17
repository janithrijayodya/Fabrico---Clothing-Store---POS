package repository.custom;

import edu.icet.entity.ProductEntity;
import repository.CrudDao;

import java.util.List;

public interface ProductDao extends CrudDao<ProductEntity> {
    List<String> getSuppliers();
}
