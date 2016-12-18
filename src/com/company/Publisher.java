package com.company;

import java.io.Serializable;

public class Publisher implements Serializable {
    private long idPublisher;
    private String name;
    private String registeredAddress;
    private String businessAddress;

    public Publisher(long idPublisher, String name, String registeredAddress, String businessAddress){
        this.idPublisher = idPublisher;
        this.name = name;
        this.registeredAddress = registeredAddress;
        this.businessAddress = businessAddress;
    }

    public Publisher(){
    }

    public void setIdPublisher(long idPublisher){
        this.idPublisher = idPublisher;
    }

    public long getIdPublisher(){
        return idPublisher;
    }

    public void setName(String name){
        if (name != "") {
            this.name = name;
        }
    }

    public String getName(){
        return name;
    }

    public void setRegisteredAddress(String registeredAddress){
        if (registeredAddress != "") {
            this.registeredAddress = registeredAddress;
        }
    }

    public String getRegisteredAddress(){
        return registeredAddress;
    }

    public void setBusinessAddress(String businessAddress){
        if (businessAddress != "") {
            this.businessAddress = businessAddress;
        }
    }

    public String getBusinessAddress(){
        return businessAddress;
    }
}
