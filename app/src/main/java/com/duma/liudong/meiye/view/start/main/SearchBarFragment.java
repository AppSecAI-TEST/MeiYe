package com.duma.liudong.meiye.view.start.main;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseFragment;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.widget.city.ShouYeDingWeiActivity;

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

    @Override
    protected void initData() {
        tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, ShouYeDingWeiActivity.class));
            }
        });
    }

    public void setTvName() {
        String string = MyApplication.getSpUtils2().getString(Constants.city);
        if (string.equals("")) {
            tvName.setText("定位中");
        } else if (string.equals("1")) {
            tvName.setText("定位失败");
        } else {
            tvName.setText(string);
        }
    }

    @OnClick(R.id.layout_sousuo)
    public void onClick() {
        StartUtil.toSousuo(mActivity);
    }
}
