package edu.icet.controller.Employee;

import edu.icet.db.DBConnection;
import edu.icet.model.Employee;
import edu.icet.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class manageEmployeeController implements employeeService{

    private static manageEmployeeController instance;

    private manageEmployeeController() {
    }

    public static manageEmployeeController getInstance() {
        return instance == null ? instance = new manageEmployeeController() : instance;
    }


    @Override
    public boolean addEmployee(Employee employee) {

        String SQL = "INSERT INTO employee VALUES (?,?,?,?)";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(SQL);

            pstm.setObject(1, employee.getEmpId());
            pstm.setObject(2,employee.getEmpName());
            pstm.setObject(3,employee.getEmpCompany());
            pstm.setObject(4,employee.getEmpEmail());

            return  pstm.executeUpdate()>0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public Employee searchEmployee(String EmpId) {
        System.out.println(EmpId);
        String SQL = "SELECT * FROM employee WHERE employee_id=?";

        try {
            ResultSet resultSet = CrudUtil.execute(SQL, EmpId);

            while (resultSet.next()){
                return new Employee(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                        );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        String SQL = "UPDATE employee SET  name=?, company=?, email=? ";

        try {
           return CrudUtil.execute(SQL,
                    employee.getEmpName(),
                    employee.getEmpCompany(),
                    employee.getEmpEmail()
            );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean removeEmployee(String EmpId) {
        String SQL = "DELETE FROM employee WHERE employee_id=?";

        try {
            return CrudUtil.execute(SQL,EmpId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String generateEmployeeID() {
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

}
