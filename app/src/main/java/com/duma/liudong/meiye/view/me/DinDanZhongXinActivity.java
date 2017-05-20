package com.duma.liudong.meiye.view.me;

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
import com.duma.liudong.meiye.model.DinDanNumBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.StartUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.BindView;
import butterknife.OnClick;
import code.qiao.com.tipsview.TipsView;

/**
 * Created by liudong on 17/4/20.
 */

public class DinDanZhongXinActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_other)
    ImageView imgOther;
    @BindView(R.id.layout_other)
    LinearLayout layoutOther;
    @BindView(R.id.layout_shiwu_daishoukuan)
    LinearLayout layoutShiwuDaishoukuan;
    @BindView(R.id.layout_shiwu_daishouhuan)
    LinearLayout layoutShiwuDaishouhuan;
    @BindView(R.id.layout_shiwu_daipingjia)
    LinearLayout layoutShiwuDaipingjia;
    @BindView(R.id.layout_shiwu_tuikuan)
    LinearLayout layoutShiwuTuikuan;
    @BindView(R.id.layout_shiwu)
    LinearLayout layoutShiwu;
    @BindView(R.id.layout_tuangou_daishoukuan)
    LinearLayout layoutTuangouDaishoukuan;
    @BindView(R.id.layout_tuangou_daishouhuan)
    LinearLayout layoutTuangouDaishouhuan;
    @BindView(R.id.layout_tuangou_daipingjia)
    LinearLayout layoutTuangouDaipingjia;
    @BindView(R.id.layout_tuangou_tuikuan)
    LinearLayout layoutTuangouTuikuan;
    @BindView(R.id.layout_tuangou)
    LinearLayout layoutTuangou;
    @BindView(R.id.layout_dinzhi_daishoukuan)
    LinearLayout layoutDinzhiDaishoukuan;
    @BindView(R.id.layout_dinzhi_daishouhuan)
    LinearLayout layoutDinzhiDaishouhuan;
    @BindView(R.id.layout_dinzhi_daipingjia)
    LinearLayout layoutDinzhiDaipingjia;
    @BindView(R.id.layout_dinzhi_tuikuan)
    LinearLayout layoutDinzhiTuikuan;
    @BindView(R.id.layout_dinzhi)
    LinearLayout layoutDinzhi;
    @BindView(R.id.layout_wanchen)
    LinearLayout layoutWanchen;
    @BindView(R.id.layout_jinxin)
    LinearLayout layoutJinxin;
    @BindView(R.id.layout_dingzhi)
    LinearLayout layoutDingzhi;
    @BindView(R.id.dian_shiwu_daishoukuan)
    TextView dianShiwuDaishoukuan;
    @BindView(R.id.dian_shiwu_daishouhuo)
    TextView dianShiwuDaishouhuo;
    @BindView(R.id.dian_shiwu_daipingjia)
    TextView dianShiwuDaipingjia;
    @BindView(R.id.dian_shiwu_tuikuan)
    TextView dianShiwuTuikuan;
    @BindView(R.id.dian_tuangou_daishoukuan)
    TextView dianTuangouDaishoukuan;
    @BindView(R.id.dian_tuangou_daishouhuan)
    TextView dianTuangouDaishouhuan;
    @BindView(R.id.dian_tuangou_daipingjia)
    TextView dianTuangouDaipingjia;
    @BindView(R.id.dian_tuangou_tuikuan)
    TextView dianTuangouTuikuan;
    @BindView(R.id.dian_dinzhi_daishoukuan)
    TextView dianDinzhiDaishoukuan;
    @BindView(R.id.dian_dinzhi_daishouhuan)
    TextView dianDinzhiDaishouhuan;
    @BindView(R.id.dian_dinzhi_daipingjia)
    TextView dianDinzhiDaipingjia;
    @BindView(R.id.dian_dinzhi_tuikuan)
    TextView dianDinzhiTuikuan;
    @BindView(R.id.dian_wanchen)
    TextView dianWanchen;
    @BindView(R.id.dian_jinxingzhong)
    TextView dianJinxingzhong;
    @BindView(R.id.dian_shiwu_daifahuo)
    TextView dianShiwuDaifahuo;
    @BindView(R.id.layout_shiwu_daifahuo)
    LinearLayout layoutShiwuDaifahuo;
    @BindView(R.id.dian_dinzhi_daifahuo)
    TextView dianDinzhiDaifahuo;
    @BindView(R.id.layout_dinzhi_daifahuo)
    LinearLayout layoutDinzhiDaifahuo;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_dindanzhongxin);
    }

    @Override
    protected void initData() {
        tvTitle.setText("订单中心");
        StartUtil.setSw(swLoading, this);
        TipsView.create(mActivity).attach(dianDinzhiDaipingjia);
        TipsView.create(mActivity).attach(dianShiwuDaishoukuan);
        TipsView.create(mActivity).attach(dianShiwuDaishouhuo);
        TipsView.create(mActivity).attach(dianShiwuDaipingjia);
        TipsView.create(mActivity).attach(dianShiwuTuikuan);
        TipsView.create(mActivity).attach(dianTuangouDaishoukuan);
        TipsView.create(mActivity).attach(dianTuangouDaishouhuan);
        TipsView.create(mActivity).attach(dianTuangouDaipingjia);
        TipsView.create(mActivity).attach(dianTuangouTuikuan);
        TipsView.create(mActivity).attach(dianDinzhiDaishoukuan);
        TipsView.create(mActivity).attach(dianDinzhiDaishouhuan);
        TipsView.create(mActivity).attach(dianDinzhiTuikuan);
        TipsView.create(mActivity).attach(dianWanchen);
        TipsView.create(mActivity).attach(dianJinxingzhong);
        TipsView.create(mActivity).attach(dianShiwuDaifahuo);
        TipsView.create(mActivity).attach(dianDinzhiDaifahuo);


        swLoading.setRefreshing(true);
        onRefresh();

    }

    private void setRes(DinDanNumBean bean) {
        setTest(dianDinzhiDaipingjia, bean.getCustom().getWc());
        setTest(dianDinzhiDaishoukuan, bean.getCustom().getWp());
        setTest(dianDinzhiDaishouhuan, bean.getCustom().getWr());
        setTest(dianDinzhiTuikuan, bean.getCustom().getRe());
        setTest(dianDinzhiDaifahuo, bean.getCustom().getSd());

        setTest(dianShiwuDaishoukuan, bean.getIndeed().getWp());
        setTest(dianShiwuDaishouhuo, bean.getIndeed().getWr());
        setTest(dianShiwuDaipingjia, bean.getIndeed().getWc());
        setTest(dianShiwuTuikuan, bean.getIndeed().getRe());
        setTest(dianShiwuDaifahuo, bean.getCustom().getSd());

        setTest(dianTuangouDaishoukuan, bean.getServer().getWp());
        setTest(dianTuangouDaishouhuan, bean.getServer().getWr());
        setTest(dianTuangouDaipingjia, bean.getServer().getWc());
        setTest(dianTuangouTuikuan, bean.getServer().getRe());
    }

    private void setTest(TextView dianDinzhiDaipingjia, String wp) {
        if (wp.isEmpty() || wp.equals("0")) {
            dianDinzhiDaipingjia.setVisibility(View.GONE);
        } else {
            dianDinzhiDaipingjia.setVisibility(View.VISIBLE);
            dianDinzhiDaipingjia.setText(wp);
        }
    }

    @OnClick({R.id.layout_shiwu_daifahuo, R.id.layout_dinzhi_daifahuo, R.id.layout_back, R.id.layout_other, R.id.layout_shiwu_daishoukuan, R.id.layout_shiwu_daishouhuan, R.id.layout_shiwu_daipingjia, R.id.layout_shiwu_tuikuan, R.id.layout_shiwu, R.id.layout_tuangou_daishoukuan, R.id.layout_tuangou_daishouhuan, R.id.layout_tuangou_daipingjia, R.id.layout_tuangou_tuikuan, R.id.layout_tuangou, R.id.layout_dinzhi_daishoukuan, R.id.layout_dinzhi_daishouhuan, R.id.layout_dinzhi_daipingjia, R.id.layout_dinzhi_tuikuan, R.id.layout_dinzhi, R.id.layout_wanchen, R.id.layout_jinxin, R.id.layout_dingzhi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.layout_other:
                break;
            case R.id.layout_shiwu_daishoukuan:
                StartUtil.toQuanBuDinDan(mActivity, "1", "1");
                break;
            case R.id.layout_shiwu_daifahuo:
                StartUtil.toQuanBuDinDan(mActivity, "1", "2");
                break;
            case R.id.layout_shiwu_daishouhuan:
                StartUtil.toQuanBuDinDan(mActivity, "1", "3");
                break;
            case R.id.layout_shiwu_daipingjia:
                StartUtil.toQuanBuDinDan(mActivity, "1", "4");
                break;
            case R.id.layout_shiwu_tuikuan:
                StartUtil.toQuanBuDinDan(mActivity, "1", "5");
                break;
            case R.id.layout_shiwu:
                StartUtil.toQuanBuDinDan(mActivity, "1");
                break;
            case R.id.layout_tuangou_daishoukuan:
                StartUtil.toQuanBuDinDan(mActivity, "3", "1");
                break;
            case R.id.layout_tuangou_daishouhuan:
                StartUtil.toQuanBuDinDan(mActivity, "3", "2");
                break;
            case R.id.layout_tuangou_daipingjia:
                StartUtil.toQuanBuDinDan(mActivity, "3", "3");
                break;
            case R.id.layout_tuangou_tuikuan:
                StartUtil.toQuanBuDinDan(mActivity, "3", "4");
                break;
            case R.id.layout_tuangou:
                StartUtil.toQuanBuDinDan(mActivity, "3");
                break;
            case R.id.layout_dinzhi_daishoukuan:
                StartUtil.toQuanBuDinDan(mActivity, "2", "1");
                break;
            case R.id.layout_dinzhi_daifahuo:
                StartUtil.toQuanBuDinDan(mActivity, "2", "2");
                break;
            case R.id.layout_dinzhi_daishouhuan:
                StartUtil.toQuanBuDinDan(mActivity, "2", "3");
                break;
            case R.id.layout_dinzhi_daipingjia:
                StartUtil.toQuanBuDinDan(mActivity, "2", "4");
                break;
            case R.id.layout_dinzhi_tuikuan:
                StartUtil.toQuanBuDinDan(mActivity, "2", "5");
                break;
            case R.id.layout_dinzhi:
                StartUtil.toQuanBuDinDan(mActivity, "2");
                break;
            case R.id.layout_wanchen:
                StartUtil.toWoDiDinZhi(mActivity, "1");
                break;
            case R.id.layout_jinxin:
                StartUtil.toWoDiDinZhi(mActivity);
                break;
            case R.id.layout_dingzhi:
                StartUtil.toWoDiDinZhi(mActivity);
                break;


        }
    }

    @Override
    public void onRefresh() {
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
        dianWanchen.setVisibility(View.GONE);
        dianJinxingzhong.setVisibility(View.GONE);
        dianShiwuDaifahuo.setVisibility(View.GONE);
        dianDinzhiDaifahuo.setVisibility(View.GONE);

        OkHttpUtils
                .get()
                .url(Api.orderNum)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        swLoading.setRefreshing(false);
                        DinDanNumBean bean = new Gson().fromJson(result, DinDanNumBean.class);
                        setRes(bean);
                    }

                    @Override
                    protected void onError(String result) {
                        super.onError(result);
                        swLoading.setRefreshing(false);
                    }
                });
    }
}
