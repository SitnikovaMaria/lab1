package model;

import javax.ejb.Stateful;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "rack")
public class Rack implements Serializable, Model{
    @Id
    @Column(name = "idRack")
    private int idRack;

    @Column(name = "idRoom")
    private int idRoom;

    @Column(name = "height")
    private int height;

    @Column(name = "width")
    private int width;

    @Column(name = "positionX")
    private int positionX;

    @Column(name = "positionY")
    private int positionY;

    public Rack(){}

    public Rack(int idRack, int idRoom, int height, int width, int positionX, int positionY){
        this.idRack = idRack;
        this.idRoom = idRoom;
        this.height = height;
        this.width = width;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void setIdRack(int idRack){
        this.idRack = idRack;
    }

    public int getIdRack(){
        return idRack;
    }

    public void setIdRoom(int idRoom){
        this.idRoom = idRoom;
    }

    public int getIdRoom(){
        return idRoom;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public int getHeight(){
        return height;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public int getWidth(){
        return width;
    }

    public void setPositionX(int positionX){
        this.positionX = positionX;
    }

    public int getPositionX(){
        return positionX;
    }

    public void setPositionY(int positionY){
        this.positionY = positionY;
    }

    public int getPositionY(){
        return positionY;
    }
}
