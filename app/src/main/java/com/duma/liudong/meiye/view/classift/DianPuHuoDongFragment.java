package com.duma.liudong.meiye.view.classift;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseFragment;
import com.duma.liudong.meiye.presenter.ShangPinLieBiaoPresenter;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.StartUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.request.RequestCall;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/6.
 */

public class DianPuHuoDongFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.btn_neibu)
    RadioButton btnNeibu;
    @BindView(R.id.btn_waibu)
    RadioButton btnWaibu;
    @BindView(R.id.layout_kong)
    LinearLayout layoutKong;
    @BindView(R.id.id_stickynavlayout_innerscrollview)
    RecyclerView rvShangping;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;

    private ShangPinLieBiaoPresenter shangPinPresenter;
    private DianPuActivity activity;

    private boolean isOne = false;
    private String type = "nei";

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_dianpuhuodong;
    }

    @Override
    protected void initData() {
        activity = (DianPuActivity) mActivity;
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
                if (isOne)
                    shangPinPresenter.QueryHttp(getBuild());
            }
        });
    }

    @Override
    protected void onLazyLoad() {
        isOne = true;
        if (!shangPinPresenter.isOne) {
            shangPinPresenter.QueryHttp(getBuild());
        }
    }

    @OnClick({R.id.btn_neibu, R.id.btn_waibu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_neibu:
                type = "nei";
                break;
            case R.id.btn_waibu:
                type = "wai";
                break;
        }
        onRefresh();
    }

    @Override
    public void onRefresh() {
        shangPinPresenter.Shuaxin();
    }

    private RequestCall getBuild() {
        shangPinPresenter.p++;
        GetBuilder getBuilder = OkHttpUtils
                .get()
                .tag("base")
                .url(Api.dynamic)
                .addParams("p", shangPinPresenter.p + "")
                .addParams("store_id", activity.store_id)
                .addParams("type", type);
        return getBuilder
                .build();
    }
}
