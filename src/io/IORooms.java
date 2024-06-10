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
import java.util.LinkedHashSet;
import models.Room;

/**
 *
 * @author Admin
 */
public class IORooms {

    public IORooms() {
    }
    
    
    public void writeFile(LinkedHashSet<Room> rooms){
        try{
            FileOutputStream fo = new FileOutputStream("rooms.txt");
            ObjectOutputStream out = new ObjectOutputStream(fo);
            out.writeObject(rooms);
            fo.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public LinkedHashSet<Room> readFile() throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream fi = new FileInputStream("rooms.txt");
        ObjectInputStream in = new ObjectInputStream(fi);
            return (LinkedHashSet<Room>) in.readObject();
    }
}
