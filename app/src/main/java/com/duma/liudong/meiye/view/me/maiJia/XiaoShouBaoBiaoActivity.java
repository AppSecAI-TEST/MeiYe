package com.duma.liudong.meiye.view.me.maiJia;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.model.XiaoShouBaoBiaoBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.StartUtil;
import com.google.gson.Gson;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/22.
 */

public class XiaoShouBaoBiaoActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_other)
    ImageView imgOther;
    @BindView(R.id.layout_other)
    LinearLayout layoutOther;
    @BindView(R.id.tv_zou_time)
    TextView tvZouTime;
    @BindView(R.id.tv_zou_Liang)
    TextView tvZouLiang;
    @BindView(R.id.tv_zou_Liang_up)
    TextView tvZouLiangUp;
    @BindView(R.id.tv_zou_e)
    TextView tvZouE;
    @BindView(R.id.tv_zou_e_up)
    TextView tvZouEUp;
    @BindView(R.id.tv_yue_time)
    TextView tvYueTime;
    @BindView(R.id.tv_yue_liang)
    TextView tvYueLiang;
    @BindView(R.id.tv_yue_liang_up)
    TextView tvYueLiangUp;
    @BindView(R.id.tv_yue_e)
    TextView tvYueE;
    @BindView(R.id.tv_yue_e_up)
    TextView tvYueEUp;
    @BindView(R.id.tv_nian_time)
    TextView tvNianTime;
    @BindView(R.id.tv_nian_liang)
    TextView tvNianLiang;
    @BindView(R.id.tv_nian_liang_up)
    TextView tvNianLiangUp;
    @BindView(R.id.tv_nian_e)
    TextView tvNianE;
    @BindView(R.id.tv_nian_e_up)
    TextView tvNianEUp;
    @BindView(R.id.rv_xiaoshou)
    RecyclerView rvXiaoshou;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;

    private String store_id;
    private XiaoShouBaoBiaoBean baoBiaoBean;
    private List<XiaoShouBaoBiaoBean.MarkBean> mList;
    private CommonAdapter<XiaoShouBaoBiaoBean.MarkBean> adapter;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_xiaoshoubaobiao);
    }

    @Override
    protected void initData() {
        tvTitle.setText("销售报表");
        StartUtil.setSw(swLoading, this);
        store_id = getIntent().getStringExtra("id");
        mList = new ArrayList<>();
        initAdapter();
        onRefresh();
    }

    private void initAdapter() {
        rvXiaoshou.setFocusable(false);
        rvXiaoshou.setNestedScrollingEnabled(false);
        rvXiaoshou.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new CommonAdapter<XiaoShouBaoBiaoBean.MarkBean>(mActivity, R.layout.rv_xiaoshou, mList) {
            @Override
            protected void convert(ViewHolder holder, XiaoShouBaoBiaoBean.MarkBean markBean, int position) {
                holder.setText(R.id.tv_time, markBean.getMoon() + "月")
                        .setText(R.id.tv_count, markBean.getCount() + "单")
                        .setText(R.id.tv_money, "¥" + markBean.getMoney());
            }
        };
        rvXiaoshou.setAdapter(adapter);
    }


    @OnClick(R.id.layout_back)
    public void onClick() {
        finish();
    }

    @Override
    public void onRefresh() {
        swLoading.setRefreshing(true);
        OkHttpUtils
                .get()
                .tag(this)
                .url(Api.sellStatement)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .addParams("store_id", store_id)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        swLoading.setRefreshing(false);
                        baoBiaoBean = new Gson().fromJson(result, XiaoShouBaoBiaoBean.class);
                        getRes();
                    }
                });
    }

    private void getRes() {
        tvZouLiang.setText(baoBiaoBean.getWeek().getCount());
        tvZouE.setText(baoBiaoBean.getWeek().getMoney() + "元");
        tvZouLiangUp.setText(baoBiaoBean.getUpweek().getCount());
        tvZouEUp.setText(baoBiaoBean.getUpweek().getMoney() + "元");

        tvYueLiang.setText(baoBiaoBean.getMonth().getCount());
        tvYueE.setText(baoBiaoBean.getMonth().getMoney() + "元");
        tvYueLiangUp.setText(baoBiaoBean.getUpmonth().getCount());
        tvYueEUp.setText(baoBiaoBean.getUpmonth().getMoney() + "元");

        tvNianLiang.setText(baoBiaoBean.getYear().getCount());
        tvNianE.setText(baoBiaoBean.getYear().getMoney() + "元");
        tvNianLiangUp.setText(baoBiaoBean.getUpyear().getCount());
        tvNianEUp.setText(baoBiaoBean.getUpyear().getMoney() + "元");

        mList.clear();
        mList.addAll(baoBiaoBean.getMark());
        adapter.notifyDataSetChanged();
    }
}
