package com.duma.liudong.meiye.view.shoppingCart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.view.me.ShiWuDinDanQuanBuActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 79953 on 2016/10/31.
 */

public class FuKuanChenGongActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView layoutName;
    @BindView(R.id.tv_shouye)
    TextView tvShouye;
    @BindView(R.id.tv_chakanDinDan)
    TextView tvChakanDinDan;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_fukuanchenggong);
    }

    @Override
    protected void initData() {
        layoutName.setText("付款成功");
    }

    @OnClick({R.id.layout_back, R.id.tv_shouye, R.id.tv_chakanDinDan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.tv_shouye:
                StartUtil.toMain(mActivity);
                break;
            case R.id.tv_chakanDinDan:
                Intent intent = new Intent(this, ShiWuDinDanQuanBuActivity.class);
                intent.putExtra("type", "");
                startActivity(intent);
                break;
        }
    }
}