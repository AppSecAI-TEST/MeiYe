package com.duma.liudong.meiye.view.me.shiWuDinDan;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.model.ShiWuDinDanBean;
import com.duma.liudong.meiye.utils.StartUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/14.
 */

public class ShiWuDianDanXiangQingActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_other)
    ImageView imgOther;
    @BindView(R.id.layout_other)
    LinearLayout layoutOther;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_kuanDiType)
    TextView tvKuanDiType;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_dianhua)
    TextView tvDianhua;
    @BindView(R.id.tv_dizhi)
    TextView tvDizhi;
    @BindView(R.id.layout_xuanZeKuaiDi)
    LinearLayout layoutXuanZeKuaiDi;
    @BindView(R.id.rv_shangping)
    RecyclerView rvShangping;
    @BindView(R.id.tv_liuyan)
    TextView tvLiuyan;
    @BindView(R.id.tv_kuaidi_type)
    TextView tvKuaidiType;
    @BindView(R.id.tv_shangPingZongJiaGe)
    TextView tvShangPingZongJiaGe;
    @BindView(R.id.tv_youHuiJuan)
    TextView tvYouHuiJuan;
    @BindView(R.id.tv_yue)
    TextView tvYue;
    @BindView(R.id.tv_jifen)
    TextView tvJifen;
    @BindView(R.id.tv_yunfei)
    TextView tvYunfei;
    @BindView(R.id.tv_shifu)
    TextView tvShifu;
    @BindView(R.id.tv_shijian)
    TextView tvShijian;
    @BindView(R.id.tv_hei)
    TextView tvHei;
    @BindView(R.id.tv_hong)
    TextView tvHong;
    @BindView(R.id.layout_btn)
    LinearLayout layoutBtn;

    private ShiWuDinDanBean bean;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_shiwudindanxiangqin);
    }

    @Override
    protected void initData() {
        tvTitle.setText("商品详情");
        bean = (ShiWuDinDanBean) getIntent().getSerializableExtra("bean");
        tvType.setText(bean.getOrder_status_desc());
        tvName.setText(bean.getConsignee());
        tvDianhua.setText(bean.getMobile());
        tvDizhi.setText(bean.getProvince() + bean.getCity() + bean.getDistrict() + bean.getAddress());
        tvYouHuiJuan.setText("-￥" + bean.getCoupon_price());
        tvYue.setText("-￥" + bean.getUser_money());
        tvShangPingZongJiaGe.setText("￥" + bean.getTotal_amount());
        tvShifu.setText(bean.getOrder_amount());
        tvYunfei.setText("￥" + bean.getShipping_price());
        tvShijian.setText(StartUtil.getShiJian(Long.parseLong(bean.getAdd_time())));
    }

    @OnClick({R.id.layout_back, R.id.tv_hei, R.id.tv_hong})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.tv_hei:

                break;
            case R.id.tv_hong:
                break;
        }
    }
}
