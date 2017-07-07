package com.duma.liudong.meiye.base.baseAdapter;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.liudong.meiye.R;

import java.util.List;

/**
 * Created by liudong on 17/5/4.
 */

public abstract class BaseAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {
    private Activity mActivity;
    private RecyclerView mRecyclerView;
    //没有数据的view
    private View notDataView;
    //没有数据时显示的文本
    private TextView tvNotData;
    private boolean isShowNotDataView;

    public BaseAdapter(Activity mActivity, RecyclerView mRecyclerView, int layoutResId) {
        super(layoutResId, null);
        this.mActivity = mActivity;
        this.mRecyclerView = mRecyclerView;
        isShowNotDataView = true;
    }


    //添加list 并更新
    public void setListOrRefresh(List list) {
        isSetAdapter();
        if (list == null || list.size() == 0) {
            setEmpty();
        } else {
            mData.clear();
            mData.addAll(list);
        }
        notifyDataSetChanged();
    }

    //设置空数据
    public void setEmpty() {
        if (isShowNotDataView) {
            setEmptyView(notDataView);
        }
    }

    //更新
    public void refresh() {
        isSetAdapter();
        if (mData.size() == 0) {
            setEmpty();
        }
        notifyDataSetChanged();

    }

    //判断是否setadapter
    public void isSetAdapter() {
        if (mRecyclerView.getAdapter() == null) {
            setBaseAdapter();
        }
    }


    /**
     * 添加空数据布局
     */
    public void setNotDataView() {
        setNotDataView(R.layout.include_no_data);
        tvNotData = (TextView) notDataView.findViewById(R.id.tv_name);
    }

    public void setNotDataView(@LayoutRes int layout) {
        notDataView = getView(layout);
    }


    //获得view
    public View getView(@LayoutRes int layout) {
        return mActivity.getLayoutInflater().inflate(layout, (ViewGroup) mRecyclerView.getParent(), false);
    }

    //设置空数据布局的textview
    public BaseAdapter setTvNotData(String notData) {
        if (tvNotData != null) {
            tvNotData.setText(notData);
        }
        return this;
    }

    //控制是否显示空数据布局
    public BaseAdapter setShowNotDataView(boolean showNotDataView) {
        isShowNotDataView = showNotDataView;
        return this;
    }

    /**
     * 添加到rv
     */
    public void setBaseAdapter() {
        mRecyclerView.setAdapter(this);
    }

    //设置layoutManager
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        mRecyclerView.setLayoutManager(layoutManager);
    }

}
