package com.duma.liudong.meiye.view.classift;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseBannaer;
import com.duma.liudong.meiye.base.BaseFragment;
import com.duma.liudong.meiye.base.BaseRvAdapter;
import com.duma.liudong.meiye.model.DianPuMainBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.ShangPinAdapter;
import com.duma.liudong.meiye.utils.StartUtil;
import com.google.gson.reflect.TypeToken;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liudong on 17/4/5.
 */

public class DianPuShouYeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.banner_shangping)
    ConvenientBanner bannerShangping;
    @BindView(R.id.layout_kong)
    LinearLayout layoutKong;
    @BindView(R.id.rv_shangping)
    RecyclerView rvShangping;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;

    private BaseRvAdapter<DianPuMainBean> adapter;

    private DianPuActivity dianPuActivity;
    RequestCall build;

    private boolean isSuccess = false;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_dianpushouye;
    }

    @Override
    protected void initData() {
        dianPuActivity = (DianPuActivity) mActivity;
        initBuild();
        StartUtil.setSw(swLoading, this);
        rvShangping.setLayoutManager(new LinearLayoutManager(mActivity));
        rvShangping.setFocusable(false);
        rvShangping.setNestedScrollingEnabled(false);
        initAdapter();
        adapter.setType(new TypeToken<ArrayList<DianPuMainBean>>() {
        }.getType());
        adapter.setKongView(layoutKong);
        onRefresh();

    }

    public void setBannerShangping() {
        new BaseBannaer().setBanner(bannerShangping, dianPuActivity.dianPubean.getStore_slide_url());
        bannerShangping.startTurning(BaseBannaer.time);
    }


    private void initAdapter() {
        adapter = new BaseRvAdapter<DianPuMainBean>(mActivity, R.layout.rv_dianpushouye, rvShangping) {
            @Override
            protected void getView(ViewHolder holder, DianPuMainBean dianPuMainBean, int position) {
                holder.setText(R.id.tv_title, dianPuMainBean.getCat_name());
                RecyclerView recyclerView = holder.getView(R.id.rv_shouye);
                new ShangPinAdapter(mActivity, recyclerView, dianPuMainBean.getList());
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
            protected void HttpSuccess() {
                isSuccess = true;
            }

            @Override
            protected void HttpError() {
                isSuccess = false;
            }
        };
    }

    @Override
    protected void onLazyLoad() {
        if (!isSuccess) {
            adapter.QueryHttp(build);
        }
    }

    private void initBuild() {
        build = OkHttpUtils
                .get()
                .tag(this)
                .url(Api.Storegoods)
                .addParams("store_id", dianPuActivity.store_id)
                .build();
    }

    @Override
    public void onRefresh() {
        adapter.QueryHttp(build);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
