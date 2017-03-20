package com.duma.liudong.meiye.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.model.AdDemo;

import java.util.List;

/**
 * Description:
 * <p/>
 * Created by rowandjj(chuyi)<br/>
 * Date: 16/1/7<br/>
 * Time: 下午2:05<br/>
 */
public class SampleAdapter extends BaseBannerAdapter<AdDemo> {


    public SampleAdapter(List<AdDemo> datas) {
        super(datas);
    }

    @Override
    public View getView(VerticalBannerView parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.home_ad, null);
    }

    @Override
    public void setItem(View view, AdDemo data) {
        TextView tvTop = (TextView) view.findViewById(R.id.tv_top);
        TextView tvBottom = (TextView) view.findViewById(R.id.tv_bottom);

        tvTop.setText(data.getTopData());
        tvBottom.setText(data.getBottomData());
    }


}












