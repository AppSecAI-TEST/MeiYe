package com.duma.liudong.meiye.base.baseAdapter;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by liudong on 17/5/4.
 */

public class BaseAdapterBuilder<T> {
    private RecyclerView.LayoutManager layoutManager;
    private onBaseAdapterListener baseAdapterListener;
    private BaseAdapter<T> baseAdapter;


    private Activity mActivity;
    private RecyclerView mRecyclerView;
    private int layoutResId;

//    public BaseAdapterBuilder(Activity mActivity, RecyclerView mRecyclerView, int layoutResId) {
//        this(mActivity, mRecyclerView, layoutResId, null);
//    }

    public BaseAdapterBuilder(Activity mActivity, RecyclerView mRecyclerView, int layoutResId) {
        this.mActivity = mActivity;
        this.mRecyclerView = mRecyclerView;
        this.layoutResId = layoutResId;
    }

    /**
     * @param layoutManager 默认linlayout
     * @return
     */
    public BaseAdapterBuilder setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        return this;
    }


    public BaseAdapterBuilder build() {
        baseAdapter = new BaseAdapter<T>(mActivity, mRecyclerView, layoutResId) {
            @Override
            protected void convert(BaseViewHolder helper, T item) {
                baseAdapterListener.getView(helper, item);
            }
        };
        if (layoutManager == null) {
            layoutManager = new LinearLayoutManager(mActivity);
        }
        baseAdapter.setLayoutManager(layoutManager);
        baseAdapter.setNotDataView();
        baseAdapter.setEmpty();
        return this;
    }

    public BaseAdapter<T> execute(onBaseAdapterListener baseAdapterListener) {
        this.baseAdapterListener = baseAdapterListener;
        return baseAdapter;
    }
}
