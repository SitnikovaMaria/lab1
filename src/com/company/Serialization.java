package com.company;

import java.io.*;

public class Serialization {



    public void saveObjectStorage(Storage tmp, String fileName) throws IOException
    {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(tmp);
        oos.flush();
        oos.close();
    }

    public Storage loadObjectBook(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream oin = new ObjectInputStream(fis);
        Storage tmp = (Storage) oin.readObject();
        return(tmp);
    }

}

