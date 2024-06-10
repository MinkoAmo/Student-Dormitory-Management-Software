/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.Account;
import views.DangKyUI;
import views.DangNhap;
import views.PheduyetUI;
import views.Menu;
import views.ThongKe;

/**
 *
 * @author minko
 */
public class EventMenu implements ActionListener{
    private Account acc;
    private Menu view;
    
    public EventMenu(Menu view, Account acc) {
        this.view = view;
        setText(acc);
    }
    
    public EventMenu(Menu view) {
        this.view = view;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        switch (src) {
            case "btnDangXuat":
                new EventDangNhap().resetAcc();
                new DangNhap().setVisible(true);
                view.dispose();
                break;
            case "btnDangKy":
                DangKyUI st = new DangKyUI();
                st.setVisible(true);
                st.setDefaultCloseOperation(1);
                break;
            case "btnXetDuyet":
                PheduyetUI dr = new PheduyetUI();
                dr.setVisible(true);
                dr.setDefaultCloseOperation(1);
                break;
            case "btnThongKe":
                ThongKe tk = new ThongKe();
                tk.setVisible(true);
                tk.setDefaultCloseOperation(1);
                break;
            default:
                break;
        }
    }
    
    
    public void setText (Account acc){
        view.setTxtUser("Xin chào: " + acc.getUser());
    }
}
