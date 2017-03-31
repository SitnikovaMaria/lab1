package model;

import java.io.Serializable;

public class User implements Serializable, Model{
    private long idUser;
    private String name;
    private String middleName;
    private String lastName;
    private String type;
    private String login;
    private String pass;

    User(){}

    public User(long idUser, String name, String middleName, String lastName,String type, String login, String pass){
        this.idUser = idUser;
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.type = type;
        this.login = login;
        this.pass = pass;
    }

    public User(String name, String middleName, String lastName,String type, String login, String pass){
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.type = type;
        this.login = login;
        this.pass = pass;
    }

    public void setIdUser(long idUser){
        this.idUser = idUser;
    }

    public long getIdUser(){
        return idUser;
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
    
    public void setLogin(String login){
        if (login != "") {
            this.login = login;
        }
    }

    public String getLogin(){
        return login;
    }
    
    public void setPass(String pass){
        if (pass != "") {
            this.pass = pass;
        }
    }

    public String getPass(){
        return pass;
    }
}
