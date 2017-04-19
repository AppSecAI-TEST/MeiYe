package com.duma.liudong.meiye.model;

import java.util.ArrayList;

/**
 * Created by liudong on 17/3/27.
 */

public class UserMoneyBean {

    /**
     * user_money : 3.00
     * detail : [{"time":"2016-08-14 00:57:49","desc":"邀请注册奖励","money":"3.00"}]
     */

    private String user_money;
    private ArrayList<DetailBean> detail;

    public String getUser_money() {
        return user_money;
    }

    public void setUser_money(String user_money) {
        this.user_money = user_money;
    }

    public ArrayList<DetailBean> getDetail() {
        return detail;
    }

    public void setDetail(ArrayList<DetailBean> detail) {
        this.detail = detail;
    }

    public static class DetailBean {
        /**
         * time : 2016-08-14 00:57:49
         * desc : 邀请注册奖励
         * money : 3.00
         */

        private String time;
        private String desc;
        private String user_money;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getUser_money() {
            return user_money;
        }

        public void setUser_money(String user_money) {
            this.user_money = user_money;
        }
    }
}
