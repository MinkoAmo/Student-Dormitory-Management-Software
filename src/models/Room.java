/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;

/**
 *
 * @author Admin
 */
public class Room implements Serializable,Comparable<Room> {

    private int roomNumber;
    private String building;
    private TypeRoom type;
    private int maxStudent;
    private LinkedHashSet<Student> students;

    public Room() {
        
    }

    public Room(int roomNumber, String building, TypeRoom type, int maxStudent, LinkedHashSet<Student> students) {
        this.roomNumber = roomNumber;
        this.building = building;
        this.type = type;
        this.maxStudent = maxStudent;
        this.students = students;
    }

    public Room(int roomNumber, String building, int maxStudent) {
        this(roomNumber, building, TypeRoom.UNKNOWN, maxStudent, new LinkedHashSet<Student>());
    }

    public Room(Room r) {
        this(r.getRoomNumber(), r.getBuilding(), r.getType(), r.getMaxStudent(), r.getStudents());
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public TypeRoom getType() {
        return type;
    }

    public void setType(TypeRoom type) {
        this.type = type;
    }

    public int getMaxStudent() {
        return maxStudent;
    }

    public void setMaxStudent(int maxStudent) {
        this.maxStudent = maxStudent;
    }

    public LinkedHashSet<Student> getStudents() {
        return students;
    }

    public void setStudents(LinkedHashSet<Student> students) {
        this.students = students;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.roomNumber);
        hash = 67 * hash + Objects.hashCode(this.building);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Room other = (Room) obj;
        if (!Objects.equals(this.roomNumber, other.roomNumber)) {
            return false;
        }
        if (!Objects.equals(this.building, other.building)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Room o) {
        return (this.getBuilding().compareTo(o.getBuilding()) == 0) ? Integer.compare(this.getRoomNumber(), o.getRoomNumber()) : this.getBuilding().compareTo(o.getBuilding());
    }
    
    @Override
    public String toString() {
        if(this.getRoomNumber() == 0)
            return "0";
        return this.getRoomNumber() + " - " + this.getBuilding() + (this.getMaxStudent() == this.getStudents().size() ? " <full>" : "");
    }
    
}
