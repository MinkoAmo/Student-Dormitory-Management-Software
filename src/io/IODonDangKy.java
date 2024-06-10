/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import models.DonDangKy;

/**
 *
 * @author Admin
 */
public class IODonDangKy {

    public IODonDangKy() {
    }

    public void writeFile(ArrayList<DonDangKy> donDangKy) {
        try {
            FileOutputStream fo = new FileOutputStream("don.dat");
            ObjectOutputStream out = new ObjectOutputStream(fo);
            out.writeObject(donDangKy);
            fo.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<DonDangKy> readFile() throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream("don.dat");
        ObjectInputStream in = new ObjectInputStream(fi);
        return (ArrayList<DonDangKy>) in.readObject();
    }
}
