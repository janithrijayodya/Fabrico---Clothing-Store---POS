package repository;

import edu.icet.entity.EmployeeEntity;
import javafx.collections.ObservableList;

public interface CrudDao <T> extends SuperDao {

    boolean add(T t);
    T search(String id);
    boolean update(T t);
    boolean remove(String id);
    String generateID();
    ObservableList<T> getAll();
}
