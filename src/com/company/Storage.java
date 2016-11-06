package com.company;

import java.util.ArrayList;

public class Storage {

    private static Storage instance;
    private ArrayList<Book> bookList = new ArrayList<>();
    private ArrayList<CopyOfTheBook> copyOfTheBookList = new ArrayList<>();

    private Storage(){
    }

    public static synchronized Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public void setBookList(ArrayList<Book> bookList){
        this.bookList = bookList;
    }

    public ArrayList<Book> getBookList(){
        return bookList;
    }

    public void setCopyOfTheBookList(ArrayList<CopyOfTheBook> copyOfTheBookList){
        this.copyOfTheBookList = copyOfTheBookList;
    }

    public ArrayList<CopyOfTheBook> getCopyOfTheBookList(){
        return copyOfTheBookList;
    }
}
