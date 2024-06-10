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
public class Person implements Serializable{

    private String name; //PHẦN SỬA
    private Calendar birthday; //PHẦN SỬA
    private Gender gender;
    private Address address;

    public Person() {
    }
//SỬA
    public Person(String name, Calendar birthday, Gender gender, Address address) throws NameException {
        NameException.checkFormatName(name);
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
    }
//    
    public Person(Person p) throws NameException {
        this(p.getName(), p.getBirthday(), p.getGender(), p.getAddress());
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) throws NameException {
        NameException.checkFormatName(name);
        this.name = name;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}