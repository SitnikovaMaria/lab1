package com;

import com.company.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 21.02.2017.
 */
public interface Controller {

    Connection conection = null;
    public ArrayList<Model> get() throws SQLException;
    public void add(Model model) throws SQLException;
    public void delete(int id);
}
