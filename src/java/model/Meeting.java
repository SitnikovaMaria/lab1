/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author admin
 */
    @Entity
    @Table(name = "meeting")
public class Meeting implements Serializable{
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMeeting")
    private long idMeeting;
    @Column(name = "name") 
    private String name; 
    @Column(name = "room") 
    private long room;
    @Column(name = "DOBegin") 
    private Date begin; 
    @Column(name = "DOEnd") 
    private Date end; 
    
    @ManyToMany( fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "meeting_user",
            joinColumns = @JoinColumn(name = "idMeeting"),
            inverseJoinColumns = @JoinColumn(name = "idUser"))
    private List<User> users = new ArrayList<>();
    public List<User> getUsers () {
        return users;
    }
 
    public void setUsers(List<User> users) {
        this.users = users;
    }
    
    public Meeting(){}
    
    public Meeting(String name, long room, Date begin, Date end){
       
        this.name= name;
        this.room = room;
        this.begin= begin;
        this.end=end;
    }
    
    public void setIdBook(long idMeeting){
        this.idMeeting = idMeeting;
    }

    public long getIdBook(){
        return idMeeting;
    }
    
    public void setName(String name){
        if (name != "") {
            this.name = name;
        }
    }

    public String getName(){
        return name;
    }
    
    public void setRoom(long room){
        this.room = room;
    }

    public long getRoom(){
        return room;
    }
    
    public void setBegin(Date begin){
        this.begin = begin;        
    }

    public Date getBegin(){
        return begin;
    }
    
    public void setEnd(Date end){
        this.end = end;        
    }

    public Date getEnd(){
        return end;
    }
}

