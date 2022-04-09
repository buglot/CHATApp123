package zDo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    //https://devcenter.heroku.com/articles/connecting-to-relational-databases-on-heroku-with-java
    private static final String url = "jdbc:postgresql://ec2-3-225-213-67.compute-1.amazonaws.com:5432/d5f37jltlcgd2t";
    private static final String username= "jmvfnxzlaxjvcx";
    private static final String password ="a1b329dbed5a8085bd0e34a638389ef1b7eef3de91903c293cfa7acccd7b3945";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }

}