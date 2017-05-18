package com.duma.liudong.meiye.presenter;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseXiaLaRvPresenter;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.model.ShangPinBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.StartUtil;
import com.google.gson.reflect.TypeToken;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;

import java.lang.reflect.Type;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by liudong on 17/3/30.
 */

public class ShangPinLieBiaoPresenter extends BaseXiaLaRvPresenter<ShangPinBean> {

    private OnShangPinListener shangPinListener;
    private int type;
    private NumberFormat numberFormat;

    public interface OnShangPinListener {
        void loading_hide();

        void loading_show();

        void onLoadMore();
    }

    //商品列表页的固定参数
    public GetBuilder getBuild() {
        p++;
        GetBuilder getBuilder = OkHttpUtils
                .get()
                .tag("base")
                .url(Api.goodindex)
                .addParams("lat", MyApplication.getSpUtils().getString(Constants.lat))
                .addParams("lng", MyApplication.getSpUtils().getString(Constants.lng))
                .addParams("p", p + "");
        return getBuilder;
    }

    public void setShangPinListener(OnShangPinListener shangPinListener) {
        this.shangPinListener = shangPinListener;
    }

    public ShangPinLieBiaoPresenter(Activity context, RecyclerView rv) {
        super(context, R.layout.rv_shangping, rv);
        setType(new TypeToken<List<ShangPinBean>>() {
        }.getType());
        numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
    }

    public void setShangping(int type) {
        this.type = type;
        if (type == 0) {
            setLayoutManagerByType(R.layout.rv_shangping);
        } else if (type == 1) {
            setLayoutManagerByType(R.layout.rv_shangping_vertical, new LinearLayoutManager(mActivity));
        } else {
            setLayoutManagerByType(R.layout.rv_miaosha, new LinearLayoutManager(mActivity));
        }
    }

    @Override
    protected void getView(ViewHolder holder, final ShangPinBean shangPinBean, int position) {
        if (type != 3) {
            ImageView img_original_img = holder.getView(R.id.img_original_img);
            ImageView img_type = holder.getView(R.id.img_type);
            ImageLoader.with(shangPinBean.getOriginal_img(), img_original_img);
            ImageLoader.with(shangPinBean.getType(), img_type);
            holder.setText(R.id.tv_goods_name, shangPinBean.getGoods_name());
            holder.setText(R.id.tv_market_price, "￥" + shangPinBean.getMarket_price());
            holder.setText(R.id.tv_shop_price, shangPinBean.getPrice());
            holder.setText(R.id.tv_sales_sum, shangPinBean.getSales_sum() + "人付款");
            holder.setText(R.id.tv_distance, shangPinBean.getDistance());
        } else {
            ImageView img_original_img = holder.getView(R.id.img_original_img);
            ImageLoader.with(shangPinBean.getOriginal_img(), img_original_img);
            holder.setText(R.id.tv_goods_name, shangPinBean.getGoods_name());
            holder.setText(R.id.tv_market_price, shangPinBean.getMarket_price());
            holder.setText(R.id.tv_distance, shangPinBean.getDistance());
            holder.setText(R.id.tv_shop_price, "¥" + shangPinBean.getPrice());
            double num1 = 0;
            double num2 = 0;
            String res = "0";
            try {
                num1 = Double.parseDouble(shangPinBean.getSales_sum());
                num2 = Double.parseDouble(shangPinBean.getStore_count()) + num1;
                res = numberFormat.format((num1 / num2) * 100);
            } catch (Exception e) {
            }

            holder.setText(R.id.tv_sales_sum, "已付" + res + "%");
            ProgressBar progressBar = holder.getView(R.id.progressBar_sum);
            progressBar.setMax((int) num2);
            progressBar.setProgress((int) num1);
            TextView textView = holder.getView(R.id.tv_qianggou);
            textView.setBackgroundColor(MyApplication.getInstance().getResources().getColor(R.color.main_red));
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StartUtil.toShangPingWeb(mActivity, Api.H5Url() + shangPinBean.getGoods_id());
                }
            });
        }

    }

    @Override
    protected void onitemClick(View view, RecyclerView.ViewHolder holder, int position) {
        StartUtil.toShangPingWeb(mActivity, Api.H5Url() + mlist.get(position).getGoods_id());
    }

    @Override
    public void setType(Type type) {
        super.setType(type);
    }

    @Override
    protected void hide_loading() {
        if (shangPinListener != null) {
            shangPinListener.loading_hide();
        }
    }

    @Override
    protected void show_loading() {
        if (shangPinListener != null) {
            shangPinListener.loading_show();
        }
    }

    @Override
    protected void loadMore() {
        if (isOne)
            shangPinListener.onLoadMore();
    }


    @Override
    public RecyclerView.LayoutManager initLayoutManager() {
        return new GridLayoutManager(mActivity, 2);
    }
}
