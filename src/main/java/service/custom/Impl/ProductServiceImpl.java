package service.custom.Impl;

import edu.icet.entity.ProductEntity;
import edu.icet.util.DaoType;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.ProductDao;
import service.custom.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    ProductDao productDao =  DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);

    @Override
    public boolean addProduct(ProductEntity product) {
        ProductEntity entity = new ModelMapper().map(product, ProductEntity.class);
      return   productDao.add(product);
    }

    @Override
    public String generateProductID() {
        return productDao.generateID();
    }

    @Override
    public List<String> getSuppliers() {
        return productDao.getSuppliers();

    }

    @Override
    public ObservableList<ProductEntity> getAll() {
        return  productDao.getAll();
    }

    @Override
    public ObservableList<ProductEntity> getGentsAll() {
        return productDao.getGentsAll();
    }

    @Override
    public ObservableList<ProductEntity> getLadiesAll() {
        return productDao.getLadiesAll();
    }

    @Override
    public ObservableList<ProductEntity> getKidsAll() {
        return productDao.getKidsAll();
    }
}
