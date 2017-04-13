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
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public static ArrayList<Book> get(String finder, String text) throws SQLException, ClassNotFoundException {
        Statement stmt = connection.createStatement();
        ArrayList<Book> tmp= new ArrayList<>();
        ResultSet rs;
        if (!text.equals("") && !finder.equals("idPublisher") && !finder.equals("catalog")) {
            rs = stmt.executeQuery("SELECT * FROM test.book WHERE " + finder + " = \"" + text + "\"");
        } else if (finder.equals("idPublisher") && !text.equals("")) {
            rs = stmt.executeQuery("SELECT * FROM test.book WHERE " + finder + " = (SELECT idPublisher FROM test.publisher WHERE name = \"" + text + "\")");
        } else if (finder.equals("catalog") && !text.equals("")) {
            rs = stmt.executeQuery("SELECT * FROM test.book WHERE idBook in (SELECT idBook FROM test.book_catalog WHERE idCatalog = (SELECT idCatalog FROM test.catalog WHERE name = \"" + text + "\"))");
        } else {
            rs = stmt.executeQuery("SELECT * FROM test.book");
        }
        jdbcPublisher a = new jdbcPublisher();
        ArrayList<Publisher> tmpPublisher = a.get();
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
            String catalogName = null;
            for (Catalog catalog: tmpCatalog){
                if(catalog.getIdCatalog()==idCatalog){
                    catalogName =  catalog.getName();
                }
            }
            int idPublisher = rs.getInt("idPublisher");
            String publisherName = null;
            for (Publisher publisher: tmpPublisher){
                if(publisher.getIdPublisher()==idPublisher){
                    publisherName =  publisher.getName();
                }
            }
            Book tmpBook = new Book(id, author, name, year, pages, catalogName, idPublisher);
            tmpBook.setPublisher(publisherName);
            tmp.add(tmpBook);
        }
        rs.close();
        stmt.close();
        connection.close();
        return (tmp);
    }
    
    public static ArrayList<Book> get() throws SQLException, ClassNotFoundException {
        Statement stmt = connection.createStatement();
        ArrayList<Book> tmp= new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM test.book");
        jdbcPublisher a = new jdbcPublisher();
        ArrayList<Publisher> tmpPublisher = a.get();
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
            String catalogName = null;
            for (Catalog catalog: tmpCatalog){
                if(catalog.getIdCatalog()==idCatalog){
                    catalogName =  catalog.getName();
                }
            }
            int idPublisher = rs.getInt("idPublisher");
            String publisherName = null;
            for (Publisher publisher: tmpPublisher){
                if(publisher.getIdPublisher()==idPublisher){
                    publisherName =  publisher.getName();
                }
            }
            Book tmpBook = new Book(id, author, name, year, pages, catalogName, idPublisher);
            tmpBook.setPublisher(publisherName);
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
        System.out.println(((Book) model).getYear());
        String year = Integer.toString(((Book) model).getYear().getYear()+1900);
        year= year.replace("-","");
        year= year+"-01-01";
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
            id = rs.getLong("idBook");
        }    
        long idCatalog = 0;
        jdbcCatalog c = new jdbcCatalog();
        ArrayList<Catalog> tmpCatalog = c.get();
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
        jdbcCopyOfBook tmpCopy = new jdbcCopyOfBook();
        ArrayList<CopyOfTheBook> listCopy = tmpCopy.get();
        for(CopyOfTheBook copy: listCopy){
            if (id.equals(Long.toString(copy.getIdBook()))){
                stmt.executeUpdate("DELETE FROM test.copyofthebook WHERE inventoryNumber = "+copy.getInventoryNumber());
            }
        }
        jdbcBookCatalog tmpBookCatalog = new jdbcBookCatalog();
        Map<Long,ArrayList<Long>> listBookCatalog = tmpBookCatalog.getWithId();
        for(Map.Entry<Long,ArrayList<Long>> bookCatalog : listBookCatalog.entrySet()){
            ArrayList<Long> lBookCatalog = bookCatalog.getValue();
            if(id.equals(Long.toString(lBookCatalog.get(0)))){
                stmt.executeUpdate("DELETE FROM test.book_catalog WHERE idBookCatalog = "+Long.toString(bookCatalog.getKey()));
            }
        }
        String sql ="DELETE FROM test.book WHERE idBook = ";
        stmt.executeUpdate(sql+id);
        stmt.close();
        connection.close();
    }

    public void change(String idBook,String authors,String name,String year,
                       String pages,String idPublisher) throws SQLException, ClassNotFoundException {
        Statement stmt = connection.createStatement();
        String sql = "UPDATE test.book SET authors = '" + authors + "', name = '" + name + "', year = '" + year + "', pages = '" + pages + "', idPublisher = '" + idPublisher + "' WHERE idBook = "+idBook;
        stmt.executeUpdate(sql);
        stmt.close();
        connection.close();
    }
}

