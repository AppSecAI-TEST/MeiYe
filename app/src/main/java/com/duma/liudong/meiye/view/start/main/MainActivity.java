package com.duma.liudong.meiye.view.start.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.BaseFragment;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.view.home.MessageActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.rdoBtn_home)
    RadioButton rdoBtnHome;
    @BindView(R.id.rdoBtn_classify)
    RadioButton rdoBtnClassify;
    @BindView(R.id.rdoBtn_forum)
    RadioButton rdoBtnForum;
    @BindView(R.id.rdoBtn_shopping_cart)
    RadioButton rdoBtnShoppingCart;
    @BindView(R.id.rdoBtn_me)
    RadioButton rdoBtnMe;
    @BindView(R.id.layout_bar_fragment)
    FrameLayout layoutBarFragment;
    @BindView(R.id.layout_message)
    LinearLayout layoutMessage;
    @BindView(R.id.layout_tab_fragment)
    FrameLayout layoutTabFragment;
    @BindView(R.id.radioGroup_p)
    RadioGroup radioGroupP;

    HomeFragment homeFragment;
    ClassifyFragment classifyFragment;
    ForumFragment forumFragment;
    ShoppingCartFragment shoppingCartFragment;
    MeFragment meFragment;
    SearchBarFragment searchBarFragment;
    ForumBarFragment forumBarFragment;
    ShoppingCartBarFragment shoppingCartBarFragment;
    MeBarFragment meBarFragment;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initData() {
        addAllFragment();
        showHome();
        rdoBtnShoppingCart.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        if (!StartUtil.isLogin()) {
                            StartUtil.toLogin(mActivity);
                            return true;
                        }
                }
                return false;
            }
        });
    }

    public void showHome() {
        showFramgment(getHomeFragment(), getSearch_BarFragment());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //去掉activit被杀死后保存的回调
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getMeFragment().isVisible()) {
            getMeFragment().refresh();
        }
        if (getHomeFragment().isVisible()) {
            getHomeFragment().onLazyLoad();
        }
    }

    @OnClick({R.id.layout_message, R.id.rdoBtn_home, R.id.rdoBtn_classify, R.id.rdoBtn_forum, R.id.rdoBtn_shopping_cart, R.id.rdoBtn_me})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rdoBtn_home:
                showHome();
                break;
            case R.id.rdoBtn_classify:
                showFramgment(getClassifyFragment(), getSearch_BarFragment());
                break;
            case R.id.rdoBtn_forum:
                showFramgment(getForumFragment(), getForum_BarFragment());
                break;
            case R.id.rdoBtn_shopping_cart:
                showFramgment(getShoppingCartFragment(), getShoppingCart_BarFragment());
                break;
            case R.id.rdoBtn_me:
                showFramgment(getMeFragment(), getMe_BarFragment());
                break;
            case R.id.layout_message:
                if (!StartUtil.isLogin()) {
                    StartUtil.toLogin(mActivity);
                    return;
                }
                startActivity(new Intent(MainActivity.this, MessageActivity.class));
                break;
        }
    }

    //切换tab
    public void showFramgment(BaseFragment fragment, BaseFragment bar_fragment) {
        hideAllFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.show(fragment);
        transaction.show(bar_fragment);
        if (!fragment.isVisible()) {
            fragment.setUserVisibleHint(true);
        }
        if (!bar_fragment.isVisible()) {
            bar_fragment.setUserVisibleHint(true);
        }
        transaction.commit();
    }

    //隐藏全部
    private void hideAllFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.hide(getHomeFragment());
        transaction.hide(getClassifyFragment());
        transaction.hide(getForumFragment());
        transaction.hide(getShoppingCartFragment());
        transaction.hide(getMeFragment());
        //bar
        transaction.hide(getSearch_BarFragment());
        transaction.hide(getForum_BarFragment());
        transaction.hide(getShoppingCart_BarFragment());
        transaction.hide(getMe_BarFragment());
        transaction.commit();
    }

    //添加全部
    private void addAllFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.layout_tab_fragment, getHomeFragment());
        transaction.add(R.id.layout_tab_fragment, getClassifyFragment());
        transaction.add(R.id.layout_tab_fragment, getForumFragment());
        transaction.add(R.id.layout_tab_fragment, getShoppingCartFragment());
        transaction.add(R.id.layout_tab_fragment, getMeFragment());
        //bar
        transaction.add(R.id.layout_bar_fragment, getSearch_BarFragment());
        transaction.add(R.id.layout_bar_fragment, getForum_BarFragment());
        transaction.add(R.id.layout_bar_fragment, getShoppingCart_BarFragment());
        transaction.add(R.id.layout_bar_fragment, getMe_BarFragment());
        transaction.commit();
    }

    /**
     * fragment 的初始化
     *
     * @return
     */

    HomeFragment getHomeFragment() {
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        return homeFragment;
    }

    ClassifyFragment getClassifyFragment() {
        if (classifyFragment == null) {
            classifyFragment = new ClassifyFragment();
        }
        return classifyFragment;
    }

    ForumFragment getForumFragment() {
        if (forumFragment == null) {
            forumFragment = new ForumFragment();
        }
        return forumFragment;
    }

    ShoppingCartFragment getShoppingCartFragment() {
        if (shoppingCartFragment == null) {
            shoppingCartFragment = new ShoppingCartFragment();
        }
        return shoppingCartFragment;
    }

    MeFragment getMeFragment() {
        if (meFragment == null) {
            meFragment = new MeFragment();
        }
        return meFragment;
    }

    SearchBarFragment getSearch_BarFragment() {
        if (searchBarFragment == null) {
            searchBarFragment = new SearchBarFragment();
        }
        return searchBarFragment;
    }

    ForumBarFragment getForum_BarFragment() {
        if (forumBarFragment == null) {
            forumBarFragment = new ForumBarFragment();
        }
        return forumBarFragment;
    }

    ShoppingCartBarFragment getShoppingCart_BarFragment() {
        if (shoppingCartBarFragment == null) {
            shoppingCartBarFragment = new ShoppingCartBarFragment();
        }
        return shoppingCartBarFragment;
    }

    MeBarFragment getMe_BarFragment() {
        if (meBarFragment == null) {
            meBarFragment = new MeBarFragment();
        }
        return meBarFragment;
    }
}
