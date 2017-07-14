package com.duma.liudong.meiye.view.shoppingCart;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.model.FuWuBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.StartUtil;
import com.google.gson.Gson;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 79953 on 2016/10/31.
 */

public class FuKuanChenGongActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView layoutName;
    @BindView(R.id.tv_shouye)
    TextView tvShouye;
    @BindView(R.id.tv_chakanDinDan)
    TextView tvChakanDinDan;
    @BindView(R.id.tv_store_name)
    TextView tvStoreName;
    @BindView(R.id.rv_fuwu)
    RecyclerView rvFuwu;
    @BindView(R.id.layout_fuwu)
    LinearLayout layoutFuwu;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;

    private String type, order_id;
    private FuWuBean bean;
    private List<FuWuBean.SupportBean> mJuanMaList;
    private CommonAdapter<FuWuBean.SupportBean> JuanMaAdapter;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_fukuanchenggong);
    }

    @Override
    protected void initData() {
        layoutName.setText("付款成功");
        type = getIntent().getStringExtra("type");
        order_id = getIntent().getStringExtra("order_id");

        mJuanMaList = new ArrayList<>();
        StartUtil.setSw(swLoading, this);
        initAdapter();
        layoutFuwu.setVisibility(View.GONE);
        onRefresh();
    }

    private void initAdapter() {
        rvFuwu.setLayoutManager(new LinearLayoutManager(mActivity));
        rvFuwu.setFocusable(false);
        rvFuwu.setNestedScrollingEnabled(false);
        JuanMaAdapter = new CommonAdapter<FuWuBean.SupportBean>(mActivity, R.layout.rv_juanma, mJuanMaList) {
            @Override
            protected void convert(ViewHolder holder, FuWuBean.SupportBean supportBean, int position) {
                TextView tv_name, tv_card_num;
                tv_name = holder.getView(R.id.tv_name);
                tv_card_num = holder.getView(R.id.tv_card_num);
                tv_card_num.setText(supportBean.getCard_num());
                if (supportBean.getIs_use().equals("0")) {
                    tv_name.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.main_red));
                    tv_card_num.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.main_red));
                } else {
                    tv_name.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.texthei));
                    tv_card_num.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.texthei));
                }
            }
        };
        rvFuwu.setAdapter(JuanMaAdapter);
    }

    @Override
    protected void OnBack() {
//        StartUtil.toQuanBuDinDan(mActivity, type, "", "", "1");
    }

    @OnClick({R.id.layout_back, R.id.tv_shouye, R.id.tv_chakanDinDan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                StartUtil.toQuanBuDinDan(mActivity, type, "", "", "1");
                break;
            case R.id.tv_shouye:
                StartUtil.toMain(mActivity);
                break;
            case R.id.tv_chakanDinDan:
                StartUtil.toQuanBuDinDan(mActivity, type, "", "", "1");
                break;
        }
    }

    @Override
    public void onRefresh() {
        if (type.equals("3")) {
            //服务
            swLoading.setRefreshing(true);
            GetResHttp();
        } else {
            swLoading.setRefreshing(false);
        }
    }

    private void GetResHttp() {
        OkHttpUtils
                .get()
                .url(Api.orderInfo)
                .tag(this)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .addParams("order_id", order_id)
                .addParams("order_type", "1")
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        swLoading.setRefreshing(false);
                        bean = new Gson().fromJson(result, FuWuBean.class);
                        initRes();
                    }

                    @Override
                    protected void onError(String result) {
                        super.onError(result);
                        swLoading.setRefreshing(false);
                    }
                });
    }

    private void initRes() {
        layoutFuwu.setVisibility(View.VISIBLE);
        tvStoreName.setText(bean.getStore_name());
        mJuanMaList.clear();
        mJuanMaList.addAll(bean.getSupport());
        JuanMaAdapter.notifyDataSetChanged();
    }
}
