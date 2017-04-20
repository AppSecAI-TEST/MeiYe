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
import com.duma.liudong.meiye.base.BaseXiaLaRvPresenter;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.model.TieziBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.StartUtil;
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

public class GuanZhuActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
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

    private BaseXiaLaRvPresenter<TieziBean> beanBaseXiaLaRvPresenter;
    private String url;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_guanzhu);
    }

    @Override
    protected void initData() {
        StartUtil.setSw(swLoading, this);
        url = getIntent().getStringExtra("url");
        if (url.equals(Api.follow_bbs)) {
            tvTitle.setText("我的关注");
        } else {
            tvTitle.setText("我的贴子");
        }

        initXiaLaAdapter();
    }

    private void initXiaLaAdapter() {
        beanBaseXiaLaRvPresenter = new BaseXiaLaRvPresenter<TieziBean>(mActivity, R.layout.rv_luntan, rvShangping) {
            @Override
            protected void hide_loading() {
                swLoading.setRefreshing(false);
            }

            @Override
            protected void show_loading() {
                swLoading.setRefreshing(true);
            }

            @Override
            protected void loadMore() {
                beanBaseXiaLaRvPresenter.QueryHttp(getBuild());
            }

            @Override
            protected RecyclerView.LayoutManager initLayoutManager() {
                return new LinearLayoutManager(mActivity);
            }

            @Override
            protected void getView(final ViewHolder holder, final TieziBean zhiDingBean, int position) {
                holder.setText(R.id.tv_content, zhiDingBean.getContent());
                holder.setText(R.id.tv_click_count, zhiDingBean.getClick_count());
                holder.setText(R.id.tv_user_name, zhiDingBean.getUser_name());
                holder.setText(R.id.tv_add_time, zhiDingBean.getAdd_time());
                holder.setText(R.id.tv_like_count, zhiDingBean.getLike_count());
                holder.setText(R.id.tv_comment_count, zhiDingBean.getComment_count() + "条评论");
                ImageView img_img_json = holder.getView(R.id.img_img_json);
                ImageView img_touxiang = holder.getView(R.id.img_touxiang);
                ImageLoader.withYuan(zhiDingBean.getHead_pic(), img_touxiang);
                img_img_json.setVisibility(View.GONE);
                if (zhiDingBean.getImg_json() != null) {
                    img_img_json.setVisibility(View.VISIBLE);
                    ImageLoader.with(zhiDingBean.getImg_json().get(0), img_img_json);
                }
                holder.setVisible(R.id.layout_guanzhu, false);
                holder.setVisible(R.id.layout_hide, false);
            }

            @Override
            protected void onitemClick(View view, RecyclerView.ViewHolder holder, int position) {
                StartUtil.toH5Web(mActivity, Api.LunTanH5Url + mlist.get(position).getBbs_id(), mlist.get(position).getTitle());
            }
        };
        beanBaseXiaLaRvPresenter.setType(new TypeToken<ArrayList<TieziBean>>() {
        }.getType());
    }

    private RequestCall getBuild() {
        beanBaseXiaLaRvPresenter.p++;
        return OkHttpUtils
                .get()
                .url(url)
                .tag("base")
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .addParams("p", beanBaseXiaLaRvPresenter.p + "")
                .build();
    }

    @OnClick(R.id.layout_back)
    public void onClick() {
        finish();
    }

    @Override
    public void onRefresh() {
        beanBaseXiaLaRvPresenter.Shuaxin();
    }
}
