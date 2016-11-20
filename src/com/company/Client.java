package com.company;

/**
 * Created by Никита on 19.11.2016.
 */
import java.net.*;
import java.io.*;


public class Client {
    private static int serverPort = 6666;
    private static String address = "127.0.0.1";
    public Client(){}
    public void saveObjectStorage(Storage tmp, String fileName) throws IOException
    {
        InetAddress ipAddress = InetAddress.getByName(address);
        Socket socket = new Socket(ipAddress, serverPort);
        OutputStream fos = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject("save");
        oos.writeObject(fileName);
        oos.writeObject(tmp);
        oos.flush();
        oos.close();
    }

    public Storage loadObjectBook(String filename) throws IOException, ClassNotFoundException
    {
        InetAddress ipAddress = InetAddress.getByName(address);
        Socket socket = new Socket(ipAddress, serverPort);
        InputStream fis = socket.getInputStream();
        ObjectInputStream oin = new ObjectInputStream(fis);
        OutputStream fos = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject("load");
        oos.writeObject(filename);
        Storage tmp = (Storage) oin.readObject();
        oin.close();
        return(tmp);
    }


    public static void main(String[] ar) {
        WindowView w = new WindowView();
        w.setBounds(400, 250, 700, 300);
        w.setVisible(true);


    }
}

