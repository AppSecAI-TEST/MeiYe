package com.duma.liudong.meiye.view.forum;

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
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.model.TieziBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.view.start.main.ForumFragment;
import com.duma.liudong.meiye.view.start.main.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/11.
 */

public class LunTanClassiftFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.layout_fatie)
    LinearLayout layoutFatie;
    @BindView(R.id.rv_zhidin)
    RecyclerView rvZhidin;
    @BindView(R.id.rv_tiezi)
    RecyclerView rvTiezi;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;
    @BindView(R.id.img_head_pic)
    ImageView imgHeadPic;


    private MainActivity activity;
    private ForumFragment forumFragment;
    private CommonAdapter<TieziBean> adapter;
    private List<TieziBean> mZhiDinList;
    private BaseXiaLaRvPresenter<TieziBean> beanBaseXiaLaRvPresenter;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_luntan_classift;
    }

    @Override
    protected void initData() {
        activity = (MainActivity) mActivity;
        forumFragment = activity.getForumFragment();
        StartUtil.setSw(swLoading, this);
        mZhiDinList = new ArrayList<>();
        rvZhidin.setFocusable(false);
        rvZhidin.setNestedScrollingEnabled(false);
        rvTiezi.setFocusable(false);
        rvTiezi.setNestedScrollingEnabled(false);
        initZhiDinAdapter();
        initXiaLaAdapter();
        if (forumFragment.isOne) {
            onLazyLoad();
            forumFragment.isOne = false;
        }
        ImageLoader.withYuan(Api.url + MyApplication.getSpUtils().getString(Constants.head_pic), imgHeadPic);
    }

    private void initXiaLaAdapter() {
        beanBaseXiaLaRvPresenter = new BaseXiaLaRvPresenter<TieziBean>(mActivity, R.layout.rv_luntan, rvTiezi) {
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
                if (isOne)
                    beanBaseXiaLaRvPresenter.QueryHttp(getBuild());
            }

            @Override
            protected RecyclerView.LayoutManager initLayoutManager() {
                return new LinearLayoutManager(mActivity);
            }

            @Override
            protected void getView(ViewHolder holder, TieziBean zhiDingBean, int position) {
                holder.setText(R.id.tv_content, zhiDingBean.getContent());
                holder.setText(R.id.tv_click_count, zhiDingBean.getClick_count());
                holder.setText(R.id.tv_user_name, zhiDingBean.getUser_name());
                holder.setText(R.id.tv_add_time, zhiDingBean.getAdd_time());
                holder.setText(R.id.tv_like_count, zhiDingBean.getLike_count());
                holder.setText(R.id.tv_comment_count, zhiDingBean.getComment_count() + "条评论");
                ImageView img_img_json = holder.getView(R.id.img_img_json);
                ImageView img_touxiang = holder.getView(R.id.img_touxiang);
                ImageLoader.withYuan(zhiDingBean.getHead_pic(), img_touxiang);
                img_img_json.setVisibility(View.GONE);
                if (zhiDingBean.getImg_json() != null) {
                    img_img_json.setVisibility(View.VISIBLE);
                    ImageLoader.with(zhiDingBean.getImg_json().get(0), img_img_json);
                }
            }
        };
        beanBaseXiaLaRvPresenter.setType(new TypeToken<ArrayList<TieziBean>>() {
        }.getType());
    }

    private RequestCall getBuild() {
        beanBaseXiaLaRvPresenter.p++;
        return OkHttpUtils
                .get()
                .url(Api.Bbsindex)
                .tag("base")
                .addParams("cat_id", forumFragment.getCat_id())
                .addParams("p", beanBaseXiaLaRvPresenter.p + "")
                .build();
    }

    private void initZhiDinAdapter() {
        rvZhidin.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new CommonAdapter<TieziBean>(mActivity, R.layout.rv_zhidin, mZhiDinList) {
            @Override
            protected void convert(ViewHolder holder, TieziBean zhiDingBean, int position) {
                holder.setText(R.id.tv_content, zhiDingBean.getContent());
                holder.setText(R.id.tv_click_count, zhiDingBean.getClick_count());
                holder.setOnClickListener(R.id.layout_onClick, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: 17/4/11 跳转详情页

                    }
                });
            }
        };
        rvZhidin.setAdapter(adapter);
    }

    @Override
    protected void onLazyLoad() {
        isTop();
        if (!beanBaseXiaLaRvPresenter.isOne) {
            beanBaseXiaLaRvPresenter.clean();
            beanBaseXiaLaRvPresenter.QueryHttp(getBuild());
        }
    }


    @OnClick(R.id.layout_fatie)
    public void onClick() {
        Intent intent = new Intent(mActivity, FaTieActivity.class);
        intent.putExtra("list", (Serializable) forumFragment.list);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        isTop();
        beanBaseXiaLaRvPresenter.Shuaxin();
    }

    private void isTop() {
        if (mZhiDinList.size() == 0) {
            OkHttpUtils.getInstance().cancelTag("zhidin");
            OkHttpUtils
                    .get()
                    .tag("zhidin")
                    .url(Api.top)
                    .addParams("cat_id", forumFragment.getCat_id())
                    .build()
                    .execute(new MyStringCallback() {
                        @Override
                        public void onMySuccess(String result) {
                            Type type = new TypeToken<ArrayList<TieziBean>>() {
                            }.getType();
                            ArrayList<TieziBean> list = new Gson().fromJson(result, type);
                            mZhiDinList.addAll(list);
                            adapter.notifyDataSetChanged();
                        }
                    });
        }
    }

}
