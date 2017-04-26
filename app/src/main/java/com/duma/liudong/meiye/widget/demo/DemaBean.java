package com.duma.liudong.meiye.widget.demo;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by liudong on 17/4/25.
 */

public class DemaBean implements MultiItemEntity {

    private String name;


    @Override
    public int getItemType() {
        return 0;
    }
}
