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
    @BindView(R.id.tv_shoucang)
    TextView tvShoucang;
    @BindView(R.id.tv_shijian)
    TextView tvShijian;
    @BindView(R.id.tv_xiaoliang)
    TextView tvXiaoliang;
    @BindView(R.id.dian_shiwu_daishoukuan)
    TextView dianShiwuDaishoukuan;
    @BindView(R.id.layout_shiwu_daishoukuan)
    LinearLayout layoutShiwuDaishoukuan;
    @BindView(R.id.dian_shiwu_daishouhuo)
    TextView dianShiwuDaishouhuo;
    @BindView(R.id.layout_shiwu_daishouhuan)
    LinearLayout layoutShiwuDaishouhuan;
    @BindView(R.id.dian_shiwu_daipingjia)
    TextView dianShiwuDaipingjia;
    @BindView(R.id.layout_shiwu_daipingjia)
    LinearLayout layoutShiwuDaipingjia;
    @BindView(R.id.dian_shiwu_tuikuan)
    TextView dianShiwuTuikuan;
    @BindView(R.id.layout_shiwu_tuikuan)
    LinearLayout layoutShiwuTuikuan;
    @BindView(R.id.layout_shiwu)
    LinearLayout layoutShiwu;
    @BindView(R.id.dian_tuangou_daishoukuan)
    TextView dianTuangouDaishoukuan;
    @BindView(R.id.layout_tuangou_daishoukuan)
    LinearLayout layoutTuangouDaishoukuan;
    @BindView(R.id.dian_tuangou_daishouhuan)
    TextView dianTuangouDaishouhuan;
    @BindView(R.id.layout_tuangou_daishouhuan)
    LinearLayout layoutTuangouDaishouhuan;
    @BindView(R.id.dian_tuangou_daipingjia)
    TextView dianTuangouDaipingjia;
    @BindView(R.id.layout_tuangou_daipingjia)
    LinearLayout layoutTuangouDaipingjia;
    @BindView(R.id.dian_tuangou_tuikuan)
    TextView dianTuangouTuikuan;
    @BindView(R.id.layout_tuangou_tuikuan)
    LinearLayout layoutTuangouTuikuan;
    @BindView(R.id.layout_tuangou)
    LinearLayout layoutTuangou;
    @BindView(R.id.dian_dinzhi_daishoukuan)
    TextView dianDinzhiDaishoukuan;
    @BindView(R.id.layout_dinzhi_daishoukuan)
    LinearLayout layoutDinzhiDaishoukuan;
    @BindView(R.id.dian_dinzhi_daishouhuan)
    TextView dianDinzhiDaishouhuan;
    @BindView(R.id.layout_dinzhi_daishouhuan)
    LinearLayout layoutDinzhiDaishouhuan;
    @BindView(R.id.dian_dinzhi_daipingjia)
    TextView dianDinzhiDaipingjia;
    @BindView(R.id.layout_dinzhi_daipingjia)
    LinearLayout layoutDinzhiDaipingjia;
    @BindView(R.id.dian_dinzhi_tuikuan)
    TextView dianDinzhiTuikuan;
    @BindView(R.id.layout_dinzhi_tuikuan)
    LinearLayout layoutDinzhiTuikuan;
    @BindView(R.id.layout_dinzhi)
    LinearLayout layoutDinzhi;
    @BindView(R.id.layout_dindantixin)
    LinearLayout layoutDindantixin;
    @BindView(R.id.layout_shangxiajia)
    LinearLayout layoutShangxiajia;
    @BindView(R.id.layout_baobiao)
    LinearLayout layoutBaobiao;
    @BindView(R.id.layout_xiaoxi)
    LinearLayout layoutXiaoxi;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;
    @BindView(R.id.layout_show)
    LinearLayout layoutShow;
    private SelletBean bean;
    private String type;
    private Intent intent;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_maijia);
    }

    @Override
    protected void initData() {
        StartUtil.setSw(swLoading, this);
        layoutShow.setVisibility(View.GONE);
        swLoading.setRefreshing(true);
        dianDinzhiDaipingjia.setVisibility(View.GONE);
        dianShiwuDaishoukuan.setVisibility(View.GONE);
        dianShiwuDaishouhuo.setVisibility(View.GONE);
        dianShiwuDaipingjia.setVisibility(View.GONE);
        dianShiwuTuikuan.setVisibility(View.GONE);
        dianTuangouDaishoukuan.setVisibility(View.GONE);
        dianTuangouDaishouhuan.setVisibility(View.GONE);
        dianTuangouDaipingjia.setVisibility(View.GONE);
        dianTuangouTuikuan.setVisibility(View.GONE);
        dianDinzhiDaishoukuan.setVisibility(View.GONE);
        dianDinzhiDaishouhuan.setVisibility(View.GONE);
        dianDinzhiTuikuan.setVisibility(View.GONE);
        onRefresh();
    }

    private void initRes() {
        ImageLoader.withYuan(bean.getStore_info().getStore_logo(), imgHeadPic);
        tvStoreName.setText(bean.getStore_name());
        tvShoucang.setText(bean.getStore_collect());
        tvShijian.setText(bean.getStore_info().getStore_time_y() + "年");
        tvXiaoliang.setText("成交" + bean.getStore_info().getOrder_num() + "笔");


        setTest(dianDinzhiDaipingjia, bean.getOrder_count().getCustom().getWc());
        setTest(dianDinzhiDaishoukuan, bean.getOrder_count().getCustom().getWp());
        setTest(dianDinzhiDaishouhuan, bean.getOrder_count().getCustom().getWr());
        setTest(dianDinzhiTuikuan, bean.getOrder_count().getCustom().getRe());

        setTest(dianShiwuDaishoukuan, bean.getOrder_count().getIndeed().getWp());
        setTest(dianShiwuDaishouhuo, bean.getOrder_count().getIndeed().getWr());
        setTest(dianShiwuDaipingjia, bean.getOrder_count().getIndeed().getWc());
        setTest(dianShiwuTuikuan, bean.getOrder_count().getIndeed().getRe());

        setTest(dianTuangouDaishoukuan, bean.getOrder_count().getServer().getWp());
        setTest(dianTuangouDaishouhuan, bean.getOrder_count().getServer().getWr());
        setTest(dianTuangouDaipingjia, bean.getOrder_count().getServer().getWc());
        setTest(dianTuangouTuikuan, bean.getOrder_count().getServer().getRe());
    }

    private void setTest(TextView dianDinzhiDaipingjia, String wp) {
        if (wp.isEmpty() || wp.equals("0")) {
            dianDinzhiDaipingjia.setVisibility(View.GONE);
        } else {
            dianDinzhiDaipingjia.setVisibility(View.VISIBLE);
            dianDinzhiDaipingjia.setText(wp);
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


    @OnClick({R.id.tv_store_xinxi, R.id.tv_maijia, R.id.layout_shiwu_daishoukuan, R.id.layout_shiwu_daishouhuan, R.id.layout_shiwu_daipingjia, R.id.layout_shiwu_tuikuan, R.id.layout_shiwu, R.id.layout_tuangou_daishoukuan, R.id.layout_tuangou_daishouhuan, R.id.layout_tuangou_daipingjia, R.id.layout_tuangou_tuikuan, R.id.layout_tuangou, R.id.layout_dinzhi_daishoukuan, R.id.layout_dinzhi_daishouhuan, R.id.layout_dinzhi_daipingjia, R.id.layout_dinzhi_tuikuan, R.id.layout_dinzhi, R.id.layout_dindantixin, R.id.layout_shangxiajia, R.id.layout_baobiao, R.id.layout_xiaoxi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_shiwu_daishoukuan:
                StartUtil.toQuanBuDinDan(mActivity, "1", "1", bean.getStore_id());
                break;
            case R.id.layout_shiwu_daishouhuan:
                StartUtil.toQuanBuDinDan(mActivity, "1", "2", bean.getStore_id());
                break;
            case R.id.layout_shiwu_daipingjia:
                StartUtil.toQuanBuDinDan(mActivity, "1", "3", bean.getStore_id());
                break;
            case R.id.layout_shiwu_tuikuan:
                StartUtil.toQuanBuDinDan(mActivity, "1", "4", bean.getStore_id());
                break;
            case R.id.layout_shiwu:
                StartUtil.toQuanBuDinDan(mActivity, "1", "0", bean.getStore_id());
                break;
            case R.id.layout_tuangou_daishoukuan:
                StartUtil.toQuanBuDinDan(mActivity, "3", "1", bean.getStore_id());
                break;
            case R.id.layout_tuangou_daishouhuan:
                StartUtil.toQuanBuDinDan(mActivity, "3", "2", bean.getStore_id());
                break;
            case R.id.layout_tuangou_daipingjia:
                StartUtil.toQuanBuDinDan(mActivity, "3", "3", bean.getStore_id());
                break;
            case R.id.layout_tuangou_tuikuan:
                StartUtil.toQuanBuDinDan(mActivity, "3", "4", bean.getStore_id());
                break;
            case R.id.layout_tuangou:
                StartUtil.toQuanBuDinDan(mActivity, "3", "0", bean.getStore_id());
                break;
            case R.id.layout_dinzhi_daishoukuan:
                StartUtil.toQuanBuDinDan(mActivity, "2", "1", bean.getStore_id());
                break;
            case R.id.layout_dinzhi_daishouhuan:
                StartUtil.toQuanBuDinDan(mActivity, "2", "2", bean.getStore_id());
                break;
            case R.id.layout_dinzhi_daipingjia:
                StartUtil.toQuanBuDinDan(mActivity, "2", "3", bean.getStore_id());
                break;
            case R.id.layout_dinzhi_tuikuan:
                StartUtil.toQuanBuDinDan(mActivity, "2", "4", bean.getStore_id());
                break;
            case R.id.layout_dinzhi:
                StartUtil.toQuanBuDinDan(mActivity, "2", "0", bean.getStore_id());
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
            case R.id.tv_maijia:
                finish();
                break;
            case R.id.tv_store_xinxi:
                //商家信息
                intent = new Intent(mActivity, DianPuJianJieActivity.class);
                intent.putExtra("bean", bean.getStore_info());
                startActivity(intent);
                break;
        }
    }
}
