package com.duma.liudong.meiye.view.home;

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
 * Created by liudong on 17/4/10.
 */

public class MiaoShaActivity extends BaseActivity {
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
        setContentView(R.layout.activity_miaosha);
    }

    @Override
    protected void initData() {
        tvTitle.setText("周日秒杀");
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new MiaoShaFragment(), "实物商品");
        viewPagerAdapter.addFragment(new MiaoShaFragment(), "定制商品");
        viewPagerAdapter.addFragment(new MiaoShaFragment(), "服务商品");
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
        //0: 实物商品 1: 定制商品,2:服务商品
        switch (viewPaterBar.getCurrentItem()) {
            case 0:
                return "1";
            case 1:
                return "2";
            default:
                return "3";
        }
    }
}
