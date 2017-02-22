package com.company;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Comparator;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Storage")
@XmlType(propOrder = {"bookList","copyOfTheBookList"})
public class Storage implements Serializable{

    private static Storage instance;
    private HashMap<Long, Book> bookList = new HashMap<>();
    private HashMap<Long, CopyOfTheBook> copyOfTheBookList = new HashMap<>();
    private HashMap<Long, Catalog> catalogList = new HashMap<>();
    private HashMap<Long, Publisher> publisherList = new HashMap<>();

    public Storage(){
    }

    public static synchronized Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    @XmlElement(name = "Book")
    @XmlElementWrapper
    public void setBookList(HashMap<Long, Book> bookList){
        this.bookList = bookList;
    }

    public HashMap<Long, Book> getBookList(){
        return bookList;
    }

    @XmlElement(name = "CopyOfBook")
    @XmlElementWrapper
    public void setCopyOfTheBookList(HashMap<Long, CopyOfTheBook> copyOfTheBookList){
        this.copyOfTheBookList = copyOfTheBookList;
    }

    public HashMap<Long, CopyOfTheBook> getCopyOfTheBookList(){
        return copyOfTheBookList;
    }

    @XmlTransient
    public void setCatalogList(HashMap<Long, Catalog> catalogList){
        this.catalogList = catalogList;
    }

    @XmlTransient
    public HashMap<Long, Catalog> getCatalogList(){
        return catalogList;
    }

    @XmlTransient
    public void setPublisherList(HashMap<Long, Publisher> publisherList){
        this.publisherList = publisherList;
    }

    @XmlTransient
    public HashMap<Long, Publisher> getPublisherList(){
        return publisherList;
    }
}


