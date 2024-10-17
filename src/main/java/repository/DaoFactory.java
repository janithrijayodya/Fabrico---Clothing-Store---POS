package repository;

import edu.icet.util.DaoType;
import repository.custom.Impl.EmployeeDaoImpl;
import repository.custom.Impl.ProductDaoImpl;
import repository.custom.Impl.SupplierDaoImpl;

public class DaoFactory {
    private  DaoFactory(){}

    private static DaoFactory instance;

    public static DaoFactory getInstance(){
        return instance==null? new DaoFactory(): instance;
    }

    public  <T extends SuperDao> T getDaoType(DaoType type){
        switch (type){
            case EMPLOYEE:return (T) new EmployeeDaoImpl();
            case SUPPLIER:return (T) new SupplierDaoImpl();
            case PRODUCT:return (T) new ProductDaoImpl();
        }
        return null;
    }
}
