package com.duma.liudong.meiye.view.home;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseFragment;
import com.duma.liudong.meiye.base.BaseRvAdapter;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.model.LinJuanBean;
import com.duma.liudong.meiye.presenter.PublicPresenter;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.DialogUtil;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.StartUtil;
import com.google.gson.reflect.TypeToken;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by liudong on 17/3/29.
 */

public class LinJuanFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.layout_kong)
    LinearLayout layoutKong;
    @BindView(R.id.rv_shangping)
    RecyclerView rvShangping;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;

    private LinJuanActivity activity;
    private BaseRvAdapter<LinJuanBean> adapter;
    private RequestCall build;
    private PublicPresenter publicPresenter;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_linjuan;
    }

    @Override
    protected void initData() {
        publicPresenter = new PublicPresenter();
        activity = (LinJuanActivity) mActivity;
        StartUtil.setSw(swLoading, this);
        initAdapater();
        if (activity.isOne) {
            refresh();
            activity.isOne = false;
        }
    }

    private void initAdapater() {
        rvShangping.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new BaseRvAdapter<LinJuanBean>(mActivity, R.layout.rv_juan, rvShangping) {
            @Override
            protected void getView(ViewHolder holder, final LinJuanBean linJuanBean, int position) {
                ImageView img_head_img = holder.getView(R.id.img_head_img);
                ImageLoader.with(Api.url + linJuanBean.getHead_img(), img_head_img);
                holder.setText(R.id.tv_name, linJuanBean.getName());
                holder.setText(R.id.tv_money, linJuanBean.getMoney());
                holder.setText(R.id.tv_condition, "满" + linJuanBean.getCondition() + "可用");
                ProgressBar progressBar = holder.getView(R.id.progressBar);
                Double max = Double.parseDouble(linJuanBean.getCreatenum());
                Double progress = Double.parseDouble(linJuanBean.getSend_num());

                progressBar.setMax(Integer.parseInt(linJuanBean.getCreatenum()));
                progressBar.setProgress(Integer.parseInt(linJuanBean.getSend_num()));

                holder.setText(R.id.tv_progressbar, "已抢" + StartUtil.setNum((progress / max) * 100) + "%");

                TextView tv_lingqu = holder.getView(R.id.tv_lingqu);
                tv_lingqu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!StartUtil.isLogin()) {
                            StartUtil.toLogin(mActivity);
                            return;
                        }
                        DialogUtil.show(mActivity);
                        publicPresenter.getYouHuiJuan(linJuanBean.getId());
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
        adapter.setType(new TypeToken<ArrayList<LinJuanBean>>() {
        }.getType());
        adapter.setKongView(layoutKong);
    }

    private RequestCall getBuild() {
        return OkHttpUtils
                .get()
                .tag("base")
                .url(Api.couponindex)
                .addParams("cat_id", activity.getId() + "")
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .build();
    }

    @Override
    protected void onLazyLoad() {
        refresh();
    }

    private void refresh() {
        adapter.QueryHttp(getBuild());
    }

    @Override
    public void onRefresh() {
        refresh();
    }
}
