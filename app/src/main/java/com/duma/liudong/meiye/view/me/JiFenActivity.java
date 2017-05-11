package com.duma.liudong.meiye.view.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.duma.liudong.meiye.model.JifenBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.StartUtil;
import com.google.gson.Gson;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/3/27.
 */

public class JiFenActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_other)
    ImageView imgOther;
    @BindView(R.id.layout_other)
    LinearLayout layoutOther;
    @BindView(R.id.tv_yue)
    TextView tvYue;
    @BindView(R.id.rv_shouZhiMingXi)
    RecyclerView rvShangping;
    @BindView(R.id.swr_loading)
    SwipeRefreshLayout swLoading;
    @BindView(R.id.tv_youhui)
    TextView tvYouhui;

    JifenBean jifenBean;
    BaseRvAdapter<JifenBean.DetailBean> adapter;
    RequestCall build;


    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_jifen);
    }

    @OnClick(R.id.layout_back)
    public void onClick() {
        finish();
    }

    @Override
    protected void initData() {
        tvTitle.setText("积分");
        StartUtil.setSw(swLoading, this);
        rvShangping.setLayoutManager(new LinearLayoutManager(mActivity));
        initAdapter();
        build = OkHttpUtils
                .get()
                .tag("base")
                .url(Api.pointsDetail)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .build();
        adapter.QueryHttp(build);
    }

    private void initAdapter() {
        adapter = new BaseRvAdapter<JifenBean.DetailBean>(mActivity, R.layout.rv_yue, rvShangping) {

            @Override
            protected void getView(ViewHolder holder, JifenBean.DetailBean detailBean, int position) {
                holder.setText(R.id.tv_name, detailBean.getDesc());
                holder.setText(R.id.tv_shijian, detailBean.getTime());
                holder.setText(R.id.tv_yue, "积分: " + detailBean.getNow_points());

                TextView view = holder.getView(R.id.tv_qian);
                view.setText(detailBean.getPoints());
                if (Double.parseDouble(detailBean.getPoints()) < 0) {
                    view.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.main_red));
                } else {
                    view.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.texthei));
                }
            }

            @Override
            protected void hide_loading() {
                swLoading.setRefreshing(false);
            }

            @Override
            protected void show_loading() {
                swLoading.setRefreshing(true);
            }

            @Nullable
            @Override
            protected List<JifenBean.DetailBean> getTs(String result) {
                jifenBean = new Gson().fromJson(result, JifenBean.class);
                tvYue.setText(jifenBean.getReward_points() + "积分");
                tvYouhui.setText("已享受" + jifenBean.getPay_points() + "积分抵扣优惠");
                return jifenBean.getDetail();
            }
        };
    }

    @Override
    public void onRefresh() {
        adapter.QueryHttp(build);
    }

}
