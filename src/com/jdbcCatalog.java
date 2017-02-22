package com;

import com.company.Catalog;
import com.company.Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by user on 21.02.2017.
 */
public class jdbcCatalog implements Controller {

    private static Connection connection;

    public jdbcCatalog() throws SQLException {
        connection = jdbcConnection.getConnection();
    }


    @Override
    public ArrayList<Model> get() throws SQLException {
        Statement stmt = connection.createStatement();
        ArrayList<Model> tmp= new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM catalog");
        while (rs.next()) {
            rs.getRow();
            long id = rs.getLong("idCatalog");
            String name = rs.getString("name");
            String idOfParent = rs.getString("idOfParent");
            Catalog tmpCatalog = new Catalog(id,name,idOfParent,0);
            tmp.add(tmpCatalog);
        }
        for (int i=0; i<tmp.size();i++){
            if ( (Integer.valueOf(((Catalog) tmp.get(i)).getNameOfChild()))!=0){
                Catalog model=(Catalog) tmp.get(i);
                String catName=((Catalog) (tmp.get(Integer.valueOf(((Catalog) tmp.get(i)).getNameOfChild())-1))).getName();
                model.setNameOfChild(catName);
                tmp.set(i,model);
            }
            else
            {
                Catalog model=(Catalog) tmp.get(i);
                model.setNameOfChild("Общий");
                tmp.set(i,model);
            }
        }
        jdbcBookCatalog a = new jdbcBookCatalog();
        ArrayList<Integer> tmpBCatalog = a.get();
        for (int i=0; i<tmpBCatalog.size();i++)
        {
            Catalog model=(Catalog) tmp.get(tmpBCatalog.get(i)-1);
            model.setCount(model.getCount()+1);
            tmp.set(tmpBCatalog.get(i)-1,model);
        }


        for (int i=0; i<tmp.size();i++){
            System.out.println(((Catalog) tmp.get(i)).getCount());
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
