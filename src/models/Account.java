/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Admin
 */
public class Account implements Serializable{
    
    private String user;
    private String password;

    public Account() {
    }

    public Account(String user, String password) throws Exception {
        if (user.equals("")){
            throw new Exception("Tên đăng nhập không được để trống.");
        }
        if (password.equals("")){
            throw new Exception("Mật khẩu không được để trống.");
        }
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) throws Exception {
        if (user.equals("")){
            throw new Exception("Tên đăng nhập không được để trống.");
        }
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws Exception {
        if (password.equals("")){
            throw new Exception("Mật khẩu không được để trống.");
        }
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }
    
}
