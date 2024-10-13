package edu.icet.util;

import edu.icet.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtil {

    public static <T> T execute(String SQL, Object... val) throws SQLException {

           PreparedStatement pstm =  DBConnection.getInstance().getConnection().prepareStatement(SQL);
        for (int i = 0; i < val.length; i++) {
            pstm.setObject(i + 1, val[i]);
        }
        if (SQL.startsWith("SELECT") || SQL.startsWith("select")) {
            return (T) pstm.executeQuery();
        } else {

            return (T) (Boolean) (pstm.executeUpdate() > 0);
        }
    }
}
