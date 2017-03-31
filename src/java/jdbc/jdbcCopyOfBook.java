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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
/**
 *
 * @author admin
 */
public class jdbcCopyOfBook {
    private static Connection connection;

    public jdbcCopyOfBook() throws SQLException, ClassNotFoundException {
        connection = Connect.getConnection();
    }

    public ArrayList<CopyOfTheBook> get() throws SQLException, ClassNotFoundException {
        Statement stmt = connection.createStatement();
        ArrayList<CopyOfTheBook> tmp= new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM copyofthebook");
        jdbcUser a = new jdbcUser();
        ArrayList<User> tmpUser= a.get();
        while (rs.next()) {
            rs.getRow();
            long inventoryNumber = rs.getLong("inventoryNumber");
            long id = rs.getLong("idBook");
            boolean issue = rs.getBoolean("issue");
            int idUser = rs.getInt("idUser");
            String reader;
            if (idUser!=0) {
                reader = (tmpUser.get(idUser - 1)).getLastName()+" "+(tmpUser.get(idUser - 1)).getName()+" "+(tmpUser.get(idUser - 1)).getMiddleName();
            }
            else
            {
                reader="NULL";
            }
            CopyOfTheBook tmpCopyOfBook = new CopyOfTheBook(inventoryNumber,id, issue,reader);
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
        int issue=0;
        if (((CopyOfTheBook) model).getIssue()) {
            issue = 1;
        }
        long idUser = 0;
        String reader= ((CopyOfTheBook) model).getReader();
        if (issue==1) {           
            jdbcUser tmpUser = new jdbcUser();
            Map<Long,User> listUser = tmpUser.getWithId();
            for(Map.Entry<Long,User> lUser : listUser.entrySet()){
                User user=lUser.getValue();                        
            if (reader.equals(user.getName()+" "+user.getLastName()+" "+user.getMiddleName())){  
                idUser=lUser.getKey();
            }
            }
        }
        stmt.executeUpdate("INSERT INTO test.copyofthebook (idBook, issue, idUser) \n" +
                "VALUES ('"+idBook+"', '"+ issue+"', '"+idUser+"');");
       
        stmt.close();
        connection.close();
    }


    public void delete(String id) throws SQLException, ClassNotFoundException {
        Statement stmt = connection.createStatement();
        String sql ="DELETE FROM test.copyofthebook WHERE inventoryNumber = ";
        stmt.executeUpdate(sql+id);              
        stmt.close();
        connection.close();
        
    }
}
