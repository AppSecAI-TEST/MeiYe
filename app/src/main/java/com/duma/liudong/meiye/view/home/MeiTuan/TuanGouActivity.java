package com.duma.liudong.meiye.view.home.MeiTuan;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.BaseXiaLaRvPresenter;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.model.ClassifyBean;
import com.duma.liudong.meiye.model.TuanGouBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.DialogUtil;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.StartUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/17.
 */

public class TuanGouActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_other)
    ImageView imgOther;
    @BindView(R.id.layout_other)
    LinearLayout layoutOther;
    @BindView(R.id.tv_refresh)
    TextView tvRefresh;
    @BindView(R.id.layout_404)
    LinearLayout layout404;
    @BindView(R.id.rv_fenlei)
    RecyclerView rvFenlei;
    @BindView(R.id.tv_fenlei)
    TextView tvFenlei;
    @BindView(R.id.layout_fenlei)
    LinearLayout layoutFenlei;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.layout_city)
    LinearLayout layoutCity;
    @BindView(R.id.tv_paixu)
    TextView tvPaixu;
    @BindView(R.id.layout_paixu)
    LinearLayout layoutPaixu;
    @BindView(R.id.rv_shangping)
    RecyclerView rvShangping;
    @BindView(R.id.ScrollableLayout)
    com.duma.liudong.meiye.widget.ScrollableLayout ScrollableLayout;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;
    @BindView(R.id.layout_kong)
    LinearLayout layoutKong;

    private ClassifyBean bean;

    private List<ClassifyBean> mlist;
    private CommonAdapter<ClassifyBean> fenLeiAdater;
    private String cate_id;
    private BaseXiaLaRvPresenter<TuanGouBean> TuanGouAdapter;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_tuangou);
    }

    @Override
    protected void initData() {
        tvTitle.setText("服务团购");
        StartUtil.setSw(swLoading, this);
        mlist = new ArrayList<>();
        rvFenlei.setLayoutManager(new GridLayoutManager(mActivity, 4));
        rvFenlei.setFocusable(false);
        rvFenlei.setNestedScrollingEnabled(false);
        rvShangping.setFocusable(false);
        rvShangping.setNestedScrollingEnabled(false);
        initAdApter();
        initTuanGouAdapter();
        rvFenlei.setAdapter(fenLeiAdater);
        initFenLeiHttp();
    }

    private void initTuanGouAdapter() {
        TuanGouAdapter = new BaseXiaLaRvPresenter<TuanGouBean>(mActivity, R.layout.rv_tuangou, rvShangping) {
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

            }

            @Override
            protected RecyclerView.LayoutManager initLayoutManager() {
                return new LinearLayoutManager(mActivity);
            }

            @Override
            protected void getView(ViewHolder holder, TuanGouBean tuanGouBean, int position) {

            }
        };
        TuanGouAdapter.setKongView(layoutKong);
        TuanGouAdapter.setType(new TypeToken<ArrayList<ClassifyBean>>() {
        }.getType());
        RequestCall build = OkHttpUtils
                .get()
                .url(Api.Tuangouindex)
                .tag("base")
                .addParams("type", "3")
                .addParams("sort", "")
                .addParams("cate_id", cate_id)
                .addParams("juli", "")
                .addParams("pick_city_id", "")
                .addParams("pick_province_id", "")
                .addParams("pick_district_id", "")
                .build();
    }

    private void initFenLeiHttp() {
        DialogUtil.show(mActivity);
        layout404.setVisibility(View.GONE);
        OkHttpUtils.getInstance().cancelTag(this);
        OkHttpUtils
                .get()
                .tag(this)
                .url(Api.cate)
                .addParams("type", "3")
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        DialogUtil.hide();
                        Type type = new TypeToken<ArrayList<ClassifyBean>>() {
                        }.getType();
                        List<ClassifyBean> list = new Gson().fromJson(result, type);
                        mlist.clear();
                        mlist.addAll(list);
                        fenLeiAdater.notifyDataSetChanged();
                    }

                    @Override
                    protected void onError(String result) {
                        super.onError(result);
                        layout404.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void initAdApter() {
        fenLeiAdater = new CommonAdapter<ClassifyBean>(mActivity, R.layout.rv_text, mlist) {
            @Override
            protected void convert(ViewHolder holder, ClassifyBean classifyBean, int position) {
                holder.setText(R.id.tv_name, classifyBean.getName());
                ImageLoader.withYuan(classifyBean.getImage(), (ImageView) holder.getView(R.id.img_head_pic));
            }
        };
    }

    @OnClick({R.id.layout_back, R.id.tv_refresh, R.id.layout_fenlei, R.id.layout_city, R.id.layout_paixu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.tv_refresh:
                break;
            case R.id.layout_fenlei:
                break;
            case R.id.layout_city:
                break;
            case R.id.layout_paixu:
                break;
        }
    }

    @Override
    public void onRefresh() {
        TuanGouAdapter.Shuaxin();
    }

}
