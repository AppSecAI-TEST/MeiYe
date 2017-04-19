package com.duma.liudong.meiye.view.start.main;

import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseFragment;
import com.duma.liudong.meiye.view.home.SouSuoActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/3/14.
 */

public class SearchBarFragment extends BaseFragment {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.layout_sousuo)
    LinearLayout layoutSousuo;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_search_bar;
    }

    public void setTvName(String name) {
        tvName.setText(name);
    }

    @OnClick(R.id.layout_sousuo)
    public void onClick() {
        startActivity(new Intent(mActivity, SouSuoActivity.class));
    }
}
