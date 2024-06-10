/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashSet;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import models.DonDangKy;
import models.RoomManager;
import models.Room;
import models.DonDangKyManager;
import models.Student;
import models.StudentManager;
import models.TrangThai;

import views.PheduyetUI;
      
public class EvenPheDuyet{
    private RoomManager roomManager = new RoomManager();
    private LinkedHashSet<Room> rooms;
    private PheduyetUI displayRoom;
    private DefaultTableModel model;
    private DonDangKyManager donmanager;
    private ArrayList<DonDangKy> dons;

    public EvenPheDuyet() {
        rooms = roomManager.getRooms();
        donmanager = new DonDangKyManager();
        donmanager.inputData();
        //dons = donmanager.getDdks();
    }
    
    public void getDonduyet(){
        dons = new ArrayList<>();
        ArrayList<DonDangKy> d = (ArrayList<DonDangKy>) donmanager.getDSDon();
        for(DonDangKy i: d){
            if(i.getTrangthai() == TrangThai.DANGXET){
                if(i.isDatcoc())
                    dons.add(0, i);
                else dons.add(i);
            }
        }
    }
    
    public void updateTable(DefaultTableModel model){
        model.setRowCount(0);
        getDonduyet();
        int stt = 1;
        for(DonDangKy i : dons){
            Student sv = i.getSv();
            String s;
            if(i.isDatcoc())
                s = "Có";
            else s = "Không";
            Object oj[] = {
                stt, sv.getId(), sv.getName(), sv.getGender(), 
                sv.getBirthday().get(Calendar.DAY_OF_MONTH) + "/" + (sv.getBirthday().get(Calendar.MONTH) + 1) + "/" + sv.getBirthday().get(Calendar.YEAR),
                sv.getAddress().toString(), sv.getPhoneNumber(),
                i.getRooms().toString(), s
            };
            model.addRow(oj);
            stt++;
        }
    }
    
    public void updateComboBox(JComboBox<String> cb, String s){
        Iterator it = rooms.iterator();
        cb.removeAllItems();
        while(it.hasNext()){
            Room r = (Room) it.next();
            if(!s.equals(String.valueOf(r.getType())))
                cb.addItem(r.toString());
        }
    }
    
    public boolean pheduyet(int index, String id_room) throws Exception{
        Student sv = dons.get(index).getSv();
        Iterator it = rooms.iterator();
        while(it.hasNext()){
            Room r = (Room) it.next();
            System.out.println(r.toString() + " " + id_room);
            if(r.toString().equals(id_room)){
                StudentManager st = new StudentManager(r);
                st.add(sv);
                donmanager.setTrangThai(dons.get(index), TrangThai.THANHCONG);
                return true;
            }
        }
        return false;
    }
    
      
}
