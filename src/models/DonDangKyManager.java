/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import io.IODonDangKy;
import io.IORooms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;

/**
 *
 * @author Nguyen Nam Lam
 */
public class DonDangKyManager implements IDangKy {

    private LinkedHashSet<Room> rooms = new LinkedHashSet<Room>();
    private ArrayList<DonDangKy> ddks = new ArrayList<>();
    private IORooms ior = new IORooms();
    private IODonDangKy iod = new IODonDangKy();

    @Override
    public void inputData() {
        try {
            LinkedHashSet<Room> li = ior.readFile();
            li.forEach(room -> {
                this.rooms.add(room);
            });
        } catch (Exception ex) {
        }
        try {
            ArrayList<DonDangKy> li = iod.readFile();
            li.forEach(ddd -> {
                this.ddks.add(ddd);
            });
        } catch (Exception ex) {
        }
    }

    @Override
    public void outputData() {
        iod.writeFile(ddks);
    }

    @Override
    public List<Room> getRooms() {
        return new ArrayList<>(this.rooms);
    }

    @Override
    public List<DonDangKy> getDSDon() {
        ArrayList<DonDangKy> li = new ArrayList<>();
        ddks.forEach(ddd -> {
            if (ddd.getTrangthai() == TrangThai.DANGXET) {
                li.add(ddd);
            }
        });
        return li;
    }

    @Override
    public boolean add(DonDangKy t) {
        for (DonDangKy d : ddks) {
            if (d.getSv().getId() == t.getSv().getId() && (d.getTrangthai() == TrangThai.DANGXET || d.getTrangthai() == TrangThai.THANHCONG)) {
                return false;
            }
        }
        return ddks.add(t);
    }

    @Override
    public String edit(DonDangKy t0, DonDangKy t1) {
        int i = 0;
        if (t0.getSv().getId() == t1.getSv().getId()) {
            i = 0;
            for (DonDangKy d : ddks) {
                if (d.getSv().getId() == t0.getSv().getId() && d.getTrangthai() == TrangThai.DANGXET) {
                    ddks.set(i, t1);
                    System.out.println(i);
                    return "Sửa thành công";
                }
                i++;
            }
        } else {
            i = 0;
            for (DonDangKy d : ddks) {
                if (d.getSv().getId() == t0.getSv().getId() && d.getTrangthai() == TrangThai.DANGXET) {
                    if (d.getSv().getId() != t1.getSv().getId()) {
                        ddks.set(i, t1);
                        System.out.println(i);
                        return "Sửa thành công";
                    } else {
                        return "Sinh viên sửa mới đã tồn tại";
                    }
                }
                i++;
            }
        }
        return "Không tìm thấy sinh viên cần sửa";
    }

    @Override
    public boolean delete(DonDangKy t) {
        for (DonDangKy d : ddks) {
            if (d.getSv().getId() == t.getSv().getId() && d.getTrangthai() == TrangThai.DANGXET) {
                return ddks.remove(d);
            }
        }
        return false;
    }

    @Override
    public DonDangKy selectedById(DonDangKy t) {
        List<DonDangKy> tmp = new ArrayList<>(this.getDSDon());
        Collections.sort(tmp);
        return tmp.get(Collections.binarySearch(tmp, t));
    }

    @Override
    public List<Room> selectedByRoom(TypeRoom typeRoom) {
        List<Room> tmp = new ArrayList<>(rooms);
        List<Room> results = new ArrayList<>();
        for (Room p : tmp) {
            if (!p.getType().equals(typeRoom) && p.getMaxStudent() > p.getStudents().size()) {
                results.add(p);
            }
        }
        return results;
    }

    @Override
    public List<DonDangKy> sorted(Comparator c, boolean isINC) {
        return DonDangKyManager.sorted(this.getDSDon(), c, isINC);
    }

    public static List<DonDangKy> sorted(List<DonDangKy> li, Comparator c, boolean isINC) {
        if (c == null) {
            if (isINC) {
                Collections.sort(li);
            } else {
                Collections.sort(li, Collections.reverseOrder());
            }
            return li;
        } else {
            if (isINC) {
                Collections.sort(li, c);
            } else {
                Collections.sort(li, c.reversed());
            }
            return li;
        }
    }

    public ArrayList<DonDangKy> getDdks() {
        return ddks;
    }

    public void setDdks(ArrayList<DonDangKy> ddks) {
        this.ddks = ddks;
    }

    public void setTrangThai(DonDangKy d, TrangThai t) {
        int index = ddks.indexOf(d);
        d.setTrangthai(t);
        ddks.set(index, d);
        this.outputData();
    }
}
