/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import connection.Connect;
import model.Book;
import model.Catalog;
import model.Publisher;
import model.Model;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.CopyOfTheBook;
import model.User;

/**
 *
 * @author admin
 */
public class jdbcBook {
private static Connection connection;

    public jdbcBook() throws SQLException, ClassNotFoundException {
        connection = Connect.getConnection();
    }

    public static ArrayList<Book> get() throws SQLException, ClassNotFoundException {
        Statement stmt = connection.createStatement();
        ArrayList<Book> tmp= new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM book");
        jdbcPublisher a = new jdbcPublisher();
        ArrayList<Publisher> tmpPublisher= a.get();
        jdbcCatalog b = new jdbcCatalog();
        jdbcBookCatalog c = new jdbcBookCatalog();
        ArrayList<Catalog> tmpCatalog= b.get();
        Map<Long,Integer> tmpBookCatalog = c.get();
        int i=0;
        while (rs.next()) {
            rs.getRow();
            long id = rs.getLong("idBook");
            String author = rs.getString("authors");
            String name = rs.getString("name");        
            String year = rs.getDate("year").toString();
            int pages = rs.getInt("pages");            
            int idCatalog = tmpBookCatalog.get(id);            
            String catalog = ((Catalog) tmpCatalog.get(idCatalog-1)).getName();
            int idPublisher = rs.getInt("idPublisher");
            String publisher = ((Publisher) tmpPublisher.get(idPublisher-1)).getName();
            Book tmpBook = new Book(id, author, name, year, pages, catalog, publisher);
            tmp.add(tmpBook);
        }
        rs.close();
        stmt.close();
        connection.close();
        return (tmp);
    }

    public void add(Book model) throws SQLException, ClassNotFoundException {
	Statement stmt = connection.createStatement();
        long id=0;
        String authors = ((Book) model).getAuthors();
        String name = ((Book) model).getName();
        //Date year = java.sql.Date.valueOf((((Book) model).getYear()).toString());
        Date year = new Date(2020);
        int pages = ((Book) model).getPages();
        String catalogName=((Book) model).getCatalog();
	String publisher = ((Book) model).getPublisher();
        jdbcPublisher a = new jdbcPublisher();
        ArrayList<Publisher> tmpPublisher= a.get();
        jdbcBookCatalog b = new jdbcBookCatalog();
        Map<Long,Integer> tmpBookCatalog = b.get(); 
        long idPublisher = 0;
        for (Publisher tmp: tmpPublisher)
            if ((((Publisher) tmp).getName()).equals(publisher)) {
                idPublisher = ((Publisher) tmp).getIdPublisher();
                break;
            }
        stmt.executeUpdate("INSERT INTO test.book (authors, name, year, pages, idPublisher) \n" +
                "VALUES ('"+authors+"', '"+name+"', '"+year+"', '"+pages+"', '"+idPublisher+"');");
        ResultSet rs = stmt.executeQuery("SELECT * FROM book");
        while (rs.next()) {            
            rs.getRow();
            id=rs.getLong("idBook");
        }    
        long idCatalog=0;    
        jdbcCatalog c = new jdbcCatalog();
        ArrayList<Catalog> tmpCatalog= c.get();
        for (Catalog tmp: tmpCatalog){
            if ((((Catalog) tmp).getName()).equals(catalogName)){
                idCatalog=tmp.getIdCatalog();
            }
        }
        stmt.executeUpdate("INSERT INTO test.book_catalog (idBook, idCatalog) \n" +
                "VALUES ('"+id+"', '"+idCatalog+"');");        
        rs.close();
        stmt.close();
        connection.close();
    }

    public void delete(String id) throws SQLException, ClassNotFoundException {
        Statement stmt = connection.createStatement();
        String sql ="DELETE FROM test.book WHERE idBook = ";
        jdbcCopyOfBook tmpCopy = new jdbcCopyOfBook();
        ArrayList<CopyOfTheBook> listCopy = tmpCopy.get();
        for(CopyOfTheBook copy: listCopy){
            if (id.equals(Long.toString(copy.getIdBook()))){
                stmt.executeUpdate("DELETE FROM test.copyofthebook WHERE inventoryNumber = "+copy.getInventoryNumber());
            }
        }
        jdbcBookCatalog tmpBookCatalog= new jdbcBookCatalog();
        Map<Long,ArrayList<Long>> listBookCatalog = tmpBookCatalog.getWithId();
        for(Map.Entry<Long,ArrayList<Long>> bookCatalog : listBookCatalog.entrySet()){
            ArrayList<Long> lBookCatalog = bookCatalog.getValue();
            if(id.equals(Long.toString(lBookCatalog.get(0)))){
                stmt.executeUpdate("DELETE FROM test.book_catalog WHERE idBookCatalog = "+Long.toString(bookCatalog.getKey()));
            }
        }
        stmt.executeUpdate(sql+id); 
        stmt.close();
        connection.close();
    }
}

