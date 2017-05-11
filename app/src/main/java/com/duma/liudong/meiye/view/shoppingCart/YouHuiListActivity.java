package com.duma.liudong.meiye.view.shoppingCart;

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
import com.duma.liudong.meiye.view.me.HongBaoFragment;
import com.duma.liudong.meiye.view.me.YouHuiJuanFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/5/10.
 */

public class YouHuiListActivity extends BaseActivity {
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
        setContentView(R.layout.activity_youhui);
    }

    @Override
    protected void initData() {
        tvTitle.setText("我的优惠");
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new YouHuiJuanFragment(), "优惠券");
        viewPagerAdapter.addFragment(new HongBaoFragment(), "红包");
        viewPaterBar.setOffscreenPageLimit(2);
        viewPaterBar.setAdapter(viewPagerAdapter);
        tabLayoutBar.setupWithViewPager(viewPaterBar);
    }

    public String getType() {
        Lg.e(viewPaterBar.getCurrentItem() + "");
        //0: 优惠券 1:红包
        switch (viewPaterBar.getCurrentItem()) {
            case 0:
                return "0";
            default:
                return "1";
        }
    }

    @OnClick(R.id.layout_back)
    public void onClick() {
        finish();
    }

}
