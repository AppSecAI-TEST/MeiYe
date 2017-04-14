package com.duma.liudong.meiye.view.me.shiWuDinDan;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseFragment;
import com.duma.liudong.meiye.base.BaseXiaLaRvPresenter;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.model.ShiWuDinDanBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.utils.Ts;
import com.google.gson.reflect.TypeToken;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by liudong on 17/4/12.
 */

public class ShiWuDinDanFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.layout_kong)
    LinearLayout layoutKong;
    @BindView(R.id.rv_shangping)
    RecyclerView rvShangping;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;

    ShiWuDinDanQuanBuActivity activity;
    private BaseXiaLaRvPresenter<ShiWuDinDanBean> baseXiaLaRvPresenter;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_shiwudindan;
    }

    @Override
    protected void initData() {
        activity = (ShiWuDinDanQuanBuActivity) mActivity;
        StartUtil.setSw(swLoading, this);
        initAdapter();
        if (activity.isOne) {
            activity.isOne = false;
            baseXiaLaRvPresenter.QueryHttp(getBuild());
        }
    }

    private void initAdapter() {
        baseXiaLaRvPresenter = new BaseXiaLaRvPresenter<ShiWuDinDanBean>(mActivity, R.layout.rv_dindan, rvShangping) {
            @Override
            protected void hide_loading() {
                swLoading.setRefreshing(false);
            }

            @Override
            protected void show_loading() {
                swLoading.setRefreshing(true);
            }

            @Override
            protected void loadMore() {
                if (baseXiaLaRvPresenter.isOne)
                    baseXiaLaRvPresenter.QueryHttp(getBuild());
            }

            @Override
            protected RecyclerView.LayoutManager initLayoutManager() {
                return new LinearLayoutManager(mActivity);
            }

            @Override
            protected void getView(ViewHolder holder, ShiWuDinDanBean shiWuBean, int position) {
                holder.setText(R.id.tv_store_name, shiWuBean.getStore_name())
                        .setText(R.id.tv_type, shiWuBean.getOrder_status_desc())
                        .setText(R.id.tv_num, shiWuBean.getGoods_list().size() + "")
                        .setText(R.id.tv_jiage, shiWuBean.getTotal_amount());

                RecyclerView recyclerView = holder.getView(R.id.rv_shangping);
                recyclerView.setFocusable(false);
                recyclerView.setNestedScrollingEnabled(false);
                recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
                CommonAdapter<ShiWuDinDanBean.GoodsListBean> adapter = new CommonAdapter<ShiWuDinDanBean.GoodsListBean>(mActivity, R.layout.include_shangping_itim, shiWuBean.getGoods_list()) {
                    @Override
                    protected void convert(ViewHolder holder, ShiWuDinDanBean.GoodsListBean goodsListBean, int position) {
                        ImageView img_head_pic = holder.getView(R.id.img_head_pic);
                        ImageLoader.with(Api.url + goodsListBean.getOriginal_img(), img_head_pic);
                        holder.setText(R.id.tv_title, goodsListBean.getGoods_name());
                        holder.setText(R.id.tv_guige, goodsListBean.getSpec_key_name());
                        holder.setText(R.id.tv_jiage, "￥" + goodsListBean.getMember_goods_price());
                        holder.setText(R.id.tv_shuliang, "x" + goodsListBean.getGoods_num());
                    }
                };
                recyclerView.setAdapter(adapter);

                TextView tv_hei_one, tv_hei_two, tv_hong;
                tv_hei_one = holder.getView(R.id.tv_hei_one);
                tv_hei_two = holder.getView(R.id.tv_hei_two);
                tv_hong = holder.getView(R.id.tv_hong);

                tv_hei_one.setOnClickListener(new OnTextClick(tv_hei_one, shiWuBean));
                tv_hei_two.setOnClickListener(new OnTextClick(tv_hei_one, shiWuBean));
                tv_hong.setOnClickListener(new OnTextClick(tv_hong, shiWuBean));

                switch (shiWuBean.getOrder_status_code()) {
                    case "WAITPAY":
                        //代付款
                        tv_hei_one.setVisibility(View.GONE);
                        tv_hei_two.setVisibility(View.VISIBLE);
                        tv_hong.setVisibility(View.VISIBLE);
                        tv_hei_two.setText("取消订单");
                        tv_hong.setText("去支付");
                        break;
                    case "WAITSEND":
                        //待发货
                        tv_hei_one.setVisibility(View.GONE);
                        tv_hei_two.setVisibility(View.VISIBLE);
                        tv_hong.setVisibility(View.VISIBLE);
                        tv_hei_two.setText("申请退款");
                        tv_hong.setText("提醒发货");
                        break;
                    case "WAITRECEIVE":
                        //待收货
                        tv_hei_one.setVisibility(View.VISIBLE);
                        tv_hei_two.setVisibility(View.VISIBLE);
                        tv_hong.setVisibility(View.VISIBLE);
                        tv_hei_one.setText("申请退款");
                        tv_hei_two.setText("查看物流");
                        tv_hong.setText("确认收货");
                        break;
                    case "WAITCCOMMENT":
                        //待评价
                        tv_hei_one.setVisibility(View.GONE);
                        tv_hei_two.setVisibility(View.VISIBLE);
                        tv_hong.setVisibility(View.VISIBLE);
                        tv_hei_two.setText("申请退款");
                        tv_hong.setText("晒单评价");
                        break;
                    case "COMMENTED":
                        //已评价==已完成
                        tv_hei_one.setVisibility(View.VISIBLE);
                        tv_hei_two.setVisibility(View.VISIBLE);
                        tv_hong.setVisibility(View.GONE);
                        tv_hei_one.setText("删除订单");
                        tv_hei_two.setText("查看订单");
                        break;
                    case "RETURNED":
                        //已退货
                        tv_hei_one.setVisibility(View.GONE);
                        tv_hei_two.setVisibility(View.VISIBLE);
                        tv_hong.setVisibility(View.GONE);
                        tv_hei_two.setText("查看订单");
                        break;
                    default:
                        //交易关闭
                        tv_hei_one.setVisibility(View.GONE);
                        tv_hei_two.setVisibility(View.VISIBLE);
                        tv_hong.setVisibility(View.GONE);
                        tv_hei_two.setText("删除订单");
                        break;
                }

            }
        };
        baseXiaLaRvPresenter.setType(new TypeToken<ArrayList<ShiWuDinDanBean>>() {
        }.getType());
        baseXiaLaRvPresenter.setKongView(layoutKong);
    }

    @Override
    protected void onLazyLoad() {
        baseXiaLaRvPresenter.QueryHttp(getBuild());
    }

    @Override
    public void onRefresh() {
        baseXiaLaRvPresenter.Shuaxin();
    }

    private RequestCall getBuild() {
        baseXiaLaRvPresenter.p++;
        return OkHttpUtils
                .get()
                .tag("base")
                .url(Api.orderList)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .addParams("type", activity.getType())
                .addParams("goods_type", "1")
                .addParams("p", baseXiaLaRvPresenter.p + "")
                .build();
    }

    class OnTextClick implements View.OnClickListener {

        private TextView textView;
        private ShiWuDinDanBean bean;
        Intent intent;

        public OnTextClick(TextView textView, ShiWuDinDanBean bean) {
            this.textView = textView;
            this.bean = bean;
        }

        @Override
        public void onClick(View v) {
            switch (textView.getText().toString()) {
                case "提醒发货":
                    Ts.setText("已提醒卖家~请耐心等待!");
                    break;
                case "取消订单":
                    Ts.setText("取消订单");
                    //调接口
                    break;
                case "确认收货":
                    Ts.setText("确认收货");
                    //调接口
                    break;
                case "删除订单":
                    Ts.setText("删除订单");
                    //调接口
                    break;
                case "去支付":
                    Ts.setText("去支付");
                    break;
                case "查看物流":
                    Ts.setText("查看物流");
                    break;
                case "晒单评价":
                    //跳转

                    break;
                case "查看订单":
                case "申请退款":
                    //跳转详情页
                    intent = new Intent(mActivity, ShiWuDianDanXiangQingActivity.class);
                    intent.putExtra("bean", bean);
                    startActivity(intent);
                    break;
            }
        }
    }
}
