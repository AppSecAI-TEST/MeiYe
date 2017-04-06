package com.duma.liudong.meiye.view.classift;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.base.MyViewPagerAdapter;
import com.duma.liudong.meiye.model.DianPubean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.google.gson.Gson;
import com.gxz.library.StickyNavLayout;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/5.
 */

public class DianPuActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.layout_sousuo)
    LinearLayout layoutSousuo;
    @BindView(R.id.img_store_logo)
    ImageView imgStoreLogo;
    @BindView(R.id.tv_store_name)
    TextView tvStoreName;
    @BindView(R.id.tv_store_time_y)
    TextView tvStoreTimeY;
    @BindView(R.id.tv_order_num)
    TextView tvOrderNum;
    @BindView(R.id.tv_store_collect)
    TextView tvStoreCollect;
    @BindView(R.id.img_store_banner)
    ImageView imgStoreBanner;


    @BindView(R.id.id_stickynavlayout_topview)
    LinearLayout idStickynavlayoutTopview;
    @BindView(R.id.id_stickynavlayout_indicator)
    TabLayout tabLayoutBar;
    @BindView(R.id.id_stickynavlayout_viewpager)
    ViewPager viewPaterBar;
    @BindView(R.id.snLayout_bar)
    StickyNavLayout snLayoutBar;
    private DianPuShouYeFragment dianPuShouYeFragment;

    public String store_id;
    public DianPubean dianPubean;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_dianpu);
    }


    @Override
    protected void initData() {
        store_id = getIntent().getStringExtra("id");
        final int[] huiDrawable = {R.drawable.img_68, R.drawable.img_69, R.drawable.img_73};
        final int[] radDrawable = {R.drawable.img_67, R.drawable.img_70, R.drawable.img_72};
        dianPuShouYeFragment = new DianPuShouYeFragment();
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(dianPuShouYeFragment, "首页");
        viewPagerAdapter.addFragment(new DianPuShangPingFragment(), "全部商品");
        viewPagerAdapter.addFragment(new DianPuHuoDongFragment(), "活动动态");
        viewPaterBar.setOffscreenPageLimit(3);
        viewPaterBar.setAdapter(viewPagerAdapter);
        tabLayoutBar.setupWithViewPager(viewPaterBar);
        for (int i = 0; i < tabLayoutBar.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayoutBar.getTabAt(i);
            if (i == 0) {
                tab.setIcon(radDrawable[i]);
            } else {
                tab.setIcon(huiDrawable[i]);
            }
        }
        tabLayoutBar.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.setIcon(radDrawable[tab.getPosition()]);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setIcon(huiDrawable[tab.getPosition()]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        OkHttpUtils
                .get()
                .tag("this")
                .url(Api.Storeindex)
                .addParams("store_id", store_id)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        dianPubean = new Gson().fromJson(result, DianPubean.class);
                        initRes();
                    }
                });
    }

    private void initRes() {
        ImageLoader.with(dianPubean.getStore_banner(), imgStoreBanner);
        ImageLoader.withYuan(dianPubean.getStore_logo(), imgStoreLogo);
        tvStoreName.setText(dianPubean.getStore_name());
        tvStoreTimeY.setText(dianPubean.getStore_time_y());
        tvOrderNum.setText(dianPubean.getOrder_num());
        tvStoreCollect.setText(dianPubean.getStore_collect());
        dianPuShouYeFragment.setBannerShangping();
    }

    @OnClick({R.id.layout_back, R.id.layout_sousuo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.layout_sousuo:
                break;
        }
    }

}
