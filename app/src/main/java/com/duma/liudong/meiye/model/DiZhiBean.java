package com.duma.liudong.meiye.model;

import java.io.Serializable;

/**
 * Created by 79953 on 2016/10/28.
 */

public class DiZhiBean implements Serializable {

    private String address_id;
    private String user_id;
    private String consignee;
    private String email;
    private String country;
    private String province;
    private String city;
    private String district;
    private String twon;
    private String address;
    private String zipcode;
    private String mobile;
    private String is_default;
    private String is_pickup;

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTwon() {
        return twon;
    }

    public void setTwon(String twon) {
        this.twon = twon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIs_default() {
        return is_default;
    }

    public void setIs_default(String is_default) {
        this.is_default = is_default;
    }

    public String getIs_pickup() {
        return is_pickup;
    }

    public void setIs_pickup(String is_pickup) {
        this.is_pickup = is_pickup;
    }

    @Override
    public String toString() {
        return "DiZhiBean{" +
                "address_id='" + address_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", consignee='" + consignee + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", twon='" + twon + '\'' +
                ", address='" + address + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", mobile='" + mobile + '\'' +
                ", is_default='" + is_default + '\'' +
                ", is_pickup='" + is_pickup + '\'' +
                '}';
    }
}
