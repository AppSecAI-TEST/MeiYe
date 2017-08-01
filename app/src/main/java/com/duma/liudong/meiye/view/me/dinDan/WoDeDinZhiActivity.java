package com.duma.liudong.meiye.view.me.dinDan;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.MyViewPagerAdapter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/21.
 */

public class WoDeDinZhiActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_other)
    ImageView imgOther;
    @BindView(R.id.layout_other)
    LinearLayout layoutOther;
    @BindView(R.id.tabLayout_bar)
    TabLayout tabLayoutBar;
    @BindView(R.id.viewPater_bar)
    ViewPager viewPaterBar;

    public boolean isOne = true;
    private int position = 0;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_wodedinzhi);
    }

    @Override
    protected void initData() {
        tvTitle.setText("我的拼团");
        position = Integer.parseInt(getIntent().getStringExtra("position"));
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new DinZhiFragment(), "进行中");
        viewPagerAdapter.addFragment(new DinZhiFragment(), "已完成");
        viewPaterBar.setOffscreenPageLimit(2);
        viewPaterBar.setAdapter(viewPagerAdapter);
        tabLayoutBar.setupWithViewPager(viewPaterBar);
        tabLayoutBar.getTabAt(position).select();
    }

    @OnClick(R.id.layout_back)
    public void onClick() {
        finish();
    }

    public String getType() {
        //1:进行中,0:已完成
        switch (viewPaterBar.getCurrentItem()) {
            case 0:
                return "1";
            default:
                return "0";
        }
    }
}
