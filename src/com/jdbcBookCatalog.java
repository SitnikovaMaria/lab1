package com;

import com.company.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by user on 22.02.2017.
 */
public class jdbcBookCatalog {

    private static Connection connection;

    public jdbcBookCatalog() throws SQLException {
        connection = jdbcConnection.getConnection();
    }

    public ArrayList<Integer> get() throws SQLException {
        Statement stmt = connection.createStatement();
        ArrayList<Integer> tmp= new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM book_catalog");
        while (rs.next()) {
            rs.getRow();
            long id = rs.getLong("idBookCatalog");
            long idBook = rs.getLong("idBook");
            int idCatalog = rs.getInt("idCatalog");
            tmp.add(idCatalog);
        }
        stmt.close();
        return (tmp);
    }

    public void add(Model model) {

    }

    public void delete(int id) {

    }
}
