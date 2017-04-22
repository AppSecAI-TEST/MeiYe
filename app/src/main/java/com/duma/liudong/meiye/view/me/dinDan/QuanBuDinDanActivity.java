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
import com.duma.liudong.meiye.utils.StartUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/12.
 */

public class QuanBuDinDanActivity extends BaseActivity {
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
    public String type = "1";//1:实物,2:定制,3:团购

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_shiwudindan);
    }

    @Override
    protected void initData() {
        type = getIntent().getStringExtra("type");
        switch (type) {
            case "1":
                tvTitle.setText("实物订单");
                break;
            case "2":
                tvTitle.setText("定制订单");
                break;
            case "3":
                tvTitle.setText("团购订单");
                break;
        }
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

    public String getfenlei_Type() {
        //0: 全部 1:待付款,2:待收货3.待评价4退款
        switch (viewPaterBar.getCurrentItem()) {
            case 0:
                return "";
            case 1:
                return "WAITPAY";
            case 2:
                return "WAITRECEIVE";
            case 3:
                return "WAITCCOMMENT";
            default:
                return "RETURNED";
        }
    }

    @Override
    protected void OnBack() {
        StartUtil.toMain(mActivity);
    }
}