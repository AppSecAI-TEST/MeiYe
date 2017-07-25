package com.duma.liudong.meiye.view.me.dinDan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.model.EvenDinDan;
import com.duma.liudong.meiye.model.FuWuBean;
import com.duma.liudong.meiye.model.PingJiaBean;
import com.duma.liudong.meiye.model.QueRenDinDanBean;
import com.duma.liudong.meiye.model.TuiKuanBean;
import com.duma.liudong.meiye.presenter.PublicPresenter;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.DialogUtil;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.utils.Ts;
import com.duma.liudong.meiye.view.dialog.QueRenUtilDialog;
import com.duma.liudong.meiye.view.dialog.ServiceDialog;
import com.google.gson.Gson;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.liudong.meiye.R.id.tv_hong;

/**
 * Created by liudong on 17/4/21.
 */

public class FuWuXiangQinActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_other)
    ImageView imgOther;
    @BindView(R.id.layout_other)
    LinearLayout layoutOther;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_store_name)
    TextView tvStoreName;
    @BindView(R.id.rv_shangping)
    RecyclerView rvShangping;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.tv_shangPingZongJiaGe)
    TextView tvShangPingZongJiaGe;
    @BindView(R.id.tv_youHuiJuan)
    TextView tvYouHuiJuan;
    @BindView(R.id.tv_yue)
    TextView tvYue;
    @BindView(R.id.tv_jifen)
    TextView tvJifen;
    @BindView(R.id.tv_shifu)
    TextView tvShifu;
    @BindView(R.id.tv_shijian)
    TextView tvShijian;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;
    @BindView(R.id.tv_hei)
    TextView tvHei;
    @BindView(R.id.tv_hong)
    TextView tvHong;
    @BindView(R.id.layout_btn)
    LinearLayout layoutBtn;
    @BindView(R.id.view_henxian)
    View viewHenxian;
    @BindView(R.id.rv_juanma)
    RecyclerView rvJuanma;
    @BindView(R.id.tv_zhifu)
    TextView tvZhifu;

    private FuWuBean bean;

    private CommonAdapter<QueRenDinDanBean.CartListBean.GoodsListBean> adapter;
    private List<QueRenDinDanBean.CartListBean.GoodsListBean> mlist;
    private String id;
    private QueRenUtilDialog QuXiaoDialog, shanchuDialog, ShouHuoDialog;
    private PublicPresenter publicPresenter;
    private String fenlei_type;
    private boolean isOne = false;
    private CommonAdapter<FuWuBean.SupportBean> JuanMaAdapter;
    private List<FuWuBean.SupportBean> mJuanMaList;
    private String store_id;//判断是不是商家端的订单详情

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_fuwuxiangqin);
    }

    @Override
    protected void initData() {
        publicPresenter = new PublicPresenter();
        publicPresenter.setGetSuccessListener(new PublicPresenter.GetSuccessListener() {
            @Override
            public void GetCodeSuccess() {
                onRefresh();
            }
        });
        initDialog();
        tvTitle.setText("订单详情");
        id = getIntent().getStringExtra("id");
        store_id = getIntent().getStringExtra("store_id");
        fenlei_type = getIntent().getStringExtra("fenlei_type");
        StartUtil.setSw(swLoading, this);
        mlist = new ArrayList<>();
        mJuanMaList = new ArrayList<>();
        rvShangping.setLayoutManager(new LinearLayoutManager(mActivity));
        rvShangping.setFocusable(false);
        rvShangping.setNestedScrollingEnabled(false);
        initAdapter();
        onRefresh();
    }

    private void initDialog() {
        QuXiaoDialog = new QueRenUtilDialog(mActivity, "", "是否取消订单", "取消", "确定");
        QuXiaoDialog.setYesClicklistener(new QueRenUtilDialog.OnYesClickListener() {
            @Override
            public void onYes() {
                isOne = true;
                publicPresenter.cancelOrder(mActivity, id);
            }
        });
        shanchuDialog = new QueRenUtilDialog(mActivity, "", "是否删除订单", "取消", "确定");
        shanchuDialog.setYesClicklistener(new QueRenUtilDialog.OnYesClickListener() {
            @Override
            public void onYes() {
                isOne = true;
                publicPresenter.delOrder(mActivity, id);
            }
        });
        ShouHuoDialog = new QueRenUtilDialog(mActivity, "", "确定收到商品了嘛?", "取消", "确定");
        ShouHuoDialog.setYesClicklistener(new QueRenUtilDialog.OnYesClickListener() {
            @Override
            public void onYes() {
                isOne = true;
                publicPresenter.confirmOrder(mActivity, id);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        onRefresh();
    }

    private void initRes() {
        tvZhifu.setText("-￥" + bean.getOrder_amount());
        tvJifen.setText("-￥" + bean.getIntegral_money());
        tvMobile.setText("购买手机号:" + bean.getMobile());
        tvType.setText(bean.getOrder_status_desc());
        tvYouHuiJuan.setText("-￥" + bean.getCoupon_price());
        tvYue.setText("-￥" + bean.getUser_money());
        tvShangPingZongJiaGe.setText("￥" + bean.getTotal_amount());
        tvShifu.setText(bean.getTotal_amount());
        tvShijian.setText(StartUtil.getShiJian(Long.parseLong(bean.getAdd_time())));
        tvCode.setText("订单编号:" + bean.getOrder_sn());
        tvStoreName.setText(bean.getStore_name());
        layoutBtn.setVisibility(View.VISIBLE);
        switch (bean.getOrder_status_code()) {
            case "WAITPAY":
                //代付款
                tvHei.setVisibility(View.VISIBLE);
                tvHong.setVisibility(View.VISIBLE);
                tvHei.setText("取消订单");
                tvHong.setText("去支付");
                break;
            case "WAITSEND":
                //待发货
                tvHei.setVisibility(View.GONE);
                tvHong.setVisibility(View.VISIBLE);
                tvHong.setText("提醒发货");
                break;
            case "WAITRECEIVE":
                //待收货
                tvHei.setVisibility(View.GONE);
                tvHong.setVisibility(View.VISIBLE);
                tvHei.setText("查看物流");
                tvHong.setText("确认收货");
                break;
            case "WAITCCOMMENT":
                //待评价
                layoutBtn.setVisibility(View.GONE);
                break;
            case "COMMENTED":
                //已评价==已完成
                tvHei.setVisibility(View.GONE);
                tvHong.setVisibility(View.GONE);
                tvHei.setText("删除订单");
                break;
            case "RETURNED":
                //已退货
                layoutBtn.setVisibility(View.GONE);
                break;
            default:
                //交易关闭
                tvHei.setVisibility(View.GONE);
                tvHong.setVisibility(View.GONE);
                tvHei.setText("删除订单");
                break;
        }
        mlist.clear();
        mlist.addAll(bean.getGoods_list());
        adapter.notifyDataSetChanged();
        mJuanMaList.clear();
        mJuanMaList.addAll(bean.getSupport());
        JuanMaAdapter.notifyDataSetChanged();
        if (mJuanMaList.size() == 0) {
            viewHenxian.setVisibility(View.GONE);
        } else {
            viewHenxian.setVisibility(View.VISIBLE);
        }
        if (!store_id.equals("")) {
            tvHei.setVisibility(View.GONE);
            tvHong.setVisibility(View.GONE);
        }
    }

    private void initAdapter() {
        adapter = new CommonAdapter<QueRenDinDanBean.CartListBean.GoodsListBean>(mActivity, R.layout.rv_dindanxiangqing, mlist) {
            @Override
            protected void convert(ViewHolder holder, final QueRenDinDanBean.CartListBean.GoodsListBean goodsListBean, int position) {
                ImageView img_head_pic = holder.getView(R.id.img_head_pic);
                ImageLoader.with(Api.url + goodsListBean.getOriginal_img(), img_head_pic);
                holder.setText(R.id.tv_title, goodsListBean.getGoods_name());
                holder.setText(R.id.tv_guige, goodsListBean.getSpec_key_name());
                holder.setText(R.id.tv_jiage, "￥" + goodsListBean.getMember_goods_price());
                holder.setText(R.id.tv_shuliang, "x" + goodsListBean.getGoods_num());
                LinearLayout layout_ziqu = holder.getView(R.id.layout_ziqu);
                if (bean.getIs_pick().equals("1")) {
                    //自取
                    layout_ziqu.setVisibility(View.VISIBLE);
                    holder.setText(R.id.tv_ziqu_name, goodsListBean.getPickup_address());
                } else {
                    layout_ziqu.setVisibility(View.GONE);
                }
                holder.setOnClickListener(R.id.layout_tel, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ServiceDialog dialog = new ServiceDialog(mActivity, goodsListBean.getPickup_phone());
                        dialog.Show();
                    }
                });
                TextView tv_shouhou, tv_pingjia;
                tv_shouhou = holder.getView(R.id.tv_shouhou);
                tv_pingjia = holder.getView(R.id.tv_pingjia);
                tv_shouhou.setVisibility(View.GONE);
                tv_pingjia.setVisibility(View.GONE);
                if (bean.getOrder_status_code().equals("WAITCCOMMENT")) {
                    tv_pingjia.setVisibility(View.VISIBLE);
                }
                if (bean.getOrder_status_code().equals("WAITSEND") || bean.getOrder_status_code().equals("WAITRECEIVE") || bean.getOrder_status_code().equals("WAITCCOMMENT")) {
                    tv_shouhou.setVisibility(View.VISIBLE);
                }
                switch (goodsListBean.getIs_send()) {
                    case "2":
                        //退款中
                        tv_shouhou.setText("退款中");
                        break;
                    case "3":
                        //退款完成
                        tv_shouhou.setText("退款完成");
                        break;
                    default:
                        tv_shouhou.setText("申请退款");
                }

                if (!store_id.equals("")) {
                    tv_shouhou.setVisibility(View.GONE);
                    tv_pingjia.setVisibility(View.GONE);
                }
                tv_pingjia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogUtil.show(mActivity);
                        OkHttpUtils
                                .get()
                                .tag(this)
                                .url(Api.isComment)
                                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                                .addParams("order_id", bean.getOrder_id())
                                .addParams("goods_id", goodsListBean.getGoods_id())
                                .build()
                                .execute(new MyStringCallback() {
                                    @Override
                                    public void onMySuccess(String result) {
                                        DialogUtil.hide();
                                        PingJiaBean pingJiaBean = new Gson().fromJson(result, PingJiaBean.class);
                                        if (pingJiaBean.getIs_comment().equals("0")) {
                                            DialogUtil.hide();
                                            Intent intent = new Intent(mActivity, PingJiaActivity.class);
                                            intent.putExtra("img", Api.url + goodsListBean.getOriginal_img());
                                            intent.putExtra("goods_id", goodsListBean.getGoods_id());
                                            intent.putExtra("order_id", bean.getOrder_id());
                                            startActivity(intent);
                                        } else {
                                            Ts.setText("您已经评价~");
                                        }
                                    }
                                });
                    }
                });
                tv_shouhou.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogUtil.show(mActivity);
                        OkHttpUtils
                                .post()
                                .tag(this)
                                .url(Api.isReturnGoods)
                                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                                .addParams("order_id", bean.getOrder_id())
                                .addParams("goods_id", goodsListBean.getGoods_id())
                                .addParams("spec_key", goodsListBean.getSpec_key())
                                .addParams("order_sn", bean.getOrder_sn())
                                .addParams("type", "3")
                                .build()
                                .execute(new MyStringCallback() {
                                    @Override
                                    public void onMySuccess(String result) {
                                        DialogUtil.hide();
                                        TuiKuanBean tuiKuanBean = new Gson().fromJson(result, TuiKuanBean.class);
                                        if (tuiKuanBean.getIs_return().equals("0")) {
                                            DialogUtil.hide();
                                            StartUtil.totuikuan(mActivity, bean.getOrder_id(), bean.getOrder_sn()
                                                    , goodsListBean.getGoods_id(), bean.getStore_name(), goodsListBean.getGoods_name()
                                                    , goodsListBean.getOriginal_img(), goodsListBean.getGoods_num(), goodsListBean.getMember_goods_price(), goodsListBean.getSpec_key());
                                        } else {
                                            StartUtil.totuikuanXiangQin(mActivity, bean.getOrder_id(), bean.getOrder_sn()
                                                    , goodsListBean.getGoods_id(), bean.getStore_name(), goodsListBean.getGoods_name()
                                                    , goodsListBean.getOriginal_img(), goodsListBean.getGoods_num(), goodsListBean.getMember_goods_price(), goodsListBean.getSpec_key());
                                        }

                                    }
                                });


                    }
                });

            }
        };
        rvShangping.setAdapter(adapter);

        rvJuanma.setLayoutManager(new LinearLayoutManager(mActivity));
        rvJuanma.setFocusable(false);
        rvJuanma.setNestedScrollingEnabled(false);
        JuanMaAdapter = new CommonAdapter<FuWuBean.SupportBean>(mActivity, R.layout.rv_juanma, mJuanMaList) {
            @Override
            protected void convert(ViewHolder holder, FuWuBean.SupportBean supportBean, int position) {
                TextView tv_name, tv_card_num;
                tv_name = holder.getView(R.id.tv_name);
                tv_card_num = holder.getView(R.id.tv_card_num);
                tv_card_num.setText(supportBean.getCard_num());
                if (supportBean.getIs_use().equals("0")) {
                    tv_name.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.main_red));
                    tv_card_num.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.main_red));
                } else {
                    tv_name.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.texthei));
                    tv_card_num.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.texthei));
                }
            }
        };
        rvJuanma.setAdapter(JuanMaAdapter);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                StartUtil.toShangPingWeb(mActivity, Api.H5Url() + mlist.get(position).getGoods_id());
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    @OnClick({R.id.layout_back, R.id.tv_hei, R.id.tv_hong})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.tv_hei:
                switch (tvHei.getText().toString()) {
                    case "取消订单":
                        QuXiaoDialog.show();
                        break;
                    case "查看物流":
                        break;
                    case "删除订单":
                        shanchuDialog.show();
                        break;
                }
                break;
            case tv_hong:
                switch (tvHong.getText().toString()) {
                    case "去支付":
                        StartUtil.toZhiFu(mActivity, bean.getOrder_id(), bean.getOrder_amount(), "1");
                        break;
                    case "提醒发货":
                        Ts.setText("已提醒卖家~请耐心等待!");
                        break;
                    case "确认收货":
                        ShouHuoDialog.show();
                        break;
                }
                break;
        }
    }

    @Override
    public void onRefresh() {
        swLoading.setRefreshing(true);
        OkHttpUtils.getInstance().cancelTag(this);
        RequestCall build = getBuild();
        build.execute(new MyStringCallback() {
            @Override
            public void onMySuccess(String result) {
                swLoading.setRefreshing(false);
                bean = new Gson().fromJson(result, FuWuBean.class);
                initRes();
            }

            @Override
            protected void onError(String result) {
                super.onError(result);
                if (result.equals("-1")) {
                    isOne = true;
                    finish();
                }
            }
        });
    }

    private RequestCall getBuild() {
        if (!store_id.equals("")) {
            return OkHttpUtils
                    .get()
                    .url(Api.sellerorderInfo)
                    .tag(this)
                    .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                    .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                    .addParams("order_id", id)
                    .addParams("order_type", "1")
                    .addParams("store_id", store_id)
                    .build();
        } else {
            return OkHttpUtils
                    .get()
                    .url(Api.orderInfo)
                    .tag(this)
                    .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                    .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                    .addParams("order_id", id)
                    .addParams("order_type", "1")
                    .build();
        }

    }

    @Override
    public void finish() {
        super.finish();
        if (isOne)
            EventBus.getDefault().post(new EvenDinDan(fenlei_type));
    }
}
