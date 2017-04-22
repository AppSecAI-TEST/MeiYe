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
import com.duma.liudong.meiye.utils.Constants;

import java.util.List;


public class SearchResultAdapter extends BaseAdapter {

    private List<City> mSearchList;
    private Activity mContext;
    private LayoutInflater mInflater;

    public SearchResultAdapter(Activity context, List<City> searchList) {
        this.mSearchList = searchList;
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mSearchList.size();
    }

    @Override
    public Object getItem(int position) {
        return mSearchList.get(position);
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
            convertView = mInflater.inflate(R.layout.item_search_list, null);
            viewHolder.tvCityName = (TextView) convertView.findViewById(R.id.tv_city_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvCityName.setText(mSearchList.get(position).getName());
        viewHolder.tvCityName.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
//                SharedPreferencesUtil.getInstance().set(SharedPreferencesUtil.dingweicity, mSearchList.get(position).getName());
                MyApplication.getSpUtils().put(Constants.city, mSearchList.get(position).getName());
                MyApplication.getSpUtils().put(Constants.lat, "");
                MyApplication.getSpUtils().put(Constants.lng, "");
                mContext.finish();
            }
        });

        return convertView;
    }

    class ViewHolder {
        TextView tvCityName;
    }

}
