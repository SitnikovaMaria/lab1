/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import connection.Connect;
import model.CopyOfTheBook;
import model.Model;
import model.Publisher;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Book;
/**
 *
 * @author admin
 */
public class jdbcPublisher {
    private static Connection connection;

    public jdbcPublisher() throws SQLException, ClassNotFoundException {
        connection = Connect.getConnection();
    }

    public ArrayList<Publisher> get() throws SQLException {
        Statement stmt = connection.createStatement();
        ArrayList<Publisher> tmp= new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM publisher");
        while (rs.next()) {
            rs.getRow();
            long id = rs.getLong("idPublisher");
            String name = rs.getString("name");
            String rAddress = rs.getString("registeredAddress");
            String bAddress = rs.getString("businessAddress");
            Publisher tmpPublisher = new Publisher(id,name,rAddress,bAddress);
            tmp.add(tmpPublisher);
        }
        rs.close();
        stmt.close();
        connection.close();
        return (tmp);
    }

    public void add(Model model) throws SQLException {
        Statement stmt = connection.createStatement();
        String name = ((Publisher) model).getName();
        String rAddress = ((Publisher) model).getRegisteredAddress();
        String bAddress = ((Publisher) model).getBusinessAddress();
        stmt.executeUpdate("INSERT INTO test.publisher (name, registeredAddress, businessAddress) \n" +
                "VALUES ('"+name+"', '"+rAddress+"', '"+bAddress+"');");
        
        stmt.close();
        connection.close();
    }

    public void delete(String id) throws SQLException, ClassNotFoundException {
        try {
            Statement stmt = connection.createStatement();
            String name = null;
            jdbcPublisher tmpPublisher = new jdbcPublisher();
            ArrayList<Publisher> lPublisher = tmpPublisher.get();
            for (Publisher publisher: lPublisher){
                if(publisher.getIdPublisher()==Long.valueOf(id)){
                    name = publisher.getName();
                }
            }
            jdbcBook tmpBook = new jdbcBook();       
            ArrayList<Book> listBook = tmpBook.get();
            for(Book book: listBook){
                if(book.getPublisher().equals(name)){
                    tmpBook = new jdbcBook();
                    tmpBook.delete(Long.toString(book.getIdBook()));
                }
            }
            String sql ="DELETE FROM test.publisher WHERE idPublisher = ";
            stmt.executeUpdate(sql+id);
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(jdbcPublisher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void change(String idPublisher,String name,String address,String mail)
            throws SQLException, ClassNotFoundException {
        Statement stmt = connection.createStatement();
        String sql = "UPDATE test.publisher SET name = '" + name + "', registeredAddress = '" + address + "', businessAddress = '" + mail + "' WHERE idPublisher = "+idPublisher;
        stmt.executeUpdate(sql);
        stmt.close();
        connection.close();
    }
}
