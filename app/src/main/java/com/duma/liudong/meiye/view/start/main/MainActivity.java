package com.duma.liudong.meiye.view.start.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.ActivityCollector;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.BaseFragment;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.model.MessageBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.utils.Ts;
import com.duma.liudong.meiye.view.home.MessageActivity;
import com.duma.liudong.meiye.view.start.LunTanFragment;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.liudong.meiye.utils.Constants.store_id;

public class MainActivity extends BaseActivity implements AMapLocationListener {

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
    @BindView(R.id.tv_dian)
    TextView tvDian;

    HomeFragment homeFragment;
    ClassifyFragment classifyFragment;
    ForumFragment forumFragment;
    ShoppingCartFragment shoppingCartFragment;
    MeFragment meFragment;
    SearchBarFragment searchBarFragment;
    ForumBarFragment forumBarFragment;
    ShoppingCartBarFragment shoppingCartBarFragment;
    MeBarFragment meBarFragment;

    LunTanFragment lunTanFragment;

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;


    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initData() {
        initGps();
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
        MyApplication.getSpUtils2().put(Constants.city, "");

    }

    private void initGps() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);

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
        getSearch_BarFragment().setTvName();
        if (MyApplication.getSpUtils2().getString(Constants.city).equals("") || MyApplication.getSpUtils2().getString(Constants.city).equals("1")) {
            //启动定位
            mLocationClient.startLocation();
        }
        if (getMeFragment().isVisible()) {
            getMeFragment().refresh();
        }
        if (getShoppingCartFragment().isVisible()) {
            getShoppingCartFragment().onLazyLoad();
        }
        if (getHomeFragment().isVisible()) {
            getHomeFragment().onLazyLoad();
        }
//        if (getForumFragment().isVisible()) {
//            getForumFragment().onLazyLoad();
//        }
        if (StartUtil.isLogin()) {
            OkHttpUtils
                    .post()
                    .tag(this)
                    .url(Api.message)
                    .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                    .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                    .addParams("store_id", store_id)
                    .build()
                    .execute(new MyStringCallback() {
                        @Override
                        public void onMySuccess(String result) {
                            List<MessageBean> list = new ArrayList<>();
                            try {
                                JSONObject jsonObject = new JSONObject(result);
                                MessageBean bean;
                                bean = new Gson().fromJson(jsonObject.getString("sys_msg"), MessageBean.class);
                                if (!bean.getMessage_type().equals("")) {
                                    if (bean.getNo_read().equals("")) {
                                        bean.setNo_read("0");
                                    }
                                    list.add(bean);
                                }
                                bean = new Gson().fromJson(jsonObject.getString("money_msg"), MessageBean.class);
                                if (!bean.getMessage_type().equals("")) {
                                    if (bean.getNo_read().equals("")) {
                                        bean.setNo_read("0");
                                    }
                                    list.add(bean);
                                }
                                bean = new Gson().fromJson(jsonObject.getString("mail_msg"), MessageBean.class);
                                if (!bean.getMessage_type().equals("")) {
                                    if (bean.getNo_read().equals("")) {
                                        bean.setNo_read("0");
                                    }
                                    list.add(bean);
                                }
                                int s = 0;
                                for (int i = 0; i < list.size(); i++) {
                                    s = s + Integer.parseInt(list.get(i).getNo_read());
                                }
                                if (s == 0) {
                                    tvDian.setVisibility(View.GONE);
                                } else {
                                    tvDian.setVisibility(View.VISIBLE);
                                }
                            } catch (JSONException e) {
                                Ts.JsonErroy();
                            }
                        }
                    });
//            OkHttpUtils
//                    .get()
//                    .tag(this)
//                    .url(Api.tixing_order)
//                    .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
//                    .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
//                    .build()
//                    .execute(new MyStringCallback() {
//                        @Override
//                        public void onMySuccess(String result) {
//                            MjXIaoXiBean mjXIaoXiBean = new Gson().fromJson(result, MjXIaoXiBean.class);
//                            if (!mjXIaoXiBean.getCount().equals("0")) {
//                                StartUtil.sendNotification(mActivity);
//                            }
//                        }
//                    });
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
                Intent intent = new Intent(MainActivity.this, MessageActivity.class);
                intent.putExtra("store_id", "");
                startActivity(intent);
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

    public ForumFragment getForumFragment() {
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

    LunTanFragment getLunTanFragment() {
        if (lunTanFragment == null) {
            lunTanFragment = new LunTanFragment();
        }
        return lunTanFragment;
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

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //可在其中解析amapLocation获取相应内容。
                MyApplication.getSpUtils2().put(Constants.city, amapLocation.getCity());
                MyApplication.getSpUtils2().put(Constants.lat, amapLocation.getLatitude() + "");
                MyApplication.getSpUtils2().put(Constants.lng, amapLocation.getLongitude() + "");
            } else {
                MyApplication.getSpUtils2().put(Constants.city, "1");
                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
            getSearch_BarFragment().setTvName();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
    }

    private long exitTime = 0;

    @Override
    protected void OnBack() {
        if ((System.currentTimeMillis() - exitTime) > 2000) // System.currentTimeMillis()无论何时调用，肯定大于2000
        {
            Ts.setText("再按一次退出App");
            exitTime = System.currentTimeMillis();
        } else {
            ActivityCollector.finishAll();
            System.exit(0);
            MyApplication.getSpUtils2().put(Constants.city, "");
        }
    }
}
