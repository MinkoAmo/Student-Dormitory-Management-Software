/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import io.IORooms;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;

/**
 *
 * @author Nguyen Nam Lam
 */
public class StudentManager implements IManager<Student> {

    private RoomManager roomManager;
    private LinkedHashSet<Student> students;
    private IORooms io = new IORooms();
    private Room room;

    public StudentManager(Room r) {
        roomManager = new RoomManager();
        room = r;
        students = r.getStudents();
    }

    @Override
    public void addAll(LinkedHashSet<Room> li) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(Student t) throws Exception{
        if(students.contains(t))
            throw new Exception("Tồn tại");
        if(students.size() == room.getMaxStudent())
            throw new Exception("MAX_SIZE");
        students.add(t);
        room.setStudents(students);
        roomManager.update(room);
        return true;
    }

    @Override
    public boolean update(Student t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Student t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Student selectedById(Student t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Student> selected(Student t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Student> sorted(Comparator c, boolean isINC) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
