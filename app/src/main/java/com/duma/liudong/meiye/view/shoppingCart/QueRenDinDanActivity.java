package com.duma.liudong.meiye.view.shoppingCart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.model.DiZhiBean;
import com.duma.liudong.meiye.model.QueRenDinDanBean;
import com.duma.liudong.meiye.model.YouHuiJuanBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.DialogUtil;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.Ts;
import com.duma.liudong.meiye.view.dialog.ServiceDialog;
import com.duma.liudong.meiye.view.me.ShouHuoDiZhiActivity;
import com.google.gson.Gson;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/10.
 */

public class QueRenDinDanActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_other)
    ImageView imgOther;
    @BindView(R.id.layout_other)
    LinearLayout layoutOther;
    @BindView(R.id.layout_kuaiDiKong)
    LinearLayout layoutKuaiDiKong;
    @BindView(R.id.tv_kuanDiType)
    TextView tvKuanDiType;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_dianhua)
    TextView tvDianhua;
    @BindView(R.id.tv_dizhi)
    TextView tvDizhi;
    @BindView(R.id.layout_kuaiDi)
    LinearLayout layoutKuaiDi;
    @BindView(R.id.layout_xuanZeKuaiDi)
    LinearLayout layoutXuanZeKuaiDi;
    @BindView(R.id.tv_store_name)
    TextView tvStoreName;
    @BindView(R.id.rv_shangping)
    RecyclerView rvShangping;
    @BindView(R.id.layout_kefu)
    LinearLayout layoutKefu;
    @BindView(R.id.edit_liuyan)
    EditText editLiuyan;
    @BindView(R.id.tv_youhuijuan)
    TextView tvYouhuijuan;
    @BindView(R.id.layout_youHuiJuan)
    LinearLayout layoutYouHuiJuan;
    @BindView(R.id.tv_hongbao)
    TextView tvHongbao;
    @BindView(R.id.layout_hongbao)
    LinearLayout layoutHongbao;
    @BindView(R.id.tv_jifen)
    TextView tvJifen;
    @BindView(R.id.swith_jifen)
    Switch swithJifen;
    @BindView(R.id.tv_yue)
    TextView tvYue;
    @BindView(R.id.swith_yue)
    Switch swithYue;
    @BindView(R.id.tv_zonge)
    TextView tvZonge;
    @BindView(R.id.tv_yunfei)
    TextView tvYunfei;
    @BindView(R.id.tv_shifu)
    TextView tvShifu;
    @BindView(R.id.tv_shijian)
    TextView tvShijian;
    @BindView(R.id.tv_zongji)
    TextView tvZongji;
    @BindView(R.id.tv_tijiaodindan)
    TextView tvTijiaodindan;
    @BindView(R.id.swith_ziqu)
    Switch swithZiqu;
    @BindView(R.id.layout_peisong)
    LinearLayout layoutPeisong;

    private String store_id;
    private boolean isOne = false;
    private QueRenDinDanBean bean;
    private int isJifen = 0;//是否使用积分
    private int isYue = 0;//是否使用余额
    private int isZiQu = 0;//是否使用自取
    private CommonAdapter<QueRenDinDanBean.CartListBean.GoodsListBean> adapter;
    private List<QueRenDinDanBean.CartListBean.GoodsListBean> mlist;

    private String addresId = "";//当前的addresd

    private YouHuiJuanBean youHuiJuanBean;//计算后优惠券
    private YouHuiJuanBean youHuiJuanBean_now;//没进行请求的优惠券

    @Override

    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_querendindan);
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        tvTitle.setText("确认订单");
        editLiuyan.setFocusable(false);
        store_id = getIntent().getStringExtra("store_id");
        mlist = new ArrayList<>();
        rvShangping.setLayoutManager(new LinearLayoutManager(mActivity));
        rvShangping.setFocusable(false);
        rvShangping.setNestedScrollingEnabled(false);
        initAdApter();
        DinDanHttp();

        swithYue.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        isYue = initInt(isYue);
                        DinDanHttp();
                        break;
                }
                return true;
            }
        });
        swithJifen.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        isJifen = initInt(isJifen);
                        DinDanHttp();
                        break;
                }
                return true;
            }
        });
        swithZiqu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        isZiQu = initInt(isZiQu);
                        DinDanHttp();
                        break;
                }
                return true;
            }
        });
    }

    @Subscribe
    public void diZhiListener(DiZhiBean diZhiBean) {
        if (diZhiBean.getAddress_id() == null) {
            //空
            addresId = "";
            layoutKuaiDiKong.setVisibility(View.VISIBLE);
            layoutKuaiDi.setVisibility(View.GONE);
        } else {
            initDiZhi(diZhiBean);
        }
    }

    @Subscribe
    public void youHuiJuanListener(YouHuiJuanBean youHuiJuanBean) {
        this.youHuiJuanBean_now = youHuiJuanBean;
        DinDanHttp();
    }

    private void initDiZhi(DiZhiBean diZhiBean) {
        addresId = diZhiBean.getAddress_id();
        tvName.setText(diZhiBean.getConsignee());
        tvDianhua.setText(diZhiBean.getMobile());
        tvDizhi.setText(diZhiBean.getProvince() + diZhiBean.getCity() + diZhiBean.getDistrict() + diZhiBean.getAddress());
    }

    private int initInt(int i) {
        if (i == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    private void initAdApter() {
        adapter = new CommonAdapter<QueRenDinDanBean.CartListBean.GoodsListBean>(mActivity, R.layout.rv_querendindan, mlist) {
            @Override
            protected void convert(ViewHolder holder, final QueRenDinDanBean.CartListBean.GoodsListBean goodsListBean, int position) {
                ImageView img_head_pic = holder.getView(R.id.img_head_pic);
                ImageLoader.with(Api.url + goodsListBean.getOriginal_img(), img_head_pic);
                holder.setText(R.id.tv_title, goodsListBean.getGoods_name());
                holder.setText(R.id.tv_guige, goodsListBean.getSpec_key_name());
                holder.setText(R.id.tv_jiage, "￥" + goodsListBean.getMember_goods_price());
                holder.setText(R.id.tv_shuliang, "x" + goodsListBean.getGoods_num());
                LinearLayout layout_ziqu = holder.getView(R.id.layout_ziqu);
                if (swithZiqu.isChecked()) {
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
            }
        };
        rvShangping.setAdapter(adapter);
    }

    private void DinDanHttp() {
        DialogUtil.show(mActivity, false);
        OkHttpUtils.getInstance().cancelTag(this);
        OkHttpUtils
                .get()
                .url(Api.makeSure)
                .tag(this)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .addParams("store_id", store_id)
                .addParams("use_points", isJifen + "")
                .addParams("is_pick", isZiQu + "")
                .addParams("use_money", isYue + "")
                .addParams("coupon_id", getId() + "")
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        isOne = true;
                        DialogUtil.hide();
                        bean = new Gson().fromJson(result, QueRenDinDanBean.class);
                        youHuiJuanBean = youHuiJuanBean_now;
                        initRes();
                    }

                    @Override
                    protected void onError(String result) {
                        super.onError(result);
                        if (!isOne) {
                            finish();
                        }
                        if (swithJifen.isChecked()) {
                            isJifen = 1;
                        } else {
                            isJifen = 0;
                        }
                        if (swithYue.isChecked()) {
                            isYue = 1;
                        } else {
                            isYue = 0;
                        }
                        if (swithZiqu.isChecked()) {
                            isZiQu = 1;
                        } else {
                            isZiQu = 0;
                        }

                        if (youHuiJuanBean_now != null) {
                            youHuiJuanBean_now = youHuiJuanBean;
                        }
                    }
                });
    }

    private String getId() {
        if (youHuiJuanBean_now == null) {
            return "";
        }
        return youHuiJuanBean_now.getId();
    }

    private void initRes() {
        tvZongji.setText(bean.getTotal_price().getTotal_fee() + "");
        tvShifu.setText(bean.getTotal_price().getTotal_fee() + "");
        tvJifen.setText(bean.getCart_list().get(0).getMark().getReward_points());
        tvYue.setText(bean.getCart_list().get(0).getMark().getUser_money());
        tvStoreName.setText(bean.getCart_list().get(0).getMark().getStore_name());
        tvZonge.setText("￥" + bean.getCart_list().get(0).getMark().getGoods_total());
        layoutKuaiDiKong.setVisibility(View.GONE);
        layoutKuaiDi.setVisibility(View.VISIBLE);
        swithJifen.setChecked(getRes(isJifen));
        swithYue.setChecked(getRes(isYue));
        swithZiqu.setChecked(getRes(isZiQu));
        if (bean.getAddress() == null) {
            layoutKuaiDiKong.setVisibility(View.VISIBLE);
            layoutKuaiDi.setVisibility(View.GONE);
        } else {
            initDiZhi(bean.getAddress());
        }
        if (youHuiJuanBean != null) {
            tvYouhuijuan.setText("满" + youHuiJuanBean.getCondition() + "减" + youHuiJuanBean.getMoney() + "元");
        } else {
            tvYouhuijuan.setText("不使用优惠券");
        }
        if (swithZiqu.isChecked()) {
            layoutPeisong.setVisibility(View.GONE);
        } else {
            layoutPeisong.setVisibility(View.VISIBLE);
        }
        mlist.clear();
        mlist.addAll(bean.getCart_list().get(0).getGoods_list());
        adapter.notifyDataSetChanged();
    }

    public boolean getRes(int i) {
        if (i == 0) {
            return false;
        } else {
            return true;
        }
    }


    @OnClick({R.id.layout_back, R.id.layout_xuanZeKuaiDi, R.id.layout_kefu, R.id.layout_youHuiJuan, R.id.layout_hongbao, R.id.tv_tijiaodindan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.layout_xuanZeKuaiDi:
                startActivity(new Intent(mActivity, ShouHuoDiZhiActivity.class));
                break;
            case R.id.layout_kefu:
                ServiceDialog dialog = new ServiceDialog(mActivity);
                dialog.Show();
                break;
            case R.id.layout_youHuiJuan:
                Intent intent = new Intent(mActivity, YouHuiJuanListActivity.class);
                intent.putExtra("id", bean.getCart_list().get(0).getMark().getStore_id());
                startActivity(intent);
                break;
            case R.id.layout_hongbao:

                break;
            case R.id.tv_tijiaodindan:
                if (!swithZiqu.isChecked() && addresId.equals("")) {
                    Ts.setText("请选择收货地址~或者选择自取!");
                    return;
                }
                tijiaoHttp();

                break;
        }
    }

    private void tijiaoHttp() {
        OkHttpUtils.getInstance().cancelTag(this);
        OkHttpUtils
                .get()
                .tag(this)
                .url(Api.makeOrder)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .addParams("store_id", store_id)
                .addParams("use_points", isJifen + "")
                .addParams("is_pick", isZiQu + "")
                .addParams("use_money", isYue + "")
                .addParams("coupon_id", getId() + "")
                .addParams("user_note", editLiuyan.getText().toString())
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        if (bean.getTotal_price().getTotal_fee() == 0) {
                            startActivity(new Intent(mActivity, FuKuanChenGongActivity.class));
                        } else {
                            startActivity(new Intent(mActivity, ZhiFuActivity.class));
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
