package com.duma.liudong.meiye.view.me;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.MyViewPagerAdapter;
import com.duma.liudong.meiye.utils.Lg;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/3/28.
 */

public class YouHuiJuanActivity extends BaseActivity {
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

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_youhuijuan);
    }

    @Override
    protected void initData() {
        tvTitle.setText("优惠券");
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new YouHuiJuanFragment(), "未使用");
        viewPagerAdapter.addFragment(new YouHuiJuanFragment(), "已使用");
        viewPagerAdapter.addFragment(new YouHuiJuanFragment(), "已过期");
        viewPaterBar.setOffscreenPageLimit(3);
        viewPaterBar.setAdapter(viewPagerAdapter);
        tabLayoutBar.setupWithViewPager(viewPaterBar);
    }

    @OnClick(R.id.layout_back)
    public void onClick() {
        finish();
    }

    public String getType() {
        Lg.e(viewPaterBar.getCurrentItem() + "");
        //0: 未使用 1:已用,2:过期
        switch (viewPaterBar.getCurrentItem()) {
            case 0:
                return "0";
            case 1:
                return "1";
            default:
                return "2";
        }
    }

}
