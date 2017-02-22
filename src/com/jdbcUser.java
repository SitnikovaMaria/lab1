package com;

import com.company.Model;
import com.company.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by user on 22.02.2017.
 */
public class jdbcUser implements Controller {
    private static Connection connection;

    public jdbcUser() throws SQLException {
        connection = jdbcConnection.getConnection();
    }

    @Override
    public ArrayList<Model> get() throws SQLException {
        Statement stmt = connection.createStatement();
        ArrayList<Model> tmp= new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM user");
        while (rs.next()) {
            rs.getRow();
            long id = rs.getLong("idUser");
            String name = rs.getString("name");
            String middlename = rs.getString("middleName");
            String lastname = rs.getString("lastName");
            String type = rs.getString("type");
            User tmpUser = new User(name,middlename,lastname,type);
            tmp.add(tmpUser);
        }
        stmt.close();
        return (tmp);
    }

    @Override
    public void add(Model model) throws SQLException {
        Statement stmt = connection.createStatement();
        String name = ((User) model).getName();
        String middlename = ((User) model).getMiddleName();
        String lastname= ((User) model).getLastName();
        String type= ((User) model).getType();
        stmt.executeUpdate("INSERT INTO test.user (name, middleName, lastName, type) \n" +
                "VALUES ('"+name+"', '"+middlename+"', '"+lastname+"', '"+type+"');");
        stmt.close();
    }

    @Override
    public void delete(int id) {

    }
}
