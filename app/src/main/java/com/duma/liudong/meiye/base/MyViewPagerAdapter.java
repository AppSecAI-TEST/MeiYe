package com.duma.liudong.meiye.base;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.duma.liudong.meiye.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 晓勇 on 2015/7/12 0012.
 */
public class MyViewPagerAdapter extends FragmentPagerAdapter {
    public List<Fragment> mFragments = new ArrayList<>();//添加的Fragment的集合
    public List<String> mFragmentsTitles = new ArrayList<>();//每个Fragment对应的title的集合


    public MyViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * @param fragment      添加Fragment
     * @param fragmentTitle Fragment的标题，即TabLayout中对应Tab的标题
     */
    public void addFragment(Fragment fragment, String fragmentTitle) {
        mFragments.add(fragment);
        mFragmentsTitles.add(fragmentTitle);
    }

    @Override
    public Fragment getItem(int position) {
        //得到对应position的Fragment
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        //返回Fragment的数量
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //得到对应position的Fragment的title
        return mFragmentsTitles.get(position);
//        return null;
    }

    public View getTabView(int position, Activity activity) {
        View view = LayoutInflater.from(activity).inflate(R.layout.tab_iem, null);
        TextView tv = (TextView) view.findViewById(R.id.tv_title);
        tv.setText(mFragmentsTitles.get(position));
//        ImageView img = (ImageView) view.findViewById(R.id.img_head_pic);
//        img.setImageResource(imageResId[position]);
        return view;
    }


}
