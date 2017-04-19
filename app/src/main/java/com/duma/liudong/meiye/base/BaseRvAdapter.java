package com.duma.liudong.meiye.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.duma.liudong.meiye.utils.Lg;
import com.google.gson.Gson;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudong on 17/3/27.
 */

public abstract class BaseRvAdapter<T> {
    public List<T> mList;
    public CommonAdapter<T> commonAdapter;

    private View layoutView;

    public BaseRvAdapter(final Context context, final int layoutId, RecyclerView rv) {
        mList = new ArrayList<>();
        commonAdapter = new CommonAdapter<T>(context, layoutId, mList) {
            @Override
            protected void convert(ViewHolder holder, T t, int position) {
                BaseRvAdapter.this.getView(holder, t, position);
            }
        };
        rv.setAdapter(commonAdapter);
        commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
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
    }

    protected abstract void getView(ViewHolder holder, T t, int position);

    protected abstract void hide_loading();

    protected abstract void show_loading();

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

    protected void onitemClick(View view, RecyclerView.ViewHolder holder, int position) {
    }

    protected void onitemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
    }

    protected void HttpSuccess() {
    }

    protected void HttpError() {
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
                if (list == null || list.size() == 0) {
                    hide_loading();
                    show_kong();
                    return;
                }
                refresh(list);
            }

            @Override
            protected void onError(String result) {
                super.onError(result);
                HttpError();
                hide_loading();
                show_kong();
            }
        });
    }

    public void refresh(List<T> list) {
        mList.clear();
        mList.addAll(list);
        HttpSuccess();
        commonAdapter.notifyDataSetChanged();
    }

    private Type type;

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
}
