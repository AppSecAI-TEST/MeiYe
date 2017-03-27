package com.duma.liudong.meiye.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
    private CommonAdapter<T> commonAdapter;
    private RvAdapterListener rvAdapterListener;

    public interface RvAdapterListener {
        void hide_Kong();

        void show_kong();

        void hide_loading();

        void show_loading();

        void convert(ViewHolder holder, Object object, int position);
    }


    public BaseRvAdapter(final Context context, final int layoutId, RecyclerView rv, final RvAdapterListener rvAdapterListener) {
        mList = new ArrayList<>();
        this.rvAdapterListener = rvAdapterListener;
        commonAdapter = new CommonAdapter<T>(context, layoutId, mList) {
            @Override
            protected void convert(ViewHolder holder, T t, int position) {
                rvAdapterListener.convert(holder, t, position);
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

    protected void onitemClick(View view, RecyclerView.ViewHolder holder, int position) {
    }

    protected void onitemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
    }

    public void QueryHttp(RequestCall build) {
        rvAdapterListener.show_loading();
        rvAdapterListener.hide_Kong();
        OkHttpUtils.getInstance().cancelTag(this);
        build.execute(new MyStringCallback() {
            @Override
            public void onMySuccess(String result) {
                rvAdapterListener.hide_loading();
                ArrayList<T> list = getTs(result);
                if (list == null || list.size() == 0) {
                    onError("");
                    return;
                }
                mList.clear();
                mList.addAll(list);
                commonAdapter.notifyDataSetChanged();
            }

            @Override
            protected void onError(String result) {
                super.onError(result);
                rvAdapterListener.hide_loading();
                rvAdapterListener.show_kong();
            }
        });

    }

    @Nullable
    protected ArrayList<T> getTs(String result) {
        Type type = new TypeToken<ArrayList<T>>() {
        }.getType();
        return new Gson().fromJson(result, type);
    }
}
