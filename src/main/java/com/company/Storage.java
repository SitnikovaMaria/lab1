package com.company;

import com.model.Book;
import com.model.Catalog;
import com.model.CopyOfTheBook;
import com.model.Publisher;

import java.io.Serializable;
import java.util.HashMap;

public class Storage implements Serializable{

    private static Storage instance;
    private HashMap<Long, Book> bookList = new HashMap<Long, Book>();
    private HashMap<Long, CopyOfTheBook> copyOfTheBookList = new HashMap<Long, CopyOfTheBook>();
    private HashMap<Long, Catalog> catalogList = new HashMap<Long, Catalog>();
    private HashMap<Long, Publisher> publisherList = new HashMap<Long, Publisher>();

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


