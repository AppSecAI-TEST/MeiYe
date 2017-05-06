package com.duma.liudong.meiye.view.me;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.BaseRvAdapter;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.model.ZujiBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.DialogUtil;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.utils.Ts;
import com.duma.liudong.meiye.view.dialog.QueRenUtilDialog;
import com.google.gson.reflect.TypeToken;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/13.
 */

public class WoDeZuJiActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
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

    private BaseRvAdapter<ZujiBean> beanBaseRvAdapter;
    private RequestCall build;
    private QueRenUtilDialog dialog;
    private String id;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_wodezuji);
    }

    @Override
    protected void initData() {
        tvTitle.setText("我的足迹");
        StartUtil.setSw(swLoading, this);
        dialog = new QueRenUtilDialog(mActivity, "", "是否删除此商品", "否", "是");
        dialog.setYesClicklistener(new QueRenUtilDialog.OnYesClickListener() {
            @Override
            public void onYes() {
                shanChuHttp(id);
            }
        });
        initAdapter();
        build = OkHttpUtils
                .get()
                .tag("base")
                .url(Api.footList)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .build();
        beanBaseRvAdapter.QueryHttp(build);
    }

    private void initAdapter() {
        rvShangping.setLayoutManager(new LinearLayoutManager(mActivity));
        beanBaseRvAdapter = new BaseRvAdapter<ZujiBean>(mActivity, R.layout.rv_zuji, rvShangping) {
            @Override
            protected void getView(ViewHolder holder, final ZujiBean zujiBean, int position) {
                holder.setText(R.id.tv_time, zujiBean.getCreated_at())
                        .setText(R.id.tv_goods_name, zujiBean.getGoods_name())
                        .setText(R.id.tv_market_price, zujiBean.getShop_price());
                ImageLoader.with(Api.url + zujiBean.getOriginal_img(), (ImageView) holder.getView(R.id.img_original_img));
                holder.setOnClickListener(R.id.img_shanchu, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        id = zujiBean.getId();
                        dialog.show();
                    }
                });
            }

            @Override
            protected void hide_loading() {
                swLoading.setRefreshing(false);
            }

            @Override
            protected void show_loading() {
                swLoading.setRefreshing(true);
            }

            @Override
            protected void onitemClick(View view, RecyclerView.ViewHolder holder, int position) {
                StartUtil.toShangPingWeb(mActivity, Api.H5Url + mList.get(position).getGoods_id());
            }
        };
        beanBaseRvAdapter.setKongView(layoutKong);
        beanBaseRvAdapter.setType(new TypeToken<ArrayList<ZujiBean>>() {
        }.getType());
    }

    private void shanChuHttp(String id) {
        DialogUtil.show(mActivity);
        OkHttpUtils.getInstance().cancelTag(this);
        OkHttpUtils
                .get()
                .tag(this)
                .url(Api.delFoot)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .addParams("id", id)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        DialogUtil.hide();
                        Ts.setText("删除成功!");
                        onRefresh();
                    }
                });
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
