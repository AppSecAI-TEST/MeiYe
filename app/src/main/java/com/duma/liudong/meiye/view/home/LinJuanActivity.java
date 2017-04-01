package com.duma.liudong.meiye.view.home;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.base.MyViewPagerAdapter;
import com.duma.liudong.meiye.model.ClassifyBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.DialogUtil;
import com.duma.liudong.meiye.utils.Lg;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/3/29.
 */

public class LinJuanActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_other)
    ImageView imgOther;
    @BindView(R.id.layout_other)
    LinearLayout layoutOther;
    @BindView(R.id.tabLayout_bar)
    TabLayout tabLayoutBar;
    @BindView(R.id.layout_fenlei)
    LinearLayout layoutFenlei;
    @BindView(R.id.viewPater_bar)
    ViewPager viewPaterBar;
    @BindView(R.id.tv_refresh)
    TextView tvRefresh;
    @BindView(R.id.layout_404)
    LinearLayout layout404;
    @BindView(R.id.view_show)
    View viewShow;
    @BindView(R.id.view_toumin)
    View viewToumin;

    private PopupWindow popupWindow;
    private CommonAdapter<ClassifyBean> adapter;
    private TextView tv_fenlei_num;
    public boolean isOne = true;
    private List<ClassifyBean> mList;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_linjuan);
    }

    @Override
    protected void initData() {
        viewToumin.setVisibility(View.GONE);
        tvTitle.setText("领卷中心");
        mList = new ArrayList<>();
        getFenleiHttp();
        final View popipWindow_view = getLayoutInflater().inflate(R.layout.pop_linjuan, null, false);
        popupWindow = new PopupWindow(popipWindow_view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        adapter = new CommonAdapter<ClassifyBean>(mActivity, R.layout.rv_linjuan, mList) {
            @Override
            protected void convert(ViewHolder holder, ClassifyBean classifyBean, final int position) {
                TextView tv_fenlei = holder.getView(R.id.tv_fenlei);
                tv_fenlei.setText(classifyBean.getName());
                tv_fenlei.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        disMiss();
                        tabLayoutBar.getTabAt(position).select();
                    }
                });
            }
        };
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                disMiss();
            }
        });
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        ConstraintLayout layout_fenlei = (ConstraintLayout) popipWindow_view.findViewById(R.id.layout_fenlei);
        tv_fenlei_num = (TextView) popipWindow_view.findViewById(R.id.tv_fenlei_num);
        RecyclerView rv_fenlei = (RecyclerView) popipWindow_view.findViewById(R.id.rv_fenlei);
        rv_fenlei.setLayoutManager(new GridLayoutManager(mActivity, 4));
        rv_fenlei.setAdapter(adapter);
        layout_fenlei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disMiss();
            }
        });
    }

    private int getPosition() {
        return tabLayoutBar.getSelectedTabPosition() == -1 ? 0 : tabLayoutBar.getSelectedTabPosition();
    }

    public String getId() {
        Lg.e("getPosition:  " + getPosition());
        Lg.e("getId:  " + mList.get(getPosition()).getId());
        return mList.get(getPosition()).getId();
    }

    private void getFenleiHttp() {
        layout404.setVisibility(View.GONE);
        layoutFenlei.setVisibility(View.INVISIBLE);
        DialogUtil.show(mActivity);
        OkHttpUtils.getInstance().cancelTag("getFenleiHttp");
        OkHttpUtils
                .get()
                .tag("getFenleiHttp")
                .url(Api.cate)
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
                        DialogUtil.hide();
                        layout404.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void initFenlei(String result) {
        layoutFenlei.setVisibility(View.VISIBLE);
        Type type = new TypeToken<List<ClassifyBean>>() {
        }.getType();
        List<ClassifyBean> list = new Gson().fromJson(result, type);
        mList.addAll(list);
        //刷新下拉分类的数据
        adapter.notifyDataSetChanged();
        tv_fenlei_num.setText("共" + mList.size() + "个分类");
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        for (int i = 0; i < mList.size(); i++) {
            viewPagerAdapter.addFragment(new LinJuanFragment(), mList.get(i).getName());
        }
        viewPaterBar.setOffscreenPageLimit(mList.size());
        viewPaterBar.setAdapter(viewPagerAdapter);
        tabLayoutBar.setupWithViewPager(viewPaterBar);
    }

    @Override
    protected void OnBack() {
        if (popupWindow.isShowing()) {
            disMiss();
        } else {
            finish();
        }
    }

    private void disMiss() {
        viewToumin.setVisibility(View.GONE);
        popupWindow.dismiss();
    }

    @OnClick({R.id.tv_refresh, R.id.layout_back, R.id.layout_fenlei})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.layout_fenlei:
                popupWindow.showAsDropDown(viewShow);
                viewToumin.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_refresh:
                getFenleiHttp();
                break;
        }
    }

}
