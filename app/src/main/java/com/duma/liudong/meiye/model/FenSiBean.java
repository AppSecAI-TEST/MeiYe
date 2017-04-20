package com.duma.liudong.meiye.model;

/**
 * Created by liudong on 17/4/20.
 */

public class FenSiBean {

    /**
     * user_id : 1
     * nickname : 123
     * head_pic : http:*www.meiye.com/Public/upload/head_pic/2017-04
     */

    private String user_id;
    private String nickname;
    private String head_pic;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNickname() {
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
}
