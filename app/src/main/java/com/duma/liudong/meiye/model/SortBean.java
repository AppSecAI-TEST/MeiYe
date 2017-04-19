package com.duma.liudong.meiye.model;

/**
 * Created by liudong on 17/4/18.
 */

public class SortBean {
    private String id;
    private String name;

    public SortBean(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
