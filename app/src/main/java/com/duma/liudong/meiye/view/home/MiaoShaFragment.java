package com.duma.liudong.meiye.view.home;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseFragment;
import com.duma.liudong.meiye.base.BaseXiaLaRvPresenter;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.model.ShangPinBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.utils.Ts;
import com.google.gson.reflect.TypeToken;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;

/**
 * Created by liudong on 17/4/10.
 */

public class MiaoShaFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.time_daojishi)
    CountdownView timeDaojishi;
    @BindView(R.id.rv_shangping)
    RecyclerView rvShangping;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;
    @BindView(R.id.layout_10)
    LinearLayout layout10;
    @BindView(R.id.layout_12)
    LinearLayout layout12;
    @BindView(R.id.layout_14)
    LinearLayout layout14;
    @BindView(R.id.layout_16)
    LinearLayout layout16;
    @BindView(R.id.layout_18)
    LinearLayout layout18;
    @BindView(R.id.tv_10)
    TextView tv10;
    @BindView(R.id.tv_10_type)
    TextView tv10Type;
    @BindView(R.id.tv_12)
    TextView tv12;
    @BindView(R.id.tv_12_type)
    TextView tv12Type;
    @BindView(R.id.tv_14)
    TextView tv14;
    @BindView(R.id.tv_14_type)
    TextView tv14Type;
    @BindView(R.id.tv_16)
    TextView tv16;
    @BindView(R.id.tv_16_type)
    TextView tv16Type;
    @BindView(R.id.tv_18)
    TextView tv18;
    @BindView(R.id.tv_18_type)
    TextView tv18Type;

    private BaseXiaLaRvPresenter<ShangPinBean> shangPinBeanBaseXiaLaRvPresenter;
    private MiaoShaActivity activity;
    private RequestCall build;
    private String activity_time = "10";
    private String Now_time = "0";
    NumberFormat numberFormat;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_miaosha;
    }

    @Override
    protected void initData() {
        activity = (MiaoShaActivity) mActivity;
        StartUtil.setSw(swLoading, this);
        numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        initAdapter();
        initTime();
        timeDaojishi.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                initTime();
            }
        });
    }


    private void initAdapter() {
        rvShangping.setFocusable(false);
        rvShangping.setNestedScrollingEnabled(false);
        shangPinBeanBaseXiaLaRvPresenter = new BaseXiaLaRvPresenter<ShangPinBean>(mActivity, R.layout.rv_miaosha, rvShangping) {
            @Override
            protected void hide_loading() {
                swLoading.setRefreshing(false);
            }

            @Override
            protected void show_loading() {
                swLoading.setRefreshing(true);
            }

            @Override
            protected void loadMore() {
                if (shangPinBeanBaseXiaLaRvPresenter.isOne) {
                    shangPinBeanBaseXiaLaRvPresenter.QueryHttp(getBuild());
                }
            }

            @Override
            protected RecyclerView.LayoutManager initLayoutManager() {
                return new LinearLayoutManager(mActivity);
            }

            @Override
            protected void getView(ViewHolder holder, final ShangPinBean shangPinBean, int position) {
                ImageView img_original_img = holder.getView(R.id.img_original_img);
                ImageLoader.with(shangPinBean.getOriginal_img(), img_original_img);
                holder.setText(R.id.tv_goods_name, shangPinBean.getGoods_name());
                holder.setText(R.id.tv_market_price, shangPinBean.getPrice());
                holder.setText(R.id.tv_distance, shangPinBean.getDistance() + "m");
                holder.setText(R.id.tv_shop_price, "¥" + shangPinBean.getMarket_price());
                double num1 = Double.parseDouble(shangPinBean.getSales_sum());
                double num2 = Double.parseDouble(shangPinBean.getStore_count()) + num1;
                String res = numberFormat.format((num1 / num2) * 100);
                holder.setText(R.id.tv_sales_sum, "已付" + res + "%");
                ProgressBar progressBar = holder.getView(R.id.progressBar_sum);
                progressBar.setMax((int) num2);
                progressBar.setProgress((int) num1);
                TextView textView = holder.getView(R.id.tv_qianggou);
                if (activity_time.equals(Now_time)) {
                    textView.setBackgroundColor(MyApplication.getInstance().getResources().getColor(R.color.main_red));
                } else {
                    textView.setBackgroundColor(MyApplication.getInstance().getResources().getColor(R.color.text_hui));
                }
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (activity_time.equals(Now_time)) {
                            StartUtil.toShangPingWeb(mActivity, Api.H5Url() + shangPinBean.getGoods_id());
                        } else {
                            Ts.setText("还没到时间呢~不要着急哦");
                        }
                    }
                });
            }
        };
        shangPinBeanBaseXiaLaRvPresenter.setType(new TypeToken<ArrayList<ShangPinBean>>() {
        }.getType());
    }

    private RequestCall getBuild() {
        shangPinBeanBaseXiaLaRvPresenter.p++;
        build = OkHttpUtils
                .get()
                .tag("base")
                .url(Api.miaosha)
                .addParams("type", activity.getType())
                .addParams("activity_time", activity_time)
                .addParams("p", shangPinBeanBaseXiaLaRvPresenter.p + "")
                .build();
        return build;
    }

    @Override
    protected void onLazyLoad() {
        super.onLazyLoad();
        shangPinBeanBaseXiaLaRvPresenter.QueryHttp(getBuild());
    }

    //当前距离本周末20点的时间
    private long getMillisecond() {
        return (getSundayOfThisWeek().getTime()) + getXiaoShi(20) - new Date().getTime();
    }

    //当前距离本周末x点的时间
    private long getMillisecond(int time) {
        return (getSundayOfThisWeek().getTime()) + getXiaoShi(time) - new Date().getTime();
    }

    public void initTime() {
        if (getMillisecond() > 0 && getMillisecond() < getXiaoShi(10)) {
            if (getMillisecond() < getXiaoShi(2)) {
                //18点到20点之间
                timeDaojishi.start(getMillisecond());
                initTextColor();
                textClick(tv18, tv18Type);
                tv10Type.setText("已结束");
                tv12Type.setText("已结束");
                tv14Type.setText("已结束");
                tv16Type.setText("已结束");
                tv18Type.setText("进行中");
                activity_time = "18";
                Now_time = "18";
            } else if (getMillisecond() < getXiaoShi(4)) {
                //16到18之间
                timeDaojishi.start(getMillisecond() - getXiaoShi(2));
                initTextColor();
                textClick(tv16, tv16Type);
                tv10Type.setText("已结束");
                tv12Type.setText("已结束");
                tv14Type.setText("已结束");
                tv16Type.setText("进行中");
                tv18Type.setText("即将开始");
                activity_time = "16";
                Now_time = "16";
            } else if (getMillisecond() < getXiaoShi(6)) {
                //14到16之间
                timeDaojishi.start(getMillisecond() - getXiaoShi(4));
                initTextColor();
                textClick(tv14, tv14Type);
                tv10Type.setText("已结束");
                tv12Type.setText("已结束");
                tv14Type.setText("进行中");
                tv16Type.setText("即将开始");
                tv18Type.setText("即将开始");
                activity_time = "14";
                Now_time = "14";
            } else if (getMillisecond() < getXiaoShi(8)) {
                //12到14之间
                timeDaojishi.start(getMillisecond() - getXiaoShi(6));
                initTextColor();
                textClick(tv12, tv12Type);
                tv10Type.setText("已结束");
                tv12Type.setText("进行中");
                tv14Type.setText("即将开始");
                tv16Type.setText("即将开始");
                tv18Type.setText("即将开始");
                activity_time = "12";
                Now_time = "12";
            } else {
                //10到12点
                timeDaojishi.start(getMillisecond() - getXiaoShi(8));
                initTextColor();
                textClick(tv10, tv10Type);
                tv10Type.setText("进行中");
                tv12Type.setText("即将开始");
                tv14Type.setText("即将开始");
                tv16Type.setText("即将开始");
                tv18Type.setText("即将开始");
                activity_time = "10";
                Now_time = "10";
            }

        } else {
            //未开始
            initTextColor();
            initText();
            //离周末10点开始的时间
            timeDaojishi.start(getMillisecond(10));
            activity_time = "10";
            Now_time = "0";
        }
        if (activity.isOne) {
            activity.isOne = false;
            shangPinBeanBaseXiaLaRvPresenter.QueryHttp(getBuild());
        }

    }

    //今天到周末的毫秒
    public static Date getSundayOfThisWeek() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.MILLISECOND, 0);
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 7);
        return c.getTime();
    }

    //每小时多少毫秒
    public int getXiaoShi(int num) {
        return num * 60 * 60 * 1000;
    }

    @Override
    public void onRefresh() {
        shangPinBeanBaseXiaLaRvPresenter.Shuaxin();
    }


    @OnClick({R.id.layout_10, R.id.layout_12, R.id.layout_14, R.id.layout_16, R.id.layout_18})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_10:
                activity_time = "10";
                tvOnclick(tv10, tv10Type);
                break;
            case R.id.layout_12:
                activity_time = "12";
                tvOnclick(tv12, tv12Type);
                break;
            case R.id.layout_14:
                activity_time = "14";
                tvOnclick(tv14, tv14Type);
                break;
            case R.id.layout_16:
                activity_time = "16";
                tvOnclick(tv16, tv16Type);
                break;
            case R.id.layout_18:
                activity_time = "18";
                tvOnclick(tv18, tv18Type);
                break;
        }
        onRefresh();
    }

    private void tvOnclick(TextView tv1, TextView tv2) {
        initTextColor();
        textClick(tv1, tv2);
    }

    private void initTextColor() {
        textHui(tv10, tv10Type);
        textHui(tv12, tv12Type);
        textHui(tv14, tv14Type);
        textHui(tv16, tv16Type);
        textHui(tv18, tv18Type);
    }

    private void initText() {
        tv10Type.setText("未开始");
        tv12Type.setText("未开始");
        tv14Type.setText("未开始");
        tv16Type.setText("未开始");
        tv18Type.setText("未开始");
    }

    private void textHui(TextView tv1, TextView tv2) {
        tv1.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.text_hui));
        tv2.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.text_hui));
    }

    private void textClick(TextView tv1, TextView tv2) {
        tv1.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.main_red));
        tv2.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.main_red));
    }

}
