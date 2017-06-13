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
import com.duma.liudong.meiye.utils.Ts;
import com.google.gson.Gson;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;

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
    @BindView(R.id.img_head_pic)
    ImageView imgHeadPic;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.layout_shangji)
    LinearLayout layoutShangji;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_huiyuan)
    TextView tvHuiyuan;

    private CommonAdapter<WoDeKeHuBean.XiajiBean> commonAdapter;
    private List<WoDeKeHuBean.XiajiBean> mList;
    private WoDeKeHuBean bean;

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
        commonAdapter = new CommonAdapter<WoDeKeHuBean.XiajiBean>(mActivity, R.layout.rv_wodekehu, mList) {
            @Override
            protected void convert(ViewHolder holder, WoDeKeHuBean.XiajiBean woDeKeHuBean, int position) {
                holder.setText(R.id.tv_mobile, woDeKeHuBean.getMobile());
                holder.setText(R.id.tv_nickname, woDeKeHuBean.getNickname());
                holder.setText(R.id.tv_time, woDeKeHuBean.getReg_time());
                holder.setText(R.id.tv_huiyuan, woDeKeHuBean.getLevel());

                ImageView i = holder.getView(R.id.img_head_pic);
                ImageLoader.withYuan(woDeKeHuBean.getHead_pic(), i);
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
                        bean = new Gson().fromJson(result, WoDeKeHuBean.class);
                        if (bean.getXiaji() == null) {
                            onError("");
                            return;
                        }
                        mList.clear();
                        mList.addAll(bean.getXiaji());
                        commonAdapter.notifyDataSetChanged();
                        initRes();
                    }

                    @Override
                    protected void onError(String result) {
                        super.onError(result);
                        swLoading.setRefreshing(false);
                        layoutKong.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void initRes() {
        try {
            if (bean.getShangji().size() != 0) {
                layoutShangji.setVisibility(View.VISIBLE);
                tvMobile.setText(bean.getShangji().get(0).getMobile());
                tvNickname.setText(bean.getShangji().get(0).getNickname());
                ImageLoader.withYuan(bean.getShangji().get(0).getHead_pic(), imgHeadPic);
                tvHuiyuan.setText(bean.getShangji().get(0).getLevel());
                tvTime.setText(bean.getShangji().get(0).getReg_time());
            } else {
                layoutShangji.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            Ts.setText("服务器异常!");
        }
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
