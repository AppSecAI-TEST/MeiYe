package com.duma.liudong.meiye.view.me;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/20.
 */

public class DinDanZhongXinActivity extends BaseActivity {
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
    TextView layoutJinxin;
    @BindView(R.id.layout_dingzhi)
    LinearLayout layoutDingzhi;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_dindanzhongxin);
    }

    @Override
    protected void initData() {
        tvTitle.setText("订单中心");
    }

    @OnClick({R.id.layout_back, R.id.layout_other, R.id.layout_shiwu_daishoukuan, R.id.layout_shiwu_daishouhuan, R.id.layout_shiwu_daipingjia, R.id.layout_shiwu_tuikuan, R.id.layout_shiwu, R.id.layout_tuangou_daishoukuan, R.id.layout_tuangou_daishouhuan, R.id.layout_tuangou_daipingjia, R.id.layout_tuangou_tuikuan, R.id.layout_tuangou, R.id.layout_dinzhi_daishoukuan, R.id.layout_dinzhi_daishouhuan, R.id.layout_dinzhi_daipingjia, R.id.layout_dinzhi_tuikuan, R.id.layout_dinzhi, R.id.layout_wanchen, R.id.layout_jinxin, R.id.layout_dingzhi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.layout_other:
                break;
            case R.id.layout_shiwu_daishoukuan:
                break;
            case R.id.layout_shiwu_daishouhuan:
                break;
            case R.id.layout_shiwu_daipingjia:
                break;
            case R.id.layout_shiwu_tuikuan:
                break;
            case R.id.layout_shiwu:
                break;
            case R.id.layout_tuangou_daishoukuan:
                break;
            case R.id.layout_tuangou_daishouhuan:
                break;
            case R.id.layout_tuangou_daipingjia:
                break;
            case R.id.layout_tuangou_tuikuan:
                break;
            case R.id.layout_tuangou:
                break;
            case R.id.layout_dinzhi_daishoukuan:
                break;
            case R.id.layout_dinzhi_daishouhuan:
                break;
            case R.id.layout_dinzhi_daipingjia:
                break;
            case R.id.layout_dinzhi_tuikuan:
                break;
            case R.id.layout_dinzhi:
                break;
            case R.id.layout_wanchen:
                break;
            case R.id.layout_jinxin:
                break;
            case R.id.layout_dingzhi:
                break;
        }
    }
}
