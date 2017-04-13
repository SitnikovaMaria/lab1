/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import connection.Connect;
import model.CopyOfTheBook;
import model.Model;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class jdbcUser {
    private static Connection connection;

    public jdbcUser() throws SQLException, ClassNotFoundException {
        connection = Connect.getConnection();
    }

    public Map<Long, User> getWithId() throws SQLException {
        Statement stmt = connection.createStatement();
        Map<Long, User> tmp = new HashMap<>();
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
            User tmpUser = new User(id,name, middlename, lastname, type, login, pass);
            tmp.put(id, tmpUser);
        }
        rs.close();
        stmt.close();
        connection.close();
        return (tmp);
    }

    public ArrayList<User> get() throws SQLException {
        Statement stmt = connection.createStatement();
        ArrayList<User> tmp = new ArrayList<>();
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
            User tmpUser = new User(id, name, middlename, lastname, type, login, pass);
            tmp.add(tmpUser);
        }
        rs.close();
        stmt.close();
        connection.close();
        return (tmp);
    }

    public ArrayList<User> getLP() throws SQLException {
        Statement stmt = connection.createStatement();
        ArrayList<User> tmp = new ArrayList<>();
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
            User tmpUser = new User(name, middlename, lastname, type, login, pass);
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
        String lastname = ((User) model).getLastName();
        String type = ((User) model).getType();
        String login = ((User) model).getLogin();
        String pass = ((User) model).getPass();
        stmt.executeUpdate("INSERT INTO test.user (name, middleName, lastName, type, login, pass) \n" +
                "VALUES ('" + name + "', '" + middlename + "', '" + lastname + "', '" + type + "', '" + login + "', '" + pass + "');");
        stmt.close();
        connection.close();
    }

    public void delete(String id) throws SQLException, ClassNotFoundException {
        try {
            Statement stmt = connection.createStatement();
            long idUser = 0;
            jdbcUser tmpUser = new jdbcUser();
            ArrayList<User> lUser = tmpUser.get();
            for (User user : lUser) {
                if (user.getIdUser() == Long.valueOf(id)) {
                    idUser = user.getIdUser();
                }
            }
            jdbcCopyOfBook tmpCopyOfBook = new jdbcCopyOfBook();
            ArrayList<CopyOfTheBook> listCopy = tmpCopyOfBook.get();
            for (CopyOfTheBook copy : listCopy) {
                if (copy.getIdUser() == idUser) {
                    tmpCopyOfBook = new jdbcCopyOfBook();
                    tmpCopyOfBook.change(Long.toString(copy.getInventoryNumber()),Long.toString(copy.getIdBook()),"false","1");
                }
            }
            String sql = "DELETE FROM test.user WHERE idUser = ";
            stmt.executeUpdate(sql + id);
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(jdbcUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void change(String idUser, String name, String middlename, String lastname,
                       String type, String login, String pass) throws SQLException, ClassNotFoundException {
        Statement stmt = connection.createStatement();
        String sql = "UPDATE test.user SET name = '" + name + "', middleName = '" + middlename + "', lastName = '" + lastname + "', type = '" + type + "', login = '" + login + "', pass = '" + pass + "' WHERE idUser = " + idUser;
        stmt.executeUpdate(sql);
        stmt.close();
        connection.close();
    }
}
