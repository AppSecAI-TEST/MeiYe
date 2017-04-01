package com.duma.liudong.meiye.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseBannerAdapter;
import com.duma.liudong.meiye.model.IndexBean;

import java.util.List;

/**
 * Description:
 * <p/>
 * Created by rowandjj(chuyi)<br/>
 * Date: 16/1/7<br/>
 * Time: 下午2:05<br/>
 */
public class SampleAdapter extends BaseBannerAdapter<List<IndexBean.ArticleBean>> {
    public SampleAdapter(List<List<IndexBean.ArticleBean>> article) {
        super(article);
    }

    @Override
    public View getView(VerticalBannerView parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.home_ad, null);
    }

    @Override
    public void setItem(View view, List<IndexBean.ArticleBean> data) {
        TextView tvTop = (TextView) view.findViewById(R.id.tv_top);
        TextView tvBottom = (TextView) view.findViewById(R.id.tv_bottom);
        TextView tv_cat_name = (TextView) view.findViewById(R.id.tv_cat_name);
        TextView tv_cat_name2 = (TextView) view.findViewById(R.id.tv_cat_name2);

        tvTop.setText(data.get(0).getTitle());
        tvBottom.setText(data.get(1).getTitle());
        tv_cat_name.setText(data.get(0).getCat_name());
        tv_cat_name2.setText(data.get(1).getCat_name());
    }

//
//    public SampleAdapter(List<List<IndexBean.ArticleBean>> datas) {
//        super(datas);
//    }
//
//    @Override
//    public View getView(VerticalBannerView parent) {
//        return LayoutInflater.from(parent.getContext()).inflate(R.layout.home_ad, null);
//    }
//
//    @Override
//    public void setItem(View view, IndexBean.ArticleBean data) {
////        TextView tvTop = (TextView) view.findViewById(R.id.tv_top);
////        TextView tvBottom = (TextView) view.findViewById(R.id.tv_bottom);
////
////        tvTop.setText(data.getTopData());
////        tvBottom.setText(data.getBottomData());
//    }


}












