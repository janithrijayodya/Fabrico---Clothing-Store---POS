package repository.custom.Impl;

import edu.icet.db.DBConnection;
import edu.icet.entity.EmployeeEntity;
import edu.icet.util.CrudUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.custom.EmployeeDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public boolean add(EmployeeEntity employee) {
        System.out.println("repo"+employee);
        String SQL = "INSERT INTO employee VALUES (?,?,?,?,?,?)";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(SQL);

            pstm.setObject(1, employee.getEmpId());
            pstm.setObject(2,employee.getEmpName());
            pstm.setObject(3,employee.getEmpCompany());
            pstm.setObject(4,employee.getEmpEmail());
            pstm.setObject(5,employee.getEmpAddress());
            pstm.setObject(6,employee.getEmpPassword());

            return  pstm.executeUpdate()>0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeEntity search(String id) {
        System.out.println(id);
        String SQL = "SELECT * FROM employee WHERE employee_id=?";

        try {
            ResultSet resultSet = CrudUtil.execute(SQL, id);

            while (resultSet.next()){
                return new EmployeeEntity(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean update(EmployeeEntity employee) {
        String SQL = "UPDATE employee SET  name=?, company=?, email=?, address=?, password=?";

        try {
            return CrudUtil.execute(SQL,
                    employee.getEmpName(),
                    employee.getEmpCompany(),
                    employee.getEmpEmail(),
                    employee.getEmpAddress(),
                    employee.getEmpPassword()
            );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean remove(String id) {
        String SQL = "DELETE FROM employee WHERE employee_id=?";

        try {
            return CrudUtil.execute(SQL,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String generateID() {
        String SQL = "SELECT employee_id FROM employee ORDER BY employee_id DESC LIMIT 1";

        try {
            ResultSet resultSet = CrudUtil.execute(SQL);

            if (resultSet.next()) {
                String lastID = resultSet.getString("employee_id");

                // Extract the numeric part from the employee ID
                int idNum = Integer.parseInt(lastID.replace("EID", ""));
                idNum++; // Increment the ID number

                // Format the new ID with leading zeros (e.g., "EID0006")
                return String.format("EID%04d", idNum);

            } else {
                // If no employees exist, start with "EID0001"
                return "EID0001";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<EmployeeEntity> getAll() {
        ObservableList<EmployeeEntity> employeeObservableList = FXCollections.observableArrayList();

        String SQL = "SELECT * FROM employee";

        try {
            ResultSet resultSet = CrudUtil.execute(SQL);

            while (resultSet.next()){
                employeeObservableList.add(
                        new EmployeeEntity(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getString(6)
                        )
                );
            }

            return employeeObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean validateEmployeeSignIn(String userID, String userPassword) {

        String SQL = "SELECT password FROM employee WHERE employee_id =? ";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL,userID);
            if(resultSet.next()){
                String dpEmployeePassword = resultSet.getString("password");
                return  userPassword.equals(dpEmployeePassword);
            }else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateEmployeePassword(String userID, String userPassword) {
        String SQL = "UPDATE employee SET password=? WHERE employee_id=? ";
        try {
            return CrudUtil.execute(SQL,userPassword,userID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
