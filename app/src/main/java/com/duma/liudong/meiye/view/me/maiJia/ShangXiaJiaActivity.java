package com.duma.liudong.meiye.view.me.maiJia;

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

public class ShangXiaJiaActivity extends BaseActivity {
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
    public String Store_id = "";

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_shangxiajia);
    }

    @Override
    protected void initData() {
        tvTitle.setText("商品上下架");
        Store_id = getIntent().getStringExtra("id");
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new ShangXiaJiaFragment(), "在售商品");
        viewPagerAdapter.addFragment(new ShangXiaJiaFragment(), "仓库商品");
        viewPaterBar.setOffscreenPageLimit(2);
        viewPaterBar.setAdapter(viewPagerAdapter);
        tabLayoutBar.setupWithViewPager(viewPaterBar);
    }


    @OnClick(R.id.layout_back)
    public void onClick() {
        finish();
    }

    public String getType() {
        //0: 下架 1:上
        switch (viewPaterBar.getCurrentItem()) {
            case 0:
                //在售
                return "1";
            default:
                //仓库商品
                return "2";

        }
    }
}
