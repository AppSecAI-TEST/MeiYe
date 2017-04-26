package com.duma.liudong.meiye.widget.demo;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.liudong.meiye.model.DemoBean;

import java.util.List;

/**
 * Created by liudong on 17/4/25.
 */

public class LoadAdapter extends BaseQuickAdapter<DemoBean, BaseViewHolder> {

    public LoadAdapter(int layoutResId, List<DemoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DemoBean item) {

    }
}
