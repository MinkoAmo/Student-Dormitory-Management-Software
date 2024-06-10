/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashSet;
import models.Account;

/**
 *
 * @author Admin
 */
public class IOAccounts {  

    public IOAccounts() {
    }
    
    public void writeAccountFromFile(LinkedHashSet<Account> lstAcc){
        try{
            FileOutputStream fos = new FileOutputStream("Accounts.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(lstAcc);
            fos.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public LinkedHashSet<Account> readAccountFromFile(){
        try{
            FileInputStream fis = new FileInputStream("Accounts.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (LinkedHashSet<Account>) ois.readObject();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return null;  
    }
}
