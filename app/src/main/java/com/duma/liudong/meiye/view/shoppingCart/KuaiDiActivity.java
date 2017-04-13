package com.duma.liudong.meiye.view.shoppingCart;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.MyViewPagerAdapter;
import com.duma.liudong.meiye.view.me.DiZhiFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 79953 on 2016/10/27.
 */

public class KuaiDiActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tab_kuaidi)
    TabLayout tabKuaidi;
    @BindView(R.id.viewPager_kuaidi)
    ViewPager viewPagerKuaidi;

    MyViewPagerAdapter viewPagerAdapter;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_kuaidi);
    }

    @Override
    protected void initData() {
        viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new DiZhiFragment(), "快递");//添加Fragment
        viewPagerAdapter.addFragment(new DiZhiFragment(), "自取");
        viewPagerKuaidi.setAdapter(viewPagerAdapter);
        tabKuaidi.setupWithViewPager(viewPagerKuaidi);
    }


//    @Override
//    public void finish() {
//        if (viewPagerKuaidi.getCurrentItem() == 0) {
//            // LD: 当前为地址页面
//            setResult(RESULT_OK, diZhiFragment.getType());
//        }
//        super.finish();
//    }

    @OnClick(R.id.layout_back)
    public void onClick() {
        finish();
    }
}
