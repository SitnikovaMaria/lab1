package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

    @Entity
    @Table(name = "user")
public class User implements Serializable, Model{
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser", unique=true, nullable=false)
    private long idUser;
    @Column(name = "name")
    private String name;
    @Column(name = "middleName")
    private String middleName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "type")
    private String type;
    @Column(name = "login")
    private String login;
    @Column(name = "pass")
    private String pass;
    
    @ManyToMany( fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "meeting_user",
            joinColumns = @JoinColumn(name = "idMeeting"),
            inverseJoinColumns = @JoinColumn(name = "idUser"))
    private Set<Meeting> meetings = new HashSet<>();
    public Set<Meeting> getMeetings () {
        return meetings;
    }
 
    public void setMeetings(Set<Meeting> cars) {
        this.meetings = cars;
    }


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

    public User() {
    }
}
