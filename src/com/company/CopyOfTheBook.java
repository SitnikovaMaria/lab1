package com.company;

import java.io.Serializable;

public class CopyOfTheBook implements Serializable{

    private long inventoryNumber;
    private long idBook;
    private boolean issue;

    public CopyOfTheBook(){}

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

    public String toString(){
        if(issue == true){
            return "Инвентарный номер: " + inventoryNumber + ";   книга: " + idBook + ";   выдана или нет: выдана.";
        }
        else{
            return "Инвентарный номер: " + inventoryNumber + ";   книга: " + idBook + ";   выдана или нет: нет.";
        }
    }
}
