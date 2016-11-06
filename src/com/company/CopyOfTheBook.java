package com.company;

import java.io.Serializable;

public class CopyOfTheBook implements Serializable{

    private long inventoryNumber;
    private long idBook;
    private boolean issue;

    public CopyOfTheBook(long inventoryNumber, long idBook, boolean issue){
        this.inventoryNumber = inventoryNumber;
        this.idBook = idBook;
        this.issue = issue;
    }

    public void setInventoryNumber(long inventoryNumber){
        this.inventoryNumber = inventoryNumber;
    }

    public long getInventoryNumber(){
        return inventoryNumber;
    }

    public void setIdBook(long idBook){
        this.idBook = idBook;
    }

    public long getIdBook(){
        return idBook;
    }

    public void setIssue(boolean issue){
        this.issue = issue;
    }

    public boolean getIssue(){
        return issue;
    }

    public void info(){
        System.out.println("Инвентарный номер: " + inventoryNumber);
        System.out.println("Идентификатор книги: " + idBook);
        if(issue==true){
            System.out.println("Информация о выдаче: выдана");
        }
        else{
            System.out.println("Информация о выдаче: не выдана");
        }
    }
}
