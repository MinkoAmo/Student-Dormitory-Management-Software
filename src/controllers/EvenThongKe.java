package controllers;


import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.table.DefaultTableModel;
import models.DonDangKy;
import models.DonDangKyManager;
import models.Student;
import models.TrangThai;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class EvenThongKe {
    private DonDangKyManager donsManager = new DonDangKyManager();
    private ArrayList<DonDangKy> dons;
    
    public EvenThongKe(){
        donsManager.inputData();
        dons = donsManager.getDdks();
    }
    
    public void updateTable(DefaultTableModel model){
        model.setRowCount(0);
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
                i.getRooms().toString(), s, i.getTrangthai()
            };
            model.addRow(oj);
            stt++;
        }
    }
    
    public boolean findSV(int id){
        dons = donsManager.getDdks();
        ArrayList<DonDangKy> n = new ArrayList<>();
        for(DonDangKy i : dons){
            if(i.getSv().getId() == id)
                n.add(i);
        }
        if(n.size()!=0){
            dons = n;
            return true;
        }
        return false;
    }
    
    public boolean loc(TrangThai t){
        dons = donsManager.getDdks();
        ArrayList<DonDangKy> n = new ArrayList<>();
        for(DonDangKy i : dons){
            if(i.getTrangthai() == t)
                n.add(i);
        }
        if(n.size()!=0){
            dons = n;
            return true;
        }
        return false;
    }
    
    public boolean clickLoc(String s){
        if(s.equals("Đang xét"))
            return loc(TrangThai.DANGXET);
        if(s.equals("Thành công"))
            return loc(TrangThai.THANHCONG);
        return loc(TrangThai.HUY);
    }
    
    public void reset(){
        dons = donsManager.getDdks();
    }
    
}
