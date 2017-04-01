package com.duma.liudong.meiye.model;

/**
 * Created by liudong on 17/3/31.
 */

public class ShaiXuanBean {
    private String res;
    private String name;

    public ShaiXuanBean(String res, String name) {
        this.res = res;
        this.name = name;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
