package com.duma.liudong.meiye.view.home;

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
import com.duma.liudong.meiye.base.MessageBean;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.utils.Ts;
import com.google.gson.Gson;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/3/27.
 */

public class MessageActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
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

    private List<MessageBean> mList;
    private BaseRvAdapter<MessageBean> adapter;
    private RequestCall build;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_message);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.QueryHttp(build);
    }

    @Override
    protected void initData() {
        tvTitle.setText("消息");
        StartUtil.setSw(swLoading, this);
        rvShangping.setLayoutManager(new LinearLayoutManager(mActivity));
        build = OkHttpUtils
                .post()
                .tag(this)
                .url(Api.message)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .build();
        adapter = new BaseRvAdapter<MessageBean>(mActivity, R.layout.rv_message, rvShangping) {
            @Nullable
            @Override
            protected List<MessageBean> getTs(String result) {
                List<MessageBean> list = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    MessageBean bean;
                    bean = new Gson().fromJson(jsonObject.getString("sys_msg"), MessageBean.class);
                    if (!bean.getMessage_type().equals("")) {
                        list.add(bean);
                    }
                    bean = new Gson().fromJson(jsonObject.getString("money_msg"), MessageBean.class);
                    if (!bean.getMessage_type().equals("")) {
                        list.add(bean);
                    }
                    bean = new Gson().fromJson(jsonObject.getString("mail_msg"), MessageBean.class);
                    if (!bean.getMessage_type().equals("")) {
                        list.add(bean);
                    }

                } catch (JSONException e) {
                    Ts.JsonErroy();
                }
                return list;
            }

            @Override
            protected void getView(ViewHolder holder, MessageBean bean, int position) {
                ImageView imageView = holder.getView(R.id.img_head_pic);
                switch (bean.getMessage_type()) {
                    case "0":
                        ImageLoader.with(R.drawable.img_24, imageView);
                        holder.setText(R.id.tv_title, "系统消息");
                        break;
                    case "1":
                        ImageLoader.with(R.drawable.img_23, imageView);
                        holder.setText(R.id.tv_title, "我的资产");
                        break;
                    case "2":
                        ImageLoader.with(R.drawable.img_25, imageView);
                        holder.setText(R.id.tv_title, "物流通知");
                        break;
                }
                holder.setText(R.id.tv_content, bean.getMessage_body());
                holder.setText(R.id.tv_time, bean.getMessage_time());

                TextView textView = holder.getView(R.id.tv_number);
                textView.setText(bean.getNo_read());
                if (bean.getNo_read().equals("")) {
                    textView.setVisibility(View.GONE);
                } else {
                    textView.setVisibility(View.VISIBLE);
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

            @Override
            protected void onitemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(MessageActivity.this, MessageContentActivity.class);
                intent.putExtra("type", mList.get(position).getMessage_type());
                switch (mList.get(position).getMessage_type()) {
                    case "0":
                        intent.putExtra("title", "系统消息");
                        break;
                    case "1":
                        intent.putExtra("title", "我的资产");
                        break;
                    case "2":
                        intent.putExtra("title", "物流通知");
                        break;
                }
                startActivity(intent);

            }
        };

        adapter.setKongView(layoutKong);
    }

    @OnClick(R.id.layout_back)
    public void onClick() {
        finish();
    }


    @Override
    public void onRefresh() {
        adapter.QueryHttp(build);
    }
}
