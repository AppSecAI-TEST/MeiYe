package com.duma.liudong.meiye.model;

import java.util.List;

/**
 * Created by liudong on 17/4/1.
 */

public class DemoBean {
    private List<goods> list;
    private huodong huodong;


    public DemoBean(List<goods> list, DemoBean.huodong huodong) {
        this.list = list;
        this.huodong = huodong;
    }

    public List<goods> getList() {
        return list;
    }

    public void setList(List<goods> list) {
        this.list = list;
    }

    public DemoBean.huodong getHuodong() {
        return huodong;
    }

    public void setHuodong(DemoBean.huodong huodong) {
        this.huodong = huodong;
    }

    public static class goods {
        String goodname;

        public goods(String goodname) {
            this.goodname = goodname;
        }

        public String getGoodname() {
            return goodname;
        }

        public void setGoodname(String goodname) {
            this.goodname = goodname;
        }
    }

    public static class huodong {
        String store_name;

        public huodong(String store_name) {
            this.store_name = store_name;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }
    }
}
