/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import model.Catalog;
import model.Model;

import connection.Connect;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@Stateless
public class jdbcCatalog {
    private static Connection connection;

    public jdbcCatalog() throws SQLException, ClassNotFoundException {
        connection = Connect.getConnection();
    }

    public ArrayList<Catalog> get() throws SQLException {
        Statement stmt = connection.createStatement();
        ArrayList<Catalog> tmp= new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM catalog");
        while (rs.next()) {
            rs.getRow();
            long id = rs.getLong("idCatalog");
            String name = rs.getString("name");
            String idOfParent = rs.getString("idOfParent");
            Catalog tmpCatalog = new Catalog(id,name,idOfParent);
            tmp.add(tmpCatalog);
        }
        for (int i=0; i<tmp.size();i++){
            if ( (Integer.valueOf(((Catalog) tmp.get(i)).getNameOfParent()))!=0){
                Catalog model=(Catalog) tmp.get(i);
                String catName=((Catalog) (tmp.get(Integer.valueOf(((Catalog) tmp.get(i)).getNameOfParent())-1))).getName();
                model.setNameOfParent(catName);
                tmp.set(i,model);
            }
            else
            {
                Catalog model=(Catalog) tmp.get(i);
                model.setNameOfParent("Общий");
                tmp.set(i,model);
            }
        }
        
        /*jdbcBookCatalog a = new jdbcBookCatalog();
        ArrayList<Integer> tmpBCatalog = a.get();
        for (int i=0; i<tmpBCatalog.size();i++)
        {
            Catalog model=(Catalog) tmp.get(tmpBCatalog.get(i)-1);
            model.setCount(model.get+1);
            tmp.set(tmpBCatalog.get(i)-1,model);
        }


        for (int i=0; i<tmp.size();i++){
            System.out.println(((Catalog) tmp.get(i)).getCount());
        }*/
        rs.close();
        stmt.close();
        connection.close();
        return (tmp);
    }

    public void add(Model model) throws SQLException {
	Statement stmt = connection.createStatement();
        String name = ((Catalog) model).getName();
        //int idOfParent = ((Catalog) model).getIdOfParent();
        /*stmt.executeUpdate("INSERT INTO test.catalog (name, idOfParent) \n" +
                "VALUES ('"+name+"', '"+idOfParent+"');");*/
        
        stmt.close();
        connection.close();
    }

    public void delete(int id) {
    }
}
