package com.duma.liudong.meiye.view.start.main;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseFragment;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.view.me.SheZhiActivity;
import com.duma.liudong.meiye.view.me.maiJia.MjMainActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/3/14.
 */

public class MeBarFragment extends BaseFragment {

    @BindView(R.id.tv_maijia)
    TextView tvMaijia;
    @BindView(R.id.img_shezhi)
    ImageView imgShezhi;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_me_bar;
    }


    @OnClick({R.id.tv_maijia, R.id.img_shezhi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_maijia:
                if (!StartUtil.isLogin()) {
                    StartUtil.toLogin(mActivity);
                    return;
                }
                startActivity(new Intent(mActivity, MjMainActivity.class));
                mActivity.overridePendingTransition(R.anim.out_to_left2, R.anim.in_from_right);
                break;
            case R.id.img_shezhi:
                startActivity(new Intent(mActivity, SheZhiActivity.class));
                break;
        }
    }
}
