package com.duma.liudong.meiye.view.forum;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseFragment;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.base.baseAdapter.BaseLoadAdapter;
import com.duma.liudong.meiye.base.baseAdapter.BaseLoadAdapterBuilder;
import com.duma.liudong.meiye.base.baseAdapter.onBaseLoadAdapterListener;
import com.duma.liudong.meiye.model.DianZanBean;
import com.duma.liudong.meiye.model.GuanZhuBean;
import com.duma.liudong.meiye.model.TieziBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.utils.Ts;
import com.duma.liudong.meiye.view.dialog.LunTanDialog;
import com.duma.liudong.meiye.view.start.main.ForumFragment;
import com.duma.liudong.meiye.view.start.main.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

import static com.duma.liudong.meiye.utils.Api.url;

/**
 * Created by liudong on 17/4/11.
 */

public class LunTanClassiftFragmentNew extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_tiezi)
    RecyclerView rvTiezi;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;

    private MainActivity activity;
    private BaseLoadAdapter baseLoadAdapter;
    private ForumFragment forumFragment;

    private RecyclerView rvTop;
    private LinearLayout layoutFaTie;
    private ImageView imgTouXiang;
    private List<TieziBean> mZhiDinList;

    private BaseQuickAdapter<TieziBean, BaseViewHolder> baseQuickAdapter;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_luntan_classift_new;
    }

    @Override
    protected void initData() {
        mZhiDinList = new ArrayList<>();
        activity = (MainActivity) mActivity;
        forumFragment = activity.getForumFragment();

        StartUtil.setSw(swLoading, this);
        baseLoadAdapter = new BaseLoadAdapterBuilder<TieziBean>(mActivity, rvTiezi, R.layout.rv_luntan)
                .build()
                .execute(new onBaseLoadAdapterListener<TieziBean>() {
                    @Override
                    public void getView(BaseViewHolder holder, final TieziBean zhiDingBean) {
                        holder.setText(R.id.tv_content, zhiDingBean.getContent());
                        holder.setText(R.id.tv_click_count, zhiDingBean.getClick_count());
                        holder.setText(R.id.tv_user_name, zhiDingBean.getUser_name());
                        holder.setText(R.id.tv_add_time, zhiDingBean.getAdd_time());
                        holder.setText(R.id.tv_zan_num, zhiDingBean.getLike_count());
                        holder.setText(R.id.tv_comment_count, zhiDingBean.getComment_count());
                        final TextView tv_zan_num = holder.getView(R.id.tv_zan_num);
                        ImageView img_img_json = holder.getView(R.id.img_img_json);
                        final ImageView img_zan = holder.getView(R.id.img_zan);
                        ImageView img_touxiang = holder.getView(R.id.img_touxiang);
                        ImageLoader.withYuan(zhiDingBean.getHead_pic(), img_touxiang);
                        img_img_json.setVisibility(View.GONE);
                        if (zhiDingBean.getImg_json() != null && zhiDingBean.getImg_json().size() != 0) {
                            img_img_json.setVisibility(View.VISIBLE);
                            ImageLoader.with(zhiDingBean.getImg_json().get(0), img_img_json);
                        }
                        holder.setOnClickListener(R.id.layout_zan, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DianZanHttp(tv_zan_num, img_zan, zhiDingBean.getBbs_id());
                            }
                        });
                        final LunTanDialog dialog = new LunTanDialog(mActivity, zhiDingBean);
                        dialog.setClicklistener(new LunTanDialog.onClickListenerInterface() {
                            @Override
                            public void onGuanZhu(TieziBean zhiDingBean, TextView tv_guanzhu) {
                                GuanZhuHttp(zhiDingBean, tv_guanzhu);
                            }

                            @Override
                            public void onShouCang(TieziBean zhiDingBean, TextView tv_shoucang) {
                                ShouCangHttp(zhiDingBean, tv_shoucang);
                            }
                        });
                        holder.setOnClickListener(R.id.layout_guanzhu, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (!StartUtil.isLogin()) {
                                    StartUtil.toLogin(mActivity);
                                    return;
                                }
                                //点击dialog
                                dialog.show();
                            }
                        });
                        holder.setOnClickListener(R.id.layout_share, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                StartUtil.toShare(mActivity, zhiDingBean.getUser_name() + "的帖子", Api.LunTanH5Url + zhiDingBean.getBbs_id());
                            }
                        });

                        if (zhiDingBean.getIs_like().equals("0")) {
                            //没点赞
                            tv_zan_num.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.texthei));
                            img_zan.setImageDrawable(MyApplication.getInstance().getResources().getDrawable(R.drawable.img_80));
                        } else {
                            tv_zan_num.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.maincolor));
                            img_zan.setImageDrawable(MyApplication.getInstance().getResources().getDrawable(R.drawable.img_79));
                        }
                    }

                    @Override
                    public void onLoadHttp(int page) {
                        page++;
                        OkHttpUtils
                                .get()
                                .url(Api.Bbsindex)
                                .tag("base")
                                .addParams("cat_id", forumFragment.getCat_id())
                                .addParams("p", page + "")
                                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                                .build()
                                .execute(new MyStringCallback() {
                                    @Override
                                    public void onMySuccess(String result) {
                                        swLoading.setRefreshing(false);
                                        List list = new Gson().fromJson(result, new TypeToken<ArrayList<TieziBean>>() {
                                        }.getType());
                                        baseLoadAdapter.setListOrRefresh(list);
                                        baseLoadAdapter.setEnableLoadMore(true);
                                    }

                                    @Override
                                    public void onError(Call call, Exception e, int id) {
                                        super.onError(call, e, id);
                                        swLoading.setRefreshing(false);
                                    }
                                });
                    }
                }).setSwLoading(swLoading);
        View view = baseLoadAdapter.getView(R.layout.inclde_luntan_herd);
        baseLoadAdapter.setHeaderView(view);
        rvTop = (RecyclerView) view.findViewById(R.id.rv_zhidin);
        layoutFaTie = (LinearLayout) view.findViewById(R.id.layout_fatie);
        imgTouXiang = (ImageView) view.findViewById(R.id.img_head_pic);
        ImageLoader.withYuan(url + MyApplication.getSpUtils().getString(Constants.head_pic), imgTouXiang);
        layoutFaTie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!StartUtil.isLogin()) {
                    StartUtil.toLogin(mActivity);
                    return;
                }
                Intent intent = new Intent(mActivity, FaTieActivity.class);
                intent.putExtra("list", (Serializable) forumFragment.list);
                startActivity(intent);
            }
        });

        rvTop.setLayoutManager(new LinearLayoutManager(mActivity));
        baseQuickAdapter = new BaseQuickAdapter<TieziBean, BaseViewHolder>(R.layout.rv_zhidin, mZhiDinList) {
            @Override
            protected void convert(BaseViewHolder holder, final TieziBean zhiDingBean) {
                holder.setText(R.id.tv_content, zhiDingBean.getContent());
                holder.setText(R.id.tv_click_count, zhiDingBean.getClick_count());
                holder.setOnClickListener(R.id.layout_onClick, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StartUtil.toH5Web(mActivity, Api.LunTanH5Url + zhiDingBean.getBbs_id(), zhiDingBean.getTitle());
                    }
                });
            }
        };
        rvTop.setAdapter(baseQuickAdapter);

        if (forumFragment.isOne) {
            onLazyLoad();
            forumFragment.isOne = false;
        }
    }

    private void ShouCangHttp(final TieziBean zhiDingBean, final TextView tv_shoucan) {
        if (!StartUtil.isLogin()) {
            StartUtil.toLogin(mActivity);
            return;
        }
        OkHttpUtils
                .post()
                .tag("ShouCangHttp")
                .url(Api.follow)
                .addParams("bbs_id", zhiDingBean.getBbs_id())
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        GuanZhuBean bean = new Gson().fromJson(result, GuanZhuBean.class);
                        if (bean.getIs_follow() == 1) {
                            Ts.setText("收藏成功!");
                            tv_shoucan.setText("取消收藏");
                        } else {
                            Ts.setText("取消收藏成功!");
                            tv_shoucan.setText("收藏");
                        }

                    }
                });
    }

    private void DianZanHttp(final TextView tv_zan_num, final ImageView img_zan, String Bbs_id) {
        if (!StartUtil.isLogin()) {
            StartUtil.toLogin(mActivity);
            return;
        }
        OkHttpUtils
                .get()
                .tag("DianZanHttp")
                .url(Api.like)
                .addParams("bbs_id", Bbs_id)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        DianZanBean bean = new Gson().fromJson(result, DianZanBean.class);
                        tv_zan_num.setText(bean.getLike_count());
                        if (bean.getStatus() == 0) {
                            //没点赞
                            tv_zan_num.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.texthei));
                            img_zan.setImageDrawable(MyApplication.getInstance().getResources().getDrawable(R.drawable.img_80));
                        } else {
                            tv_zan_num.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.maincolor));
                            img_zan.setImageDrawable(MyApplication.getInstance().getResources().getDrawable(R.drawable.img_79));
                        }
                    }
                });
    }

    @Override
    public void onLazyLoad() {
//        isTop();
        onRefresh();
    }

    private void GuanZhuHttp(final TieziBean zhiDingBean, final TextView tv_shoucan) {
        if (!StartUtil.isLogin()) {
            StartUtil.toLogin(mActivity);
            return;
        }
        OkHttpUtils
                .get()
                .tag("GuanZhuHttp")
                .url(Api.user_follow)
                .addParams("be_user_id", zhiDingBean.getUser_id())
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        GuanZhuBean bean = new Gson().fromJson(result, GuanZhuBean.class);
                        if (bean.getIs_follow() == 1) {
                            Ts.setText("关注成功!");
                            tv_shoucan.setText("取消关注");
                        } else {
                            Ts.setText("取消关注成功!");
                            tv_shoucan.setText("关注");
                        }
                    }
                });
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
                            baseQuickAdapter.notifyDataSetChanged();
                        }
                    });
        }
    }

    @Override
    public void onRefresh() {
        isTop();
        ImageLoader.withYuan(url + MyApplication.getSpUtils().getString(Constants.head_pic), imgTouXiang);
        baseLoadAdapter.onRefresh();
    }

}
