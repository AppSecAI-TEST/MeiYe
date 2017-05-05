package com.duma.liudong.meiye.view.classift.dianPu;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.base.MyViewPagerAdapter;
import com.duma.liudong.meiye.model.DianPubean;
import com.duma.liudong.meiye.model.SlideBus;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.DialogUtil;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.Ts;
import com.duma.liudong.meiye.view.dialog.ServiceDialog;
import com.duma.liudong.meiye.view.home.SouSuoActivity;
import com.duma.liudong.meiye.widget.ScrollableLayout;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/5.
 */

public class DianPuActivity extends BaseActivity implements ScrollableLayout.OnScrollListener {
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
    @BindView(R.id.TabLayout)
    TabLayout tabLayoutBar;
    @BindView(R.id.viewPager)
    ViewPager viewPaterBar;
    @BindView(R.id.ScrollableLayout)
    com.duma.liudong.meiye.widget.ScrollableLayout ScrollableLayout;
    @BindView(R.id.btn_feilei)
    RadioButton btnFeilei;
    @BindView(R.id.btn_jianjie)
    RadioButton btnJianjie;
    @BindView(R.id.btn_maijia)
    RadioButton btnMaijia;
    @BindView(R.id.group_btn)
    RadioGroup groupBtn;
    @BindView(R.id.layout_shoucang)
    LinearLayout layoutShoucang;
    private DianPuShouYeFragment dianPuShouYeFragment;
    public String store_id;
    public DianPubean dianPubean;
    private ServiceDialog serviceDialog;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_dianpu);
    }


    @Override
    protected void initData() {
        groupBtn.setVisibility(View.GONE);
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

        ScrollableLayout.setOnScrollListener(this);
    }

    @Override
    protected void OnBack() {
        if (isSticked()) {
            int y = ScrollableLayout.mCurY;
            ValueAnimator anim = ValueAnimator.ofFloat(y, 0f);
            anim.setDuration(300);
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float currentValue = (float) animation.getAnimatedValue();
                    ScrollableLayout.scrollTo(0, (int) currentValue - 1);
                }
            });
            anim.start();
            EventBus.getDefault().post(new SlideBus(1));
        } else {
            finish();
        }
    }

    public boolean isSticked() {
        return ScrollableLayout.isSticked();
    }

    private void initRes() {
        ImageLoader.with(dianPubean.getStore_banner(), imgStoreBanner);
        ImageLoader.withYuan(dianPubean.getStore_logo(), imgStoreLogo);
        tvStoreName.setText(dianPubean.getStore_name());
        tvStoreTimeY.setText(dianPubean.getStore_time_y() + "年");
        tvOrderNum.setText("成交" + dianPubean.getOrder_num() + "笔");
        tvStoreCollect.setText(dianPubean.getStore_collect());
        dianPuShouYeFragment.setBannerShangping();
        groupBtn.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.layout_back, R.id.layout_sousuo, R.id.btn_feilei, R.id.btn_jianjie, R.id.btn_maijia, R.id.layout_shoucang})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.layout_sousuo:
                startActivity(new Intent(mActivity, SouSuoActivity.class));
                break;
            case R.id.layout_shoucang:
                ShouCangHttp();
                break;
            case R.id.btn_feilei:
                Intent intent = new Intent(mActivity, DianPuClassiftActivity.class);
                intent.putExtra("id", store_id);
                startActivity(intent);
                break;
            case R.id.btn_jianjie:
                Intent intent1 = new Intent(mActivity, DianPuJianJieActivity.class);
                intent1.putExtra("bean", dianPubean);
                startActivity(intent1);
                break;
            case R.id.btn_maijia:
                serviceDialog = new ServiceDialog(mActivity, dianPubean.getStore_phone());
                serviceDialog.Show();
                break;
        }
    }

    private void ShouCangHttp() {
        DialogUtil.show(mActivity);
        OkHttpUtils.getInstance().cancelTag("ShouCangHttp");
        OkHttpUtils
                .get()
                .tag("ShouCangHttp")
                .url(Api.storeCollect)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .addParams("store_id", store_id)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        DialogUtil.hide();
                        Ts.setText("收藏成功!");
                    }
                });
    }

    @Override
    public void onScroll(int currentY, int maxY) {
        if (currentY == maxY) {
            //打开下拉刷新
            EventBus.getDefault().post(new SlideBus(0));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
