package zDo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class doDB {

    private Connection DB_connection;
    public static final String queryInsert = "insert into cuser(username,passwords) values(?,?)"; //register
    public static final String querySearch = "select username,passwords FROM cuser where username =?"; //login
    public static final String querySearchAll = "select username,passwords FROM cuser"; //low down delay
    public static final String querySearchEmail = "select username,passwords FROM cuser where email";
    public static final String ColumnLabel_username = "username";
    public static final String ColumnLabel_passwords = "passwords";
    public static final String ColumnLabel_email = "email";
    private PreparedStatement sqlDB;

    public doDB() {
        try {
            DB_connection = ConnectDB.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet doSearchDB(String query,String username) throws SQLException {
        sqlDB = DB_connection.prepareStatement(query);
        sqlDB.setString(1, username);
        return sqlDB.executeQuery();
    }

    public void InsertDB(String query,String username ,String passwords) throws SQLException{
        sqlDB = DB_connection.prepareStatement(query);
        sqlDB.setString(1,username);
        sqlDB.setString(2,passwords);
        sqlDB.executeUpdate();
    }
}
