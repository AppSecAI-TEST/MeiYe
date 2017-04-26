package com.duma.liudong.meiye.view.me;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.utils.Ts;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/20.
 */

public class SheZhiActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_other)
    ImageView imgOther;
    @BindView(R.id.layout_other)
    LinearLayout layoutOther;
    @BindView(R.id.layout_qingchu)
    LinearLayout layoutQingchu;
    @BindView(R.id.tv_banbenhao)
    TextView tvBanbenhao;
    @BindView(R.id.layout_banbenha)
    LinearLayout layoutBanbenha;
    @BindView(R.id.tv_niCheng)
    TextView tvNiCheng;
    @BindView(R.id.layout_banquan)
    LinearLayout layoutBanquan;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_shezhi);
    }

    @Override
    protected void initData() {
        tvTitle.setText("设置");
        tvBanbenhao.setText(AppUtils.getAppVersionName(mActivity));
    }

    @OnClick({R.id.layout_back, R.id.layout_qingchu, R.id.layout_banbenha, R.id.layout_banquan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.layout_qingchu:
                Ts.setText("清除成功~");
                break;
            case R.id.layout_banbenha:
                // TODO: 17/4/20 升级
                break;
            case R.id.layout_banquan:
                StartUtil.toH5Web(mActivity, Api.banquanH5, "版权信息");
                break;
        }
    }
}
