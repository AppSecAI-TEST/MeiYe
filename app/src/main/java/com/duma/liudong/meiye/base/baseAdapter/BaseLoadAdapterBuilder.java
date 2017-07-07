package com.duma.liudong.meiye.base.baseAdapter;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by liudong on 17/5/4.
 */

public class BaseLoadAdapterBuilder<T> {
    private RecyclerView.LayoutManager layoutManager;
    private onBaseLoadAdapterListener<T> baseAdapterListener;
    private BaseLoadAdapter<T> baseLoadAdapter;


    private Activity mActivity;
    private RecyclerView mRecyclerView;
    private int layoutResId;

//    public BaseLoadAdapterBuilder(Activity mActivity, RecyclerView mRecyclerView, int layoutResId) {
//        this(mActivity, mRecyclerView, layoutResId, null);
//    }

    public BaseLoadAdapterBuilder(Activity mActivity, RecyclerView mRecyclerView, int layoutResId) {
        this.mActivity = mActivity;
        this.mRecyclerView = mRecyclerView;
        this.layoutResId = layoutResId;
    }

    /**
     * @param layoutManager 默认linlayout
     * @return
     */
    public BaseLoadAdapterBuilder setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        return this;
    }


    public BaseLoadAdapterBuilder build() {
        baseLoadAdapter = new BaseLoadAdapter<T>(mActivity, mRecyclerView, layoutResId) {
            @Override
            protected void convert(BaseViewHolder helper, T item) {
                baseAdapterListener.getView(helper, item);
            }

            @Override
            protected void onLoadHttp(int page) {
                baseAdapterListener.onLoadHttp(page);
            }
        };
        if (layoutManager == null) {
            layoutManager = new LinearLayoutManager(mActivity);
        }
        baseLoadAdapter.setLayoutManager(layoutManager);
        baseLoadAdapter.setNotDataView();
        return this;
    }


    public BaseLoadAdapter execute(onBaseLoadAdapterListener baseAdapterListener) {
        this.baseAdapterListener = baseAdapterListener;
        return baseLoadAdapter;
    }
}
