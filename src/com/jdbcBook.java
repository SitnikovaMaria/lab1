package com;

import com.company.Book;
import com.company.Catalog;
import com.company.Model;
import com.company.Publisher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 21.02.2017.
 */
public class jdbcBook implements Controller{
    private static Connection connection;

    public jdbcBook() throws SQLException {
        connection = jdbcConnection.getConnection();
    }

    @Override
    public ArrayList<Model> get() throws SQLException {
        Statement stmt = connection.createStatement();
        ArrayList<Model> tmp= new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM book");
        jdbcPublisher a = new jdbcPublisher();
        ArrayList<Model> tmpPublisher= a.get();
        jdbcCatalog b = new jdbcCatalog();
        jdbcBookCatalog c = new jdbcBookCatalog();
        ArrayList<Model> tmpCatalog= b.get();
        ArrayList<Integer> tmpBookCtalog = c.get();
        int i=0;
        while (rs.next()) {
            i++;
            rs.getRow();
            long id = rs.getLong("idBook");
            String author = rs.getString("authors");
            String name = rs.getString("name");
            int year = rs.getDate("year").getYear();
            int pages = rs.getInt("pages");
            int idCatalog = tmpBookCtalog.get(i-1);
            String catalog = ((Catalog) tmpCatalog.get(idCatalog-1)).getName();
            int idPublisher = rs.getInt("idPublisher");
            String publisher = ((Publisher) tmpPublisher.get(idPublisher-1)).getName();
            Book tmpBook = new Book(id, author, name, year, pages, catalog, publisher);
            tmp.add(tmpBook);
        }
        stmt.close();
        return (tmp);
    }

    @Override
    public void add(Model model) {

    }

    @Override
    public void delete(int id) {
        
    }
}
