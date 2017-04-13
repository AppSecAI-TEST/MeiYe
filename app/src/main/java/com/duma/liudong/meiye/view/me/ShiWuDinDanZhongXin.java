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

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/12.
 */

public class ShiWuDinDanZhongXin extends BaseActivity {
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

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_shiwudindan);
    }

    @Override
    protected void initData() {
        tvTitle.setText("优惠券");
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new ShiWuDinDanFragment(), "全部");
        viewPagerAdapter.addFragment(new ShiWuDinDanFragment(), "待付款");
        viewPagerAdapter.addFragment(new ShiWuDinDanFragment(), "待收货");
        viewPagerAdapter.addFragment(new ShiWuDinDanFragment(), "待评价");
        viewPagerAdapter.addFragment(new ShiWuDinDanFragment(), "退款");
        viewPaterBar.setOffscreenPageLimit(5);
        viewPaterBar.setAdapter(viewPagerAdapter);
        tabLayoutBar.setupWithViewPager(viewPaterBar);
    }

    @OnClick(R.id.layout_back)
    public void onClick() {
        finish();
    }
}
