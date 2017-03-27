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
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.model.WoDeKeHuBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.StartUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/3/27.
 */

public class WoDekeHuActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.layout_kong)
    LinearLayout layoutKong;
    @BindView(R.id.rv_shangping)
    RecyclerView rvShangping;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;

    private CommonAdapter<WoDeKeHuBean> commonAdapter;
    private List<WoDeKeHuBean> mList;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_wodekehu);
    }

    @Override
    protected void initData() {
        tvTitle.setText("我的客户");
        StartUtil.setSw(swLoading, this);
        swLoading.setRefreshing(true);
        rvShangping.setLayoutManager(new LinearLayoutManager(mActivity));
        mList = new ArrayList<>();
        commonAdapter = new CommonAdapter<WoDeKeHuBean>(mActivity, R.layout.rv_wodekehu, mList) {
            @Override
            protected void convert(ViewHolder holder, WoDeKeHuBean woDeKeHuBean, int position) {
                holder.setText(R.id.tv_mobile, woDeKeHuBean.getMobile());
                holder.setText(R.id.tv_nickname, woDeKeHuBean.getNickname());

                ImageView i = holder.getView(R.id.img_head_pic);
                if (woDeKeHuBean.getHead_pic() == null) {
                    ImageLoader.withYuan(R.drawable.touxiang, i);
                } else {
                    ImageLoader.withYuan(Api.url + woDeKeHuBean.getHead_pic(), i);
                }
            }
        };
        rvShangping.setAdapter(commonAdapter);
        queryHttp();
    }

    private void queryHttp() {
        layoutKong.setVisibility(View.GONE);
        OkHttpUtils.getInstance().cancelTag(this);
        OkHttpUtils
                .post()
                .tag(this)
                .url(Api.myClient)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        swLoading.setRefreshing(false);
                        Type type = new TypeToken<ArrayList<WoDeKeHuBean>>() {
                        }.getType();
                        ArrayList<WoDeKeHuBean> list = new Gson().fromJson(result, type);
                        if (list == null) {
                            onError("");
                            return;
                        }
                        mList.clear();
                        mList.addAll(list);
                        commonAdapter.notifyDataSetChanged();
                    }

                    @Override
                    protected void onError(String result) {
                        super.onError(result);
                        swLoading.setRefreshing(false);
                        layoutKong.setVisibility(View.VISIBLE);
                    }
                });
    }

    @OnClick(R.id.layout_back)
    public void onClick() {
        finish();
    }

    @Override
    public void onRefresh() {
        queryHttp();
    }
}
