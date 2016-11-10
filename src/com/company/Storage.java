package com.company;

import java.util.HashMap;

public class Storage {

    private static Storage instance;
    private HashMap<Long, Book> bookList = new HashMap<Long, Book>();
    private HashMap<Long, CopyOfTheBook> copyOfTheBookList = new HashMap<Long, CopyOfTheBook>();

    private Storage(){
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
}

