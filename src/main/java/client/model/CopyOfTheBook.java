package client.model;

import java.io.Serializable;

public class CopyOfTheBook implements Serializable{

    private long inventoryNumber;
    private long idBook;
    private boolean issue;
    private String reader;

    public CopyOfTheBook(){}

    public CopyOfTheBook(long inventoryNumber, long idBook, boolean issue, String reader){
        this.inventoryNumber = inventoryNumber;
        this.idBook = idBook;
        this.issue = issue;
        this.reader = reader;
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

    public void setReader(String reader){
        this.reader = reader;
    }

    public String getReader(){
        return reader;
    }
}
