/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import io.IORooms;
import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class RoomManager implements IManager<Room>, Serializable {
    private final int MAXROOMS = 70;

    private LinkedHashSet<Room> rooms;
    private IORooms input = new IORooms();

    public LinkedHashSet<Room> getRooms() {
        return rooms;
    }

    public void setRooms(LinkedHashSet<Room> rooms) {
        this.rooms = rooms;
        writeData();
    }
    
    

    public RoomManager() {
        try {
            rooms = input.readFile();
        } catch (Exception ex) {
            rooms  = new LinkedHashSet<Room>();
        }
        input.writeFile(rooms);
    }
    
    public void writeData(){
        input.writeFile(rooms);
    }

    @Override
    public void addAll(LinkedHashSet<Room> li) {
        li.forEach(room -> {
            this.rooms.add(room);
        });
    }

    @Override
    public boolean add(Room t) {
        if(rooms.size() < MAXROOMS)
            return this.rooms.add(t);
        return false;
    }

    @Override
    public boolean update(Room t) {
        Iterator it = rooms.iterator();
        LinkedHashSet<Room> newRooms = new LinkedHashSet<>();
        while(it.hasNext()){
            Room r = (Room) it.next();
            newRooms.add(r);
            if(r.equals(t))
                newRooms.add(t);
        }
        rooms = newRooms;
        writeData();
        return true;
    }

    @Override
    public boolean delete(Room t) {
        return this.rooms.remove(t);
    }

    @Override
    public Room selectedById(Room t) {
        
        Iterator it = rooms.iterator();
        while(it.hasNext()){
            Room r = (Room) it.next();
            if(r.equals(t))
                return r;
        }
        return null;
    }

    @Override
    public ArrayList<Room> selected(Room t) {
        return new ArrayList<Room>();
    }

    @Override
    public ArrayList<Room> sorted(Comparator c, boolean isINC) {
        return new ArrayList<Room>();
    }
}
