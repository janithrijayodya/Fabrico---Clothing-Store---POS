package repository;

import edu.icet.util.DaoType;
import repository.custom.Impl.*;

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
            case ORDER: return (T) new OrderDaoImpl();
            case ORDERDETAILS: return (T) new OrderDetailsDaoImpl();
        }
        return null;
    }
}
