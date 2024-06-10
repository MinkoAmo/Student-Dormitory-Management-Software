/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Address;
import models.DonDangKy;
import models.DonDangKyManager;
import models.Gender;
import models.IDangKy;
import models.Room;
import models.Student;
import models.TrangThai;
import models.TypeRoom;
import util.CompareByName;
import util.NameException;
import views.DangKyUI;

/**
 *
 * @author Admin
 */
public class DangKyController implements ActionListener, KeyListener, MouseListener {

    private DangKyUI view;
    private IDangKy model = new DonDangKyManager();
    private boolean checkSort = false;
    private Map<String, Room> map;
    private List<DonDangKy> list;
    private DefaultTableModel table;

    public DangKyController(DangKyUI view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        System.out.println(src);
        if (src.equals("Đăng ký")) {
            this.btnAddActionPerformed(e);
        }
        if (src.equals("Sửa")) {
            this.btnUpdateActionPerformed(e);
        }
        if (src.equals("Xoá")) {
            this.btnDelActionPerformed(e);
        }
        if (src.equals("Tìm")) {
            this.btnSearchActionPerformed(e);
        }
        if (src.equals("Sắp xếp")) {
            this.jrbNameActionPerformed(e);
        }
        if (src.equals("Reset")) {
            this.btnResetActionPerformed(e);
        }
        if (src.equals("Nam")) {
            this.rbMaleActionPerformed(e);
        }
        if (src.equals("Nữ")) {
            this.rbFemaleActionPerformed(e);
        }
        if (src.equals("Đặt phòng")) {
            if (view.jCheckBoxDatPhong.isSelected()) {
                this.updateCombobox(this.getTypeVal());
            } else {
                view.jcbRoom.removeAllItems();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.jTableKeyReleased(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.jTableMouseClicked(e);
    }

    public void init() {
        view.rbMale.setSelected(true);
        model.inputData();
        table = (DefaultTableModel) view.jTable.getModel();
        list = new ArrayList<DonDangKy>(model.getDSDon());
        this.updateTable();
    }

    //Cập nhật dữ liệu hiển thị table
    private void updateTable() {
        table.setRowCount(0);
        int i = 1;
        for (DonDangKy item : list) {
            table.addRow(new Object[]{i++, item.getSv().getId(),
                item.getSv().getName(), (item.getSv().getGender() == Gender.MALE ? "Nam" : "Nữ"),
                item.getSv().getBirthday().get(Calendar.DAY_OF_MONTH) + "/" + (item.getSv().getBirthday().get(Calendar.MONTH) + 1) + "/" + item.getSv().getBirthday().get(Calendar.YEAR),
                item.getSv().getAddress(), item.getSv().getPhoneNumber(),
                item.isDatcoc() ? "Đã đặt cọc" : "Chưa đặt cọc"
            });
        }
        table.fireTableDataChanged();
    }

    private void updateCombobox(TypeRoom t) {
        view.jcbRoom.removeAllItems();
        map = new TreeMap<>();
        List<Room> rs = model.selectedByRoom(t);
        rs.forEach((var r) -> {
            map.put(r.toString(), r);
        });
        map.entrySet().forEach(m -> {
            view.jcbRoom.addItem(m.getKey());
        });
    }

    private Gender getGenderVal() {
        return view.rbMale.isSelected() ? Gender.MALE : Gender.FEMALE;
    }

    private TypeRoom getTypeVal() {
        return view.rbMale.isSelected() ? TypeRoom.FEMALE : TypeRoom.MALE;
    }

    //Hiển thị chi tiết thông tin đơn
    private void displayDetail(int selectedIndex) {
        if (selectedIndex >= 0) {
            displayDetailDon(list.get(selectedIndex));
        }
    }

    private void displayDetailDon(DonDangKy d) {
        view.txtId.setText("" + d.getSv().getId());
        view.txtName.setText(d.getSv().getName());
        if (d.getSv().getGender() == Gender.MALE) {
            view.rbMale.setSelected(true);
            this.updateCombobox(TypeRoom.FEMALE);
        } else {
            view.rbFemale.setSelected(true);
            this.updateCombobox(TypeRoom.MALE);
        }
        view.txtdd.setText(d.getSv().getBirthday().get(Calendar.DAY_OF_MONTH) + "");
        view.txtmm.setText((d.getSv().getBirthday().get(Calendar.MONTH) + 1) + "");
        view.txtyyyy.setText(d.getSv().getBirthday().get(Calendar.YEAR) + "");
        view.txtPhone.setText(d.getSv().getPhoneNumber() + "");
        view.txtxa.setText(d.getSv().getAddress().getWardName());
        view.txthuyen.setText(d.getSv().getAddress().getDistrictName());
        view.txttinh.setText(d.getSv().getAddress().getCityName());
        view.jCheckBoxDatCoc.setSelected(d.isDatcoc());
        for (int i = 0; i < map.size(); i++) {
            if (d.getRooms().getRoomNumber() != 0 || view.jcbRoom.getItemAt(i).replaceAll(" <full>", "").equals(d.getRooms().toString())) {
                view.jCheckBoxDatPhong.setSelected(true);
                view.jcbRoom.setSelectedIndex(i);
                return;
            }
        }
        view.jCheckBoxDatPhong.setSelected(false);
        view.jcbRoom.removeAllItems();
    }

    private void validateText(StringBuilder error) {
        if (view.txtId.getText().equals("")) {
            error.append("Mã sinh viên chưa được nhập!");
        }
        if (view.txtName.getText().equals("")) {
            error.append("\nTên sinh viên chưa được nhập!");
        }
        if (view.txtPhone.getText().equals("")) {
            error.append("\nSĐT chưa được nhập!");
        }
        if (view.txtdd.getText().equals("")) {
            error.append("\nNgày chưa được nhập!");
        }
        if (view.txtmm.getText().equals("")) {
            error.append("\nTháng chưa được nhập!");
        }
        if (view.txtyyyy.getText().equals("")) {
            error.append("\nNăm chưa được nhập!");
        }
        if (view.txttinh.getText().equals("")) {
            error.append("\nTỉnh chưa được nhập!");
        }
        if (view.txthuyen.getText().equals("")) {
            error.append("\nHuyện chưa được nhập!");
        }
        if (view.txtxa.getText().equals("")) {
            error.append("\nXã chưa được nhập!");
        }
    }

    //Xử lý sự kiện thêm đơn
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        try {
            //Kiểm tra dữ liệu nhập
            StringBuilder error = new StringBuilder();
            this.validateText(error);
            if (error.length() != 0) {
                JOptionPane.showMessageDialog(view, error.toString(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            //Gán dữ liệu đơn
            Calendar d = Calendar.getInstance();
            d.set(Integer.parseInt(view.txtyyyy.getText()), Integer.parseInt(view.txtmm.getText()) - 1, Integer.parseInt(view.txtdd.getText()));
            Student st = new Student(Integer.parseInt(view.txtId.getText()),
                    view.txtName.getText(), d,
                    this.getGenderVal(), new Address(view.txttinh.getText(), view.txthuyen.getText(), view.txttinh.getText()),
                    Integer.parseInt(view.txtPhone.getText()),
                    new Room());
            Room r = (view.jcbRoom.getSelectedItem() != null) ? new Room(map.get(view.jcbRoom.getSelectedItem())) : new Room();
            DonDangKy ddk = new DonDangKy(st, TrangThai.DANGXET, view.jCheckBoxDatCoc.isSelected(), r, "");

            //Thực hiện thêm đơn
            boolean result = model.add(ddk);
            if (result) {
                list = new ArrayList<DonDangKy>(model.getDSDon());
                model.outputData();
                this.updateTable();
                JOptionPane.showMessageDialog(view, "Thêm đơn thành công");
            } else {
                JOptionPane.showMessageDialog(view, "Sinh viên đã đăng kí", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NameException e1) {
            JOptionPane.showMessageDialog(view, e1.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Xử lý sự kiện sửa đơn
    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        try {
            //Kiểm tra dữ liệu nhập
            StringBuilder error = new StringBuilder();
            this.validateText(error);
            if (error.length() != 0) {
                JOptionPane.showMessageDialog(view, error.toString(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            //Gán dữ liệu đơn
            Calendar d = Calendar.getInstance();
            d.set(Integer.parseInt(view.txtyyyy.getText()), Integer.parseInt(view.txtmm.getText()) - 1, Integer.parseInt(view.txtdd.getText()));
            Student st = new Student(Integer.parseInt(view.txtId.getText()),
                    view.txtName.getText(), d,
                    this.getGenderVal(), new Address(view.txttinh.getText(), view.txthuyen.getText(), view.txttinh.getText()),
                    Integer.parseInt(view.txtPhone.getText()),
                    new Room());
            Room r = (view.jcbRoom.getSelectedItem() != null) ? new Room(map.get(view.jcbRoom.getSelectedItem())) : new Room();
            DonDangKy ddk = new DonDangKy(st, TrangThai.DANGXET, view.jCheckBoxDatCoc.isSelected(), r, "");

            //Thực hiện sửa đơn
            String str = "";
            if (view.jTable.getSelectedRow() >= 0) {
                str = model.edit(list.get(view.jTable.getSelectedRow()), ddk);
            } else {
                str = model.edit(new DonDangKy(new Student(Integer.parseInt(view.txtId.getText()))), ddk);
            }
            if (str.equals("Sửa thành công")) {
                list = new ArrayList<DonDangKy>(model.getDSDon());
                model.outputData();// Ghi vào file
                this.updateTable();
                JOptionPane.showMessageDialog(view, str);
            } else {
                JOptionPane.showMessageDialog(view, str, "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NameException e1) {
            JOptionPane.showMessageDialog(view, e1.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Xử lý sự kiện xoá đơn
    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        try {
            //Kiểm tra dữ liệu nhập
            StringBuilder error = new StringBuilder();
            if (view.txtId.getText().equals("")) {
                error.append("Mã sinh viên chưa được nhập!");
            }
            if (error.length() != 0) {
                JOptionPane.showMessageDialog(view, error.toString(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int opt = JOptionPane.showConfirmDialog(view, "Bạn chắc chắn muốn xoá: " + view.txtId.getText(), "Xác nhận", JOptionPane.YES_NO_OPTION);
            //Xác nhận xoá
            if (opt == JOptionPane.NO_OPTION || opt == JOptionPane.CLOSED_OPTION) {
                return;
            }

            //Thực hiện xoá
            boolean result = model.delete(new DonDangKy(new Student(Integer.parseInt(view.txtId.getText()))));
            if (result) {
                list = new ArrayList<DonDangKy>(model.getDSDon());
                updateTable();
                model.outputData(); //Ghi file
                JOptionPane.showMessageDialog(view, "Xoá thành công");
            } else {
                JOptionPane.showMessageDialog(view, "Sinh viên không tồn tại");
            }
        } catch (IndexOutOfBoundsException e1) {
            JOptionPane.showMessageDialog(view, "Sinh viên không tồn tại");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Tìm sinh viên theo mã sinh viên
    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        try {
            table.setRowCount(0);
            DonDangKy item = model.selectedById(new DonDangKy(new Student(Integer.parseInt(view.txtId.getText()))));
            table.addRow(new Object[]{1, item.getSv().getId(),
                item.getSv().getName(), (item.getSv().getGender() == Gender.MALE ? "Nam" : "Nữ"),
                item.getSv().getBirthday().get(Calendar.DAY_OF_MONTH) + "/" + (item.getSv().getBirthday().get(Calendar.MONTH) + 1) + "/" + item.getSv().getBirthday().get(Calendar.YEAR),
                item.getSv().getAddress(), item.getSv().getPhoneNumber(),
                item.isDatcoc() ? "Đã đặt cọc" : "Chưa đặt cọc"
            });
            table.fireTableDataChanged();
            this.displayDetailDon(item);
        } catch (IndexOutOfBoundsException e1) {
            JOptionPane.showMessageDialog(view, "Sinh viên không tồn tại");
            this.updateTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        view.txtId.setText("");
        view.txtName.setText("");
        view.txtdd.setText("");
        view.txtmm.setText("");
        view.txtyyyy.setText("");
        view.txtPhone.setText("");
        view.txtxa.setText("");
        view.txthuyen.setText("");
        view.txttinh.setText("");
        view.jCheckBoxDatPhong.setSelected(false);
        view.jCheckBoxDatCoc.setSelected(false);
        view.jcbRoom.removeAllItems();
        list = model.getDSDon();
        updateTable();
    }

    private void rbMaleActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if (view.jCheckBoxDatPhong.isSelected()) {
            this.updateCombobox(TypeRoom.FEMALE);
        }
    }

    private void rbFemaleActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if (view.jCheckBoxDatPhong.isSelected()) {
            this.updateCombobox(TypeRoom.MALE);
        }
    }

    //Sắp xếp thêo tên
    private void jrbNameActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if (checkSort) {
            checkSort = false;
        } else {
            checkSort = true;
        }
        list = model.sorted(new CompareByName(), checkSort);
        updateTable();
    }

    //Chọn sinh viên tròng table
    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        int selectedRow = view.jTable.getSelectedRow();
        this.displayDetail(selectedRow);
    }

    //Phím up-down ở bàn phím
    private void jTableKeyReleased(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN) {
            int selectedRow = view.jTable.getSelectedRow();
            this.displayDetail(selectedRow);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
