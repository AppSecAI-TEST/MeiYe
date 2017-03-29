package com.duma.liudong.meiye.presenter;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.utils.Constants;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudong on 17/3/29.
 */

public abstract class BaseAdAdapter<T> {
    private List<T> mList;
    private CommonAdapter<T> adapter;
    private Activity mActivity;
    private RecyclerView recyclerView;

    private int type;

    public BaseAdAdapter(Activity activity, RecyclerView recyclerView) {
        this(activity, recyclerView, Constants.default_AD);
    }

    public BaseAdAdapter(Activity activity, RecyclerView recyclerView, int type) {
        this.mActivity = activity;
        this.recyclerView = recyclerView;
        this.type = type;
        mList = new ArrayList<>();
        recyclerView.setFocusable(false);
        recyclerView.setNestedScrollingEnabled(false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        initadapter();
        recyclerView.setAdapter(adapter);
    }

    public void setmList(List<T> list) {
        mList.clear();
        this.mList.addAll(list);
        adapter.notifyDataSetChanged();
    }


    private void initadapter() {
        adapter = new CommonAdapter<T>(mActivity, getRv_ad(), mList) {
            @Override
            protected void convert(ViewHolder holder, T t, int position) {
                getView(holder, t, position);
            }
        };
    }

    protected abstract void getView(ViewHolder holder, T t, int position);

    private int getRv_ad() {
        switch (type) {
            case Constants.dianPu_AD:
                return R.layout.rv_ad_dianpu;
            case Constants.dingZhi_AD:
                return R.layout.rv_ad_dingzhi;
            default:
                return R.layout.rv_ad;
        }

    }
}
