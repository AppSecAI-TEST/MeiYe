package com.duma.liudong.meiye.view.start.main;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseFragment;
import com.duma.liudong.meiye.base.BaseRvAdapter;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.model.GouWuCheBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.StartUtil;
import com.google.gson.Gson;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.request.RequestCall;

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

    private BaseRvAdapter<GouWuCheBean.CartListBean> adapter;
    private GouWuCheBean gouWuCheBean;
    private List<String> cartList;
    private List<String> goodsNumList;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_shopping_cart;
    }

    @Override
    protected void initData() {
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

    private void initAdapter() {
        adapter = new BaseRvAdapter<GouWuCheBean.CartListBean>(mActivity, R.layout.rv_gouwuche, rvShangping) {
            @Override
            protected void getView(ViewHolder holder, final GouWuCheBean.CartListBean cartListBean, int position) {
                holder.setText(R.id.tv_store_name, cartListBean.getMark().getStore_name());
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
//                checkBox.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        clearList();
//                        for (int i = 0; i < cartListBean.getGoods_list().size(); i++) {
//                            cartList.add(cartListBean.getGoods_list().get(i).getId());
//                            goodsNumList.add(cartListBean.getGoods_list().get(i).getGoods_num());
//                        }
//                        if (checkBox.isChecked()) {
//                            ShuaXin("1");
//                        } else {
//                            ShuaXin("0");
//                        }
//                    }
//                });

                CommonAdapter<GouWuCheBean.CartListBean.GoodsListBean> commonAdapter = new CommonAdapter<GouWuCheBean.CartListBean.GoodsListBean>
                        (mActivity, R.layout.rv_gouwuche_shangping, cartListBean.getGoods_list()) {
                    @Override
                    protected void convert(ViewHolder holder, final GouWuCheBean.CartListBean.GoodsListBean goodsListBean, int position) {
                        holder.setText(R.id.tv_title, goodsListBean.getGoods_name());
                        holder.setText(R.id.tv_guige, goodsListBean.getSpec_key_name());
                        holder.setText(R.id.tv_jiage, "￥" + goodsListBean.getMember_goods_price());
                        holder.setText(R.id.tv_shuliang, "x" + goodsListBean.getGoods_num());
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
//                        checkBox.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                clearList();
//                                cartList.add(goodsListBean.getId());
//                                goodsNumList.add(goodsListBean.getGoods_num());
//                                if (checkBox.isChecked()) {
//                                    ShuaXin("1");
//                                } else {
//                                    ShuaXin("0");
//                                }
//                            }
//                        });
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
                tvHeji.setText("￥" + gouWuCheBean.getTotal_price().getTotal_fee());
                tvYijian.setText("已减" + gouWuCheBean.getTotal_price().getCut_fee());
                int c = 0;
                for (int i = 0; i < gouWuCheBean.getCart_list().size(); i++) {
                    for (int j = 0; j < gouWuCheBean.getCart_list().get(i).getGoods_list().size(); j++) {
                        if (gouWuCheBean.getCart_list().get(i).getGoods_list().get(j).getSelected().equals("0")) {
                            c++;
                            break;
                        }
                    }
                }
                if (c == 0) {
                    cbQuanXuan.setChecked(true);
                } else {
                    cbQuanXuan.setChecked(false);
                }
            }

            @Nullable
            @Override
            protected List<GouWuCheBean.CartListBean> getTs(String result) {
                gouWuCheBean = new Gson().fromJson(result, GouWuCheBean.class);
                return gouWuCheBean.getCart_list();
            }
        };
    }

    private void clearList() {
        cartList.clear();
        goodsNumList.clear();
    }

    @Override
    protected void onLazyLoad() {
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

    @Override
    public void onRefresh() {
        onLazyLoad();
    }
}
