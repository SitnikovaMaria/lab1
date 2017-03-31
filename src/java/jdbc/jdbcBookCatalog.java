/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import connection.Connect;
import model.Model;
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
public class jdbcBookCatalog {
    private static Connection connection;

    public jdbcBookCatalog() throws SQLException, ClassNotFoundException {
        connection = Connect.getConnection();
    }

    public Map<Long,Integer> get() throws SQLException {
        Statement stmt = connection.createStatement();
        Map<Long,Integer> tmp= new HashMap<>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM book_catalog");
        while (rs.next()) {
            rs.getRow();
            long id = rs.getLong("idBookCatalog");
            long idBook = rs.getLong("idBook");
            int idCatalog = rs.getInt("idCatalog");
            tmp.put(idBook,idCatalog);
        }
       rs.close();
        stmt.close();
        connection.close();
        return (tmp);
    }
    
    public Map<Long,ArrayList<Long>> getWithId() throws SQLException {
        Statement stmt = connection.createStatement();
        Map<Long,ArrayList<Long>> tmp= new HashMap<>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM book_catalog");
        while (rs.next()) {
            rs.getRow();
            long id = rs.getLong("idBookCatalog");
            long idBook = rs.getLong("idBook");
            long idCatalog = rs.getInt("idCatalog");
            ArrayList<Long> lBookCatalog= new ArrayList<Long>();
            lBookCatalog.add(0, idBook);
            lBookCatalog.add(1, idCatalog);
            tmp.put(id,lBookCatalog);
        }
        rs.close();
        stmt.close();
        connection.close();
        return (tmp);
    }

    public void add(Model model) throws SQLException {
	Statement stmt = connection.createStatement();
    //    int idBook = ((BookCatalog) model).getIdBook();
	/*int idCatalog = ((BookCatalog) model).getIdCatalog();
        stmt.executeUpdate("INSERT INTO test.bookcatalog (idBook, idCatalog) \n" +
                "VALUES ('"+idBook+"', '"+idCatalog+"');");*/
        
        stmt.close();
        connection.close();
    }

    public void delete(int id) {

    }
}
