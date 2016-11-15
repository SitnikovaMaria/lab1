package com.company;

import java.io.*;

public class Serialization {

    

public void saveObjectBook(Book tmp, String fileName) throws IOException
{
    FileOutputStream fos = new FileOutputStream(fileName);
    ObjectOutputStream oos = new ObjectOutputStream(fos);
    oos.writeObject(tmp);
    oos.flush();
    oos.close();
}

public void loadObjectBook(String filename) throws IOException, ClassNotFoundException {
    FileInputStream fis = new FileInputStream(filename);
    ObjectInputStream oin = new ObjectInputStream(fis);
    Book tmp = (Book) oin.readObject();
}

public void saveObjectCopy(CopyOfTheBook tmp, String filename) throws IOException {
    FileOutputStream fos = new FileOutputStream(filename);
    ObjectOutputStream oos = new ObjectOutputStream(fos);
    oos.writeObject(tmp);
    oos.flush();
    oos.close();
}

public void loadObjectCopy(String filename) throws IOException, ClassNotFoundException {
    FileInputStream fis = new FileInputStream(filename);
    ObjectInputStream oin = new ObjectInputStream(fis);
    CopyOfTheBook tmp = (CopyOfTheBook) oin.readObject();
}
}
