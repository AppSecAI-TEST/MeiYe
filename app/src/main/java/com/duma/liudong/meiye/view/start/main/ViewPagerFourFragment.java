package com.duma.liudong.meiye.view.start.main;

import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseFragment;
import com.duma.liudong.meiye.utils.StartUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/27.
 */

public class ViewPagerFourFragment extends BaseFragment {
    @BindView(R.id.tv_start)
    TextView tvStart;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_viewpager_four;
    }


    @OnClick(R.id.tv_start)
    public void onClick() {
        StartUtil.toMain(mActivity);
    }
}
