package com.company;

import java.io.Serializable;

public class Book implements Serializable{

    private long idBook;
    private String authors;
    private String name;
    private int year;
    private int pages;
    private Catalog catalog;
    private Publisher publisher;

    Book(){}

    public Book(long idBook, String authors, String name, int year, int pages/*, Catalog catalog, Publisher publisher*/){
        this.idBook = idBook;
        this.authors = authors;
        this.name = name;
        this.year = year;
        this.pages = pages;
        /*this.catalog= catalog;
        this.publisher=publisher;*/
    }

    public void setIdBook(long idBook){
        this.idBook = idBook;
    }

    public long getIdBook(){
        return idBook;
    }

    public void setCatalog(Catalog catalog){
        if (catalog!=this.catalog) {
            this.catalog.setCount(this.catalog.getCount() - 1);
            this.catalog = catalog;
            catalog.setCount(catalog.getCount() + 1);
        }
    }

    public Catalog getCatalog(){return catalog;}

    public void setPublisher(Publisher publisher){
        this.publisher = publisher;
    }

    public Publisher getPublisher(){
        return publisher;
    }

    public void setAuthors(String authors){
        this.authors = authors;
    }

    public String getAuthors(){
        return authors;
    }

    public void setName(String name){
        if (name != "") {
            this.name = name;
        }
    }

    public String getName(){
        return name;
    }

    public void setYear(int year){
        this.year = year;
    }

    public int getYear(){
        return year;
    }

    public void setPages(int pages){
        this.pages = pages;
    }

    public int getPages(){
        return pages;
    }
}



