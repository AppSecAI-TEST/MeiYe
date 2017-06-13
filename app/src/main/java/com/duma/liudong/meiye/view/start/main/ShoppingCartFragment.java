package com.duma.liudong.meiye.view.start.main;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseFragment;
import com.duma.liudong.meiye.base.BaseRvAdapter;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.model.GouWuCheBean;
import com.duma.liudong.meiye.model.GouWuCheTypeBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.utils.Ts;
import com.duma.liudong.meiye.view.dialog.ShoppingCartDialog;
import com.google.gson.Gson;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.request.RequestCall;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/3/14.
 */

public class ShoppingCartFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.layout_kong)
    LinearLayout layoutKong;
    @BindView(R.id.rv_shangping)
    RecyclerView rvShangping;
    @BindView(R.id.cb_quanXuan)
    CheckBox cbQuanXuan;
    @BindView(R.id.tv_heji)
    TextView tvHeji;
    @BindView(R.id.tv_yijian)
    TextView tvYijian;
    @BindView(R.id.layout_jisuan)
    LinearLayout layoutJisuan;
    @BindView(R.id.tv_btn)
    TextView tvBtn;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;
    @BindView(R.id.layout_bar)
    LinearLayout layoutBar;

    private BaseRvAdapter<GouWuCheBean.CartListBean> adapter;
    private GouWuCheBean gouWuCheBean;
    private List<String> cartList;
    private List<String> goodsNumList;

    private int type = Constants.jiesuan;
    private MainActivity activity;
    private ShoppingCartDialog dialog;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_shopping_cart;
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        activity = (MainActivity) mActivity;
        dialog = new ShoppingCartDialog(mActivity);
        cartList = new ArrayList<>();
        goodsNumList = new ArrayList<>();
        StartUtil.setSw(swLoading, this);
        rvShangping.setLayoutManager(new LinearLayoutManager(mActivity));
        initAdapter();
        adapter.setKongView(layoutKong);
        cbQuanXuan.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        clearList();
                        for (int i = 0; i < gouWuCheBean.getCart_list().size(); i++) {
                            for (int j = 0; j < gouWuCheBean.getCart_list().get(i).getGoods_list().size(); j++) {
                                cartList.add(gouWuCheBean.getCart_list().get(i).getGoods_list().get(j).getId());
                                goodsNumList.add(gouWuCheBean.getCart_list().get(i).getGoods_list().get(j).getGoods_num());
                            }
                        }
                        if (!cbQuanXuan.isChecked()) {
                            ShuaXin("1");
                        } else {
                            ShuaXin("0");
                        }
                        break;
                }
                return true;
            }
        });
        cbQuanXuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearList();
                for (int i = 0; i < gouWuCheBean.getCart_list().size(); i++) {
                    for (int j = 0; j < gouWuCheBean.getCart_list().get(i).getGoods_list().size(); j++) {
                        cartList.add(gouWuCheBean.getCart_list().get(i).getGoods_list().get(j).getId());
                        goodsNumList.add(gouWuCheBean.getCart_list().get(i).getGoods_list().get(j).getGoods_num());
                    }
                }
                if (cbQuanXuan.isChecked()) {
                    ShuaXin("1");
                } else {
                    ShuaXin("0");
                }
            }
        });
    }


    @Subscribe
    public void setOnBianji(GouWuCheTypeBean bean) {
        type = bean.getType();
        refreshType();
        adapter.commonAdapter.notifyDataSetChanged();
    }

    private void refreshType() {
        if (type == Constants.jiesuan) {
            if (gouWuCheBean != null) {
                tvBtn.setText("结算 (" + gouWuCheBean.getTotal_price().getNum() + ")");
            } else {
                tvBtn.setText("结算 (" + 0 + ")");
            }
            layoutJisuan.setVisibility(View.VISIBLE);
        } else {
            tvBtn.setText("删除");
            layoutJisuan.setVisibility(View.INVISIBLE);
        }
    }

    private void clearList() {
        cartList.clear();
        goodsNumList.clear();
    }

    @Override
    protected void onLazyLoad() {
        layoutBar.setVisibility(View.GONE);
        clearList();
        ShuaXin("");
    }

    private void ShuaXin(String cart_select) {
        adapter.QueryHttp(getBuild(cart_select));
    }

    @OnClick({R.id.cb_quanXuan, R.id.tv_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cb_quanXuan:
                break;
            case R.id.tv_btn:
                if (tvBtn.getText().toString().equals("删除")) {
                    //删除
                    delectHttp();
                } else {
                    dialog.show();
                }
                break;
        }
    }


    //从新获取购物车列表
    public RequestCall getBuild(String cart_select) {
        GetBuilder getBuilder = OkHttpUtils
                .get()
                .tag("base")
                .url(Api.cartList)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token));
        for (int i = 0; i < cartList.size(); i++) {
            getBuilder.addParams("cart_select[" + cartList.get(i) + "]", cart_select);
            getBuilder.addParams("goods_num[" + cartList.get(i) + "]", goodsNumList.get(i));
        }

        return getBuilder
                .build();
    }

    //购物车列表适配器
    private void initAdapter() {
        adapter = new BaseRvAdapter<GouWuCheBean.CartListBean>(mActivity, R.layout.rv_gouwuche, rvShangping) {
            @Override
            protected void getView(ViewHolder holder, final GouWuCheBean.CartListBean cartListBean, int position) {
                holder.setText(R.id.tv_store_name, cartListBean.getMark().getStore_name());
                holder.setText(R.id.tv_postage, cartListBean.getMark().getPostage());
                holder.setText(R.id.tv_coupon, cartListBean.getMark().getCoupon());
                LinearLayout layout_manjian = holder.getView(R.id.layout_manjian);
                LinearLayout layout_baoyou = holder.getView(R.id.layout_baoyou);
                layout_manjian.setVisibility(View.VISIBLE);
                layout_baoyou.setVisibility(View.VISIBLE);
                if (cartListBean.getMark().getPostage() == null || cartListBean.getMark().getPostage().equals("null")) {
                    layout_baoyou.setVisibility(View.GONE);
                }
                if (cartListBean.getMark().getCoupon() == null || cartListBean.getMark().getCoupon().equals("null")) {
                    layout_manjian.setVisibility(View.GONE);
                }
                RecyclerView recyclerView = holder.getView(R.id.rv_shangping);
                recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
                recyclerView.setFocusable(false);
                recyclerView.setNestedScrollingEnabled(false);
                final CheckBox checkBox = holder.getView(R.id.cb_dianpu);
                //判断当前店铺是否全选
                int c = 0;
                for (int i = 0; i < cartListBean.getGoods_list().size(); i++) {
                    if (cartListBean.getGoods_list().get(i).getSelected().equals("0")) {
                        c++;
                    }
                }
                if (c == 0) {
                    checkBox.setChecked(true);
                } else {
                    checkBox.setChecked(false);
                }
                checkBox.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_UP:
                                clearList();
                                for (int i = 0; i < cartListBean.getGoods_list().size(); i++) {
                                    cartList.add(cartListBean.getGoods_list().get(i).getId());
                                    goodsNumList.add(cartListBean.getGoods_list().get(i).getGoods_num());
                                }
                                if (!checkBox.isChecked()) {
                                    ShuaXin("1");
                                } else {
                                    ShuaXin("0");
                                }
                                break;
                        }
                        return true;
                    }
                });

                CommonAdapter<GouWuCheBean.CartListBean.GoodsListBean> commonAdapter = new CommonAdapter<GouWuCheBean.CartListBean.GoodsListBean>
                        (mActivity, R.layout.rv_gouwuche_shangping, cartListBean.getGoods_list()) {
                    @Override
                    protected void convert(ViewHolder holder, final GouWuCheBean.CartListBean.GoodsListBean goodsListBean, int position) {
                        TextView tv_jian = holder.getView(R.id.tv_jian);
                        TextView tv_jia = holder.getView(R.id.tv_jia);
                        LinearLayout layout_guige = holder.getView(R.id.layout_guige);
                        LinearLayout layout_jiesuan = holder.getView(R.id.layout_jiesuan);
                        LinearLayout layout_shangchu = holder.getView(R.id.layout_shangchu);
                        layout_jiesuan.setVisibility(View.GONE);
                        layout_shangchu.setVisibility(View.GONE);
                        if (type == Constants.jiesuan) {
                            layout_jiesuan.setVisibility(View.VISIBLE);
                        } else {
                            layout_shangchu.setVisibility(View.VISIBLE);
                        }
                        holder.setText(R.id.tv_title, goodsListBean.getGoods_name());
                        holder.setText(R.id.tv_guige, goodsListBean.getSpec_key_name());
                        holder.setText(R.id.tv_guige2, goodsListBean.getSpec_key_name());
                        holder.setText(R.id.tv_jiage, "￥" + goodsListBean.getMember_goods_price());
                        holder.setText(R.id.tv_shuliang, "x" + goodsListBean.getGoods_num());
                        holder.setText(R.id.tv_num, goodsListBean.getGoods_num());
                        ImageView imageView = holder.getView(R.id.img_head_pic);
                        ImageLoader.with(Api.url + goodsListBean.getOriginal_img(), imageView);
                        final CheckBox checkBox = holder.getView(R.id.cb_shangping);
                        if (goodsListBean.getSelected().equals("1")) {
                            checkBox.setChecked(true);
                        } else {
                            checkBox.setChecked(false);
                        }

                        checkBox.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                switch (event.getAction()) {
                                    case MotionEvent.ACTION_UP:
                                        clearList();
                                        cartList.add(goodsListBean.getId());
                                        goodsNumList.add(goodsListBean.getGoods_num());
                                        if (!checkBox.isChecked()) {
                                            ShuaXin("1");
                                        } else {
                                            ShuaXin("0");
                                        }
                                        break;
                                }
                                return true;
                            }
                        });

                        View.OnClickListener listener = new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int num;
                                switch (v.getId()) {
                                    case R.id.tv_jian:
                                        clearList();
                                        cartList.add(goodsListBean.getId());
                                        num = Integer.parseInt(goodsListBean.getGoods_num()) - 1;
                                        goodsNumList.add(num + "");
                                        if (checkBox.isChecked()) {
                                            ShuaXin("1");
                                        } else {
                                            ShuaXin("0");
                                        }
                                        break;
                                    case R.id.tv_jia:
                                        clearList();
                                        cartList.add(goodsListBean.getId());
                                        num = Integer.parseInt(goodsListBean.getGoods_num()) + 1;
                                        goodsNumList.add(num + "");
                                        if (checkBox.isChecked()) {
                                            ShuaXin("1");
                                        } else {
                                            ShuaXin("0");
                                        }
                                        break;
                                    case R.id.layout_guige:
                                        break;
                                }
                            }
                        };
                        tv_jian.setOnClickListener(listener);
                        tv_jia.setOnClickListener(listener);
                        layout_guige.setOnClickListener(listener);
                    }
                };
                recyclerView.setAdapter(commonAdapter);

            }

            @Override
            protected void hide_loading() {
                swLoading.setRefreshing(false);
            }

            @Override
            protected void show_loading() {
                swLoading.setRefreshing(true);
            }

            @Override
            protected void HttpSuccess() {
                layoutBar.setVisibility(View.VISIBLE);
                tvHeji.setText("￥" + gouWuCheBean.getTotal_price().getTotal_fee());
                tvYijian.setText("已减" + gouWuCheBean.getTotal_price().getCut_fee());
                refreshType();
                int c = 0;
                try {
                    for (int i = 0; i < gouWuCheBean.getCart_list().size(); i++) {
                        for (int j = 0; j < gouWuCheBean.getCart_list().get(i).getGoods_list().size(); j++) {
                            if (gouWuCheBean.getCart_list().get(i).getGoods_list().get(j).getSelected().equals("0")) {
                                c++;
                                break;
                            }
                        }
                    }
                } catch (Exception e) {
                    Ts.setText("服务器错误,请联系管理员!");
                }
                if (c == 0) {
                    cbQuanXuan.setChecked(true);
                } else {
                    cbQuanXuan.setChecked(false);
                }

                dialog.setList(adapter.mList);
            }

            @Nullable
            @Override
            protected List<GouWuCheBean.CartListBean> getTs(String result) {
                gouWuCheBean = new Gson().fromJson(result, GouWuCheBean.class);
                return gouWuCheBean.getCart_list();
            }
        };
    }

    @Override
    public void onRefresh() {
        onLazyLoad();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    //删除购物车接口
    private void delectHttp() {
        OkHttpUtils.getInstance().cancelTag("this");
        OkHttpUtils
                .post()
                .tag("this")
                .url(Api.delcart)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        onLazyLoad();
                    }
                });
    }

}
