package com;

import com.company.CopyOfTheBook;
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
public class jdbcCopyOfBook implements Controller{

    private static Connection connection;

    public jdbcCopyOfBook() throws SQLException {
        connection = jdbcConnection.getConnection();
    }

    @Override
    public ArrayList<Model> get() throws SQLException {
        Statement stmt = connection.createStatement();
        ArrayList<Model> tmp= new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM copyofthebook");
        jdbcUser a = new jdbcUser();
        ArrayList<Model> tmpUser= a.get();
        while (rs.next()) {
            rs.getRow();
            long inventoryNumber = rs.getLong("inventoryNumber");
            long id = rs.getLong("idBook");
            boolean issue = rs.getBoolean("issue");
            int idUser = rs.getInt("idUser");
            String reader;
            if (idUser!=0) {
                reader = ((User) tmpUser.get(idUser - 1)).getName();
            }
            else
            {
                reader="NULL";
            }
            CopyOfTheBook tmpCopyOfBook = new CopyOfTheBook(inventoryNumber,id, issue,reader);
            tmp.add(tmpCopyOfBook);
        }
        stmt.close();
        return (tmp);
    }

    @Override
    public void add(Model model) throws SQLException {
        Statement stmt = connection.createStatement();
        long idBook = ((CopyOfTheBook) model).getIdBook();
        int issue=0;
        if (((CopyOfTheBook) model).getIssue()) {
            issue = 1;
        }
        int idUser = 0;
        if (issue==1) {
            jdbcUser a= new jdbcUser();
            ArrayList<Model> tmpUser = a.get();
            for (int i=0;i<tmpUser.size();i++) {
                if (((CopyOfTheBook) model).getReader().equals(((User) tmpUser.get(i)).getLastName())) {
                    idUser = i+1;
                }
            }
        }
        stmt.executeUpdate("INSERT INTO test.copyofthebook (idBook, issue, idUser) \n" +
                "VALUES ('"+idBook+"', '"+ issue+"', '"+idUser+"');");
        stmt.close();
    }

    @Override
    public void delete(int id) {

    }
}
