package com.duma.liudong.meiye.view.me.maiJia;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseFragment;
import com.duma.liudong.meiye.base.BaseXiaLaRvPresenter;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.model.ShangXiaJiaBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.DialogUtil;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.utils.Ts;
import com.duma.liudong.meiye.view.dialog.QueRenUtilDialog;
import com.google.gson.reflect.TypeToken;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by liudong on 17/4/21.
 */

public class ShangXiaJiaFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.layout_kong)
    LinearLayout layoutKong;
    @BindView(R.id.rv_shangping)
    RecyclerView rvShangping;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;

    private BaseXiaLaRvPresenter<ShangXiaJiaBean> shangXiaJiaBeanBaseXiaLaRvPresenter;
    private ShangXiaJiaActivity activity;
    private String goods;
    private QueRenUtilDialog dialog;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_shangxiajia;
    }

    @Override
    protected void initData() {
        StartUtil.setSw(swLoading, this);
        activity = (ShangXiaJiaActivity) mActivity;
        dialog = new QueRenUtilDialog(mActivity, "", "确定删除该商品嘛?", "取消", "确定");
        dialog.setYesClicklistener(new QueRenUtilDialog.OnYesClickListener() {
            @Override
            public void onYes() {
                ShanChuHttp();
            }
        });
        rvShangping.setLayoutManager(new LinearLayoutManager(mActivity));
        initAdapter();
        if (activity.isOne) {
            shangXiaJiaBeanBaseXiaLaRvPresenter.QueryHttp(getBuild());
            activity.isOne = false;
        }
    }

    private void initAdapter() {
        shangXiaJiaBeanBaseXiaLaRvPresenter = new BaseXiaLaRvPresenter<ShangXiaJiaBean>(mActivity, R.layout.rv_shangxiajia, rvShangping) {
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
                if (shangXiaJiaBeanBaseXiaLaRvPresenter.isOne)
                    shangXiaJiaBeanBaseXiaLaRvPresenter.QueryHttp(getBuild());
            }

            @Override
            protected RecyclerView.LayoutManager initLayoutManager() {
                return new LinearLayoutManager(mActivity);
            }

            @Override
            protected void getView(final ViewHolder holder, final ShangXiaJiaBean shangXiaJiaBean, int position) {
                holder.setText(R.id.tv_goods_name, shangXiaJiaBean.getGoods_name())
                        .setText(R.id.tv_market_price, shangXiaJiaBean.getMarket_price());
                ImageLoader.with(shangXiaJiaBean.getOriginal_img(), (ImageView) holder.getView(R.id.img_original_img));
                final String type;
                if (activity.getType().equals("1")) {
                    type = "下架";
                    holder.setText(R.id.tv_type, "下架");
                } else {
                    type = "上架";
                    holder.setText(R.id.tv_type, "上架");
                }

                holder.setOnClickListener(R.id.layout_type, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShangXiaJiaHttp(shangXiaJiaBean.getGoods_id(), type);
                    }
                });
                holder.setOnClickListener(R.id.layout_shanchu, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goods = shangXiaJiaBean.getGoods_id();
                        dialog.show();
                    }
                });
            }
        };
        shangXiaJiaBeanBaseXiaLaRvPresenter.setType(new TypeToken<ArrayList<ShangXiaJiaBean>>() {
        }.getType());
        shangXiaJiaBeanBaseXiaLaRvPresenter.setKongView(layoutKong);
    }

    private void ShangXiaJiaHttp(String id, final String type) {
        DialogUtil.show(mActivity);
        OkHttpUtils
                .get()
                .tag("ShangXiaJiaHttp")
                .url(activity.getType().equals("1") ? Api.is_warehouse : Api.is_onsale)
                .addParams("goods_id", id)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        DialogUtil.hide();
                        Ts.setText(type + "成功!");
                        onRefresh();
                    }
                });
    }

    private void ShanChuHttp() {
        DialogUtil.show(mActivity);
        OkHttpUtils
                .get()
                .tag("ShanChuHttp")
                .url(Api.del)
                .addParams("goods_id", goods)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        DialogUtil.hide();
                        Ts.setText("删除成功!");
                        onRefresh();
                    }
                });
    }

    private RequestCall getBuild() {
        shangXiaJiaBeanBaseXiaLaRvPresenter.p++;
        return OkHttpUtils
                .get()
                .tag("base")
                .url(Api.my_list)
                .addParams("store_id", activity.Store_id)
                .addParams("type", activity.getType())
                .addParams("p", shangXiaJiaBeanBaseXiaLaRvPresenter.p + "")
                .build();
    }

    @Override
    protected void onLazyLoad() {
        shangXiaJiaBeanBaseXiaLaRvPresenter.QueryHttp(getBuild());
    }


    @Override
    public void onRefresh() {
        shangXiaJiaBeanBaseXiaLaRvPresenter.Shuaxin();
    }
}
