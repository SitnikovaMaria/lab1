package com.company;

import java.io.Serializable;

/**
 * Created by user on 07.12.2016.
 */

public class Catalog implements Serializable {

    private String name;
    private Catalog nameOfChild;
    private int count;

    Catalog(){}

    public Catalog(String name, Catalog nameOfChild, int count){
        this.name = name;
        this.nameOfChild= nameOfChild;
        this.count = count;
    }

    public void setName(String name){
        if (name != "") {
            this.name = name;
        }
    }

    public String getName(){
        return name;
    }

    public void setNameOfChild(Catalog nameOfChild){
        if (nameOfChild.getName()!=""){
            this.nameOfChild=nameOfChild;
        }
    }

    public Catalog getNameOfChild(){return nameOfChild;}

    public void setCount(int count){
        this.count = count;
    }

    public int getCount(){return count; }
}