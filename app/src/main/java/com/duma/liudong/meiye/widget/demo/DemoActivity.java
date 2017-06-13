package com.duma.liudong.meiye.widget.demo;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.model.ShangPinBean;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.widget.demo.base.BaseAdapterUtil;
import com.duma.liudong.meiye.widget.demo.base.BasePullAdapter;
import com.duma.liudong.meiye.widget.demo.base.BasePullAdapterBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/25.
 */

public class DemoActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_other)
    ImageView imgOther;
    @BindView(R.id.layout_other)
    LinearLayout layoutOther;
    @BindView(R.id.rv_shangping)
    RecyclerView rvShangping;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;

    private List<ShangPinBean> mList;
    int p = 0;

    BasePullAdapter adapter;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_demo);
    }

    @Override
    protected void initData() {
        StartUtil.setSw(swLoading, this);
        mList = new ArrayList<>();

        adapter = BaseAdapterUtil
                .getPullAdapter()
                .setLayoutManager(new GridLayoutManager(mActivity, 2))
                .build(mActivity, rvShangping, R.layout.rv_shangping, mList)
                .execute(new BasePullAdapterBuilder.onBaseAdapterListener<ShangPinBean>() {
                    @Override
                    public void getView(BaseViewHolder holder, ShangPinBean shangPinBean) {
                        ImageView img_original_img = holder.getView(R.id.img_original_img);
                        ImageView img_type = holder.getView(R.id.img_type);
                        ImageLoader.with(shangPinBean.getOriginal_img(), img_original_img);
                        ImageLoader.with(shangPinBean.getType(), img_type);
                        holder.setText(R.id.tv_goods_name, shangPinBean.getGoods_name());
                        holder.setText(R.id.tv_market_price, "￥" + shangPinBean.getMarket_price());
                        holder.setText(R.id.tv_shop_price, shangPinBean.getPrice());
                        holder.setText(R.id.tv_sales_sum, shangPinBean.getSales_sum() + "人付款");
                        holder.setText(R.id.tv_distance, shangPinBean.getDistance() + "m");
                    }
                });
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

            }
        }, rvShangping);
    }


    @OnClick(R.id.layout_back)
    public void onClick() {
    }

    @Override
    public void onRefresh() {
        swLoading.setRefreshing(true);
        p++;
        OkHttpUtils
                .get()
                .url("http://meiye.duma-ivy.cn/App/Goods/index?cat_id=55&goods_type=1")
                .addParams("p", p + "")
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        swLoading.setRefreshing(false);
                        Type type = new TypeToken<List<ShangPinBean>>() {
                        }.getType();
                        List<ShangPinBean> list = new Gson().fromJson(result, type);
                        mList.clear();
                        mList.addAll(list);
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
