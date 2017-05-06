package com.duma.liudong.meiye.widget.city;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.model.City;
import com.duma.liudong.meiye.utils.Constants;

import java.util.List;

public class HotCityAdapter extends BaseAdapter {

    private List<City> mHotCityList;
    private LayoutInflater mInflater;
    private Activity mContext;

    public HotCityAdapter(Activity context, List<City> hotCityList) {
        this.mHotCityList = hotCityList;
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mHotCityList.size();
    }

    @Override
    public Object getItem(int position) {
        return mHotCityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_city, null);
            viewHolder.tvCityName = (TextView) convertView
                    .findViewById(R.id.tv_city_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvCityName.setText(mHotCityList.get(position).getName());
        viewHolder.tvCityName.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
//                SharedPreferencesUtil.getInstance().set(SharedPreferencesUtil.dingweicity, mHotCityList.get(position).getName());
                MyApplication.getSpUtils2().put(Constants.city, mHotCityList.get(position).getName());
                MyApplication.getSpUtils2().put(Constants.lat, "");
                MyApplication.getSpUtils2().put(Constants.lng, "");
                mContext.finish();
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView tvCityName;
    }

}
