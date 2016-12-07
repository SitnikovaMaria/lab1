package com.company;

import java.io.Serializable;

public class Catalog implements Serializable {
    private long idCatalog;
    private String name;
    private String nameOfChild;
    private int count;

    public Catalog(int idCatalog, String name, String nameOfChild, int count){
        this.idCatalog = idCatalog;
        this.name = name;
        this.nameOfChild = nameOfChild;
        this.count = count;
    }

    public void setIdCatalog(long idCatalog){
        this.idCatalog = idCatalog;
    }

    public long getIdCatalog(){
        return idCatalog;
    }

    public void setName(String name){
        if (name != "") {
            this.name = name;
        }
    }

    public String getName(){
        return name;
    }

    public void setNameOfChild(String nameOfChild){
        if (nameOfChild != ""){
            this.nameOfChild = nameOfChild;
        }
    }

    public String getNameOfChild(){return nameOfChild;}

    public void setCount(int count){
        this.count = count;
    }

    public int getCount(){return count; }
}