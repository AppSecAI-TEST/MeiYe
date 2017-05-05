package com.duma.liudong.meiye.widget.demo.base;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by liudong on 17/5/4.
 */

public class BasePullAdapterBuilder<T> {
    private RecyclerView.LayoutManager layoutManager;
    private onBaseAdapterListener<T> baseAdapterListener;
    private BasePullAdapter<T> basePullAdapter;
    private boolean isNotDataView = true;//判断是否加入空布局,默认为 true
    private int layout;//布局的view

    /**
     * @param layoutManager 默认linlayout
     * @return
     */
    public BasePullAdapterBuilder setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        return this;
    }

    /**
     * 为true 显示默认空布局
     * 为false 不显示空布局
     * 为布局id 显示布局view
     *
     * @param isNotDataView
     * @return
     */
    public BasePullAdapterBuilder setNotDataView(boolean isNotDataView) {
        this.isNotDataView = isNotDataView;
        return this;
    }

    public BasePullAdapterBuilder setNotDataView(@LayoutRes int layout) {
        isNotDataView = true;
        this.layout = layout;
        return this;
    }

    public BasePullAdapterBuilder build(Activity mActivity, RecyclerView mRecyclerView, int layoutResId, List mList) {
        basePullAdapter = new BasePullAdapter<T>(mActivity, mRecyclerView, layoutResId, mList) {
            @Override
            protected void convert(BaseViewHolder helper, T item) {
                baseAdapterListener.getView(helper, item);
            }
        };
        if (layoutManager == null) {
            layoutManager = new LinearLayoutManager(mActivity);
        }
        basePullAdapter.setLayoutManager(layoutManager);
        if (isNotDataView && layout != 0) {
            basePullAdapter.setNotDataView(layout);
        } else if (isNotDataView) {
            basePullAdapter.setNotDataView();
        }
        basePullAdapter.setBaseAdapter();
        return this;
    }

    public BasePullAdapter execute(onBaseAdapterListener baseAdapterListener) {
        this.baseAdapterListener = baseAdapterListener;
        return basePullAdapter;
    }

    public interface onBaseAdapterListener<T> {
        void getView(BaseViewHolder helper, T item);
    }
}
