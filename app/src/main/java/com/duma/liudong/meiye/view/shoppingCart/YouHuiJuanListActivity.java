package com.duma.liudong.meiye.view.shoppingCart;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.view.me.YouHuiJuanFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/13.
 */

public class YouHuiJuanListActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_other)
    ImageView imgOther;
    @BindView(R.id.layout_other)
    LinearLayout layoutOther;
    @BindView(R.id.layout_fragment)
    FrameLayout layoutFragment;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_youhuijuan_list);
    }

    @Override
    protected void initData() {
        tvTitle.setText("优惠券");
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.layout_fragment, new YouHuiJuanFragment());
        transaction.commit();
    }

    @OnClick(R.id.layout_back)
    public void onClick() {
        finish();
    }
}
