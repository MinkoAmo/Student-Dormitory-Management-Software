/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Nguyen Nam Lam
 */
public interface IDangKy {

    void inputData();

    void outputData();

    List<Room> getRooms();

    List<DonDangKy> getDSDon();

    boolean add(DonDangKy t);

    String edit(DonDangKy t0, DonDangKy t1);

    boolean delete(DonDangKy t);

    DonDangKy selectedById(DonDangKy t);

    List<DonDangKy> sorted(Comparator c, boolean isINC);

    List<Room> selectedByRoom(TypeRoom typeRoom);
}
