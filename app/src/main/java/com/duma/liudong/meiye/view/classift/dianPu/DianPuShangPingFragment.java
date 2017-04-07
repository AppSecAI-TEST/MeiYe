package com.duma.liudong.meiye.view.classift.dianPu;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseFragment;
import com.duma.liudong.meiye.model.SlideBus;
import com.duma.liudong.meiye.presenter.ShangPinLieBiaoPresenter;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.StartUtil;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.request.RequestCall;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/6.
 */

public class DianPuShangPingFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.btn_zonghe)
    RadioButton btnZonghe;
    @BindView(R.id.btn_xinpin)
    RadioButton btnXinpin;
    @BindView(R.id.btn_xiaoliang)
    RadioButton btnXiaoliang;
    @BindView(R.id.btn_jiage)
    RadioButton btnJiage;
    @BindView(R.id.layout_kong)
    LinearLayout layoutKong;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;
    @BindView(R.id.id_stickynavlayout_innerscrollview)
    RecyclerView rvShangping;

    private ShangPinLieBiaoPresenter shangPinPresenter;
    private DianPuActivity activity;
    private String paixuName, paixu;

    private boolean isOne = false;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_dianpushangping;
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        paixuName = Constants.zonghe;
        paixu = Constants.zhenXu;
        activity = (DianPuActivity) mActivity;
        StartUtil.setSw(swLoading, this);
        swLoading.setEnabled(false);
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

    @Subscribe
    public void typeXiala(SlideBus slideBus) {
        if (slideBus.getRes() == 0) {
            swLoading.setEnabled(true);
        } else {
            swLoading.setEnabled(false);
        }
    }

    @OnClick({R.id.btn_zonghe, R.id.btn_xinpin, R.id.btn_xiaoliang, R.id.btn_jiage})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_zonghe:
                paixuName = Constants.zonghe;
                paixu = Constants.zhenXu;
                break;
            case R.id.btn_xinpin:
                paixuName = Constants.xinpin;
                paixu = Constants.zhenXu;
                break;
            case R.id.btn_xiaoliang:
                paixuName = Constants.xiaoLiang;
                paixu = Constants.daoXu;
                break;
            case R.id.btn_jiage:
                paixuName = Constants.jiaGe;
                paixu = Constants.zhenXu;
                break;
        }
        onRefresh();
    }

    @Override
    protected void onLazyLoad() {
        isOne = true;
        if (!shangPinPresenter.isOne) {
            shangPinPresenter.QueryHttp(getBuild());
        }
    }

    @Override
    public void onRefresh() {
        shangPinPresenter.Shuaxin();
    }

    private RequestCall getBuild() {
        GetBuilder getBuilder = shangPinPresenter.getBuild();
        getBuilder
                .addParams("store_id", activity.store_id)
                .addParams(paixuName, paixu);
        return getBuilder
                .build();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
