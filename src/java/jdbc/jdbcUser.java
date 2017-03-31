/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import connection.Connect;
import model.Model;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author admin
 */
public class jdbcUser {
    private static Connection connection;

    public jdbcUser() throws SQLException, ClassNotFoundException {
        connection = Connect.getConnection();
    }

        public Map<Long ,User> getWithId() throws SQLException {
        Statement stmt = connection.createStatement();
        Map<Long,User> tmp= new HashMap<>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM user");
        while (rs.next()) {
            rs.getRow();
            long id = rs.getLong("idUser");
            String name = rs.getString("name");
            String middlename = rs.getString("middleName");
            String lastname = rs.getString("lastName");
            String type = rs.getString("type");
            User tmpUser = new User(name,middlename,lastname,type);
            tmp.put(id, tmpUser);
        }
        rs.close();
        stmt.close();
        connection.close();
        return (tmp);
    }
        
    public ArrayList<User> get() throws SQLException {
        Statement stmt = connection.createStatement();
        ArrayList<User> tmp= new ArrayList<>();
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
        rs.close();
        stmt.close();
        connection.close();
        return (tmp);
    }
    
    public ArrayList<User> getLP() throws SQLException {
        Statement stmt = connection.createStatement();
        ArrayList<User> tmp= new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM user");
        while (rs.next()) {
            rs.getRow();
            long id = rs.getLong("idUser");
            String name = rs.getString("name");
            String middlename = rs.getString("middleName");
            String lastname = rs.getString("lastName");
            String type = rs.getString("type");   
            String login = rs.getString("login");
            String pass = rs.getString("pass"); 
            User tmpUser = new User(name,middlename,lastname,type,login,pass);
            tmp.add(tmpUser);
        }
        rs.close();
        stmt.close();
        connection.close();
        return (tmp);
    }

    public void add(Model model) throws SQLException {
        Statement stmt = connection.createStatement();
        String name = ((User) model).getName();
        String middlename = ((User) model).getMiddleName();
        String lastname= ((User) model).getLastName();
        String type= ((User) model).getType();
        String login= ((User) model).getLogin();
        String pass=((User)model).getPass();
        stmt.executeUpdate("INSERT INTO test.user (name, middleName, lastName, type, login, pass) \n" +
                "VALUES ('"+name+"', '"+middlename+"', '"+lastname+"', '"+type+"', '"+login+"', '"+pass+"');");
        
        stmt.close();
        connection.close();
    }


    public void delete(int id) {
          
    }    
}
