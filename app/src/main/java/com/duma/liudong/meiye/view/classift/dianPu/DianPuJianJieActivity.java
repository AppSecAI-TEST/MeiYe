package com.duma.liudong.meiye.view.classift.dianPu;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.MyViewPagerAdapter;
import com.duma.liudong.meiye.model.DianPubean;
import com.duma.liudong.meiye.utils.StartUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/7.
 */

public class DianPuJianJieActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.layout_other)
    LinearLayout layoutOther;
    @BindView(R.id.tabLayout_bar)
    TabLayout tabLayoutBar;
    @BindView(R.id.viewPater_bar)
    ViewPager viewPaterBar;

    public DianPubean dianPubean;
    @BindView(R.id.tv_jubao)
    TextView tvJubao;

    private String id;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_dianpujianjie);
    }

    @Override
    protected void initData() {
        tvTitle.setText("店铺简介");
        dianPubean = (DianPubean) getIntent().getSerializableExtra("bean");
        id = getIntent().getStringExtra("id");
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new DianPuJianJiOneFragment(), "基本信息");
        viewPagerAdapter.addFragment(new DianPuJianJiTwoFragment(), "认证信息");
        viewPaterBar.setAdapter(viewPagerAdapter);
        tabLayoutBar.setupWithViewPager(viewPaterBar);

        tvJubao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartUtil.toJubao_dp(mActivity, id);
            }
        });
    }


    @OnClick(R.id.layout_back)
    public void onClick() {
        finish();
    }

}
