package com.duma.liudong.meiye.view.me.maiJia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.model.SelletBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.utils.Ts;
import com.duma.liudong.meiye.view.classift.dianPu.DianPuJianJieActivity;
import com.duma.liudong.meiye.view.home.MessageActivity;
import com.duma.liudong.meiye.view.home.MessageContentActivity;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/20.
 */

public class MjMainActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.tv_maijia)
    TextView tvMaijia;
    @BindView(R.id.tv_store_xinxi)
    TextView tvStoreXinxi;
    @BindView(R.id.img_head_pic)
    ImageView imgHeadPic;
    @BindView(R.id.tv_store_name)
    TextView tvStoreName;
    @BindView(R.id.tv_shijian)
    TextView tvShijian;
    @BindView(R.id.tv_xiaoliang)
    TextView tvXiaoliang;
    @BindView(R.id.layout_daishoukuan)
    LinearLayout layoutDaishoukuan;
    @BindView(R.id.layout_daishouhuo)
    LinearLayout layoutDaishouhuo;
    @BindView(R.id.layout_daipingjia)
    LinearLayout layoutDaipingjia;
    @BindView(R.id.layout_tuikuan)
    LinearLayout layoutTuikuan;
    @BindView(R.id.img_dindan_type)
    ImageView imgDindanType;
    @BindView(R.id.tv_dindan_name)
    TextView tvDindanName;
    @BindView(R.id.layout_dindan_type)
    LinearLayout layoutDindanType;
    @BindView(R.id.layout_dindantixin)
    LinearLayout layoutDindantixin;
    @BindView(R.id.layout_shangxiajia)
    LinearLayout layoutShangxiajia;
    @BindView(R.id.layout_baobiao)
    LinearLayout layoutBaobiao;
    @BindView(R.id.layout_xiaoxi)
    LinearLayout layoutXiaoxi;
    @BindView(R.id.layout_show)
    LinearLayout layoutShow;
    @BindView(R.id.tv_shoucang)
    TextView tvShoucang;
    @BindView(R.id.dian_daifukuan)
    TextView dianDaifukuan;
    @BindView(R.id.dian_daishouhuo)
    TextView dianDaishouhuo;
    @BindView(R.id.dian_daipingjia)
    TextView dianDaipingjia;
    @BindView(R.id.dian_tuikuan)
    TextView dianTuikuan;
    @BindView(R.id.tv_shouhuo)
    TextView tvShouhuo;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;
    private SelletBean bean;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_maijia);
    }

    @Override
    protected void initData() {
        StartUtil.setSw(swLoading, this);
        layoutShow.setVisibility(View.GONE);
        dianDaifukuan.setVisibility(View.GONE);
        dianDaishouhuo.setVisibility(View.GONE);
        dianDaipingjia.setVisibility(View.GONE);
        dianTuikuan.setVisibility(View.GONE);
        swLoading.setRefreshing(true);
        onRefresh();
    }

    private void initRes() {
        ImageLoader.withYuan(bean.getStore_info().getStore_logo(), imgHeadPic);
        tvStoreName.setText(bean.getStore_name());
        tvShoucang.setText(bean.getStore_collect());
        tvShijian.setText(bean.getStore_info().getStore_time_y() + "年");
        tvXiaoliang.setText("成交" + bean.getStore_info().getOrder_num() + "笔");
        tvShouhuo.setText("待收货");
        switch (bean.getSc_id()) {
            case "1":
                //团购订单
                imgDindanType.setImageDrawable(MyApplication.getInstance().getResources().getDrawable(R.drawable.img_96));
                tvDindanName.setText("团购订单");
                tvShouhuo.setText("待使用");
                break;
            case "2":
                //实物订单
                imgDindanType.setImageDrawable(MyApplication.getInstance().getResources().getDrawable(R.drawable.img_95));
                tvDindanName.setText("实物订单");
                break;
            case "3":
                //定制订单
                imgDindanType.setImageDrawable(MyApplication.getInstance().getResources().getDrawable(R.drawable.img_97));
                tvDindanName.setText("定制订单");
                break;
        }

        setTest(dianDaifukuan, bean.getWait_pay());
        setTest(dianDaishouhuo, bean.getWait_shipping());
        setTest(dianDaipingjia, bean.getWait_confirm());
        setTest(dianTuikuan, bean.getRefund_pay());
    }

    private void setTest(TextView dianDinzhiDaipingjia, String wp) {
        if (wp.isEmpty() || wp.equals("0")) {
            dianDinzhiDaipingjia.setVisibility(View.GONE);
        } else {
            dianDinzhiDaipingjia.setVisibility(View.VISIBLE);
            dianDinzhiDaipingjia.setText(wp);
        }
    }

    @OnClick({R.id.tv_maijia, R.id.tv_store_xinxi, R.id.layout_daishoukuan, R.id.layout_daishouhuo, R.id.layout_daipingjia, R.id.layout_tuikuan, R.id.layout_dindan_type, R.id.layout_dindantixin, R.id.layout_shangxiajia, R.id.layout_baobiao, R.id.layout_xiaoxi})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.tv_maijia:
                finish();
                break;
            case R.id.tv_store_xinxi:
                //商家信息
                intent = new Intent(mActivity, DianPuJianJieActivity.class);
                intent.putExtra("bean", bean.getStore_info());
                startActivity(intent);
                break;
            case R.id.layout_daishoukuan:
                StartUtil.toQuanBuDinDan(mActivity, bean.getSc_id(), "1", bean.getStore_id());
                break;
            case R.id.layout_daishouhuo:
                StartUtil.toQuanBuDinDan(mActivity, bean.getSc_id(), "2", bean.getStore_id());
                break;
            case R.id.layout_daipingjia:
                StartUtil.toQuanBuDinDan(mActivity, bean.getSc_id(), "3", bean.getStore_id());
                break;
            case R.id.layout_tuikuan:
                StartUtil.toQuanBuDinDan(mActivity, bean.getSc_id(), "4", bean.getStore_id());
                break;
            case R.id.layout_dindan_type:
                StartUtil.toQuanBuDinDan(mActivity, bean.getSc_id(), "0", bean.getStore_id());
                break;
            case R.id.layout_dindantixin:
                intent = new Intent(mActivity, MessageContentActivity.class);
                intent.putExtra("type", "3");
                startActivity(intent);
                break;
            case R.id.layout_shangxiajia:
                //商品上下架
                intent = new Intent(mActivity, ShangXiaJiaActivity.class);
                intent.putExtra("id", bean.getStore_id());
                startActivity(intent);
                break;
            case R.id.layout_baobiao:
                //销售报表
                intent = new Intent(mActivity, XiaoShouBaoBiaoActivity.class);
                intent.putExtra("id", bean.getStore_id());
                startActivity(intent);
                break;
            case R.id.layout_xiaoxi:
                intent = new Intent(mActivity, MessageActivity.class);
                intent.putExtra("store_id", bean.getStore_id());
                startActivity(intent);
                break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    @Override
    public void onRefresh() {
        OkHttpUtils
                .get()
                .url(Api.sellerindex)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        layoutShow.setVisibility(View.VISIBLE);
                        bean = new Gson().fromJson(result, SelletBean.class);
                        if (bean.getStore_id().equals("")) {
                            Ts.setText("您还没开店呢!");
                            finish();
                        }
                        initRes();
                        swLoading.setRefreshing(false);
                    }

                    @Override
                    protected void onError(String result) {
                        super.onError(result);
                        swLoading.setRefreshing(false);
                        finish();
                    }
                });
    }
}
