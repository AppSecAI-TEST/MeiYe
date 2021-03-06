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
    public String type = "1";//1:实物,2:拼团,3:服务
    public int position = 0;
    public String store_id = "";
    public String isFinish = "";

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_shiwudindan);
    }

    @Override
    protected void initData() {
        type = getIntent().getStringExtra("type");
        store_id = getIntent().getStringExtra("store_id");
        position = Integer.parseInt(getIntent().getStringExtra("position").equals("") ? "0" : getIntent().getStringExtra("position"));
        isFinish = getIntent().getStringExtra("isFinish");
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new ShiWuDinDanFragment(), "全部");
        viewPagerAdapter.addFragment(new ShiWuDinDanFragment(), "待付款");
        switch (type) {
            case "1":
                viewPagerAdapter.addFragment(new ShiWuDinDanFragment(), "待发货");
                viewPagerAdapter.addFragment(new ShiWuDinDanFragment(), "待收货");
                tvTitle.setText("实物订单");
                break;
            case "2":
                viewPagerAdapter.addFragment(new ShiWuDinDanFragment(), "待发货");
                viewPagerAdapter.addFragment(new ShiWuDinDanFragment(), "待收货");
                tvTitle.setText("定制订单");
                break;
            case "3":
                viewPagerAdapter.addFragment(new ShiWuDinDanFragment(), "待使用");
                tvTitle.setText("服务订单");
                break;
        }
        viewPagerAdapter.addFragment(new ShiWuDinDanFragment(), "待评价");
        viewPagerAdapter.addFragment(new ShiWuDinDanFragment(), "退款中");
        viewPaterBar.setOffscreenPageLimit(5);
        viewPaterBar.setAdapter(viewPagerAdapter);
        tabLayoutBar.setupWithViewPager(viewPaterBar);
        tabLayoutBar.getTabAt(position).select();
    }

    @OnClick(R.id.layout_back)
    public void onClick() {
        if (isFinish.equals("")) {
            finish();
        } else {
            StartUtil.toMain(mActivity);
        }

    }

    public String getfenlei_Type() {
        //0: 全部 1:待付款2.待发货,3:待收货4.待评价5退款
        if (!type.equals("3")) {
            switch (viewPaterBar.getCurrentItem()) {
                case 0:
                    return "";
                case 1:
                    return "WAITPAY";
                case 2:
                    return "WAITSEND";
                case 3:
                    return "WAITRECEIVE";
                case 4:
                    return "WAITCCOMMENT";
                default:
                    return "RETURNED";
            }
        } else {
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

    }

    @Override
    protected void OnBack() {
        if (isFinish.equals("")) {
            finish();
        } else {
            StartUtil.toMain(mActivity);
        }
    }
}
