package com;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import java.net.*;
import java.io.*;

public class Server {
    private static int port = 6666;
    public static void main(String[] ar)    {

        try {
            ServerSocket ss = new ServerSocket(port);

            String line = null;
            while(true) {
                Socket socket = ss.accept();
                InputStream sin = socket.getInputStream();
                OutputStream sout = socket.getOutputStream();
                ObjectInputStream in = new ObjectInputStream(sin);
                ObjectOutputStream out = new ObjectOutputStream(sout);
                out.flush();
                if ((line = (String) in.readObject())!=null) {

                    switch (line) {
                        case "save":
                            String filename = (String) in.readObject();
                            System.out.print("save "+filename);
                            FileOutputStream fos = new FileOutputStream(filename);
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            oos.writeObject(in.readObject());
                            oos.flush();
                            oos.close();
                            break;
                        case "load":
                            filename = (String) in.readObject();
                            System.out.print("load "+filename);
                            FileInputStream fis = new FileInputStream(filename);
                            ObjectInputStream oin = new ObjectInputStream(fis);
                            out.writeObject(oin.readObject());
                            out.flush();
                            out.close();
                            oin.close();
                            break;
                        case "saveandmerge":
                            filename = (String) in.readObject();
                            System.out.print("saveandmerge "+filename);
                            fis = new FileInputStream(filename);
                            oin = new ObjectInputStream(fis);
                            out.writeObject(oin.readObject());
                            out.flush();
                            fos = new FileOutputStream(filename);
                            oos = new ObjectOutputStream(fos);
                            oos.writeObject(in.readObject());
                            oos.flush();
                            oos.close();
                            out.close();
                            oin.close();
                            break;
                    }
                }
            }
        }
        catch(Exception x) { x.printStackTrace(); }
    }
}
