package com.duma.liudong.meiye.view.start.main;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.model.SlideBus;
import com.duma.liudong.meiye.utils.Lg;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/3/22.
 */

public class SlideActivity extends BaseActivity implements SeekBar.OnSeekBarChangeListener {
    @BindView(R.id.sb_bar)
    SeekBar sbBar;
    @BindView(R.id.tv_text)
    TextView tvText;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_slide);
    }

    @Override
    protected void initData() {
        sbBar.setOnSeekBarChangeListener(this);
    }

    @OnClick(R.id.sb_bar)
    public void onClick() {
        Lg.e("111");
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar.getProgress() == seekBar.getMax()) {
            //滑动完成
            tvText.setVisibility(View.VISIBLE);
            tvText.setText("完成验证");
            tvText.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.white));
            finish();
            EventBus.getDefault().post(new SlideBus(0));
        } else {
            tvText.setVisibility(View.GONE);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        if (seekBar.getProgress() != 0) {
            initSb(seekBar);
        }
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (seekBar.getProgress() != seekBar.getMax()) {
            initSb(seekBar);
        }
    }

    private void initSb(SeekBar seekBar) {
        seekBar.setProgress(0);
        tvText.setVisibility(View.VISIBLE);
        tvText.setText("请按住滑块,拖到最右边");
        tvText.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.texthei));
    }
}
