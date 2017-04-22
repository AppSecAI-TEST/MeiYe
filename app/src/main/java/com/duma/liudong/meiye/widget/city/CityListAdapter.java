package com.duma.liudong.meiye.widget.city;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.utils.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class CityListAdapter extends BaseAdapter {

    private Activity mContext;
    private List<City> mAllCityList;
    private List<City> mHotCityList;
    public HashMap<String, Integer> alphaIndexer;// 存放存在的汉语拼音首字母和与之对应的列表位置
    private String[] sections;// 存放存在的汉语拼音首字母

    private final int VIEW_TYPE = 3;// view的类型个数

    public CityListAdapter(Activity context, List<City> allCityList,
                           List<City> hotCityList) {
        this.mContext = context;
        this.mAllCityList = allCityList;
        this.mHotCityList = hotCityList;

        alphaIndexer = new HashMap<String, Integer>();
        sections = new String[allCityList.size()];

        // 这里的主要目的是将listview中要显示字母的条目保存下来，方便在滑动时获得位置，alphaIndexer在Acitivity有调用
        for (int i = 0; i < mAllCityList.size(); i++) {
            // 当前汉语拼音首字母
            String currentStr = getAlpha(mAllCityList.get(i).getPinyin());
            // 上一个汉语拼音首字母，如果不存在为" "
            String previewStr = (i - 1) >= 0 ? getAlpha(mAllCityList.get(i - 1)
                    .getPinyin()) : " ";
            if (!previewStr.equals(currentStr)) {
                String name = getAlpha(mAllCityList.get(i).getPinyin());
                alphaIndexer.put(name, i);
                sections[i] = name;
            }
        }
    }

    @Override
    public int getViewTypeCount() {

        return VIEW_TYPE;
    }

    @Override
    public int getItemViewType(int position) {
        return position < 2 ? position : 2;
    }

    @Override
    public int getCount() {
        return mAllCityList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAllCityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        int viewType = getItemViewType(position);
        if (viewType == 0) {// 热门城市
            convertView = View.inflate(mContext,
                    R.layout.item_recent_visit_city, null);
            TextView tvRecentVisitCity = (TextView) convertView
                    .findViewById(R.id.tv_recent_visit_city);
            tvRecentVisitCity.setText("热门城市");
            MyGridView gvRecentVisitCity = (MyGridView) convertView
                    .findViewById(R.id.gv_recent_visit_city);
            gvRecentVisitCity.setAdapter(new HotCityAdapter(mContext,
                    mHotCityList));
        } else if (viewType == 1) {// 全部城市，仅展示“全部城市这四个字”
            convertView = View.inflate(mContext,
                    R.layout.item_all_city_textview, null);
        } else {// 数据库中所有的城市的名字展示
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(mContext, R.layout.item_city_list,
                        null);
                viewHolder.tvAlpha = (TextView) convertView
                        .findViewById(R.id.tv_alpha);
                viewHolder.tvCityName = (TextView) convertView
                        .findViewById(R.id.tv_city_name);
                viewHolder.llMain = (LinearLayout) convertView
                        .findViewById(R.id.ll_main);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            if (position >= 1) {
                viewHolder.tvCityName.setText(mAllCityList.get(position)
                        .getName());
                viewHolder.llMain.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
//                        SharedPreferencesUtil.getInstance().set(SharedPreferencesUtil.dingweicity, mAllCityList.get(position).getName());
                        MyApplication.getSpUtils().put(Constants.city, mAllCityList.get(position).getName());
                        MyApplication.getSpUtils().put(Constants.lat, "");
                        MyApplication.getSpUtils().put(Constants.lng, "");
                        mContext.finish();
                    }
                });
                String currentStr = getAlpha(mAllCityList.get(position)
                        .getPinyin());
                String previewStr = (position - 1) >= 0 ? getAlpha(mAllCityList
                        .get(position - 1).getPinyin()) : " ";
                // 如果当前的条目的城市名字的拼音的首字母和其前一条条目的城市的名字的拼音的首字母不相同，则将布局中的展示字母的TextView展示出来
                if (!previewStr.equals(currentStr)) {
                    viewHolder.tvAlpha.setVisibility(View.VISIBLE);
                    viewHolder.tvAlpha.setText(currentStr);
                } else {
                    viewHolder.tvAlpha.setVisibility(View.GONE);
                }
            }

        }

        return convertView;
    }

    // 获得汉语拼音首字母
    private String getAlpha(String str) {
        if (str == null) {
            return "#";
        }
        if (str.trim().length() == 0) {
            return "#";
        }
        char c = str.trim().substring(0, 1).charAt(0);
        // 正则表达式，判断首字母是否是英文字母
        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        if (pattern.matcher(c + "").matches()) {
            return (c + "").toUpperCase();
        } else if (str.equals("0")) {
            return "热门";
        } else if (str.equals("1")) {
            return "全部";
        } else {
            return "#";
        }
    }

    class ViewHolder {
        TextView tvAlpha;
        TextView tvCityName;
        LinearLayout llMain;
    }

}
