/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class Address implements Serializable{

    private String cityName;
    private String districtName;
    private String wardName;

    public Address() {
    }

    public Address(String cityName, String districtName, String wardName) {
        this.cityName = cityName;
        this.districtName = districtName;
        this.wardName = wardName;
    }
    
    public Address(Address addr) {
        this(addr.getCityName(), addr.getDistrictName(), addr.getWardName());
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }
    
    @Override
    public String toString(){
        return wardName +" "+districtName +" "+cityName;
    }
}
