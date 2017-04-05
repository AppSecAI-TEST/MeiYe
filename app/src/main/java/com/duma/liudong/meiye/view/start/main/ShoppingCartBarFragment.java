package com.duma.liudong.meiye.view.start.main;

import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseFragment;
import com.duma.liudong.meiye.model.GouWuCheTypeBean;
import com.duma.liudong.meiye.utils.Constants;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/3/14.
 */

public class ShoppingCartBarFragment extends BaseFragment {

    @BindView(R.id.tv_bianji)
    TextView tvBianji;

    private int type = Constants.jiesuan;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_shopping_cart_bar;
    }

    @OnClick(R.id.tv_bianji)
    public void onClick() {
        if (type == Constants.jiesuan) {
            type = Constants.shanchu;
            tvBianji.setText("完成");
        } else {
            type = Constants.jiesuan;
            tvBianji.setText("编辑");
        }
        EventBus.getDefault().post(new GouWuCheTypeBean(type));
    }
}
