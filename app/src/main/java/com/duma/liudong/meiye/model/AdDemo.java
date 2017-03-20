package com.duma.liudong.meiye.model;

/**
 * Created by liudong on 17/3/16.
 */

public class AdDemo {
    String topData;
    String bottomData;

    public AdDemo(String topData, String bottomData) {
        this.topData = topData;
        this.bottomData = bottomData;
    }

    public String getTopData() {
        return topData;
    }

    public void setTopData(String topData) {
        this.topData = topData;
    }

    public String getBottomData() {
        return bottomData;
    }

    public void setBottomData(String bottomData) {
        this.bottomData = bottomData;
    }
}
