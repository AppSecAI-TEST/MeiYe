package com.duma.liudong.meiye.widget.demo;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.model.DemoBean;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/25.
 */

public class DemoActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_other)
    ImageView imgOther;
    @BindView(R.id.layout_other)
    LinearLayout layoutOther;
    @BindView(R.id.layout_kong)
    LinearLayout layoutKong;
    @BindView(R.id.rv_shangping)
    RecyclerView rvShangping;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_demo);
    }

    @Override
    protected void initData() {

        BaseQuickAdapter<DemoBean, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<DemoBean, BaseViewHolder>(3123) {
            @Override
            protected void convert(BaseViewHolder helper, DemoBean item) {

            }
        };
        baseQuickAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

            }
        }, rvShangping);
    }


    @OnClick(R.id.layout_back)
    public void onClick() {
    }
}
