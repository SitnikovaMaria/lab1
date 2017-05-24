package jdbc;

import connection.Connect;
import model.*;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class jdbcRack {
    private static Connection connection;

    public jdbcRack() throws SQLException, ClassNotFoundException {
        connection = Connect.getConnection();
    }

    public HashMap<Integer,Rack> get() throws SQLException, ClassNotFoundException {
        Statement stmt = connection.createStatement();
        HashMap<Integer,Rack> tmp = new HashMap<>();
        ResultSet rs = stmt.executeQuery("SELECT * FROM rack");
        while (rs.next()) {
            rs.getRow();
            int idRack = rs.getInt("idRack");
            int idRoom = rs.getInt("idRoom");
            int height = rs.getInt("height");
            int width = rs.getInt("width");
            int positionX = rs.getInt("positionX");
            int positionY = rs.getInt("positionY");
            Rack tmpRack = new Rack(idRack, idRoom, height, width, positionX, positionY);
            tmp.put(idRack,tmpRack);
        }
        rs.close();
        stmt.close();
        connection.close();
        return (tmp);
    }

    public void add(Model model) throws SQLException, ClassNotFoundException {
        Statement stmt = connection.createStatement();
        int idRack = ((Rack) model).getIdRack();
        int idRoom = ((Rack) model).getIdRoom();
        int height = ((Rack) model).getHeight();
        int width = ((Rack) model).getWidth();
        int positionX = ((Rack) model).getPositionX();
        int positionY = ((Rack) model).getPositionY();
            jdbcRoom tmpRoom = new jdbcRoom();
            HashMap<Integer, Room> listRoom = tmpRoom.get();
            for (HashMap.Entry<Integer, Room> lRoom : listRoom.entrySet()) {
                Room room = lRoom.getValue();
                if (idRoom == room.getIdRoom()) {
                    idRoom = lRoom.getKey();
                }
            }
        stmt.executeUpdate("INSERT INTO test.rack (idRack, idRoom, height, width, positionX, positionY) \n" +
                "VALUES ('" + idRack + "', '" + idRoom + "', '" + height + "', '" + width + "', '" + positionX + "', '" + positionY + "');");
        stmt.close();
        connection.close();
    }

    public void delete(String idRack) throws SQLException, ClassNotFoundException {
        Statement stmt = connection.createStatement();
        String sql = "DELETE FROM test.rack WHERE idRack = " + idRack;
        stmt.executeUpdate(sql);
        stmt.close();
        connection.close();
    }

    public void change(String idRack, String idRoom, String height, String width, String positionX, String positionY)
            throws SQLException, ClassNotFoundException {
        Statement stmt = connection.createStatement();
        String sql = "UPDATE test.rack SET idRoom='"+idRoom+"', height='"+height+"', width='"+width+"', positionX='"+positionX+"', positionY='"+positionY+"' WHERE idRack = " + idRack;
        stmt.executeUpdate(sql);
        stmt.close();
        connection.close();
    }
}
