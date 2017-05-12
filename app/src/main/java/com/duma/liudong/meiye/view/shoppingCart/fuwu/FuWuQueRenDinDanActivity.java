package com.duma.liudong.meiye.view.shoppingCart.fuwu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.model.FuWuQuRenDinDanBean;
import com.duma.liudong.meiye.model.OridBean;
import com.duma.liudong.meiye.model.YouHuiJuanBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.DialogUtil;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.view.shoppingCart.YouHuiListActivity;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/10.
 */

public class FuWuQueRenDinDanActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.img_other)
    ImageView imgOther;
    @BindView(R.id.layout_other)
    LinearLayout layoutOther;
    @BindView(R.id.tv_store_name)
    TextView tvStoreName;
    @BindView(R.id.img_head_pic)
    ImageView imgHeadPic;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_jiage)
    TextView tvJiage;
    @BindView(R.id.tv_shuliang)
    TextView tvShuliang;
    @BindView(R.id.layout_jiesuan)
    LinearLayout layoutJiesuan;
    @BindView(R.id.tv_jian)
    TextView tvJian;
    @BindView(R.id.tv_goods_num)
    TextView tvGoodsNum;
    @BindView(R.id.tv_jia)
    TextView tvJia;
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
    @BindView(R.id.tv_shangping_title)
    TextView tvShangpingTitle;

    private String goods_id;
    private boolean isOne = false;
    private FuWuQuRenDinDanBean bean;
    private int isJifen = 0;//是否使用积分
    private int isYue = 0;//是否使用余额


    private YouHuiJuanBean youHuiJuanBean;//计算后优惠券
    private YouHuiJuanBean youHuiJuanBean_now;//没进行请求的优惠券


    private int goods_num = 1;//商品数量

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_fuwu_querendindan);
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        tvTitle.setText("确认订单");
        goods_id = getIntent().getStringExtra("goods_id");
        DinDanHttp();
        tvShijian.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
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
    }

    @Subscribe
    public void youHuiJuanListener(YouHuiJuanBean youHuiJuanBean) {
        this.youHuiJuanBean_now = youHuiJuanBean;
        DinDanHttp();
    }


    private int initInt(int i) {
        if (i == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    private void DinDanHttp() {
        DialogUtil.show(mActivity);
        OkHttpUtils.getInstance().cancelTag(this);
        OkHttpUtils
                .get()
                .url(Api.serverSure)
                .tag(this)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .addParams("goods_id", goods_id)
                .addParams("use_points", isJifen + "")
                .addParams("use_money", isYue + "")
                .addParams("coupon_id", getId() + "")
                .addParams("goods_num", goods_num + "")
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        isOne = true;
                        DialogUtil.hide();
                        bean = new Gson().fromJson(result, FuWuQuRenDinDanBean.class);
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
        goods_num = bean.getGoods_num();
        tvStoreName.setText(bean.getStore_name());
        ImageLoader.with(Api.url + bean.getOriginal_img(), imgHeadPic);
        tvShangpingTitle.setText(bean.getGoods_name());
        tvJiage.setText(bean.getMember_price());
        tvZonge.setText("￥" + getMoney() + "");
        tvShifu.setText(bean.getTotal() + "");
        tvZongji.setText("" + bean.getTotal());
        tvJifen.setText(bean.getReward_points());
        tvYue.setText(bean.getBalance());
        tvShuliang.setText("x" + bean.getGoods_num());
        tvGoodsNum.setText(bean.getGoods_num() + "");

        swithJifen.setChecked(getRes(isJifen));
        swithYue.setChecked(getRes(isYue));
        if (youHuiJuanBean != null) {
            tvYouhuijuan.setText("满" + youHuiJuanBean.getCondition() + "减" + youHuiJuanBean.getMoney() + "元");
        } else {
            tvYouhuijuan.setText("不使用优惠/红包");
        }
    }

    private double getMoney() {
        return bean.getGoods_num() * Double.parseDouble(bean.getMember_price());
    }

    public boolean getRes(int i) {
        if (i == 0) {
            return false;
        } else {
            return true;
        }
    }


    @OnClick({R.id.layout_back, R.id.layout_youHuiJuan, R.id.layout_hongbao, R.id.tv_tijiaodindan, R.id.tv_jian, R.id.tv_jia})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.layout_youHuiJuan:
                Intent intent = new Intent(mActivity, YouHuiListActivity.class);
                intent.putExtra("id", bean.getStore_id());
                intent.putExtra("money", getMoney() + "");
                startActivity(intent);
                break;
            case R.id.layout_hongbao:

                break;
            case R.id.tv_jia:
                goods_num++;
                DinDanHttp();
                break;
            case R.id.tv_jian:
                if (goods_num-- == 0) {
                    goods_num++;
                    return;
                }
                DinDanHttp();
                break;
            case R.id.tv_tijiaodindan:
                tijiaoHttp();
                break;
        }
    }

    private void tijiaoHttp() {
        DialogUtil.show(mActivity);
        OkHttpUtils.getInstance().cancelTag(this);
        OkHttpUtils
                .get()
                .tag(this)
                .url(Api.serverOrder)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .addParams("goods_id", goods_id)
                .addParams("use_points", isJifen + "")
                .addParams("use_money", isYue + "")
                .addParams("coupon_id", getId() + "")
                .addParams("goods_num", goods_num + "")
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        DialogUtil.hide();
                        OridBean oridBean = new Gson().fromJson(result, OridBean.class);
                        if (bean.getTotal() == 0) {
                            StartUtil.toZhiFuSuccess(mActivity, "3", oridBean.getOrder_id());
                        } else {
                            StartUtil.toZhiFu(mActivity, oridBean.getOrder_id(), bean.getTotal() + "", "3");
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
