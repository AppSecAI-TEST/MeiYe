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

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/5.
 */

public class TouTiaoActivity extends BaseActivity {

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
        setContentView(R.layout.activity_toutiao);
    }

    @Override
    protected void initData() {
        tvTitle.setText("美帝头条");
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new TouTiaoFragment(), "文章推荐");
        viewPagerAdapter.addFragment(new WenJuanFragment(), "问卷调查");
        viewPaterBar.setOffscreenPageLimit(2);
        viewPaterBar.setAdapter(viewPagerAdapter);
        tabLayoutBar.setupWithViewPager(viewPaterBar);
    }


    @OnClick(R.id.layout_back)
    public void onClick() {
        finish();
    }
}
