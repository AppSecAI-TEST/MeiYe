package com.duma.liudong.meiye.view.me;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.BaseRvAdapter;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.model.FenSiBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.StartUtil;
import com.google.gson.reflect.TypeToken;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/20.
 */

public class FenSiActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
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

    private BaseRvAdapter<FenSiBean> beanBaseRvAdapter;
    private RequestCall build;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_fensi);
    }

    @Override
    protected void initData() {
        StartUtil.setSw(swLoading, this);
        tvTitle.setText("我的粉丝");
        rvShangping.setLayoutManager(new LinearLayoutManager(mActivity));
        beanBaseRvAdapter = new BaseRvAdapter<FenSiBean>(mActivity, R.layout.rv_fensi, rvShangping) {
            @Override
            protected void getView(ViewHolder holder, FenSiBean fenSiBean, int position) {
                holder.setText(R.id.tv_name, fenSiBean.getNickname());
                ImageLoader.withYuan(fenSiBean.getHead_pic(), (ImageView) holder.getView(R.id.img_head_pic));
            }

            @Override
            protected void hide_loading() {
                swLoading.setRefreshing(false);
            }

            @Override
            protected void show_loading() {
                swLoading.setRefreshing(true);
            }
        };
        beanBaseRvAdapter.setKongView(layoutKong);
        beanBaseRvAdapter.setType(new TypeToken<ArrayList<FenSiBean>>() {
        }.getType());
        build = OkHttpUtils
                .get()
                .url(Api.follow_list)
                .tag("base")
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .build();

        beanBaseRvAdapter.QueryHttp(build);
    }

    @OnClick(R.id.layout_back)
    public void onClick() {
        finish();
    }

    @Override
    public void onRefresh() {
        beanBaseRvAdapter.QueryHttp(build);
    }
}
