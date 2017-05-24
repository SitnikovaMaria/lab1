package utils;

import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Color;
import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import jdbc.jdbcRack;
import jdbc.jdbcRoom;
import model.Rack;
import model.Room;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.dom.GenericDOMImplementation;

import org.w3c.dom.Document;
import org.w3c.dom.DOMImplementation;

public class TestSVGGen {

    public void paint(Graphics2D g2d) throws SQLException, ClassNotFoundException {
        g2d.setPaint(Color.black);
        jdbcRack tmpRack = new jdbcRack();
        HashMap<Integer, Rack> listRack = tmpRack.get();

//        jdbcRoom tmpRoom = new jdbcRoom();
//        HashMap<Integer, Room> listRoom = tmpRoom.get();
//
//        HashMap<Integer, Room> level1Room = new HashMap<Integer, Room>();
//        HashMap<Integer, Room> level2Room = new HashMap<Integer, Room>();
//        HashMap<Integer, Room> level3Room = new HashMap<Integer, Room>();
//        HashMap<Integer, Room> level4Room = new HashMap<Integer, Room>();
//        HashMap<Integer, Room> level5Room = new HashMap<Integer, Room>();
//        for (HashMap.Entry<Integer, Room> lRoom : listRoom.entrySet()) {
//            Room room = lRoom.getValue();
//            if (room.getLevel() == 1) {
//                level1Room.put(room.getIdRoom(), room.clone());
//                //Logger.getLogger(TestSVGGen.class.getName()).log(Level.WARNING, "Room with id="+room.getIdRoom()+" added, level="+room.getLevel());
//            }
//            if (room.getLevel() == 2) {
//                level2Room.put(room.getIdRoom(), room.clone());
//                //Logger.getLogger(TestSVGGen.class.getName()).log(Level.WARNING, "Room with id="+room.getIdRoom()+" added, level="+room.getLevel());
//            }
//            if (room.getLevel() == 3) {
//                level3Room.put(room.getIdRoom(), room.clone());
//                //Logger.getLogger(TestSVGGen.class.getName()).log(Level.WARNING, "Room with id="+room.getIdRoom()+" added, level="+room.getLevel());
//            }
//            if (room.getLevel() == 4) {
//                level4Room.put(room.getIdRoom(), room.clone());
//                //Logger.getLogger(TestSVGGen.class.getName()).log(Level.WARNING, "Room with id="+room.getIdRoom()+" added, level="+room.getLevel());
//
//            }
//            if (room.getLevel() == 5) {
//                level5Room.put(room.getIdRoom(), room.clone());
//                //Logger.getLogger(TestSVGGen.class.getName()).log(Level.WARNING, "Room with id="+room.getIdRoom()+" added, level="+room.getLevel());
//            }
//        }
//
//        g2d.drawString("level 1", 10, 10);
//        int x = 10;
//        int y = 10;
//        for (HashMap.Entry<Integer, Room> lRoom : level1Room.entrySet()) {
//            y=y+20;
//            g2d.drawString("Room "+lRoom.getValue().getRoomNumber(), x, y);
//            for (HashMap.Entry<Integer, Rack> lRack : listRack.entrySet()) {
//                Rack rack = lRack.getValue();
//                y=y+20;
//                g2d.drawString("Rack "+rack.getIdRack(), x, y);
//                if (lRoom.getValue().getIdRoom() == rack.getIdRoom()){
//                    Logger.getLogger(TestSVGGen.class.getName()).log(Level.SEVERE, "lRack x="+rack.getPositionX());
//                    Logger.getLogger(TestSVGGen.class.getName()).log(Level.SEVERE, "lRack y="+rack.getPositionY());
//                    g2d.fill(new Rectangle((x+rack.getPositionX())*5, (y+rack.getPositionY())*5, rack.getWidth()*5, rack.getHeight()*5));
//                }
//                y=y+300;
//            }
//            y=y+20;
//        }
//
//        y=y+150;
//
//        g2d.drawString("level 2", x, y);
//        for (HashMap.Entry<Integer, Room> lRoom : level2Room.entrySet()) {
//            y=y+20;
//            g2d.drawString("Room "+lRoom.getValue().getRoomNumber(), x, y);
//            for (HashMap.Entry<Integer, Rack> lRack : listRack.entrySet()) {
//                Rack rack = lRack.getValue();
//                y=y+20;
//                g2d.drawString("Rack "+rack.getIdRack(), x, y);
//                if (lRoom.getValue().getIdRoom() == rack.getIdRoom()){
//                    Logger.getLogger(TestSVGGen.class.getName()).log(Level.SEVERE, "lRack x="+rack.getPositionX());
//                    Logger.getLogger(TestSVGGen.class.getName()).log(Level.SEVERE, "lRack y="+rack.getPositionY());
//                    g2d.fill(new Rectangle((x+rack.getPositionX())*5, (y+rack.getPositionY())*5, rack.getWidth()*5, rack.getHeight()*5));
//                }
//                y=y+300;
//            }
//            y=y+20;
//        }
//
//        y=y+150;
//
//        g2d.drawString("level 3", x, y);
//        for (HashMap.Entry<Integer, Room> lRoom : level3Room.entrySet()) {
//            y=y+20;
//            g2d.drawString("Room "+lRoom.getValue().getRoomNumber(), x, y);
//            for (HashMap.Entry<Integer, Rack> lRack : listRack.entrySet()) {
//                Rack rack = lRack.getValue();
//                y=y+20;
//                g2d.drawString("Rack "+rack.getIdRack(), x, y);
//                if (lRoom.getValue().getIdRoom() == rack.getIdRoom()){
//                    Logger.getLogger(TestSVGGen.class.getName()).log(Level.SEVERE, "lRack x="+rack.getPositionX());
//                    Logger.getLogger(TestSVGGen.class.getName()).log(Level.SEVERE, "lRack y="+rack.getPositionY());
//                    g2d.fill(new Rectangle((x+rack.getPositionX())*5, (y+rack.getPositionY())*5, rack.getWidth()*5, rack.getHeight()*5));
//                }
//                y=y+300;
//            }
//            y=y+20;
//        }
//
//        y=y+150;
//
//        g2d.drawString("level 4", x, y);
//        for (HashMap.Entry<Integer, Room> lRoom : level4Room.entrySet()) {
//            y=y+20;
//            g2d.drawString("Room "+lRoom.getValue().getRoomNumber(), x, y);
//            for (HashMap.Entry<Integer, Rack> lRack : listRack.entrySet()) {
//                Rack rack = lRack.getValue();
//                y=y+20;
//                g2d.drawString("Rack "+rack.getIdRack(), x, y);
//                if (lRoom.getValue().getIdRoom() == rack.getIdRoom()){
//                    Logger.getLogger(TestSVGGen.class.getName()).log(Level.SEVERE, "lRack x="+rack.getPositionX());
//                    Logger.getLogger(TestSVGGen.class.getName()).log(Level.SEVERE, "lRack y="+rack.getPositionY());
//                    g2d.fill(new Rectangle((x+rack.getPositionX())*5, (y+rack.getPositionY())*5, rack.getWidth()*5, rack.getHeight()*5));
//                }
//                y=y+300;
//            }
//            y=y+20;
//        }
//
//        y=y+150;
//
//        g2d.drawString("level 5", x, y);
//        for (HashMap.Entry<Integer, Room> lRoom : level5Room.entrySet()) {
//            y=y+20;
//            g2d.drawString("Room "+lRoom.getValue().getRoomNumber(), x, y);
//            for (HashMap.Entry<Integer, Rack> lRack : listRack.entrySet()) {
//                Rack rack = lRack.getValue();
//                y=y+20;
//                g2d.drawString("Rack "+rack.getIdRack(), x, y);
//                if (lRoom.getValue().getIdRoom() == rack.getIdRoom()){
//                    Logger.getLogger(TestSVGGen.class.getName()).log(Level.SEVERE, "lRack x="+rack.getPositionX());
//                    Logger.getLogger(TestSVGGen.class.getName()).log(Level.SEVERE, "lRack y="+rack.getPositionY());
//                    g2d.fill(new Rectangle((x+rack.getPositionX())*5, (y+rack.getPositionY())*5, rack.getWidth()*5, rack.getHeight()*5));
//                }
//                y=y+300;
//            }
//            y=y+20;
//        }

        for (HashMap.Entry<Integer, Rack> lRack : listRack.entrySet()) {
            Rack rack = lRack.getValue();
                g2d.fill(new Rectangle(rack.getPositionX()*5, rack.getPositionY()*5, rack.getWidth()*5, rack.getHeight()*5));
        }
    }

    public String gen() throws IOException, SQLException, ClassNotFoundException {

        // Get a DOMImplementation.
        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();

        // Create an instance of org.w3c.dom.Document.
        String svgNS = "http://www.w3.org/2000/svg";
        Document document = domImpl.createDocument(svgNS, "svg", null);

        // Create an instance of the SVG Generator.
        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

        // Ask the test to render into the SVG Graphics2D implementation.
        TestSVGGen test = new TestSVGGen();
        test.paint(svgGenerator);

        // Finally, stream out SVG to the standard output using
        // UTF-8 encoding.
        boolean useCSS = true; // we want to use CSS style attributes
        //Writer out = new OutputStreamWriter(System.out, "UTF-8");
        Writer out = new StringWriter();
        svgGenerator.stream(out, useCSS);
        return out.toString();
    }
}