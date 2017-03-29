package com.duma.liudong.meiye.view.start.main;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseFragment;
import com.duma.liudong.meiye.base.BaseRvAdapter;
import com.duma.liudong.meiye.model.ClassifyBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.DialogUtil;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.google.gson.reflect.TypeToken;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by liudong on 17/3/14.
 */

public class ClassifyFragment extends BaseFragment {

    @BindView(R.id.rv_fu)
    RecyclerView rvFu;
    @BindView(R.id.rv_zi)
    RecyclerView rvZi;

    RequestCall build;
    BaseRvAdapter<ClassifyBean> OneAdapter;
    CommonAdapter<ClassifyBean.SecondBean> TwoAdapter;
    private boolean one = true;

    List<ClassifyBean.SecondBean> TwoList;
    @BindView(R.id.img_ad)
    ImageView imgAd;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_classify;
    }

    @Override
    protected void initData() {
        rvFu.setLayoutManager(new LinearLayoutManager(mActivity));
        rvZi.setLayoutManager(new LinearLayoutManager(mActivity));
        TwoList = new ArrayList<>();
        initAdapter();
        build = OkHttpUtils
                .get()
                .tag("base")
                .url(Api.cate)
                .build();
        OneAdapter.setType(new TypeToken<ArrayList<ClassifyBean>>() {
        }.getType());
        rvZi.setFocusable(false);
        rvZi.setNestedScrollingEnabled(false);
        rvZi.setAdapter(TwoAdapter);
    }

    private void initAdapter() {
        OneAdapter = new BaseRvAdapter<ClassifyBean>(mActivity, R.layout.rv_fu, rvFu) {
            @Override
            protected void getView(ViewHolder holder, ClassifyBean classifyBean, final int position) {
                final TextView tv_name = holder.getView(R.id.tv_name);
                tv_name.setText(classifyBean.getName());
                tv_name.setTextColor(mActivity.getResources().getColor(R.color.texthei));
                tv_name.setBackgroundColor(mActivity.getResources().getColor(R.color.white));
                if (classifyBean.isClick()) {
                    tv_name.setBackgroundColor(mActivity.getResources().getColor(R.color.bg_hui));
                    tv_name.setTextColor(mActivity.getResources().getColor(R.color.main_red));
                }
                tv_name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TwoRefresh(position);
                    }
                });
            }

            @Override
            protected void hide_loading() {
                DialogUtil.hide();
            }

            @Override
            protected void show_loading() {
                DialogUtil.show(mActivity);
            }

            @Override
            protected void HttpSuccess() {
                TwoRefresh(0);
                one = false;
            }
        };
        TwoAdapter = new CommonAdapter<ClassifyBean.SecondBean>(mActivity, R.layout.rv_zi, TwoList) {
            @Override
            protected void convert(ViewHolder holder, ClassifyBean.SecondBean secondBean, int position) {
                TextView tv_name = holder.getView(R.id.tv_name);
                tv_name.setText(secondBean.getName());

                CommonAdapter<ClassifyBean.SecondBean.ThreeBean> ThreeAdapter;
                ThreeAdapter = new CommonAdapter<ClassifyBean.SecondBean.ThreeBean>(mActivity, R.layout.rv_zi_grid, secondBean.getThree()) {
                    @Override
                    protected void convert(ViewHolder holder, ClassifyBean.SecondBean.ThreeBean threeBean, int position) {
                        ImageView imageView = holder.getView(R.id.img_fengmian);
                        ImageLoader.with(threeBean.getImage(), imageView);
                        holder.setText(R.id.tv_name, threeBean.getName());
                    }
                };
                RecyclerView recyclerView = holder.getView(R.id.rv_three);
                recyclerView.setFocusable(false);
                recyclerView.setNestedScrollingEnabled(false);
                recyclerView.setLayoutManager(new GridLayoutManager(mActivity, 3));
                recyclerView.setAdapter(ThreeAdapter);
            }
        };

    }

    private void TwoRefresh(int position) {
        for (int i = 0; i < OneAdapter.mList.size(); i++) {
            OneAdapter.mList.get(i).setClick(false);
        }
        OneAdapter.mList.get(position).setClick(true);
        OneAdapter.commonAdapter.notifyDataSetChanged();
        TwoList.clear();
        TwoList.addAll(OneAdapter.mList.get(position).getSecond());
        ImageLoader.with(OneAdapter.mList.get(position).getImage(), imgAd);
        TwoAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onLazyLoad() {
        if (one)
            OneAdapter.QueryHttp(build);

    }

}
