package repository.custom.Impl;

import edu.icet.entity.OwnerEntity;
import edu.icet.util.CrudUtil;
import javafx.collections.ObservableList;
import repository.custom.OwnerDao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OwnerDaoImpl implements OwnerDao {
    @Override
    public boolean add(OwnerEntity owner) {
        String SQL = "INSERT INTO owner VALUES(?,?,?)";

        try {
            return CrudUtil.execute(SQL,
                        owner.getOwner_id(),
                        owner.getEmail(),
                        owner.getPassword()
                    );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String generateID() {
        String SQL = "SELECT owner_id  FROM owner ORDER BY owner_id  DESC LIMIT 1";

        try {
            ResultSet resultSet = CrudUtil.execute(SQL);

            if (resultSet.next()) {
                String lastID = resultSet.getString("owner_id");

                // Extract the numeric part from the employee ID
                int idNum = Integer.parseInt(lastID.replace("MID", ""));
                idNum++;

                // Format the new ID with leading zeros (e.g., "EID0006")
                return String.format("MID%04d", idNum);

            } else {
                return "MID0001";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<OwnerEntity> getAll() {
        return null;
    }
    @Override
    public OwnerEntity search(String id) {
        return null;
    }

    @Override
    public boolean update(OwnerEntity owner) {
        return false;
    }

    @Override
    public boolean remove(String id) {
        return false;
    }

    @Override
    public boolean validateOwnerSignIn(String userID, String userPassword) {
        String SQL = "SELECT password FROM owner WHERE owner_id =? ";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL,userID);
            if(resultSet.next()){
                String dpUserPassword = resultSet.getString("password");
                return  userPassword.equals(dpUserPassword);
            }else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateOwnerPassword(String userID, String userPassword) {
        String SQL = "UPDATE owner SET password = ? WHERE owner_id = ? ";
        try {
            return CrudUtil.execute(SQL,userPassword,userID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
