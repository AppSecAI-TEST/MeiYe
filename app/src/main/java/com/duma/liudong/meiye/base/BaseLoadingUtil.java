package com.duma.liudong.meiye.base;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

/**
 * Created by 79953 on 2016/9/29.
 */

public class BaseLoadingUtil {
    private LoadMoreWrapper loadMoreWrapper;
    private View loadingVier;
    private LinearLayout layout_loading, layout_text;
    private TextView tv_biaoti;

    public BaseLoadingUtil(RecyclerView.Adapter adapter, Activity activity) {
        loadMoreWrapper = new LoadMoreWrapper(adapter);
        LinearLayout linearLayout = new LinearLayout(activity);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        loadingVier = LayoutInflater.from(activity).inflate(R.layout.default_loading, linearLayout);

        initView();
    }

    private void initView() {
        layout_loading = (LinearLayout) loadingVier.findViewById(R.id.layout_loading);
        layout_text = (LinearLayout) loadingVier.findViewById(R.id.layout_text);
        tv_biaoti = (TextView) loadingVier.findViewById(R.id.tv_biaoti);
        show();
    }

    public void show() {
        layout_loading.setVisibility(View.VISIBLE);
        layout_text.setVisibility(View.GONE);
    }

    public void setBiaoTi(String biaoTi) {
        tv_biaoti.setText(biaoTi);
    }

    public void hide() {
        layout_loading.setVisibility(View.GONE);
        layout_text.setVisibility(View.VISIBLE);
    }

    public LoadMoreWrapper getLoadAdapter(LoadMoreWrapper.OnLoadMoreListener listener) {
        loadMoreWrapper.setLoadMoreView(loadingVier);
        loadMoreWrapper.setOnLoadMoreListener(listener);
        return loadMoreWrapper;
    }

}
