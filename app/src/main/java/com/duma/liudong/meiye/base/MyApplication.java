package com.duma.liudong.meiye.base;


import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.SPUtils;
import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import okhttp3.OkHttpClient;


public class MyApplication extends Application {
    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Utils.init(this);
        Logger.init(Constants.TAG);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor(Constants.TAG))
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
//        CrashHandler.getInstance().init(this);
    }

    private static class LazyHolder {
        private static final SPUtils INSTANCE = new SPUtils("MeiYe");
    }

    public static SPUtils getSpUtils() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder2 {
        private static final SPUtils INSTANCE = new SPUtils("one");
    }

    public static SPUtils getSpUtils2() {
        return LazyHolder2.INSTANCE;
    }
}
