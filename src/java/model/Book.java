package model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


public class Book implements Serializable, Model{

    private long idBook;

    private String authors;

    private String name;

    private Date year;

    private int pages;

    private String catalog;
    private String publisher;
    private int idPublisher;

    public Book(){}

    public Book(long idBook, String authors, String name, String year, int pages, String catalog, String publisher){
        this.idBook = idBook;
        this.authors = authors;
        this.name = name;
        try{
            SimpleDateFormat format = new SimpleDateFormat("yyyy");
            Date yearD = format.parse(year);
            this.year = yearD;
        }catch (ParseException e){
        }
        this.pages = pages;
        this.catalog = catalog;
        this.publisher = publisher;
    }

    public Book(long idBook, String authors, String name, String year, int pages, String catalog, int idPublisher){
        this.idBook = idBook;
        this.authors = authors;
        this.name = name;
        try{
            SimpleDateFormat format = new SimpleDateFormat("yyyy");
            Date yearD = format.parse(year);
            this.year = yearD;
        }catch (ParseException e){
        }
        this.pages = pages;
        this.catalog = catalog;
        this.idPublisher = idPublisher;
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

    public void setYear(String year){
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        try{
            Date yearD = format.parse(year);
            this.year = yearD;
        } catch (ParseException e){
        }
    }

    public Date getYear(){
        return year;
    }

    public void setPages(int pages){
        this.pages = pages;
    }

    public int getPages(){
        return pages;
    }

    public void setCatalog(String catalog){
        this.catalog = catalog;
    }

    public String getCatalog(){return catalog;}

    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    public String getPublisher(){
        return publisher;
    }

    public void setIdPublisher(int idPublisher){
        this.idPublisher = idPublisher;
    }

    public int getIdPublisher(){
        return idPublisher;
    }
}