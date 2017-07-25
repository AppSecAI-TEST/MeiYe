package com.duma.liudong.meiye.view.me.dinDan;

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
import com.duma.liudong.meiye.model.EvenDinDan;
import com.duma.liudong.meiye.model.QueRenDinDanBean;
import com.duma.liudong.meiye.model.ShiWuDinDanBean;
import com.duma.liudong.meiye.presenter.PublicPresenter;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.utils.Ts;
import com.duma.liudong.meiye.view.dialog.QueRenUtilDialog;
import com.google.gson.reflect.TypeToken;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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

    QuanBuDinDanActivity activity;
    private BaseXiaLaRvPresenter<ShiWuDinDanBean> baseXiaLaRvPresenter;
    private PublicPresenter publicPresenter;
    private QueRenUtilDialog QuXiaoDialog, shanchuDialog, ShouHuoDialog;
    private String id;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_shiwudindan;
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        publicPresenter = new PublicPresenter();
        publicPresenter.setGetSuccessListener(new PublicPresenter.GetSuccessListener() {
            @Override
            public void GetCodeSuccess() {
                onRefresh();
            }
        });
        initDialog();
        activity = (QuanBuDinDanActivity) mActivity;
        StartUtil.setSw(swLoading, this);
        initAdapter();
        if (activity.isOne) {
            activity.isOne = false;
            baseXiaLaRvPresenter.QueryHttp(getBuild());
        }
    }

    @Subscribe
    public void onRes(EvenDinDan evenDinDan) {
        if (evenDinDan.getType().equals(activity.getfenlei_Type())) {
            onRefresh();
        }
    }

    private void initDialog() {
        QuXiaoDialog = new QueRenUtilDialog(mActivity, "", "是否取消订单", "取消", "确定");
        QuXiaoDialog.setYesClicklistener(new QueRenUtilDialog.OnYesClickListener() {
            @Override
            public void onYes() {
                publicPresenter.cancelOrder(mActivity, id);
            }
        });
        shanchuDialog = new QueRenUtilDialog(mActivity, "", "是否删除订单", "取消", "确定");
        shanchuDialog.setYesClicklistener(new QueRenUtilDialog.OnYesClickListener() {
            @Override
            public void onYes() {
                publicPresenter.delOrder(mActivity, id);
            }
        });
        ShouHuoDialog = new QueRenUtilDialog(mActivity, "", "确定收到商品了嘛?", "取消", "确定");
        ShouHuoDialog.setYesClicklistener(new QueRenUtilDialog.OnYesClickListener() {
            @Override
            public void onYes() {
                publicPresenter.confirmOrder(mActivity, id);
            }
        });
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
            protected void getView(ViewHolder holder, final ShiWuDinDanBean shiWuBean, int position) {
                holder.setText(R.id.tv_store_name, shiWuBean.getStore_name())
                        .setText(R.id.tv_type, shiWuBean.getOrder_status_desc())
                        .setText(R.id.tv_num, shiWuBean.getGoods_list().size() + "")
                        .setText(R.id.tv_jiage, shiWuBean.getTotal_amount());

                RecyclerView recyclerView = holder.getView(R.id.rv_shangping);
                recyclerView.setFocusable(false);
                recyclerView.setNestedScrollingEnabled(false);
                recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
                CommonAdapter<QueRenDinDanBean.CartListBean.GoodsListBean> adapter = new CommonAdapter<QueRenDinDanBean.CartListBean.GoodsListBean>(mActivity, R.layout.include_shangping_itim, shiWuBean.getGoods_list()) {
                    @Override
                    protected void convert(ViewHolder holder, QueRenDinDanBean.CartListBean.GoodsListBean goodsListBean, int position) {
                        ImageView img_head_pic = holder.getView(R.id.img_head_pic);
                        ImageLoader.with(Api.url + goodsListBean.getOriginal_img(), img_head_pic);
                        holder.setText(R.id.tv_title, goodsListBean.getGoods_name());
                        holder.setText(R.id.tv_guige, goodsListBean.getSpec_key_name());
                        holder.setText(R.id.tv_jiage, "￥" + goodsListBean.getMember_goods_price());
                        holder.setText(R.id.tv_shuliang, "x" + goodsListBean.getGoods_num());
                        holder.setOnClickListener(R.id.layout_onClick, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                StartUtil.toXiangQin(mActivity, activity.type, shiWuBean.getOrder_id(), activity.getfenlei_Type(), activity.store_id);
                            }
                        });
                    }
                };
                recyclerView.setAdapter(adapter);

                TextView tv_hei_one, tv_hei_two, tv_hong;
                tv_hei_one = holder.getView(R.id.tv_hei_one);
                tv_hei_two = holder.getView(R.id.tv_hei_two);
                tv_hong = holder.getView(R.id.tv_hong);

                tv_hei_one.setOnClickListener(new OnTextClick(tv_hei_one, shiWuBean));
                tv_hei_two.setOnClickListener(new OnTextClick(tv_hei_two, shiWuBean));
                tv_hong.setOnClickListener(new OnTextClick(tv_hong, shiWuBean));

                if (activity.store_id.equals("")) {
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
                            if (activity.getfenlei_Type().equals("RETURNED")) {
                                tv_hong.setVisibility(View.GONE);
                            }
                            break;
                        case "WAITRECEIVE":
                            //1:实物,2:定制,3:团购
                            //待收货->待使用
                            if (activity.type.equals("3")) {
                                tv_hei_one.setVisibility(View.VISIBLE);
                                tv_hei_two.setVisibility(View.GONE);
                                tv_hong.setVisibility(View.VISIBLE);
                                tv_hei_one.setText("申请退款");
//                            tv_hei_two.setText("查看物流");
                                tv_hong.setText("查看劵码");
                            } else {
                                tv_hei_one.setVisibility(View.VISIBLE);
                                tv_hei_two.setVisibility(View.VISIBLE);
                                tv_hong.setVisibility(View.VISIBLE);
                                tv_hei_one.setText("申请退款");
                                tv_hei_two.setText("查看物流");
                                tv_hong.setText("确认收货");
                                if (shiWuBean.getIs_pick().equals("1")) {
                                    //自取
                                    tv_hei_two.setVisibility(View.GONE);
                                }
                            }

                            break;
                        case "WAITCCOMMENT":
                            //待评价
                            if (activity.type.equals("3")) {
                                tv_hei_one.setVisibility(View.GONE);
                                tv_hei_two.setVisibility(View.VISIBLE);
                                tv_hong.setVisibility(View.VISIBLE);
                                tv_hei_two.setText("申请退款");
                                tv_hong.setText("评价晒单");
                            } else {
                                tv_hei_one.setVisibility(View.GONE);
                                tv_hei_two.setVisibility(View.GONE);
                                tv_hong.setVisibility(View.VISIBLE);
//                            tv_hei_two.setText("申请退款");
                                tv_hong.setText("评价晒单");
                            }
                            break;
                        case "COMMENTED":
                            //已评价==已完成
                            tv_hei_one.setVisibility(View.GONE);
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
                            tv_hei_two.setVisibility(View.GONE);
                            tv_hong.setVisibility(View.GONE);
                            tv_hei_two.setText("删除订单");
                            break;
                    }
                } else {
                    tv_hei_one.setVisibility(View.GONE);
                    tv_hei_two.setVisibility(View.VISIBLE);
                    tv_hong.setVisibility(View.GONE);
                    tv_hei_two.setText("查看订单");
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
        if (activity.store_id.equals("")) {
            baseXiaLaRvPresenter.p++;
            return OkHttpUtils
                    .get()
                    .tag("base")
                    .url(Api.orderList)
                    .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                    .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                    .addParams("type", activity.getfenlei_Type())
                    .addParams("goods_type", activity.type)
                    .addParams("p", baseXiaLaRvPresenter.p + "")
                    .build();
        } else {
            baseXiaLaRvPresenter.p++;
            return OkHttpUtils
                    .get()
                    .tag("base")
                    .url(Api.sellerorderList)
                    .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                    .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                    .addParams("type", activity.getfenlei_Type())
                    .addParams("goods_type", activity.type)
                    .addParams("p", baseXiaLaRvPresenter.p + "")
                    .addParams("store_id", activity.store_id)
                    .build();
        }

    }

    class OnTextClick implements View.OnClickListener {

        private TextView textView;
        private ShiWuDinDanBean bean;

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
                    id = bean.getOrder_id();
                    QuXiaoDialog.show();
                    //调接口
                    break;
                case "确认收货":
                    id = bean.getOrder_id();
                    ShouHuoDialog.show();
                    //调接口
                    break;
                case "删除订单":
                    id = bean.getOrder_id();
                    shanchuDialog.show();
                    //调接口
                    break;
                case "去支付":
                    StartUtil.toZhiFu(mActivity, bean.getOrder_id(), bean.getOrder_amount(), activity.type);
                    break;
                case "查看物流":
                    StartUtil.toH5Web(mActivity, Api.WuLiuH5 + bean.getOrder_id(), "查看物流");
                    break;
                case "评价晒单":
                case "查看订单":
                case "申请退款":
                default:
                    //跳转详情页
//                    intent = new Intent(mActivity, ShiWuDianDanXiangQingActivity.class);
//                    intent.putExtra("id", bean.getOrder_id());
//                    intent.putExtra("type", activity.getfenlei_Type());
//                    startActivity(intent);
                    StartUtil.toXiangQin(mActivity, activity.type, bean.getOrder_id(), activity.getfenlei_Type(), activity.store_id);
                    break;
            }
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
