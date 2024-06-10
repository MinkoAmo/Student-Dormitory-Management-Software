/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import io.IOAccounts;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedHashSet;
import javax.swing.JOptionPane;
import models.Account;
import views.DangNhap;
import views.Menu;

/**
 *
 * @author minko
 */
public class EventDangNhap implements ActionListener{
    static private Account acc;
    private DangNhap view;
    
    public EventDangNhap(DangNhap view) {
        this.view = view;
    }

    public EventDangNhap() {
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {   
        String src = e.getActionCommand();
        switch (src) {
            case "btnDangNhap":
                try {
                    String tenDangNhap = view.getTxtTenDangNhap();
                    String matKhau = view.getTxtMatKhau();
                    Account ac = new Account(tenDangNhap, matKhau);
                    IOAccounts ioa = new IOAccounts();   

                    LinkedHashSet<Account> lstTemp = ioa.readAccountFromFile();

                    Iterator it = lstTemp.iterator();
                    boolean flag = false;

                    while(it.hasNext()){
                        if (ac.equals( (Account)it.next())){
                            acc = ac;
                            new Menu().setVisible(true);
                            view.dispose();
                            flag = true;
                        }
                    }
                    if (flag == false){
                        JOptionPane.showMessageDialog(null, "Tên đăng nhập hoặc mật khẩu không đúng", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        view.setTxtTenDangNhap("");
                        view.setTxtMatKhau("");
                    }
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }                
                break;
            case "btnTaiLai":
                view.setTxtTenDangNhap("");
                view.setTxtMatKhau("");
                break;
            default:
                break;
        }
    }
    
    public void resetAcc(){
        acc = null;
    }
    public Account getAcc(){
        return acc;
    }
}
