package com.company;

import java.io.Serializable;
/**
 * Created by user on 07.12.2016.
 */
public class User implements Model{
    private String name;
    private String middleName;
    private String lastName;
    private String type;

    User(){}

    public User(String name, String middleName, String lastName,String type){
        this.name=name;
        this.middleName=middleName;
        this.lastName=lastName;
        this.type=type;
    }
    public void setName(String name){
        if (name != "") {
            this.name = name;
        }
    }

    public String getName(){
        return name;
    }

    public void setLastName(String lastName){
        if (name != "") {
            this.lastName = lastName;
        }
    }

    public String getLastName(){
        return lastName;
    }

    public void setMiddleName(String middleName){
        if (middleName != "") {
            this.middleName = middleName;
        }
    }

    public String getMiddleName(){
        return middleName;
    }

    public String getType(){return type;}
    
}
