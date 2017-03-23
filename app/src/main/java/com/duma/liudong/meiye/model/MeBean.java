package com.duma.liudong.meiye.model;

/**
 * Created by liudong on 17/3/23.
 */

public class MeBean {

    /**
     * head_pic : null
     * nick_name : null
     * level : 1
     * ref_num : 18888888888
     * distribut_money : 0.00
     * new_money : null
     * total_client : 2
     * new_client : 0
     * user_money : 3.00
     * reward_points : 0
     */


    private String head_pic;
    private String nick_name;
    private String level;
    private String ref_num;
    private String distribut_money;
    private String new_money;
    private String total_client;
    private String new_client;
    private String user_money;
    private String reward_points;

    public String getHead_pic() {
        return head_pic;
    }

    public void setHead_pic(String head_pic) {
        this.head_pic = head_pic;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRef_num() {
        return ref_num;
    }

    public void setRef_num(String ref_num) {
        this.ref_num = ref_num;
    }

    public String getDistribut_money() {
        return distribut_money;
    }

    public void setDistribut_money(String distribut_money) {
        this.distribut_money = distribut_money;
    }

    public String getNew_money() {
        return new_money;
    }

    public void setNew_money(String new_money) {
        this.new_money = new_money;
    }

    public String getTotal_client() {
        return total_client;
    }

    public void setTotal_client(String total_client) {
        this.total_client = total_client;
    }

    public String getNew_client() {
        return new_client;
    }

    public void setNew_client(String new_client) {
        this.new_client = new_client;
    }

    public String getUser_money() {
        return user_money;
    }

    public void setUser_money(String user_money) {
        this.user_money = user_money;
    }

    public String getReward_points() {
        return reward_points;
    }

    public void setReward_points(String reward_points) {
        this.reward_points = reward_points;
    }

    @Override
    public String toString() {
        return "MeBean{" +
                "head_pic=" + head_pic +
                ", nick_name=" + nick_name +
                ", level='" + level + '\'' +
                ", ref_num='" + ref_num + '\'' +
                ", distribut_money='" + distribut_money + '\'' +
                ", new_money=" + new_money +
                ", total_client='" + total_client + '\'' +
                ", new_client='" + new_client + '\'' +
                ", user_money='" + user_money + '\'' +
                ", reward_points='" + reward_points + '\'' +
                '}';
    }
}
