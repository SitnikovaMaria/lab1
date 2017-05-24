package model;

import javax.ejb.Stateful;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "room")
public class Room implements Serializable, Model{
    @Id
    @Column(name = "idRoom")
    private int idRoom;

    @Column(name = "roomNumber")
    private int roomNumber;

    @Column(name = "level")
    private int level;

    public Room(){}

    public Room(int idRoom, int roomNumber, int level){
        this.idRoom = idRoom;
        this.roomNumber = roomNumber;
        this.level = level;
    }

    public void setIdRoom(int idRoom){
        this.idRoom = idRoom;
    }

    public int getIdRoom(){
        return idRoom;
    }

    public void setRoomNumber(int roomNumber){
        this.roomNumber = roomNumber;
    }

    public int getRoomNumber(){
        return roomNumber;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public int getLevel(){
        return level;
    }

    @Override
    public Room clone() {
        return new Room(this.getIdRoom(),this.getRoomNumber(),this.getLevel());
    }
}
