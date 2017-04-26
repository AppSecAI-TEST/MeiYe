package com.duma.liudong.meiye.widget.demo;

import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by liudong on 17/4/25.
 */

public class SimpleAdapter<T> {
    private volatile static SimpleAdapter mInstance;

    public static SimpleAdapter initClient(SimpleAdapter simpleAdapter) {
        if (mInstance == null) {
            synchronized (OkHttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new SimpleAdapter();
                }
            }
        }
        return mInstance;
    }
}
