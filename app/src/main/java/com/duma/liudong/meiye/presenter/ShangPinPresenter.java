package com.duma.liudong.meiye.presenter;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseXiaLaRvPresenter;
import com.duma.liudong.meiye.model.ShangPinBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.StartUtil;
import com.google.gson.reflect.TypeToken;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by liudong on 17/3/30.
 */

public class ShangPinPresenter extends BaseXiaLaRvPresenter<ShangPinBean> {

    private OnShangPinListener shangPinListener;

    public interface OnShangPinListener {
        void loading_hide();

        void loading_show();

        void onLoadMore();
    }

    public void setShangPinListener(OnShangPinListener shangPinListener) {
        this.shangPinListener = shangPinListener;
    }

    public ShangPinPresenter(Activity context, RecyclerView rv) {
        super(context, R.layout.rv_shangping, rv);
        setType(new TypeToken<List<ShangPinBean>>() {
        }.getType());
    }

    public void setShangping(int type) {
        if (type == 0) {
            setLayoutManagerByType(R.layout.rv_shangping);
        } else {
            setLayoutManagerByType(R.layout.rv_shangping_vertical, new LinearLayoutManager(mActivity));
        }
    }

    @Override
    protected void getView(ViewHolder holder, ShangPinBean shangPinBean, int position) {
        ImageView img_original_img = holder.getView(R.id.img_original_img);
        ImageView img_type = holder.getView(R.id.img_type);
        ImageLoader.with(shangPinBean.getOriginal_img(), img_original_img);
        ImageLoader.with(shangPinBean.getType(), img_type);
        holder.setText(R.id.tv_goods_name, shangPinBean.getGoods_name());
        holder.setText(R.id.tv_market_price, "￥" + shangPinBean.getMarket_price());
        holder.setText(R.id.tv_shop_price, shangPinBean.getShop_price());
        holder.setText(R.id.tv_sales_sum, shangPinBean.getSales_sum() + "人付款");
        holder.setText(R.id.tv_distance, shangPinBean.getDistance() + "m");
    }

    @Override
    protected void onitemClick(View view, RecyclerView.ViewHolder holder, int position) {
        StartUtil.toShangPingWeb(mActivity, Api.url + "/index.php/Mobile/Goods/goodsdetail?goods_id=" + mlist.get(position).getGoods_id());
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
        shangPinListener.onLoadMore();
    }

    @Override
    public RecyclerView.LayoutManager initLayoutManager() {
        return new GridLayoutManager(mActivity, 2);
    }
}
