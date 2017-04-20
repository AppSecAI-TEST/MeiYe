package com.duma.liudong.meiye.view.classift.dianPu;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.BaseXiaLaRvPresenter;
import com.duma.liudong.meiye.model.DianPuListBean;
import com.duma.liudong.meiye.utils.Api;
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
 * Created by liudong on 17/4/19.
 */

public class DianPuListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
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

    private String keyword = "";
    private BaseXiaLaRvPresenter<DianPuListBean> beanBaseXiaLaRvPresenter;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_dianpu_list);
    }

    @Override
    protected void initData() {
        keyword = getIntent().getStringExtra("keyword");
        if (keyword.equals("")) {
            tvTitle.setText("全部店铺");
        } else {
            tvTitle.setText(keyword);
        }
        StartUtil.setSw(swLoading, this);
        initAdapter();
    }

    private void initAdapter() {
        beanBaseXiaLaRvPresenter = new BaseXiaLaRvPresenter<DianPuListBean>(mActivity, R.layout.rv_dianpu_list, rvShangping) {
            @Override
            protected void hide_loading() {
                swLoading.setRefreshing(false);
            }

            @Override
            protected void show_loading() {
                swLoading.setRefreshing(true);
            }

            @Override
            protected void loadMore() {
                beanBaseXiaLaRvPresenter.QueryHttp(getBuild());
            }

            @Override
            protected RecyclerView.LayoutManager initLayoutManager() {
                return new GridLayoutManager(mActivity, 2);
            }

            @Override
            protected void getView(ViewHolder holder, DianPuListBean dianPuListBean, int position) {
                holder.setText(R.id.tv_store_name, dianPuListBean.getStore_name());
                ImageLoader.with(dianPuListBean.getStore_logo(), (ImageView) holder.getView(R.id.img_store_logo));
                ImageLoader.with(dianPuListBean.getStore_avatar(), (ImageView) holder.getView(R.id.img_head_pic));
            }

            @Override
            protected void onitemClick(View view, RecyclerView.ViewHolder holder, int position) {
                StartUtil.toDianPu(mActivity, mlist.get(position).getStore_id());
            }
        };
        beanBaseXiaLaRvPresenter.setKongView(layoutKong);
        beanBaseXiaLaRvPresenter.setType(new TypeToken<ArrayList<DianPuListBean>>() {
        }.getType());
    }

    private RequestCall getBuild() {
        beanBaseXiaLaRvPresenter.p++;
        return OkHttpUtils
                .get()
                .tag("base")
                .url(Api.store_search)
                .addParams("keyword", keyword)
                .addParams("p", beanBaseXiaLaRvPresenter.p + "")
                .build();
    }

    @OnClick(R.id.layout_back)
    public void onClick() {
        finish();
    }

    @Override
    public void onRefresh() {
        beanBaseXiaLaRvPresenter.Shuaxin();
    }
}
