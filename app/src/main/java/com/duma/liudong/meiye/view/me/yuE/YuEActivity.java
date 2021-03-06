package com.duma.liudong.meiye.view.me.yuE;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.duma.liudong.meiye.model.UserMoneyBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.utils.Ts;
import com.google.gson.Gson;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.liudong.meiye.R.id.tv_tixian;

/**
 * Created by liudong on 17/3/27.
 */

public class YuEActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
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
    @BindView(tv_tixian)
    TextView tvTixian;
    @BindView(R.id.rv_shouZhiMingXi)
    RecyclerView rvShangping;
    @BindView(R.id.swr_loading)
    SwipeRefreshLayout swLoading;

    UserMoneyBean userMoneyBean;
    BaseRvAdapter<UserMoneyBean.DetailBean> adapter;
    RequestCall build;
    @BindView(R.id.layout_kong)
    LinearLayout layoutKong;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_yue);
    }

    @Override
    protected void initData() {
        tvTitle.setText("余额");
        StartUtil.setSw(swLoading, this);
        rvShangping.setLayoutManager(new LinearLayoutManager(mActivity));
        initAdapter();
        build = OkHttpUtils
                .get()
                .tag("base")
                .url(Api.moneyDetail)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .build();
        adapter.QueryHttp(build);
    }

    private void initAdapter() {
        adapter = new BaseRvAdapter<UserMoneyBean.DetailBean>(mActivity, R.layout.rv_yue, rvShangping) {
            @Override
            protected void getView(ViewHolder holder, UserMoneyBean.DetailBean detailBean, int position) {
                holder.setText(R.id.tv_name, detailBean.getDesc());
                holder.setText(R.id.tv_shijian, detailBean.getTime());
                holder.setText(R.id.tv_yue, "余额: " + detailBean.getNow_money());

                TextView view = holder.getView(R.id.tv_qian);
                view.setText(detailBean.getUser_money());
                try {
                    if (Double.parseDouble(detailBean.getUser_money()) < 0) {
                        view.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.main_red));
                    } else {
                        view.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.texthei));
                    }
                } catch (Exception e) {
                    Ts.setText("服务器金额异常,请联系管理员!");
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
            protected ArrayList<UserMoneyBean.DetailBean> getTs(String result) {
                userMoneyBean = new Gson().fromJson(result, UserMoneyBean.class);
                tvYue.setText("¥" + userMoneyBean.getUser_money());
                return userMoneyBean.getDetail();
            }

            @Override
            protected void HttpSuccess() {
                tvTixian.setVisibility(View.VISIBLE);
            }

            @Override
            protected void HttpError() {
                tvTixian.setVisibility(View.GONE);
            }
        };
        adapter.setKongView(layoutKong);
    }

    @OnClick({R.id.layout_back, tv_tixian})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case tv_tixian:
                Intent intent = new Intent(mActivity, TiXianActivity.class);
                intent.putExtra("money", userMoneyBean.getUser_money());
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onRefresh() {
        adapter.QueryHttp(build);
    }

}
