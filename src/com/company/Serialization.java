package com.company;

import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;

public class Serialization {

    

public void saveObjectBook(Book tmp)
{
    FileOutputStream fos = new FileOutputStream("tempBook.out");
    OutputStream os = new OutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(fos);
    oos.writeObject(tmp);    
    oos.flush();
    oos.close();
}

public void loadObjectBook()
{
    FileInputStream fis = new FileInputStream("tempBook.in");
    ObjectInputStream oin = new ObjectInputStream(fis);
    Book tmp = (Book) oin.readObject();
}

public void saveObjectCopy(CopyOfTheBook tmp)
{
    FileOutputStream fos = new FileOutputStream("tempCopy.out");
    ObjectOutputStream oos = new ObjectOutputStream(fos);
    oos.writeObject(tmp);
    oos.flush();
    oos.close();
}

public void loadObjectCopy()
{
    FileInputStream fis = new FileInputStream("tempCopy.in");
    ObjectInputStream oin = new ObjectInputStream(fis);
    CopyOfTheBook tmp = (CopyOfTheBook) oin.readObject();
}
}
