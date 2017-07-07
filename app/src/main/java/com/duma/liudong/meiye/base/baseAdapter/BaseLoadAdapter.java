package com.duma.liudong.meiye.base.baseAdapter;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudong on 2017/6/19.
 */

public abstract class BaseLoadAdapter<T> extends BaseAdapter<T> implements BaseQuickAdapter.RequestLoadMoreListener {
    private int page;//分页
    private int mOldItemSize;//
    private SwipeRefreshLayout swLoading;
    private boolean isRefresh = false;

    public BaseLoadAdapter(Activity mActivity, RecyclerView mRecyclerView, int layoutResId) {
        super(mActivity, mRecyclerView, layoutResId);
        setOnLoadMoreListener(this, mRecyclerView);
        setAutoLoadMoreSize(2);
    }

    public BaseLoadAdapter setLoadSize(int size) {
        setAutoLoadMoreSize(size);
        return this;
    }

    public BaseLoadAdapter setSwLoading(SwipeRefreshLayout swLoading) {
        this.swLoading = swLoading;
        return this;
    }

    public void load_show() {
        if (swLoading != null) {
            swLoading.setRefreshing(true);
        }
    }

    public void load_hide() {
        if (swLoading != null) {
            swLoading.setRefreshing(false);
        }
    }

    @Override
    public void setListOrRefresh(List list) {
        isSetAdapter();
        if (list == null) {
            list = new ArrayList();
        }
        if (list.size() == 0 && page == 0) {
            //第一页的时候是空数据
            setEmpty();
        } else if (list.size() == 0) {
            //不是第一页 是空数据 说明加载到最后一页了
            loadMoreEnd();
        } else {
            //正常加载 页数才加1
            page++;
            if (isRefresh) {
                //下拉刷新
                isRefresh = false;
                setNewData(list);
            } else {
                //上一页的item的个数
                mOldItemSize = getData().size();
                //添加最新数据
                addData(list);
                //这一页还有数据 说明还没加载完 这个貌似要放在最后 这个会预加载
                loadMoreComplete();
            }
        }
    }

    @Override
    public void onLoadMoreRequested() {
        if (getData().size() > mOldItemSize) {
            onRefresh(getPage());
        } else {
            /**
             * 这一页和上一页的数据一样 说明已经加载完毕
             */
            loadMoreEnd();
        }
    }

    public BaseLoadAdapter onRefresh(int page) {
        load_show();
        onLoadHttp(page);
        return this;
    }

    //下拉刷新
    public BaseLoadAdapter onRefresh() {
        page = 0;
        mOldItemSize = 0;
        isRefresh = true;
        return onRefresh(0);
    }

    public void httpError() {
        loadMoreFail();
    }

    //给前台从写的
    protected void onLoadHttp(int page) {

    }

    public int getPage() {
        return page;
    }


    public SwipeRefreshLayout getSwLoading() {
        return swLoading;
    }
}
