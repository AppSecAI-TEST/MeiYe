package com.duma.liudong.meiye.view.start.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.widget.CircleIndicator;
import com.duma.liudong.meiye.widget.FragAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by liudong on 17/4/27.
 */

public class ViewPaterActivity extends BaseActivity {
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.indicator)
    CircleIndicator indicator;
    private List<Fragment> fragmentList;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_viewpager);
    }


    @Override
    protected void initData() {
        fragmentList = new ArrayList<>();

        fragmentList.add(new ViewPagerOneFragment());
        fragmentList.add(new ViewPagerTwoFragment());
        fragmentList.add(new ViewPagerThreeFragment());
        fragmentList.add(new ViewPagerThreeOneFragment());
        fragmentList.add(new ViewPagerFourFragment());

        FragAdapter adapter = new FragAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
    }
}
