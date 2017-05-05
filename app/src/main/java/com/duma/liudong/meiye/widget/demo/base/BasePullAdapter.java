package com.duma.liudong.meiye.widget.demo.base;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.liudong.meiye.R;

import java.util.List;

/**
 * Created by liudong on 17/5/4.
 */

public abstract class BasePullAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {
    public BaseQuickAdapter<T, BaseViewHolder> adapter;

    private Activity mActivity;
    private RecyclerView mRecyclerView;
    private int layoutResId;
    private List<T> mList;
    private int p;//当前页数

    public BasePullAdapter(Activity mActivity, RecyclerView mRecyclerView, int layoutResId, List mList) {
        super(layoutResId, mList);
        this.mActivity = mActivity;
        this.mRecyclerView = mRecyclerView;
        this.layoutResId = layoutResId;
        this.mList = mList;
        p = 0;
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        mRecyclerView.setLayoutManager(layoutManager);
    }

    /**
     * 添加空布局
     */
    public void setNotDataView() {
        View notDataView = mActivity.getLayoutInflater().inflate(R.layout.include_no_data, (ViewGroup) mRecyclerView.getParent(), false);
        this.setEmptyView(notDataView);
    }

    public void setNotDataView(@LayoutRes int layout) {
        View notDataView = mActivity.getLayoutInflater().inflate(layout, (ViewGroup) mRecyclerView.getParent(), false);
        this.setEmptyView(notDataView);
    }

    /**
     * 最后添加到rv
     */
    public void setBaseAdapter() {
        mRecyclerView.setAdapter(this);
    }

}
