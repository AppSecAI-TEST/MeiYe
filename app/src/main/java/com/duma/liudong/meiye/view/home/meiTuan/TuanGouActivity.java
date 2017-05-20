package com.duma.liudong.meiye.view.home.meiTuan;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.BasePopWindos;
import com.duma.liudong.meiye.base.BaseXiaLaRvPresenter;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.model.CityBean;
import com.duma.liudong.meiye.model.ClassifyBean;
import com.duma.liudong.meiye.model.SortBean;
import com.duma.liudong.meiye.model.TuanGouBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.DialogUtil;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.Lg;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.utils.Ts;
import com.duma.liudong.meiye.view.home.MessageActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.request.RequestCall;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/17.
 */

public class TuanGouActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, com.duma.liudong.meiye.widget.ScrollableLayout.OnScrollListener {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_refresh)
    TextView tvRefresh;
    @BindView(R.id.layout_404)
    LinearLayout layout404;
    @BindView(R.id.rv_fenlei)
    RecyclerView rvFenlei;
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
    @BindView(R.id.view_toumin)
    View viewToumin;
    @BindView(R.id.layout_show)
    View layoutShow;
    @BindView(R.id.layout_message)
    LinearLayout layoutMessage;
    @BindView(R.id.tv_fenlei)
    TextView tvFenlei;
    @BindView(R.id.layout_ss)
    LinearLayout layoutSs;

    private ClassifyBean bean;

    private List<ClassifyBean> mlist;//top分类的list
    private List<ClassifyBean> FenleiList;//分类菜单的list
    private List<SortBean> PaixuList;//排序菜单list
    private List<CityBean> QuyuList;//区域菜单list
    private List<CityBean.ChildrenBean> JieDaoList;//街道菜单list
    private CommonAdapter<ClassifyBean> fenLeiAdater;
    private BaseXiaLaRvPresenter<TuanGouBean> TuanGouAdapter;
    private DecimalFormat df;
    private BasePopWindos QuYuPopWindos, FenLeiPopWindos, PaiXunWindos;
    private ValueAnimator anim;//移动动画对象
    private int onClick = 0;//当前选中的那个菜单
    private CommonAdapter<ClassifyBean> popAdapter;//pop分类rv
    private CommonAdapter<SortBean> paiXuPopAdapter;//pop排序rv
    private CommonAdapter<CityBean> cityPopAdapter;//pop城市rv
    private CommonAdapter<CityBean.ChildrenBean> JieDaoPopAdapter;//pop街道rv
    private boolean isCityOne = false;//是否获得当前城市列表
    private CityBean cityBean;//区域菜单的附近
    private String sort = "";//排序筛选 为空默认
    private String cate_id = "";//分类id
    private String pick_district_id = "";//区域id
    private String pick_street_id = "";//街道id

    private String onClickCity = "";//当前选中的city

    private String res = "";//传过来的小分类

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_tuangou);
    }

    @Override
    protected void initData() {
        res = getIntent().getStringExtra("res");
        df = new DecimalFormat("0.0");
        StartUtil.setSw(swLoading, this);
        mlist = new ArrayList<>();
        FenleiList = new LinkedList<>();
        PaixuList = new ArrayList<>();
        QuyuList = new ArrayList<>();
        JieDaoList = new ArrayList<>();
        initPaixuList();
        rvFenlei.setLayoutManager(new GridLayoutManager(mActivity, 4));
        rvFenlei.setFocusable(false);
        rvFenlei.setNestedScrollingEnabled(false);
        rvShangping.setFocusable(false);
        rvShangping.setNestedScrollingEnabled(false);
        initAdApter();
        initTuanGouAdapter();
        rvFenlei.setAdapter(fenLeiAdater);
        ScrollableLayout.setOnScrollListener(this);
        ScrollableLayout.getHelper().setCurrentScrollableContainer(rvShangping);
        initPopWindos();
        initAnimator();
        initFenLeiHttp();//获取分类列表
    }


    private void initAnimator() {
        anim = new ValueAnimator();
//        setValue();
        anim.setDuration(300);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                ScrollableLayout.scrollTo(0, (int) currentValue - 1);
            }
        });
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                PopShow();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void PopShow() {
        switch (onClick) {
            case 0:
                FenLeiPopWindos.Show(layoutShow);
                break;
            case 1:
                if (isCityOne) {
                    cityPopAdapter.notifyDataSetChanged();
                    QuYuPopWindos.Show(layoutShow);
                } else {
                    getCityHttp();
                }
                break;
            case 2:
                PaiXunWindos.Show(layoutShow);
                break;
            case 3:
                onRefresh();
                break;
            case 4:
                load();
                break;
        }
    }

    private void setValue() {
//        anim.setFloatValues(ScrollableLayout.mCurY, ScrollableLayout.maxY);
        anim.setFloatValues(ScrollableLayout.mCurY, ConvertUtils.dp2px(190));
    }

    private void initPopWindos() {
        FenLeiPopWindos = new BasePopWindos(mActivity, R.layout.pop_fenlei);
        QuYuPopWindos = new BasePopWindos(mActivity, R.layout.pop_city);
        PaiXunWindos = new BasePopWindos(mActivity, R.layout.pop_paixu);
        FenLeiPopWindos.setViewToumin(viewToumin);
        QuYuPopWindos.setViewToumin(viewToumin);
        PaiXunWindos.setViewToumin(viewToumin);
        //分类菜单
        RecyclerView recyclerView = (RecyclerView) FenLeiPopWindos.getView().findViewById(R.id.rv_fenlei);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        popAdapter = new CommonAdapter<ClassifyBean>(mActivity, R.layout.pop_rv_fenlei, FenleiList) {
            @Override
            protected void convert(ViewHolder holder, final ClassifyBean classifyBean, int position) {
                final TextView tv_fenlei = holder.getView(R.id.tv_fenlei);
                TextView tv_num = holder.getView(R.id.tv_num);
                ImageView imageView = holder.getView(R.id.img_gougou);
                tv_fenlei.setText(classifyBean.getName());
                tv_num.setText(classifyBean.getGoods_num());
                if (cate_id.equals(classifyBean.getId())) {
                    imageView.setVisibility(View.VISIBLE);
                    tv_fenlei.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.main_red));
                    tv_num.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.main_red));
                } else {
                    imageView.setVisibility(View.INVISIBLE);
                    tv_fenlei.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.texthei));
                    tv_num.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.texthei));
                }
                holder.setOnClickListener(R.id.layout_onClick, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cate_id = classifyBean.getId();
                        tvFenlei.setText(classifyBean.getName());
                        onRefresh();
                    }
                });
            }
        };
        recyclerView.setAdapter(popAdapter);
        //排序菜单
        RecyclerView paixuRv = (RecyclerView) PaiXunWindos.getView().findViewById(R.id.rv_fenlei);
        paixuRv.setLayoutManager(new LinearLayoutManager(mActivity));
        paiXuPopAdapter = new CommonAdapter<SortBean>(mActivity, R.layout.pop_rv_fenlei, PaixuList) {
            @Override
            protected void convert(ViewHolder holder, final SortBean sortBean, int position) {
                final TextView tv_fenlei = holder.getView(R.id.tv_fenlei);
                TextView tv_num = holder.getView(R.id.tv_num);
                ImageView imageView = holder.getView(R.id.img_gougou);
                tv_num.setVisibility(View.GONE);
                tv_fenlei.setText(sortBean.getName());
                if (sort.equals(sortBean.getId())) {
                    imageView.setVisibility(View.VISIBLE);
                    tv_fenlei.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.main_red));
                } else {
                    imageView.setVisibility(View.INVISIBLE);
                    tv_fenlei.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.texthei));
                }
                holder.setOnClickListener(R.id.layout_onClick, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sort = sortBean.getId();
                        tvPaixu.setText(sortBean.getName());
                        onRefresh();
                    }
                });
            }
        };
        paixuRv.setAdapter(paiXuPopAdapter);

        //区域的全市区域菜单
        RecyclerView rv_qu = (RecyclerView) QuYuPopWindos.getView().findViewById(R.id.rv_qu);
        RecyclerView tv_jieDao = (RecyclerView) QuYuPopWindos.getView().findViewById(R.id.tv_jieDao);
        rv_qu.setLayoutManager(new LinearLayoutManager(mActivity));
        tv_jieDao.setLayoutManager(new LinearLayoutManager(mActivity));
        cityPopAdapter = new CommonAdapter<CityBean>(mActivity, R.layout.rv_city, QuyuList) {
            @Override
            protected void convert(ViewHolder holder, final CityBean cityBean, final int position) {
                TextView tv_name = holder.getView(R.id.tv_name);
                tv_name.setText(cityBean.getName());
                if (cityBean.getId().equals(onClickCity)) {
                    //当前选中的区
                    refreshAdapter(position);
                    tv_name.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.main_red));
                    tv_name.setBackgroundColor(MyApplication.getInstance().getResources().getColor(R.color.bg_hui2));
                } else {
                    tv_name.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.texthei));
                    tv_name.setBackgroundColor(MyApplication.getInstance().getResources().getColor(R.color.white));
                }
                tv_name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickCity = cityBean.getId();
                        notifyDataSetChanged();
                        refreshAdapter(position);
                    }
                });
            }
        };
        rv_qu.setAdapter(cityPopAdapter);

        //区域的2级街道菜单
        JieDaoPopAdapter = new CommonAdapter<CityBean.ChildrenBean>(mActivity, R.layout.rv_jiedao, JieDaoList) {
            @Override
            protected void convert(ViewHolder holder, final CityBean.ChildrenBean childrenBean, int position) {
                TextView tv_name = holder.getView(R.id.tv_name);
                tv_name.setText(childrenBean.getName());
                if (pick_street_id.equals(childrenBean.getId())) {
                    //当前选中的区
                    tv_name.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.main_red));
                } else {
                    tv_name.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.texthei));
                }
                tv_name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pick_district_id = onClickCity;
                        pick_street_id = childrenBean.getId();
                        tvCity.setText(childrenBean.getName());
                        onRefresh();
                    }
                });
            }
        };
        tv_jieDao.setAdapter(JieDaoPopAdapter);
    }

    private void refreshAdapter(int position) {
        JieDaoList.clear();
        JieDaoList.addAll(QuyuList.get(position).getChildren());
        JieDaoPopAdapter.notifyDataSetChanged();
    }


    //团购列表
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
                if (TuanGouAdapter.isOne) {
                    load();
                }
            }

            @Override
            protected RecyclerView.LayoutManager initLayoutManager() {
                return new LinearLayoutManager(mActivity);
            }

            @Override
            protected void getView(ViewHolder holder, TuanGouBean tuanGouBean, int position) {
                ImageLoader.with(tuanGouBean.getOriginal_img(), (ImageView) holder.getView(R.id.img_head_pic));
                holder.setText(R.id.tv_goods_name, tuanGouBean.getGoods_name())
                        .setText(R.id.tv_distance, tuanGouBean.getDistance())
                        .setText(R.id.tv_store_name, tuanGouBean.getStore_name())
                        .setText(R.id.tv_market_price, "¥" + tuanGouBean.getMarket_price())
                        .setText(R.id.tv_price, tuanGouBean.getPrice())
                        .setText(R.id.tv_sales_sum, "已售" + tuanGouBean.getSales_sum());
            }

            @Override
            protected void onitemClick(View view, RecyclerView.ViewHolder holder, int position) {
                StartUtil.toShangPingWeb(mActivity, Api.H5Url() + mlist.get(position).getGoods_id());
            }
        };
        TuanGouAdapter.setKongView(layoutKong);
        TuanGouAdapter.setType(new TypeToken<ArrayList<TuanGouBean>>() {
        }.getType());
    }

    //第一次必须运行它
    private void load() {
        TuanGouAdapter.QueryHttp(getBuild());
    }

    private RequestCall getBuild() {
        TuanGouAdapter.p++;
        GetBuilder getBuilder = OkHttpUtils
                .get()
                .url(Api.Tuangouindex)
                .tag("base")
                .addParams("type", "3")
                .addParams("p", TuanGouAdapter.p + "")
                .addParams("sort", sort)
                .addParams("cate_id", cate_id)
                .addParams("pick_city_name", MyApplication.getSpUtils2().getString(Constants.city))
                .addParams("lat", MyApplication.getSpUtils2().getString(Constants.lat))
                .addParams("lng", MyApplication.getSpUtils2().getString(Constants.lng));
        if (pick_district_id.equals("")) {
            getBuilder.addParams("juli", pick_street_id);
        } else {
            getBuilder.addParams("pick_street_id", pick_street_id)
                    .addParams("pick_district_id", pick_district_id);
        }

        return getBuilder.build();
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
                        Type type = new TypeToken<List<ClassifyBean>>() {
                        }.getType();
                        List<ClassifyBean> list = null;
                        list = new Gson().fromJson(result, type);
                        mlist.clear();
                        try {
                            mlist.addAll(list.subList(0, 8));
                        } catch (Exception e) {
                            Ts.setText("生活服务分类异常,请联系管理员!");
                        }
                        FenleiList.clear();
                        FenleiList.addAll(list);
                        FenleiList.add(0, new ClassifyBean("", "全部"));
                        fenLeiAdater.notifyDataSetChanged();
                        //查找相同分类
                        boolean isEmpty = true;
                        for (int i = 0; i < mlist.size(); i++) {
                            if (res.equals(mlist.get(i).getName())) {
                                isEmpty = false;
                                cate_id = mlist.get(i).getId();
                                tvFenlei.setText(mlist.get(i).getName());
                                onClick = 4;
                                animStart();
                            }
                        }
                        if (isEmpty) {
                            load();
                        }
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
            protected void convert(ViewHolder holder, final ClassifyBean classifyBean, int position) {
                holder.setText(R.id.tv_name, classifyBean.getName());
                ImageLoader.withYuan(classifyBean.getImage(), (ImageView) holder.getView(R.id.img_head_pic));
                holder.setOnClickListener(R.id.layout_onClick, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cate_id = classifyBean.getId();
                        tvFenlei.setText(classifyBean.getName());
                        onClick = 3;
                        animStart();
                    }
                });
            }
        };
    }

    @OnClick({R.id.layout_message, R.id.layout_back, R.id.tv_refresh, R.id.layout_fenlei, R.id.layout_city, R.id.layout_paixu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.tv_refresh:
                initFenLeiHttp();
                break;
            case R.id.layout_fenlei:
                onClick = 0;
                dialogOrAnimStart();
                break;
            case R.id.layout_city:
                onClick = 1;
                dialogOrAnimStart();
                break;
            case R.id.layout_paixu:
                onClick = 2;
                dialogOrAnimStart();
                break;
            case R.id.layout_message:
                if (!StartUtil.isLogin()) {
                    StartUtil.toLogin(mActivity);
                    return;
                }
                Intent intent = new Intent(mActivity, MessageActivity.class);
                intent.putExtra("store_id", "");
                startActivity(intent);
                break;

        }
    }

    private void dialogOrAnimStart() {
        //根布局有一根1dp的线
        if ((ScrollableLayout.mCurY + 1) >= ScrollableLayout.maxY) {
            PopShow();
        } else {
            animStart();
        }

    }

    private void animStart() {
        if (!anim.isRunning()) {
            setValue();
            anim.start();
        }
    }

    @Override
    public void onRefresh() {
        FenLeiPopWindos.disMiss();
        popAdapter.notifyDataSetChanged();
        PaiXunWindos.disMiss();
        paiXuPopAdapter.notifyDataSetChanged();
        QuYuPopWindos.disMiss();
        cityPopAdapter.notifyDataSetChanged();
        TuanGouAdapter.Shuaxin();
    }

    @Override
    public void onScroll(int currentY, int maxY) {
        if (currentY < 10) {
            swLoading.setEnabled(true);
        } else {
            swLoading.setEnabled(false);
        }

    }

    private void initPaixuList() {
        PaixuList.add(new SortBean("", "智能排序"));
        PaixuList.add(new SortBean("1", "离我最近"));
        PaixuList.add(new SortBean("2", "好评优先"));
        PaixuList.add(new SortBean("3", "最新发布"));
        PaixuList.add(new SortBean("4", "人气优先"));
        PaixuList.add(new SortBean("5", "价格最低"));
        PaixuList.add(new SortBean("6", "价格最高"));
        List<CityBean.ChildrenBean> children = new LinkedList<>();
        children.add(new CityBean.ChildrenBean("0", "附近(智能距离)"));
        children.add(new CityBean.ChildrenBean("1", "1Km"));
        children.add(new CityBean.ChildrenBean("3", "3Km"));
        children.add(new CityBean.ChildrenBean("5", "5Km"));
        children.add(new CityBean.ChildrenBean("7", "7Km"));
        children.add(new CityBean.ChildrenBean("10", "10Km"));
        children.add(new CityBean.ChildrenBean("", "全城"));

        cityBean = new CityBean("", "全城", children);
    }

    private void getCityHttp() {
        DialogUtil.show(mActivity);
        OkHttpUtils.getInstance().cancelTag("getCityHttp");
        OkHttpUtils
                .get()
                .tag("getCityHttp")
                .url(Api.address)
                .addParams("name", MyApplication.getSpUtils2().getString(Constants.city))
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        isCityOne = true;
                        DialogUtil.hide();
                        Type type = new TypeToken<List<CityBean>>() {
                        }.getType();
                        List<CityBean> list = new Gson().fromJson(result, type);
                        QuyuList.addAll(list);
                        QuyuList.add(0, cityBean);
                        refreshAdapter(0);
                        QuYuPopWindos.Show(layoutShow);
                    }
                });
    }


    @OnClick(R.id.layout_ss)
    public void onClick() {
        Lg.e("???");
        StartUtil.toSousuo(mActivity, "服务");
    }
}
