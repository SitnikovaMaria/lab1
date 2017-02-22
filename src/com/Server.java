package com;

/**
 * Created by Никита on 19.11.2016.
 */
import com.company.CopyOfTheBook;
import com.company.Model;
import com.company.Publisher;
import com.company.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import java.net.*;
import java.io.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

public class Server {
    public static void main(String args[]) throws SQLException {
        jdbcCopyOfBook a = new jdbcCopyOfBook();
        CopyOfTheBook tmp= new CopyOfTheBook(1,3,true,"testov");
        a.add(tmp);
        new Communication();

    }
}

class Communication {
    public Communication() {
        try {
            ServerSocket server = null;
            client = null;
            try {
                server = new ServerSocket(6666);
                System.out.println("Waiting...");
                numberOfOnline = 0;
                while(true) {
                    client = server.accept();
                    numberOfOnline++;
                    System.out.println("One more client has been connected");
                    System.out.println("There are " + Communication.numberOfOnline + " clients online");
                    Runnable r = new ThreadEchoHandler(client);
                    Thread t = new Thread(r);
                    t.start();
                }
            }
            finally {
                client.close();
                server.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Socket client;
    static int numberOfOnline;
}

class ThreadEchoHandler implements Runnable {
    public ThreadEchoHandler(Socket st) {
        client = st;
    }
    public void run() {
        try {
            String line;
            InputStream sin = client.getInputStream();
            OutputStream sout = client.getOutputStream();
            ObjectInputStream in = new ObjectInputStream(sin);
            ObjectOutputStream out = new ObjectOutputStream(sout);
            try {
                if ((line = (String) in.readObject())!=null) {
                    switch (line) {
                        case "save":
                            String filename = (String) in.readObject();
                            System.out.println("save "+filename);
                            FileOutputStream fos = new FileOutputStream(filename);
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            oos.writeObject(in.readObject());
                            oos.flush();
                            oos.close();
                            break;
                        case "load":
                            filename = (String) in.readObject();
                            System.out.println("load "+filename);
                            FileInputStream fis = new FileInputStream(filename);
                            ObjectInputStream oin = new ObjectInputStream(fis);
                            out.writeObject(oin.readObject());
                            out.flush();
                            out.close();
                            oin.close();
                            break;
                        case "saveandmerge":
                            filename = (String) in.readObject();
                            System.out.println("saveandmerge "+filename);
                            fos = new FileOutputStream(filename);
                            oos = new ObjectOutputStream(fos);
                            fis = new FileInputStream(filename);
                            oin = new ObjectInputStream(fis);
                            out.writeObject(oin.readObject());
                            out.flush();
                            oos.writeObject(in.readObject());
                            oos.flush();
                            oos.close();
                            out.close();
                            oin.close();
                            break;
                    }
                }
            }

            catch (IOException e) {
                System.out.println("Probably this page does not exist");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            client.close();
            Communication.numberOfOnline--;
            System.out.println("There are " + Communication.numberOfOnline + " clients online");
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    private Socket client;
}


