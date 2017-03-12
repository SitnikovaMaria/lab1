package client.model;

import java.io.Serializable;

public class User implements Serializable{
    private String name;
    private String middleName;
    private String lastName;
    private String type;

    User(){}

    public User(String name, String middleName, String lastName,String type){
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.type = type;
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

    public void setType(String type){
        if (type != "") {
            this.type = type;
        }
    }

    public String getType(){
        return type;
    }
}
