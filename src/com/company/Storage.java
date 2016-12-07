package com.company;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Comparator;

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

    public void setBookList(HashMap<Long, Book> bookList){
        this.bookList = bookList;
    }

    public HashMap<Long, Book> getBookList(){
        return bookList;
    }

    public void setCopyOfTheBookList(HashMap<Long, CopyOfTheBook> copyOfTheBookList){
        this.copyOfTheBookList = copyOfTheBookList;
    }

    public HashMap<Long, CopyOfTheBook> getCopyOfTheBookList(){
        return copyOfTheBookList;
    }

    public void setCatalogList(HashMap<Long, Catalog> catalogList){
        this.catalogList = catalogList;
    }

    public HashMap<Long, Catalog> getCatalogList(){
        return catalogList;
    }

    public void setPublisherList(HashMap<Long, Publisher> publisherList){
        this.publisherList = publisherList;
    }

    public HashMap<Long, Publisher> getPublisherList(){
        return publisherList;
    }
}


