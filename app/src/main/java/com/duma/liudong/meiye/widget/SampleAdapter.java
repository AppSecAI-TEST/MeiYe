package com.duma.liudong.meiye.widget;

import android.view.LayoutInflater;
import android.view.View;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.model.IndexBean;

import java.util.List;

/**
 * Description:
 * <p/>
 * Created by rowandjj(chuyi)<br/>
 * Date: 16/1/7<br/>
 * Time: 下午2:05<br/>
 */
public class SampleAdapter extends BaseBannerAdapter<IndexBean.ArticleBean> {


    public SampleAdapter(List<IndexBean.ArticleBean> datas) {
        super(datas);
    }

    @Override
    public View getView(VerticalBannerView parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.home_ad, null);
    }

    @Override
    public void setItem(View view, IndexBean.ArticleBean data) {
//        TextView tvTop = (TextView) view.findViewById(R.id.tv_top);
//        TextView tvBottom = (TextView) view.findViewById(R.id.tv_bottom);
//
//        tvTop.setText(data.getTopData());
//        tvBottom.setText(data.getBottomData());
    }


}












