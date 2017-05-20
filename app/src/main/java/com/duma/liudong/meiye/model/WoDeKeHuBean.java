package com.duma.liudong.meiye.model;

import java.util.List;

/**
 * Created by liudong on 17/3/27.
 */

public class WoDeKeHuBean {

    private List<XiajiBean> xiaji;
    private List<XiajiBean> shangji;

    public List<XiajiBean> getXiaji() {
        return xiaji;
    }

    public void setXiaji(List<XiajiBean> xiaji) {
        this.xiaji = xiaji;
    }

    public List<XiajiBean> getShangji() {
        return shangji;
    }

    public void setShangji(List<XiajiBean> shangji) {
        this.shangji = shangji;
    }

    public static class XiajiBean {
        /**
         * user_id : 6
         * nickname :
         * head_pic : http://meiye.duma-ivy.cn
         * mobile : 15270001512
         */

        private String user_id;
        private String nickname;
        private String head_pic;
        private String mobile;
        private String reg_time;
        private String level;

        public String getReg_time() {
            return reg_time;
        }

        public void setReg_time(String reg_time) {
            this.reg_time = reg_time;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
