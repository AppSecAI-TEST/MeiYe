package com.duma.liudong.meiye.view.home;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseFragment;
import com.duma.liudong.meiye.base.BaseXiaLaRvPresenter;
import com.duma.liudong.meiye.model.ToutiaoBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.StartUtil;
import com.google.gson.reflect.TypeToken;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by liudong on 17/4/5.
 */

public class TouTiaoFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.layout_kong)
    LinearLayout layoutKong;
    @BindView(R.id.rv_shangping)
    RecyclerView rvShangping;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;

    private BaseXiaLaRvPresenter<ToutiaoBean> beanBaseXiaLaRvPresenter;

    private boolean isSuccess = false;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_toutiao;
    }

    @Override
    protected void initData() {
        StartUtil.setSw(swLoading, this);
        initAdapter();
    }

    @Override
    protected void onLazyLoad() {
        if (!isSuccess)
            beanBaseXiaLaRvPresenter.Shuaxin();
    }

    private void initAdapter() {
        beanBaseXiaLaRvPresenter = new BaseXiaLaRvPresenter<ToutiaoBean>(mActivity, R.layout.rv_toutiao, rvShangping) {

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
            protected void getView(ViewHolder holder, ToutiaoBean toutiaoBean, int position) {
                ImageView imageView = holder.getView(R.id.img_thumb);
                holder.setText(R.id.tv_title, toutiaoBean.getTitle());
                holder.setText(R.id.img_cat_name, toutiaoBean.getCat_name());
                holder.setText(R.id.tv_author, "发帖人:" + toutiaoBean.getAuthor());
                holder.setText(R.id.tv_publish_time, toutiaoBean.getPublish_time());
                holder.setText(R.id.tv_click, "人气:" + toutiaoBean.getClick());
                ImageLoader.with(Api.url + toutiaoBean.getThumb(), imageView);
            }

            @Override
            protected void onitemClick(View view, RecyclerView.ViewHolder holder, int position) {
                StartUtil.toH5Web(mActivity, Api.TouTiaoH5Url + mlist.get(position).getArticle_id(), mlist.get(position).getTitle());
            }

            @Override
            protected void onSuccess() {
                isSuccess = true;
            }
        };
        beanBaseXiaLaRvPresenter.setKongView(layoutKong);
        beanBaseXiaLaRvPresenter.setType(new TypeToken<ArrayList<ToutiaoBean>>() {
        }.getType());
    }

    @Override
    public void onRefresh() {
        beanBaseXiaLaRvPresenter.Shuaxin();
    }

    private RequestCall getBuild() {
        beanBaseXiaLaRvPresenter.p++;
        return OkHttpUtils
                .get()
                .tag("base")
                .url(Api.Toutiaoindex)
                .addParams("p", beanBaseXiaLaRvPresenter.p + "")
                .build();
    }


}
