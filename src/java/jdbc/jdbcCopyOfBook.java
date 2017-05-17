/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import connection.Connect;
import model.CopyOfTheBook;
import model.Model;
import model.User;
import servlets.CheckUser;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class jdbcCopyOfBook {
    private static Connection connection;

    public jdbcCopyOfBook() throws SQLException, ClassNotFoundException {
        connection = Connect.getConnection();
    }

        public ArrayList<CopyOfTheBook> get() throws SQLException, ClassNotFoundException {
        Statement stmt = connection.createStatement();
        ArrayList<CopyOfTheBook> tmp = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM test.copyofthebook");
        jdbcUser a = new jdbcUser();
        ArrayList<User> tmpUser = a.get();
        while (rs.next()) {
            rs.getRow();
            long inventoryNumber = rs.getLong("inventoryNumber");
            long id = rs.getLong("idBook");
            boolean issue = rs.getBoolean("issue");
            long idUser = rs.getLong("idUser");
            /*String reader;
            if (idUser != 0) {
                reader = (tmpUser.get(idUser - 1)).getLastName() + " " + (tmpUser.get(idUser - 1)).getName() + " " + (tmpUser.get(idUser - 1)).getMiddleName();
            } else {
                reader = "NULL";
            }*/
            CopyOfTheBook tmpCopyOfBook = new CopyOfTheBook(inventoryNumber, id, issue, idUser);
            tmp.add(tmpCopyOfBook);
        }
        rs.close();
        stmt.close();
        connection.close();
        return (tmp);
    }
        
    public ArrayList<CopyOfTheBook> get(String finder, String text) throws SQLException, ClassNotFoundException {
        Statement stmt = connection.createStatement();
        ArrayList<CopyOfTheBook> tmp = new ArrayList<>();
        ResultSet rs;
        if (!text.equals("") && !finder.equals("idUser"))
        {
            rs = stmt.executeQuery("SELECT * FROM test.copyofthebook WHERE " + finder + " = \"" + text + "\"");
        }
        else if (finder.equals("idUser") && !text.equals("")) {
            rs = stmt.executeQuery("SELECT * FROM test.copyofthebook WHERE " + finder + " = (SELECT idUser FROM test.user WHERE name = \"" + text + "\")");
        }
        else            
        {
           rs = stmt.executeQuery("SELECT * FROM test.copyofthebook");
        }
        jdbcUser a = new jdbcUser();
        ArrayList<User> tmpUser = a.get();
        while (rs.next()) {
            rs.getRow();
            long inventoryNumber = rs.getLong("inventoryNumber");
            long id = rs.getLong("idBook");
            boolean issue = rs.getBoolean("issue");
            long idUser = rs.getLong("idUser");
            CopyOfTheBook tmpCopyOfBook = new CopyOfTheBook(inventoryNumber, id, issue, idUser);
            tmp.add(tmpCopyOfBook);
        }
        rs.close();
        stmt.close();
        connection.close();
        return (tmp);
    }

    public void add(Model model) throws SQLException, ClassNotFoundException {
        Statement stmt = connection.createStatement();
        long idBook = ((CopyOfTheBook) model).getIdBook();
        int issue = 0;
        if (((CopyOfTheBook) model).getIssue()) {
            issue = 1;
        }
        long idUser = 0;
        String reader = ((CopyOfTheBook) model).getReader();
        if (issue == 1) {
            jdbcUser tmpUser = new jdbcUser();
            Map<Long, User> listUser = tmpUser.getWithId();
            for (Map.Entry<Long, User> lUser : listUser.entrySet()) {
                User user = lUser.getValue();
                System.out.print(user.getName() + " " + user.getLastName() + " " + user.getMiddleName());
                if (reader.equals(user.getLastName() + " " + user.getName() + " " + user.getMiddleName())) {
                    idUser = lUser.getKey();
                }
            }
        }
        stmt.executeUpdate("INSERT INTO test.copyofthebook (idBook, issue, idUser) \n" +
                "VALUES ('" + idBook + "', '" + issue + "', '" + idUser + "');");
        stmt.close();
        connection.close();
    }

    public void delete(String id) throws SQLException, ClassNotFoundException {
        Statement stmt = connection.createStatement();
        String sql = "DELETE FROM test.copyofthebook WHERE inventoryNumber = " + id;
        stmt.executeUpdate(sql);
        stmt.close();
        connection.close();
    }

    public void take(String inventoryNumber, String loginId) throws SQLException, ClassNotFoundException {
        Statement stmt = connection.createStatement();
        String sql = "UPDATE test.copyofthebook SET issue = '1', idUser = " + loginId + " WHERE inventoryNumber = " + inventoryNumber;
        stmt.executeUpdate(sql);
        stmt.close();
        connection.close();
    }

    public void replace(String inventoryNumber, String loginId, String idUser) throws SQLException, ClassNotFoundException {
        Statement stmt = connection.createStatement();
        Logger.getLogger(CheckUser.class.getName()).log(Level.SEVERE, "PARAMETR LOGIN ID: " + loginId);
        Logger.getLogger(CheckUser.class.getName()).log(Level.SEVERE, "PARAMETR USER ID: " + idUser);
        if (loginId.equals(idUser)){
            String sql = "UPDATE test.copyofthebook SET issue = '0', idUser = '0' WHERE inventoryNumber = " + inventoryNumber;
            stmt.executeUpdate(sql);
        }
        stmt.close();
        connection.close();
    }

    public void change(String inventoryNumber,String idBook,String issue,String idUser)
            throws SQLException, ClassNotFoundException {
        Statement stmt = connection.createStatement();
        Logger.getLogger(jdbcCopyOfBook.class.getName()).log(Level.SEVERE, "!!!2 issue:" + issue);
        Logger.getLogger(jdbcCopyOfBook.class.getName()).log(Level.SEVERE, "!!!2 idUser:" + idUser);
        String sql = "UPDATE test.copyofthebook SET idBook = '" + idBook + "', issue = '" + issue + "', idUser = '" + idUser + "' WHERE inventoryNumber = " + inventoryNumber;
        stmt.executeUpdate(sql);
        stmt.close();
        connection.close();
    }
}
