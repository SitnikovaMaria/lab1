package com;


import com.company.Model;
import com.company.Publisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by user on 21.02.2017.
 */
public class jdbcPublisher implements Controller {

    private static Connection connection;

    public jdbcPublisher() throws SQLException {
        connection = jdbcConnection.getConnection();
    }

    @Override
    public ArrayList<Model> get() throws SQLException {
        Statement stmt = connection.createStatement();
        ArrayList<Model> tmp= new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM publisher");
        while (rs.next()) {
            rs.getRow();
            long id = rs.getLong("idPublisher");
            String name = rs.getString("name");
            String rAddress = rs.getString("registeredAddress");
            String bAddress = ("businessAddress");
            Publisher tmpPublisher = new Publisher(id,name,rAddress,bAddress);
            tmp.add(tmpPublisher);
        }
        stmt.close();
        return (tmp);
    }

    @Override
    public void add(Model model) throws SQLException {
        Statement stmt = connection.createStatement();
        String name = ((Publisher) model).getName();
        String rAddress = ((Publisher) model).getRegisteredAddress();
        String bAddress = ((Publisher) model).getBusinessAddress();
        stmt.executeUpdate("INSERT INTO test.publisher (name, registeredAddress, businessAddress) \n" +
                "VALUES ('"+name+"', '"+rAddress+"', '"+bAddress+"');");
        stmt.close();
    }

    @Override
    public void delete(int id) {

    }
}
