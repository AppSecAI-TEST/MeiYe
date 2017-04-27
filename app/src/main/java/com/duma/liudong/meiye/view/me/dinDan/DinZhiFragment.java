package com.duma.liudong.meiye.view.me.dinDan;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseFragment;
import com.duma.liudong.meiye.base.BaseXiaLaRvPresenter;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.model.WodeDinZhiBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.StartUtil;
import com.google.gson.reflect.TypeToken;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by liudong on 17/4/21.
 */

public class DinZhiFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.layout_kong)
    LinearLayout layoutKong;
    @BindView(R.id.rv_shangping)
    RecyclerView rvShangping;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;

    private WoDeDinZhiActivity activity;
    private BaseXiaLaRvPresenter<WodeDinZhiBean> beanBaseXiaLaRvPresenter;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_dinzhi;
    }

    @Override
    protected void initData() {
        activity = (WoDeDinZhiActivity) mActivity;
        StartUtil.setSw(swLoading, this);
        initAdpater();
        if (activity.isOne) {
            activity.isOne = false;
            beanBaseXiaLaRvPresenter.QueryHttp(getBuild());
        }
    }


    private void initAdpater() {
        beanBaseXiaLaRvPresenter = new BaseXiaLaRvPresenter<WodeDinZhiBean>(mActivity, R.layout.rv_wodedinzhi, rvShangping) {
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
                if (beanBaseXiaLaRvPresenter.isOne) {
                    beanBaseXiaLaRvPresenter.QueryHttp(getBuild());
                }
            }

            @Override
            protected RecyclerView.LayoutManager initLayoutManager() {
                return new LinearLayoutManager(mActivity);
            }

            @Override
            protected void getView(ViewHolder holder, WodeDinZhiBean wodeDinZhiBean, int position) {
                ImageView img_head_pic = holder.getView(R.id.img_head_pic);
                ImageLoader.with(Api.url + wodeDinZhiBean.getOriginal_img(), img_head_pic);
                holder.setText(R.id.tv_title, wodeDinZhiBean.getGoods_name());
                holder.setText(R.id.tv_guige, wodeDinZhiBean.getSpec_key_name());
                holder.setText(R.id.tv_jiage, "￥" + wodeDinZhiBean.getMember_goods_price());
                holder.setText(R.id.tv_shuliang, "x" + wodeDinZhiBean.getGoods_num());
                holder.setText(R.id.tv_store_name, wodeDinZhiBean.getStore_name());
                holder.setText(R.id.tv_type, wodeDinZhiBean.getStatus());
                double v = Integer.parseInt(wodeDinZhiBean.getGoods_num()) * Double.parseDouble(wodeDinZhiBean.getMember_goods_price());
                holder.setText(R.id.tv_jiage, v + "");
                holder.setText(R.id.tv_num, wodeDinZhiBean.getSell_up());
                holder.setText(R.id.tv_danjia, wodeDinZhiBean.getMember_goods_price());
            }

            @Override
            protected void onitemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(activity, DinZhiXiangQinActivity.class);
                intent.putExtra("id", mlist.get(position).getOrder_id());
                intent.putExtra("DinZhitype", mlist.get(position).getStatus());
                activity.startActivity(intent);
            }
        };
        beanBaseXiaLaRvPresenter.setKongView(layoutKong);
        beanBaseXiaLaRvPresenter.setType(new TypeToken<ArrayList<WodeDinZhiBean>>() {
        }.getType());
    }

    @Override
    protected void onLazyLoad() {
        beanBaseXiaLaRvPresenter.QueryHttp(getBuild());
    }

    private RequestCall getBuild() {
        beanBaseXiaLaRvPresenter.p++;
        return OkHttpUtils
                .get()
                .tag(this)
                .url(Api.mySpell)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .addParams("type", activity.getType())
                .addParams("p", beanBaseXiaLaRvPresenter.p + "")
                .build();
    }

    @Override
    public void onRefresh() {
        beanBaseXiaLaRvPresenter.Shuaxin();
    }
}
