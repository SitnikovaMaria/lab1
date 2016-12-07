package com.company;

import java.io.Serializable;

/**
 * Created by user on 07.12.2016.
 */
public class Publisher implements Serializable {
    private String name;
    private String registeredAddress;
    private String businessAddress;

    Publisher(){}

    public Publisher(String name, String registeredAddress, String businessAddress){
        this.name = name;
        this.registeredAddress= registeredAddress;
        this.businessAddress = businessAddress;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        if (name != "") {
            this.name = name;
        }
    }

    public String getRegisteredAddress(){
        return registeredAddress;
    }

    public void setRegisteredAddress(String registeredAddress){
        if (registeredAddress != "") {
            this.registeredAddress = registeredAddress;
        }
    }

    public String getBusinessAddress(){
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress){
        if (businessAddress != "") {
            this.businessAddress = businessAddress;
        }
    }


}
