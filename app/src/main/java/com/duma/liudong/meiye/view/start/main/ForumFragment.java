package com.duma.liudong.meiye.view.start.main;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseFragment;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.base.MyViewPagerAdapter;
import com.duma.liudong.meiye.model.ForumBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.DialogUtil;
import com.duma.liudong.meiye.view.forum.LunTanClassiftFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by liudong on 17/3/14.
 */

public class ForumFragment extends BaseFragment {


    @BindView(R.id.tabLayout_bar)
    TabLayout tabLayoutBar;
    @BindView(R.id.viewPater_bar)
    ViewPager viewPaterBar;

    public List<ForumBean> list;
    public boolean isOne = true;
    public List<LunTanClassiftFragment> mList;
    public boolean isSuccess = false;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_forum;
    }

    @Override
    protected void initData() {
        mList = new ArrayList<>();
    }

    @Override
    protected void onLazyLoad() {
        if (!isSuccess) {
            DialogUtil.show(mActivity);
            OkHttpUtils.getInstance().cancelTag(this);
            OkHttpUtils
                    .get()
                    .tag(this)
                    .url(Api.cat)
                    .build()
                    .execute(new MyStringCallback() {
                        @Override
                        public void onMySuccess(String result) {
                            DialogUtil.hide();
                            Type type = new TypeToken<ArrayList<ForumBean>>() {
                            }.getType();
                            list = new Gson().fromJson(result, type);
                            initTab();
                        }
                    });
        } else {
            mList.get(0).onLazyLoad();
        }

    }

    private void initTab() {
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(mActivity.getSupportFragmentManager());
        for (int i = 0; i < list.size(); i++) {
            LunTanClassiftFragment fragment = new LunTanClassiftFragment();
            mList.add(fragment);
            viewPagerAdapter.addFragment(fragment, list.get(i).getCat_name());
        }
        viewPaterBar.setOffscreenPageLimit(list.size());
        viewPaterBar.setAdapter(viewPagerAdapter);
        tabLayoutBar.setupWithViewPager(viewPaterBar);
        isSuccess = true;
    }

    public String getCat_id() {
        return list.get(viewPaterBar.getCurrentItem()).getCat_id();
    }
}
