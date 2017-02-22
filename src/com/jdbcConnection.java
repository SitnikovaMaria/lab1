package com;
import java.sql.*;

/**
 * Created by user on 21.02.2017.
 */
public class jdbcConnection {
    private static Connection connection;
    

    public jdbcConnection(String login, String password) throws SQLException {
    }

    public static Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
        return connection;
    }
}
