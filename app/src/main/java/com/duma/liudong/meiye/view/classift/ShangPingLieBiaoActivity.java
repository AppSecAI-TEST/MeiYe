package com.duma.liudong.meiye.view.classift;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.base.MyViewPagerAdapter;
import com.duma.liudong.meiye.model.ClassifyBean;
import com.duma.liudong.meiye.model.ShaiXuanBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.DialogUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/3/30.
 */

public class ShangPingLieBiaoActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_other)
    ImageView imgOther;
    @BindView(R.id.layout_other)
    LinearLayout layoutOther;
    @BindView(R.id.layout_tag_flow)
    TagFlowLayout layoutTagFlow;
    @BindView(R.id.layout_tag_Jiage)
    TagFlowLayout layoutTagJiage;
    @BindView(R.id.btn_chongzhi)
    Button btnChongzhi;
    @BindView(R.id.btn_queding)
    Button btnQueding;
    @BindView(R.id.layout_right)
    LinearLayout layoutRight;
    @BindView(R.id.layout_drawerLayout)
    DrawerLayout layoutDrawerLayout;
    @BindView(R.id.tabLayout_bar)
    TabLayout tabLayoutBar;
    @BindView(R.id.tv_refresh)
    TextView tvRefresh;
    @BindView(R.id.layout_404)
    LinearLayout layout404;
    @BindView(R.id.viewPater_bar)
    ViewPager viewPaterBar;

    public int type = 0;//当前试图类型
    public List<ShaiXuanBean> list_shaixuan;
    public List<String> list_jiage;
    private TagAdapter<ShaiXuanBean> shaixuanAdapter;
    private TagAdapter<String> jiaGeAdapter;
    public String key, Value, title, goods_type;
    public boolean isOne = true;
    private List<ClassifyBean> mList;
    private MyViewPagerAdapter viewPagerAdapter;

    private List<PaiXuFragment> paiXuFragmentList;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_shangpingliebiao);
    }

    @Override
    protected void initData() {
        layoutDrawerLayout.setFocusable(true);
        key = getIntent().getStringExtra("key");
        Value = getIntent().getStringExtra("Value");
        title = getIntent().getStringExtra("title");
        goods_type = getIntent().getStringExtra("goods_type");

        if (key.equals(Constants.marketing_type)) {
            if (Value.equals("4") || Value.equals("5") || Value.equals("6")) {
                //有抢购的type
                type = 3;
                layoutOther.setVisibility(View.GONE);
            }
        }

        tvTitle.setText(title);
        imgOther.setImageDrawable(mActivity.getResources().getDrawable(R.drawable.l4q));
        initDrawable();

        viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        mList = new ArrayList<>();
        paiXuFragmentList = new ArrayList<>();
        tabLayoutBar.setVisibility(View.GONE);
        if (goods_type.equals("2")) {
            //拼团  有tab
            tabLayoutBar.setVisibility(View.VISIBLE);
            initClassift();
        } else {
            //没有tab
            PaiXuFragment fragment = new PaiXuFragment();
            viewPagerAdapter.addFragment(fragment, "demo");
            paiXuFragmentList.add(fragment);
            viewPaterBar.setOffscreenPageLimit(1);
            viewPaterBar.setAdapter(viewPagerAdapter);
            tabLayoutBar.setupWithViewPager(viewPaterBar);
        }
    }

    private void initClassift() {
        DialogUtil.show(mActivity);
        layout404.setVisibility(View.GONE);
        OkHttpUtils.getInstance().cancelTag("initClassift");
        OkHttpUtils
                .get()
                .tag("initClassift")
                .url(Api.cate)
                .addParams("type", "2")
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        DialogUtil.hide();
                        initFenlei(result);
                    }

                    @Override
                    protected void onError(String result) {
                        super.onError(result);
                        layout404.setVisibility(View.VISIBLE);
                    }
                });
    }

    private int getPosition() {
        return tabLayoutBar.getSelectedTabPosition() == -1 ? 0 : tabLayoutBar.getSelectedTabPosition();
    }

    public String getId() {
        return mList.get(getPosition()).getId();
    }

    private void initFenlei(String result) {
        Type type = new TypeToken<List<ClassifyBean>>() {
        }.getType();
        List<ClassifyBean> list = new Gson().fromJson(result, type);
        mList.addAll(list);
        for (int i = 0; i < mList.size(); i++) {
            PaiXuFragment fragment = new PaiXuFragment();
            viewPagerAdapter.addFragment(fragment, mList.get(i).getName());
            paiXuFragmentList.add(fragment);
        }
        viewPaterBar.setOffscreenPageLimit(mList.size());
        viewPaterBar.setAdapter(viewPagerAdapter);
        tabLayoutBar.setupWithViewPager(viewPaterBar);
    }

    private void initDrawable() {
        list_shaixuan = new ArrayList<>();
        list_shaixuan.add(new ShaiXuanBean("交易保障", "filter_jiaoyi"));
        list_shaixuan.add(new ShaiXuanBean("7天包退换", "filter_qitian"));
        list_shaixuan.add(new ShaiXuanBean("正品保障", "filter_zhengpin"));
        list_shaixuan.add(new ShaiXuanBean("2小时发货", "filter_erxiashi"));
        list_jiage = new ArrayList<>();
        list_jiage.add("价格从高到低");
        list_jiage.add("价格从低到高");
        final LayoutInflater mInflater = LayoutInflater.from(mActivity);
        layoutTagFlow.setAdapter(shaixuanAdapter = new TagAdapter<ShaiXuanBean>(list_shaixuan) {
            @Override
            public View getView(FlowLayout parent, int position, ShaiXuanBean s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.btn_btn,
                        layoutTagFlow, false);
                tv.setText(s.getRes());
                return tv;
            }
        });
        layoutTagJiage.setAdapter(jiaGeAdapter = new TagAdapter<String>(list_jiage) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.btn_btn,
                        layoutTagFlow, false);
                tv.setText(s);
                return tv;
            }
        });
    }


    public void openDrawer() {
        layoutDrawerLayout.openDrawer(layoutRight);
    }

    @OnClick({R.id.layout_back, R.id.layout_other, R.id.btn_chongzhi, R.id.btn_queding, R.id.tv_refresh})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.layout_other:
                if (type == 0) {
                    type = 1;
                } else {
                    type = 0;
                }
                paiXuFragmentList.get(getPosition()).setType();
                break;
            case R.id.btn_chongzhi:
                shaixuanAdapter.notifyDataChanged();
                jiaGeAdapter.notifyDataChanged();
                break;
            case R.id.btn_queding:
                layoutDrawerLayout.closeDrawer(layoutRight);
                paiXuFragmentList.get(getPosition()).onRefresh();
                break;
            case R.id.tv_refresh:
                //分类没有加载出来的刷新
                initClassift();
                break;
        }
    }


    @Override
    protected void OnBack() {
        if (layoutDrawerLayout.isDrawerOpen(layoutRight)) {
            layoutDrawerLayout.closeDrawer(layoutRight);
        } else {
            finish();
        }
    }

}
