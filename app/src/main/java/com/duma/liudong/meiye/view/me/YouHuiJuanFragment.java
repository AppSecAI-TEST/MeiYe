package com.duma.liudong.meiye.view.me;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseFragment;
import com.duma.liudong.meiye.base.BaseRvAdapter;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.model.YouHuiJuanBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.view.shoppingCart.YouHuiListActivity;
import com.google.gson.reflect.TypeToken;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by 79953 on 2016/11/2.
 */

public class YouHuiJuanFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.layout_kong)
    LinearLayout layoutKong;
    @BindView(R.id.rv_youhuijuan)
    RecyclerView rvYouhuijuan;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;

    private BaseRvAdapter<YouHuiJuanBean> adapter;
    private YouHuiJuanActivity activity;
    private RequestCall build;
    private YouHuiListActivity youHuiListActivity;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_youhuijuan;
    }

    @Override
    protected void initData() {
        StartUtil.setSw(swLoading, this);
        rvYouhuijuan.setLayoutManager(new LinearLayoutManager(mActivity));
        initAdapter();
        try {
            activity = (YouHuiJuanActivity) mActivity;
            if (activity.isOne) {
                adapter.QueryHttp(getBuild());
                activity.isOne = false;
            }
        } catch (Exception e) {
            activity = null;
            adapter.QueryHttp(getBuild());
        }
    }

    private void initAdapter() {
        adapter = new BaseRvAdapter<YouHuiJuanBean>(mActivity, R.layout.rv_youhuijuan, rvYouhuijuan) {
            @Override
            protected void getView(ViewHolder holder, YouHuiJuanBean youHuiJuanBean, int position) {
                holder.setText(R.id.tv_money, youHuiJuanBean.getMoney());
                holder.setText(R.id.tv_name, youHuiJuanBean.getName());
                holder.setText(R.id.tv_condition, youHuiJuanBean.getCondition());
                holder.setText(R.id.tv_time, "使用日期:" + StartUtil.getTime(Long.parseLong(youHuiJuanBean.getUse_start_time()) * 1000) + " - " + StartUtil.getTime(Long.parseLong(youHuiJuanBean.getUse_end_time()) * 1000));
                TextView tv_type = holder.getView(R.id.tv_type);
                if (activity != null) {
                    switch (activity.getType()) {
                        case "0":
                            tv_type.setText("立即使用");
                            tv_type.setTextColor(mActivity.getResources().getColor(R.color.main_red));
                            break;
                        case "1":
                            tv_type.setText("已使用");
                            tv_type.setTextColor(mActivity.getResources().getColor(R.color.text_hui));
                            break;
                        case "2":
                            tv_type.setText("已过期");
                            tv_type.setTextColor(mActivity.getResources().getColor(R.color.text_hui));
                            break;
                    }
                } else {
                    tv_type.setText("立即使用");
                    tv_type.setTextColor(mActivity.getResources().getColor(R.color.main_red));
                }


            }

            @Override
            protected void onitemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if (activity == null) {
                    mActivity.finish();
                    EventBus.getDefault().post(mList.get(position));
                } else {
                    StartUtil.toDianPu(mActivity, mList.get(position).getStore_id());
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
        };


        adapter.setKongView(layoutKong);
        adapter.setType(new TypeToken<ArrayList<YouHuiJuanBean>>() {
        }.getType());
    }

    private RequestCall getBuild() {
        if (activity == null) {
            build = OkHttpUtils
                    .get()
                    .tag("base")
                    .url(Api.coupon)
                    .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                    .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                    .addParams("type", "0")
                    .addParams("store_id", mActivity.getIntent().getStringExtra("id"))
                    .addParams("money", mActivity.getIntent().getStringExtra("money"))
                    .build();
        } else {
            build = OkHttpUtils
                    .get()
                    .tag("base")
                    .url(Api.coupon)
                    .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                    .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                    .addParams("type", activity.getType())
                    .build();
        }

        return build;
    }

    @Override
    protected void onLazyLoad() {
        super.onLazyLoad();
        adapter.QueryHttp(getBuild());
    }


    @Override
    public void onRefresh() {
        adapter.QueryHttp(build);
    }

}
