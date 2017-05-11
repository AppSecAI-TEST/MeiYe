package com.duma.liudong.meiye.model;

import java.util.List;

/**
 * Created by liudong on 17/3/28.
 */

public class JifenBean {

    /**
     * reward_points : 1000
     * pay_points : 200
     * detail : [{"time":"2016-08-14 00:57:49","desc":"签到积分奖励","points":"100"},{"time":"2016-08-14 00:57:49","desc":"签到积分奖励","points":"100"},{"time":"2016-08-14 00:57:49","desc":"签到积分奖励","points":"100"},{"time":"2016-08-14 00:57:49","desc":"订单245454825使用","points":"-200"}]
     */

    private String reward_points;
    private String pay_points;
    private List<DetailBean> detail;

    public String getReward_points() {
        return reward_points;
    }

    public void setReward_points(String reward_points) {
        this.reward_points = reward_points;
    }

    public String getPay_points() {
        return pay_points;
    }

    public void setPay_points(String pay_points) {
        this.pay_points = pay_points;
    }

    public List<DetailBean> getDetail() {
        return detail;
    }

    public void setDetail(List<DetailBean> detail) {
        this.detail = detail;
    }

    public static class DetailBean {
        /**
         * time : 2016-08-14 00:57:49
         * desc : 签到积分奖励
         * points : 100
         */

        private String time;
        private String desc;
        private String points;
        private String now_points;

        public String getNow_points() {
            return now_points;
        }

        public void setNow_points(String now_points) {
            this.now_points = now_points;
        }

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

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }
    }
}
