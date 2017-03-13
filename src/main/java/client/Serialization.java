package client;

import client.model.Book;
import client.model.CopyOfTheBook;

import java.io.*;
import java.util.HashMap;

public class Serialization {
    public static void saveObjectStorage(Storage tmp, String fileName) throws IOException{
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(tmp);
        oos.flush();
        oos.close();
    }

    public static Storage loadObjectBook(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream oin = new ObjectInputStream(fis);
        Storage tmp = (Storage) oin.readObject();
        oin.close();
        return(tmp);
    }

    public static void saveAndMergeObjectStorage(Storage tmpNew, String fileName) throws IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream oin = new ObjectInputStream(fis);
        FileOutputStream fos = new FileOutputStream(fileName,true);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        Storage tmp = (Storage) oin.readObject();
        oin.close();
        HashMap<Long, Book> tmpBook = new HashMap<Long, Book>();
        tmpBook = tmp.getBookList();
        HashMap<Long, Book> tmpNewBook = new HashMap<Long, Book>();
        tmpNewBook = tmpNew.getBookList();
        for ( Book tmpNewB : tmpNewBook.values()) {
            boolean check = true;
            for (Book tmpB : tmpBook.values()) {
                if (tmpB.getYear() == tmpNewB.getYear()) {
                    if (tmpB.getPages() == tmpNewB.getPages()) {
                        if (tmpB.getAuthors().equals(tmpNewB.getAuthors())) {
			    if (tmpB.getCatalog().equals(tmpNewB.getCatalog())) {
				if (tmpB.getPublisher().equals(tmpNewB.getPublisher())) {
                            	    if (tmpB.getName().equals(tmpNewB.getName())) {
                                        check = false;
                            	    }
				}
			    }
                        }
                    }
                }
            }
            if (check == true){
                tmpBook.put(tmpNewB.getIdBook(),tmpNewB);
            }
        }
        HashMap<Long, CopyOfTheBook> tmpCopy = new HashMap<Long, CopyOfTheBook>();
        tmpCopy = tmp.getCopyOfTheBookList();
        HashMap<Long, CopyOfTheBook> tmpNewCopy = new HashMap<Long, CopyOfTheBook>();
        tmpNewCopy = tmpNew.getCopyOfTheBookList();
        for ( CopyOfTheBook tmpNewC : tmpNewCopy.values()) {
            boolean check = true;
            for (CopyOfTheBook tmpC : tmpCopy.values()) {
                if (tmpC.getInventoryNumber()==tmpNewC.getInventoryNumber() && tmpC.getIssue()==tmpNewC.getIssue()){
                    check=false;
                }
            }
            if (check == true){
                tmpCopy.put(tmpNewC.getIdBook(),tmpNewC);
            }
        }
        tmp.setBookList(tmpBook);
        tmp.setCopyOfTheBookList(tmpCopy);
        oos.writeObject(tmp);
        oos.flush();
        oos.close();
    }
}
