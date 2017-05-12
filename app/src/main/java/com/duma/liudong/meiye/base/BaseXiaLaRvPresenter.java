package com.duma.liudong.meiye.base;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.duma.liudong.meiye.utils.Lg;
import com.google.gson.Gson;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudong on 16/11/15.
 */

public abstract class BaseXiaLaRvPresenter<T> implements LoadMoreWrapper.OnLoadMoreListener {

    public RecyclerView mRecyclerView;
    public Activity mActivity;
    public List<T> mlist;
    public BaseLoadingUtil baseLoadingUtil;//操作下拉刷新的工具
    public LoadMoreWrapper mAdapter;//下拉刷新的适配器
    public int p = 0;//当前页数
    private View layoutView;//空页面的布局
    private Type type;
    /**
     * 是否第一次请求数据
     * 只要请求一次成功的数据 并且数据不为空 就会变成true
     */
    public boolean isOne = false;

    public BaseXiaLaRvPresenter(Activity activity, final int layoutId, RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
        this.mActivity = activity;
        mlist = new ArrayList<>();
        setLayoutManagerByType(layoutId);
    }

    public void setLayoutManagerByType(final int layoutId) {
        mRecyclerView.setLayoutManager(initLayoutManager());
        initAdapterByRv(layoutId);
    }

    public void setLayoutManagerByType(final int layoutId, RecyclerView.LayoutManager layoutManager) {
        mRecyclerView.setLayoutManager(layoutManager);
        initAdapterByRv(layoutId);
    }

    private void initAdapterByRv(int layoutId) {
        baseLoadingUtil = new BaseLoadingUtil(initAdapter(layoutId), mActivity);
        mAdapter = baseLoadingUtil.getLoadAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    public CommonAdapter<T> initAdapter(final int layoutId) {
        CommonAdapter<T> adapter = new CommonAdapter<T>(mActivity, layoutId, mlist) {

            @Override
            protected void convert(ViewHolder holder, T t, int position) {
                getView(holder, t, position);
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                onitemClick(view, holder, position);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                onitemLongClick(view, holder, position);
                return false;
            }
        });
        return adapter;
    }

    @Override
    public void onLoadMoreRequested() {
        onLoadMore();
    }

    public void QueryHttp(RequestCall build) {
        show_loading();
        hide_Kong();
        OkHttpUtils.getInstance().cancelTag("base");
        build.execute(new MyStringCallback() {
            @Override
            public void onMySuccess(String result) {
                hide_loading();
                List<T> list = getTs(result);
                if (list == null) {
                    onError("");
                    return;
                }
                isOne = true;
                //查看是否空数据
                if (list.size() == 0) {
                    IsKong();
                } else {
                    mlist.addAll(list);
                    mAdapter.notifyDataSetChanged();
                }
                onSuccess();
            }

            @Override
            protected void onError(String result) {
                super.onError(result);
                hide_loading();
                IsKong();
            }
        });
    }

    //刷新就是清除 list 同时通知adapter,会自动触发更多,不要自动调用
    public void Shuaxin() {
        clean();
        mAdapter.notifyDataSetChanged();
    }

    private void clean() {
        hide_Kong();
        p = 0;
        mlist.clear();
    }

    //判断当前页数是否第一页,第一页 显示为空页面,不是第一页 显示加载完毕
    public void IsKong() {
        p--;
//        if (p == 0) {
//            show_kong();
//        } else {
//            baseLoadingUtil.hide();
//        }
        if (p == 0) {
            show_kong();
        }
        baseLoadingUtil.hide();
    }

    //直接用gson实现list困难的话 可以重写此方法
    @Nullable
    protected List<T> getTs(String result) {
        if (type == null) {
            Lg.e("没有设置type");
        }
        return new Gson().fromJson(result, type);
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setKongView(View layoutView) {
        this.layoutView = layoutView;
    }

    public void hide_Kong() {
        if (layoutView != null) {
            layoutView.setVisibility(View.GONE);
        }
    }

    public void show_kong() {
        if (layoutView != null) {
            layoutView.setVisibility(View.VISIBLE);
        }
    }


    protected abstract void hide_loading();

    protected abstract void show_loading();

    protected abstract void loadMore();

    protected abstract RecyclerView.LayoutManager initLayoutManager();

    protected abstract void getView(ViewHolder holder, T t, int position);

    protected void onitemClick(View view, RecyclerView.ViewHolder holder, int position) {
    }

    protected void onitemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
    }

    protected void onSuccess() {
    }

    /**
     * 这个更多的监听
     * 只要适配器set进去 或者刷新的时候就都会走
     */
    protected void onLoadMore() {
        loadMore();
    }
}
