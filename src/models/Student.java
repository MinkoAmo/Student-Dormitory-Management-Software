/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Calendar;
import util.NameException;

/**
 *
 * @author Admin
 */
public class Student extends Person implements Comparable<Student>, Serializable { //THÊM MỚI

    private int id;
    private int phoneNumber;
    private Room room;

    public Student() {
    }

    public Student(int id) {
        this.id = id;
    }
//SỬA
    public Student(int id, String name, Calendar birthday, Gender gender, Address address, int phoneNumber, Room room) throws NameException {
        super(name, birthday, gender, address);
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.room = room;
    }
//
    public Student(Student s) throws NameException {
        this(s.getId(), s.getName(), s.getBirthday(), s.getGender(), s.getAddress(), s.getPhoneNumber(), s.getRoom());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id;
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
        final Student other = (Student) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Student o) {
        return Integer.compare(this.getId(), o.getId());
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", phoneNumber=" + phoneNumber + ", room=" + room + '}';
    }
    
}
