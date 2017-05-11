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
import com.duma.liudong.meiye.base.BaseRvAdapter;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.model.FenSiBean;
import com.duma.liudong.meiye.model.GuanZhuBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.StartUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/20.
 */

public class FenSiActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_other)
    ImageView imgOther;
    @BindView(R.id.layout_other)
    LinearLayout layoutOther;
    @BindView(R.id.layout_kong)
    LinearLayout layoutKong;
    @BindView(R.id.rv_shangping)
    RecyclerView rvShangping;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;

    private BaseRvAdapter<FenSiBean> beanBaseRvAdapter;
    private RequestCall build;

    private String type;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_fensi);
    }

    @Override
    protected void initData() {
        StartUtil.setSw(swLoading, this);
        type = getIntent().getStringExtra("type");
        initAdapter();
        if (type.equals("1")) {
            tvTitle.setText("我的粉丝");
            build = OkHttpUtils
                    .get()
                    .url(Api.follow_list)
                    .tag("base")
                    .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                    .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                    .build();
        } else {
            tvTitle.setText("我的关注");
            build = OkHttpUtils
                    .get()
                    .url(Api.my_follow_list)
                    .tag("base")
                    .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                    .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                    .build();
        }


        beanBaseRvAdapter.QueryHttp(build);
    }

    private void initAdapter() {
        rvShangping.setLayoutManager(new LinearLayoutManager(mActivity));
        beanBaseRvAdapter = new BaseRvAdapter<FenSiBean>(mActivity, R.layout.rv_fensi, rvShangping) {
            @Override
            protected void getView(ViewHolder holder, final FenSiBean fenSiBean, int position) {
                holder.setText(R.id.tv_name, fenSiBean.getNickname());
                ImageLoader.withYuan(fenSiBean.getHead_pic(), (ImageView) holder.getView(R.id.img_head_pic));
                final ImageView imageView = holder.getView(R.id.img_guanzhu);
                final TextView textView = holder.getView(R.id.tv_guanzhu);
                holder.setOnClickListener(R.id.layout_guanzhu, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GuanZhuHttp(fenSiBean.getUser_id(), imageView, textView);
                    }
                });
                if (fenSiBean.getIs_follow() == null || fenSiBean.getIs_follow().equals("1")) {
                    imageView.setImageDrawable(MyApplication.getInstance().getResources().getDrawable(R.drawable.im_22));
                    textView.setText("取消关注");
                } else {
                    imageView.setImageDrawable(MyApplication.getInstance().getResources().getDrawable(R.drawable.img_83));
                    textView.setText("关注");
                }

                switch (fenSiBean.getSex()) {
                    case "1":
                        holder.setText(R.id.tv_sex, "男");
                        break;
                    case "2":
                        holder.setText(R.id.tv_sex, "女");
                        break;
                    default:
                        holder.setText(R.id.tv_sex, "保密");
                        break;

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
        beanBaseRvAdapter.setKongView(layoutKong);
        beanBaseRvAdapter.setType(new TypeToken<ArrayList<FenSiBean>>() {
        }.getType());
    }

    private void GuanZhuHttp(String be_user_id, final ImageView imageView, final TextView textView) {
        if (!StartUtil.isLogin()) {
            StartUtil.toLogin(mActivity);
            return;
        }
        OkHttpUtils
                .get()
                .tag("GuanZhuHttp")
                .url(Api.user_follow)
                .addParams("be_user_id", be_user_id)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        GuanZhuBean bean = new Gson().fromJson(result, GuanZhuBean.class);
                        if (bean.getIs_follow() == 1) {
                            imageView.setImageDrawable(MyApplication.getInstance().getResources().getDrawable(R.drawable.im_22));
                            textView.setText("取消关注");
                        } else {
                            imageView.setImageDrawable(MyApplication.getInstance().getResources().getDrawable(R.drawable.img_83));
                            textView.setText("关注");
                        }

                    }
                });
    }

    @OnClick(R.id.layout_back)
    public void onClick() {
        finish();
    }

    @Override
    public void onRefresh() {
        beanBaseRvAdapter.QueryHttp(build);
    }
}
