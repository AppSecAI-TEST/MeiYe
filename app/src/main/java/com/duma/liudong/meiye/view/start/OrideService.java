package com.duma.liudong.meiye.view.start;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.model.MjXIaoXiBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.StartUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by liudong on 2017/6/24.
 */

public class OrideService extends Service {

    private Timer mTuiSongTimer;
    private TimerTask mTuiSongTask;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private void initTask() {
        mTuiSongTimer = new Timer();
        mTuiSongTask = new TimerTask() {
            @Override
            public void run() {
                if (StartUtil.isLogin()) {
                    OkHttpUtils
                            .get()
                            .tag(this)
                            .url(Api.tixing_order)
                            .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                            .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                            .build()
                            .execute(new MyStringCallback() {
                                @Override
                                public void onMySuccess(String result) {
                                    MjXIaoXiBean mjXIaoXiBean = new Gson().fromJson(result, MjXIaoXiBean.class);
                                    if (!mjXIaoXiBean.getCount().equals("0")) {
                                        StartUtil.sendNotification(MyApplication.getInstance());
                                    }
                                }
                            });
                }
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mTuiSongTimer != null) {
            mTuiSongTimer.cancel();
        }
        initTask();
        mTuiSongTimer.schedule(mTuiSongTask, 0, 10 * 1000);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
