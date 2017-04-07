package com.duma.liudong.meiye.view.classift.dianPu;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.BaseRvAdapter;
import com.duma.liudong.meiye.model.DianPuClassiftBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.StartUtil;
import com.google.gson.reflect.TypeToken;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/7.
 */

public class DianPuClassiftActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
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
    @BindView(R.id.layout_onClick)
    LinearLayout layoutOnClick;


    private BaseRvAdapter<DianPuClassiftBean> adapter;
    private String store_id;
    private RequestCall build;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_dianpuclassift);
    }

    @Override
    protected void initData() {
        store_id = getIntent().getStringExtra("id");
        StartUtil.setSw(swLoading, this);
        tvTitle.setText("店铺分类");
        initAdapter();
        build = getBuild();
        onRefresh();
    }


    private void initAdapter() {
        rvShangping.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new BaseRvAdapter<DianPuClassiftBean>(mActivity, R.layout.rv_dianpuclassift, rvShangping) {
            @Override
            protected void getView(ViewHolder holder, final DianPuClassiftBean dianPuClassiftBean, int position) {
                holder.setText(R.id.tv_cat_name, dianPuClassiftBean.getCat_name());
                RecyclerView recyclerView = holder.getView(R.id.rv_fenlei);
                recyclerView.setLayoutManager(new GridLayoutManager(mActivity, 2));
                recyclerView.setFocusable(false);
                recyclerView.setNestedScrollingEnabled(false);
                CommonAdapter<DianPuClassiftBean.ChildrenBean> adapter;
                adapter = new CommonAdapter<DianPuClassiftBean.ChildrenBean>(mActivity, R.layout.rv_dianpu_classirt_fenlei, dianPuClassiftBean.getChildren()) {
                    @Override
                    protected void convert(ViewHolder holder, final DianPuClassiftBean.ChildrenBean childrenBean, int position) {
                        holder.setText(R.id.tv_title, childrenBean.getCat_name());
                        holder.setOnClickListener(R.id.layout_onClick, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //跳转到商品列表页 带的是 cat_id
                                StartUtil.toShangPingLieBiao(mActivity, Constants.cat_id, childrenBean.getCat_id(), childrenBean.getCat_name());
                            }
                        });
                    }
                };
                recyclerView.setAdapter(adapter);
                holder.setOnClickListener(R.id.layout_onClick, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //跳转到商品列表页 带的是 cat_id
                        StartUtil.toShangPingLieBiao(mActivity, Constants.cat_id, dianPuClassiftBean.getCat_id(), dianPuClassiftBean.getCat_name());
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
        };
        adapter.setKongView(layoutKong);
        adapter.setType(new TypeToken<ArrayList<DianPuClassiftBean>>() {
        }.getType());
    }

    @Override
    public void onRefresh() {
        adapter.QueryHttp(build);
    }


    private RequestCall getBuild() {
        return OkHttpUtils
                .get()
                .tag("base")
                .url(Api.store_class)
                .addParams("store_id", store_id)
                .build();
    }

    @OnClick({R.id.layout_back, R.id.layout_onClick})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.layout_onClick:
                StartUtil.toShangPingLieBiao(mActivity, Constants.store_id, store_id, "全部商品");
                break;
        }
    }
}
