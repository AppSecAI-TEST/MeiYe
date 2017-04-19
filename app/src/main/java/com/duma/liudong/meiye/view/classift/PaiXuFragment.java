package com.duma.liudong.meiye.view.classift;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseFragment;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.presenter.ShangPinLieBiaoPresenter;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.StartUtil;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.request.RequestCall;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/3/30.
 */

public class PaiXuFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.img_zonghe)
    ImageView imgZonghe;
    @BindView(R.id.layout_zonghe)
    LinearLayout layoutZonghe;
    @BindView(R.id.tv_juli)
    TextView tvJuli;
    @BindView(R.id.img_juli)
    ImageView imgJuli;
    @BindView(R.id.layout_juli)
    LinearLayout layoutJuli;
    @BindView(R.id.tv_xiaoliang)
    TextView tvXiaoliang;
    @BindView(R.id.img_xiaoliang)
    ImageView imgXiaoliang;
    @BindView(R.id.layout_xiaoliang)
    LinearLayout layoutXiaoliang;
    @BindView(R.id.layout_shaixuan)
    LinearLayout layoutShaixuan;
    @BindView(R.id.tv_zonghe)
    TextView tvZonghe;
    @BindView(R.id.layout_kong)
    LinearLayout layoutKong;
    @BindView(R.id.rv_shangping)
    RecyclerView rvShangping;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;


    private ShangPinLieBiaoPresenter shangPinPresenter;
    private String paixuName = "";
    private String paixu = "";

    private ShangPingLieBiaoActivity mActivity_shangping;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_paixu;
    }

    @Override
    protected void initData() {
        mActivity_shangping = (ShangPingLieBiaoActivity) mActivity;
        StartUtil.setSw(swLoading, this);
        shangPinPresenter = new ShangPinLieBiaoPresenter(mActivity, rvShangping);
        shangPinPresenter.setKongView(layoutKong);
        shangPinPresenter.setShangPinListener(new ShangPinLieBiaoPresenter.OnShangPinListener() {
            @Override
            public void loading_hide() {
                swLoading.setRefreshing(false);
            }

            @Override
            public void loading_show() {
                swLoading.setRefreshing(true);
            }

            @Override
            public void onLoadMore() {
                shangPinPresenter.QueryHttp(getBuild());
            }

        });
        setType();
        refresh();
        if (mActivity_shangping.isOne) {
            shangPinPresenter.QueryHttp(getBuild());
            mActivity_shangping.isOne = false;
        }
    }

    @Override
    protected void onLazyLoad() {
        shangPinPresenter.QueryHttp(getBuild());
    }

    public void setType() {
        shangPinPresenter.setShangping(mActivity_shangping.type);
    }

    @Override
    public void onRefresh() {
        shangPinPresenter.Shuaxin();
    }

    private RequestCall getBuild() {
        GetBuilder getBuilder = shangPinPresenter.getBuild();
        getBuilder
                .addParams(mActivity_shangping.key, mActivity_shangping.Value)//跳转过来的功能字段
                .addParams("goods_type", mActivity_shangping.goods_type)//商品分类 默认1 1实物商品2定制商品3团购商品（必填）
                .addParams(paixuName, paixu);//单个排序

        if (mActivity_shangping.goods_type.equals("2")) {
            //如果是定制
            getBuilder.addParams("cat_id", mActivity_shangping.getId());
        }
        //条件筛选
        for (Integer integer : mActivity_shangping.layoutTagFlow.getSelectedList()) {
            getBuilder.addParams(mActivity_shangping.list_shaixuan.get(integer).getName(), "1");
        }
        //价格筛选
        for (Integer integer : mActivity_shangping.layoutTagJiage.getSelectedList()) {
            if (integer == 0) {
                getBuilder.addParams(Constants.jiaGe, Constants.daoXu);
            } else {
                getBuilder.addParams(Constants.jiaGe, Constants.zhenXu);
            }
        }
        return getBuilder
                .build();
    }

    @OnClick({R.id.layout_zonghe, R.id.layout_juli, R.id.layout_xiaoliang, R.id.layout_shaixuan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_zonghe:
                paixuName = "";
                paixu = "";
                refresh();
                break;
            case R.id.layout_juli:
                if (paixuName.equals(Constants.juLi)) {
                    setPaixu();
                } else {
                    paixu = Constants.zhenXu;
                    paixuName = Constants.juLi;
                }
                refresh();
                break;
            case R.id.layout_xiaoliang:
                if (paixuName.equals(Constants.xiaoLiang)) {
                    setPaixu();
                } else {
                    paixuName = Constants.xiaoLiang;
                    paixu = Constants.zhenXu;
                }
                refresh();
                break;
            case R.id.layout_shaixuan:
                mActivity_shangping.openDrawer();
                break;
        }
    }

    private void setPaixu() {
        if (paixu.equals(Constants.zhenXu)) {
            paixu = Constants.daoXu;
        } else {
            paixu = Constants.zhenXu;
        }
    }

    private void refresh() {
        setMoRen(tvJuli, imgJuli);
        setMoRen(tvXiaoliang, imgXiaoliang);
        setMoRen(tvZonghe, imgZonghe);

        switch (paixuName) {
            case Constants.xiaoLiang:
                setClick(tvXiaoliang, imgXiaoliang);
                break;
            case Constants.juLi:
                setClick(tvJuli, imgJuli);
                break;
            default:
                setClick(tvZonghe, imgZonghe);
        }
        onRefresh();
    }

    private void setMoRen(TextView tv, ImageView img) {
        tv.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.texthei));
        img.setImageDrawable(MyApplication.getInstance().getResources().getDrawable(R.drawable.l2q));
    }

    private void setClick(TextView tv, ImageView img) {
        tv.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.main_red));
        if (paixu.equals(Constants.zhenXu) || paixuName.equals("")) {
            img.setImageDrawable(MyApplication.getInstance().getResources().getDrawable(R.drawable.l3q));
        } else {
            img.setImageDrawable(MyApplication.getInstance().getResources().getDrawable(R.drawable.l1q));
        }

    }

}
