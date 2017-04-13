package com.duma.liudong.meiye.view.me;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 79953 on 2016/10/28.
 */

public class ShouHuoDiZhiActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_shouhuodizhi_fragment);
    }

    @Override
    protected void initData() {
        tvTitle.setText("收货地址");
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.layout_addFragment, DiZhiFragment.newInstance());
        transaction.commit();
    }

    @OnClick(R.id.layout_back)
    public void onClick() {
        finish();
    }

}
