package com.duma.liudong.meiye.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.MyApplication;

/**
 * Created by liudong on 17/3/21.
 */

public class CodeTimeUtil extends CountDownTimer {

    private TextView tv_code;

    /**
     * @param millisInFuture The number of millis in the future from the call
     *                       to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                       is called.
     */
    public CodeTimeUtil(long millisInFuture, TextView textView) {
        super(millisInFuture * 1000, 100);
        this.tv_code = textView;
    }

    public void startTime() {
        StartUtil.tvHui(tv_code);
        start();
    }

    @Override
    public void onTick(long millisUntilFinished) {
        tv_code.setText(millisUntilFinished / 1000 + "s后重试");
    }

    @Override
    public void onFinish() {
        tv_code.setText(MyApplication.getInstance().getString(R.string.code));
        StartUtil.tvRed(tv_code);
    }
}
