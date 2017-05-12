package com.duma.liudong.meiye.utils;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.model.ShangPinBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by liudong on 17/4/6.
 */

public class ShangPinAdapter {
    public ShangPinAdapter(final Activity mActivity, RecyclerView recyclerView, List<ShangPinBean> datas) {
        recyclerView.setLayoutManager(new GridLayoutManager(mActivity, 2));
        recyclerView.setFocusable(false);
        recyclerView.setNestedScrollingEnabled(false);
        CommonAdapter<ShangPinBean> adapter = new CommonAdapter<ShangPinBean>(mActivity, R.layout.rv_ad, datas) {
            @Override
            protected void convert(ViewHolder holder, final ShangPinBean shangPinBean, int position) {
                ImageView imageView = holder.getView(R.id.img_original_img);
                ImageLoader.with(shangPinBean.getOriginal_img(), imageView);
                holder.setText(R.id.tv_goods_name, shangPinBean.getGoods_name());
                holder.setText(R.id.tv_market_price, "¥" + shangPinBean.getMarket_price());
                holder.setText(R.id.tv_shop_price, shangPinBean.getPrice());
                holder.setText(R.id.tv_sales_sum, shangPinBean.getSales_sum() + "人付款");
                holder.setOnClickListener(R.id.layout_onClick, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StartUtil.toShangPingWeb(mActivity, Api.H5Url() + shangPinBean.getGoods_id());
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }
}
