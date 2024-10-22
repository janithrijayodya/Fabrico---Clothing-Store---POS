package service;

import edu.icet.util.ServiceType;
import service.custom.Impl.*;
import service.custom.OrderDetailsService;

public class ServiceFactory {

    private static  ServiceFactory instance;

    private ServiceFactory(){}

    public static ServiceFactory getInstance(){
        return instance == null?  new ServiceFactory() : instance ;
    }


    public <T extends SuperService> T getServiceType(ServiceType type){
        switch (type){
            case EMPLOYEE:return (T) new EmployeeServiceImpl();
            case PRODUCT:return (T) new ProductServiceImpl();
            case SUPPLIER:return (T) new SupplierServiceImpl();
            case ORDER: return (T) new OrderServiceImpl();
            case ORDERDETAILS: return (T) new OrderDetailsServiceImpl();
        }
        return null;
    }
}
