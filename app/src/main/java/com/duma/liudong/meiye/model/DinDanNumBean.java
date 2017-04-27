package com.duma.liudong.meiye.model;

/**
 * Created by liudong on 17/4/27.
 */

public class DinDanNumBean {

    /**
     * indeed : {"wp":"0","wr":"0","wc":"0","re":"0","total":0}
     * custom : {"wp":"0","wr":"0","wc":"0","re":"0","total":0}
     * server : {"wp":"0","wr":"1","wc":"0","re":"0","total":1}
     */

    private IndeedBean indeed;
    private CustomBean custom;
    private ServerBean server;

    public IndeedBean getIndeed() {
        return indeed;
    }

    public void setIndeed(IndeedBean indeed) {
        this.indeed = indeed;
    }

    public CustomBean getCustom() {
        return custom;
    }

    public void setCustom(CustomBean custom) {
        this.custom = custom;
    }

    public ServerBean getServer() {
        return server;
    }

    public void setServer(ServerBean server) {
        this.server = server;
    }

    public static class IndeedBean {
        /**
         * wp : 0
         * wr : 0
         * wc : 0
         * re : 0
         * total : 0
         */

        private String wp;
        private String wr;
        private String wc;
        private String re;
        private int total;

        public String getWp() {
            return wp;
        }

        public void setWp(String wp) {
            this.wp = wp;
        }

        public String getWr() {
            return wr;
        }

        public void setWr(String wr) {
            this.wr = wr;
        }

        public String getWc() {
            return wc;
        }

        public void setWc(String wc) {
            this.wc = wc;
        }

        public String getRe() {
            return re;
        }

        public void setRe(String re) {
            this.re = re;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }

    public static class CustomBean {
        /**
         * wp : 0
         * wr : 0
         * wc : 0
         * re : 0
         * total : 0
         */

        private String wp;
        private String wr;
        private String wc;
        private String re;
        private int total;

        public String getWp() {
            return wp;
        }

        public void setWp(String wp) {
            this.wp = wp;
        }

        public String getWr() {
            return wr;
        }

        public void setWr(String wr) {
            this.wr = wr;
        }

        public String getWc() {
            return wc;
        }

        public void setWc(String wc) {
            this.wc = wc;
        }

        public String getRe() {
            return re;
        }

        public void setRe(String re) {
            this.re = re;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }

    public static class ServerBean {
        /**
         * wp : 0
         * wr : 1
         * wc : 0
         * re : 0
         * total : 1
         */

        private String wp;
        private String wr;
        private String wc;
        private String re;
        private int total;

        public String getWp() {
            return wp;
        }

        public void setWp(String wp) {
            this.wp = wp;
        }

        public String getWr() {
            return wr;
        }

        public void setWr(String wr) {
            this.wr = wr;
        }

        public String getWc() {
            return wc;
        }

        public void setWc(String wc) {
            this.wc = wc;
        }

        public String getRe() {
            return re;
        }

        public void setRe(String re) {
            this.re = re;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }
}
