package com.company;

import java.io.Serializable;

public class Book implements Serializable{

    private long idBook;
    private String authors;
    private String name;
    private int year;
    private int pages;

    public Book(){}
    public Book(long idBook, String authors, String name, int year, int pages){
        this.idBook = idBook;
        this.authors = authors;
        this.name = name;
        this.year = year;
        this.pages = pages;
    }

    public void setIdBook(long idBook){
        this.idBook = idBook;
    }

    public long getIdBook(){
        return idBook;
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

    public String toString(){
        return "id: " + idBook + ";   авторы: " + authors + ";   название: " + name + ";   год издания: " + year + ";   страницы: " + pages + ".";
    }
}