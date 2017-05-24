package jdbc;

import connection.Connect;
import model.Model;
import model.Room;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class jdbcRoom {
    private static Connection connection;

    public jdbcRoom() throws SQLException, ClassNotFoundException {
        connection = Connect.getConnection();
    }

    public HashMap<Integer,Room> get() throws SQLException, ClassNotFoundException {
        Statement stmt = connection.createStatement();
        HashMap<Integer,Room> tmp = new HashMap();
        ResultSet rs = stmt.executeQuery("SELECT * FROM room");
        while (rs.next()) {
            rs.getRow();
            int idRoom = rs.getInt("idRoom");
            int roomNumber = rs.getInt("roomNumber");
            int level = rs.getInt("level");
            Room tmpRoom = new Room(idRoom, roomNumber, level);
            tmp.put(idRoom,tmpRoom);
        }
        rs.close();
        stmt.close();
        connection.close();
        return (tmp);
    }
}
