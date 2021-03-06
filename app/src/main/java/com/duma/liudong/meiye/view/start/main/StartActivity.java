package com.duma.liudong.meiye.view.start.main;

import android.content.Intent;
import android.os.Bundle;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.StartUtil;

/**
 * Created by liudong on 17/4/27.
 */

public class StartActivity extends BaseActivity {

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_start);
    }

    @Override
    protected void initData() {
        StartUtil.goOridService(mActivity);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                gogogo();
//            }
//        }, 500);
        gogogo();
    }

    private void gogogo() {
        if (MyApplication.getSpUtils2().getString(Constants.isOne).equals(Constants.isOne)) {
            StartUtil.toMain(mActivity);
        } else {
            startActivity(new Intent(mActivity, ViewPaterActivity.class));
            MyApplication.getSpUtils2().put(Constants.isOne, Constants.isOne);
        }
        finish();
    }
}
