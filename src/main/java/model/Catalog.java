package model;

import java.io.Serializable;

public class Catalog implements Serializable, Model {
    private long idCatalog;
    private String name;
    private String nameOfParent;

    public Catalog(long idCatalog, String name, String nameOfParent){
        this.idCatalog = idCatalog;
        this.name = name;
        this.nameOfParent = nameOfParent;
    }

    public Catalog(){
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

    public void setNameOfParent(String nameOfParent){
        if (nameOfParent != ""){
            this.nameOfParent = nameOfParent;
        }
    }

    public String getNameOfParent(){return nameOfParent;}
}