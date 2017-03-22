package com.duma.liudong.meiye.model;

/**
 * Created by liudong on 17/3/22.
 */

public class LoginBean {
    /**
     * user_id : 2
     * phone : 15088653868
     * token : mrknoc1489542142
     */

    private String user_id;
    private String phone;
    private String token;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "user_id='" + user_id + '\'' +
                ", phone='" + phone + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
