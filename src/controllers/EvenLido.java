/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.ArrayList;
import models.DonDangKy;
import models.DonDangKyManager;
import models.TrangThai;

/**
 *
 * @author Admin
 */
public class EvenLido {
    DonDangKyManager donsManager;

    public EvenLido() {
        donsManager = new DonDangKyManager();
        donsManager.inputData();
    }
    
    public void clickXacNhan(int id, String lido){
        ArrayList<DonDangKy> d = (ArrayList<DonDangKy>) donsManager.getDSDon();
        for(DonDangKy i : d){
            if(i.getSv().getId() == id && i.getTrangthai() == TrangThai.DANGXET){
                i.setLido(lido);
                donsManager.setTrangThai(i, TrangThai.HUY);
                break;
            }
                
        }
    }
    
}
