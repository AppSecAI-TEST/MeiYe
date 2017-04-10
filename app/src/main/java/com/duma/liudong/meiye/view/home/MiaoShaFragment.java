package com.duma.liudong.meiye.view.home;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseFragment;
import com.duma.liudong.meiye.base.BaseRvAdapter;
import com.duma.liudong.meiye.model.YouHuiJuanBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.StartUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
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

    private BaseRvAdapter<YouHuiJuanBean> adapter;
    private MiaoShaActivity activity;
    private RequestCall build;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_miaosha;
    }

    @Override
    protected void initData() {
        activity = (MiaoShaActivity) mActivity;
        StartUtil.setSw(swLoading, this);
        timeDaojishi.start((getSundayOfThisWeek().getTime()) + getXiaoShi(10) - new Date().getTime());
    }

    private RequestCall getBuild() {
        build = OkHttpUtils
                .get()
                .tag("base")
                .url(Api.miaosha)
                .addParams("type", activity.getType())
                .addParams("activity_time", "10")
                .addParams("p", "1")
                .build();
        return build;
    }

    @Override
    protected void onLazyLoad() {
        super.onLazyLoad();
//        adapter.QueryHttp(getBuild());
    }


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

    public int getXiaoShi(int num) {
        return num * 60 * 60 * 1000;
    }

    @Override
    public void onRefresh() {
        adapter.QueryHttp(build);
    }

}
