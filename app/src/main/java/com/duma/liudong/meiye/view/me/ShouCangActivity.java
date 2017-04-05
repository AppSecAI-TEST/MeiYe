package com.duma.liudong.meiye.view.me;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.Lg;
import com.duma.liudong.meiye.utils.StartUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/5.
 */

public class ShouCangActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
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


    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_shoucang);
    }

    @Override
    protected void initData() {
        tvTitle.setText("收藏");
        StartUtil.setSw(swLoading, this);
        initAdapter();
        RequestCall build = getBuild();
        build.execute(new MyStringCallback() {
            @Override
            public void onMySuccess(String result) {
                Lg.e(result);
            }
        });
    }

    private RequestCall getBuild() {
        return OkHttpUtils
                .get()
                .tag("base")
                .url(Api.collectList)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .build();
    }

    private void initAdapter() {

    }

    @OnClick(R.id.layout_back)
    public void onClick() {
        finish();
    }

    @Override
    public void onRefresh() {

    }
}
