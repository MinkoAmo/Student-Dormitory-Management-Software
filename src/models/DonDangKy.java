/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class DonDangKy implements Serializable, Comparable<DonDangKy>{
    private Student sv;
    private TrangThai trangthai;
    private boolean datcoc;
    private Room rooms;
    private String lido;

    public DonDangKy(Student sv, TrangThai trangthai, boolean datcoc, Room rooms, String lido) {
        this.sv = sv;
        this.trangthai = trangthai;
        this.datcoc = datcoc;
        this.rooms = rooms;
        this.lido = lido;
    }
    
    public DonDangKy(DonDangKy t) {
        this(t.getSv(), t.getTrangthai(), t.isDatcoc(), t.getRooms(), t.getLido());
    }

    public DonDangKy(Student sv) {
        this.sv = sv;
    }

    public Student getSv() {
        return sv;
    }

    public void setSv(Student sv) {
        this.sv = sv;
    }

    public TrangThai getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(TrangThai trangthai) {
        this.trangthai = trangthai;
    }

    public boolean isDatcoc() {
        return datcoc;
    }

    public void setDatcoc(boolean datcoc) {
        this.datcoc = datcoc;
    }

    public Room getRooms() {
        return rooms;
    }

    public void setRooms(Room rooms) {
        this.rooms = rooms;
    }

    public String getLido() {
        return lido;
    }

    public void setLido(String lido) {
        this.lido = lido;
    }

    @Override
    public int compareTo(DonDangKy o) {
        return this.sv.compareTo(o.getSv());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DonDangKy d = (DonDangKy) obj;
        return sv.equals(d.getSv()) && this.trangthai == d.getTrangthai();
    }
}
