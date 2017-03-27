package com.duma.liudong.meiye.model;

/**
 * Created by liudong on 17/3/27.
 */

public class WoDeKeHuBean {

    @Override
    public String toString() {
        return "ResultBean{" +
                "user_id='" + user_id + '\'' +
                ", nickname=" + nickname +
                ", head_pic=" + head_pic +
                ", mobile='" + mobile + '\'' +
                '}';
    }

    /**
     * user_id : 1
     * nickname : null
     * head_pic : null
     * mobile : 16666666666
     */


    private String user_id;
    private String nickname;
    private String head_pic;
    private String mobile;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNickname() {
        if (nickname == null) {
            return "用户:" + mobile;
        }
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHead_pic() {
        return head_pic;
    }

    public void setHead_pic(String head_pic) {
        this.head_pic = head_pic;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
