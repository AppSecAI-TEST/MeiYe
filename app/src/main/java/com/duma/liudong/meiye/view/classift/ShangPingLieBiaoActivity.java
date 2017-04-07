package com.duma.liudong.meiye.view.classift;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.model.ShaiXuanBean;
import com.duma.liudong.meiye.model.SlideBus;
import com.duma.liudong.meiye.presenter.ShangPinLieBiaoPresenter;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.StartUtil;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.request.RequestCall;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/3/30.
 */

public class ShangPingLieBiaoActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_other)
    ImageView imgOther;
    @BindView(R.id.layout_other)
    LinearLayout layoutOther;

    @BindView(R.id.layout_kong)
    LinearLayout layoutKong;
    @BindView(R.id.rv_shangping)
    RecyclerView rvShangping;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;
    @BindView(R.id.layout_fragment)
    FrameLayout layoutFragment;
    @BindView(R.id.layout_tag_flow)
    TagFlowLayout layoutTagFlow;
    @BindView(R.id.layout_drawerLayout)
    DrawerLayout layoutDrawerLayout;
    @BindView(R.id.layout_right)
    LinearLayout layoutRight;
    @BindView(R.id.layout_tag_Jiage)
    TagFlowLayout layoutTagJiage;
    @BindView(R.id.btn_chongzhi)
    Button btnChongzhi;
    @BindView(R.id.btn_queding)
    Button btnQueding;

    private String key, Value, title;
    private PaiXuFragment paiXuFragment;
    private ShangPinLieBiaoPresenter shangPinPresenter;
    private int type = 0;//当前试图类型

    private List<ShaiXuanBean> list_shaixuan;
    private List<String> list_jiage;
    private TagAdapter<ShaiXuanBean> shaixuanAdapter;
    private TagAdapter<String> jiaGeAdapter;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_shangpingliebiao);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {
        layoutDrawerLayout.setFocusable(true);
        //初始化排序bar
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.layout_fragment, getPaixuFragment());
        transaction.commit();
        key = getIntent().getStringExtra("key");
        Value = getIntent().getStringExtra("Value");
        title = getIntent().getStringExtra("title");
        tvTitle.setText(title);
        imgOther.setImageDrawable(mActivity.getResources().getDrawable(R.drawable.l4q));

        StartUtil.setSw(swLoading, this);
        shangPinPresenter = new ShangPinLieBiaoPresenter(mActivity, rvShangping);
        shangPinPresenter.setKongView(layoutKong);
        shangPinPresenter.setShangPinListener(new ShangPinLieBiaoPresenter.OnShangPinListener() {
            @Override
            public void loading_hide() {
                swLoading.setRefreshing(false);
            }

            @Override
            public void loading_show() {
                swLoading.setRefreshing(true);
            }

            @Override
            public void onLoadMore() {
                shangPinPresenter.QueryHttp(getBuild());
            }

        });

        list_shaixuan = new ArrayList<>();
        list_shaixuan.add(new ShaiXuanBean("交易保障", "filter_jiaoyi"));
        list_shaixuan.add(new ShaiXuanBean("7天包退换", "filter_qitian"));
        list_shaixuan.add(new ShaiXuanBean("正品保障", "filter_zhengpin"));
        list_shaixuan.add(new ShaiXuanBean("2小时发货", "filter_erxiashi"));
        list_jiage = new ArrayList<>();
        list_jiage.add("价格从高到低");
        list_jiage.add("价格从低到高");


        final LayoutInflater mInflater = LayoutInflater.from(mActivity);
        layoutTagFlow.setAdapter(shaixuanAdapter = new TagAdapter<ShaiXuanBean>(list_shaixuan) {
            @Override
            public View getView(FlowLayout parent, int position, ShaiXuanBean s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.btn_btn,
                        layoutTagFlow, false);
                tv.setText(s.getRes());
                return tv;
            }
        });
        layoutTagJiage.setAdapter(jiaGeAdapter = new TagAdapter<String>(list_jiage) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.btn_btn,
                        layoutTagFlow, false);
                tv.setText(s);
                return tv;
            }
        });
    }

    @Subscribe
    public void sendCode(SlideBus slideBus) {
        switch (slideBus.getRes()) {
            case 0:
                //排序事件
                onRefresh();
                break;
            case 1:
                //筛选事件
                layoutDrawerLayout.openDrawer(layoutRight);
                break;
        }
    }

    @OnClick({R.id.layout_back, R.id.layout_other, R.id.btn_chongzhi, R.id.btn_queding})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.layout_other:
                if (type == 0) {
                    type = 1;
                } else {
                    type = 0;
                }
                shangPinPresenter.setShangping(type);
                break;
            case R.id.btn_chongzhi:
                shaixuanAdapter.notifyDataChanged();
                jiaGeAdapter.notifyDataChanged();
                break;
            case R.id.btn_queding:
                layoutDrawerLayout.closeDrawer(layoutRight);
                onRefresh();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    PaiXuFragment getPaixuFragment() {
        if (paiXuFragment == null) {
            paiXuFragment = new PaiXuFragment();
        }
        return paiXuFragment;
    }

    @Override
    public void onRefresh() {
        shangPinPresenter.Shuaxin();
    }

    private RequestCall getBuild() {
        GetBuilder getBuilder = shangPinPresenter.getBuild();
        getBuilder
                .addParams("key", Value)
                .addParams(paiXuFragment.paixuName, paiXuFragment.paixu);
        for (Integer integer : layoutTagFlow.getSelectedList()) {
            getBuilder.addParams(list_shaixuan.get(integer).getName(), "1");
        }
        for (Integer integer : layoutTagJiage.getSelectedList()) {
            if (integer == 0) {
                getBuilder.addParams(Constants.jiaGe, Constants.daoXu);
            } else {
                getBuilder.addParams(Constants.jiaGe, Constants.zhenXu);
            }
        }
        return getBuilder
                .build();
    }

    @Override
    protected void OnBack() {
        if (layoutDrawerLayout.isDrawerOpen(layoutRight)) {
            layoutDrawerLayout.closeDrawer(layoutRight);
        } else {
            finish();
        }
    }
}
