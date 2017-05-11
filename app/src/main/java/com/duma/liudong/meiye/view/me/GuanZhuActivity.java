package com.duma.liudong.meiye.view.me;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.BaseXiaLaRvPresenter;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.model.DianZanBean;
import com.duma.liudong.meiye.model.GuanZhuBean;
import com.duma.liudong.meiye.model.TieziBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.DialogUtil;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.utils.Ts;
import com.duma.liudong.meiye.view.dialog.QueRenUtilDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/20.
 */

public class GuanZhuActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_other)
    ImageView imgOther;
    @BindView(R.id.layout_other)
    LinearLayout layoutOther;
    @BindView(R.id.layout_kong)
    LinearLayout layoutKong;
    @BindView(R.id.rv_shangping)
    RecyclerView rvShangping;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;

    private BaseXiaLaRvPresenter<TieziBean> beanBaseXiaLaRvPresenter;
    private String url;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_guanzhu);
    }

    @Override
    protected void initData() {
        StartUtil.setSw(swLoading, this);
        url = getIntent().getStringExtra("url");
        if (url.equals(Api.follow_bbs)) {
            tvTitle.setText("我的收藏");
        } else {
            tvTitle.setText("我的贴子");
        }

        initXiaLaAdapter();
    }

    private void initXiaLaAdapter() {
        beanBaseXiaLaRvPresenter = new BaseXiaLaRvPresenter<TieziBean>(mActivity, R.layout.rv_luntan, rvShangping) {
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
                beanBaseXiaLaRvPresenter.QueryHttp(getBuild());
            }

            @Override
            protected RecyclerView.LayoutManager initLayoutManager() {
                return new LinearLayoutManager(mActivity);
            }

            @Override
            protected void getView(final ViewHolder holder, final TieziBean zhiDingBean, int position) {
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
                holder.setOnClickListener(R.id.layout_share, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StartUtil.toShare(mActivity, zhiDingBean.getUser_name() + "的帖子", Api.LunTanH5Url + zhiDingBean.getBbs_id());
                    }
                });
//                if (zhiDingBean.getIs_like().equals("0")) {
//                    //没点赞
//                    tv_zan_num.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.texthei));
//                    img_zan.setImageDrawable(MyApplication.getInstance().getResources().getDrawable(R.drawable.img_80));
//                } else {
//                    tv_zan_num.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.maincolor));
//                    img_zan.setImageDrawable(MyApplication.getInstance().getResources().getDrawable(R.drawable.img_79));
//                }

                holder.setVisible(R.id.layout_guanzhu, false);
                holder.setVisible(R.id.layout_hide, false);
                holder.setVisible(R.id.layout_shanchu, true);
                holder.setOnClickListener(R.id.layout_shanchu, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (url.equals(Api.follow_bbs)) {
                            QueRenUtilDialog dialog = new QueRenUtilDialog(mActivity, "", "是否要取消该收藏?", "否", "是");
                            dialog.setYesClicklistener(new QueRenUtilDialog.OnYesClickListener() {
                                @Override
                                public void onYes() {
                                    ShouCangHttp(zhiDingBean);
                                }
                            });
                            dialog.show();
                        } else {
                            QueRenUtilDialog dialog = new QueRenUtilDialog(mActivity, "", "是否要删除该帖子?", "否", "是");
                            dialog.setYesClicklistener(new QueRenUtilDialog.OnYesClickListener() {
                                @Override
                                public void onYes() {
                                    shanChuHttp(zhiDingBean.getBbs_id());
                                }
                            });
                            dialog.show();
                        }

                    }
                });
            }

            @Override
            protected void onitemClick(View view, RecyclerView.ViewHolder holder, int position) {
                StartUtil.toH5Web(mActivity, Api.LunTanH5Url + mlist.get(position).getBbs_id(), mlist.get(position).getTitle());
            }
        };
        beanBaseXiaLaRvPresenter.setKongView(layoutKong);
        beanBaseXiaLaRvPresenter.setType(new TypeToken<ArrayList<TieziBean>>() {
        }.getType());
    }

    private void ShouCangHttp(final TieziBean zhiDingBean) {
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
                        } else {
                            Ts.setText("取消收藏成功!");
                        }
                        beanBaseXiaLaRvPresenter.Shuaxin();

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

    private void shanChuHttp(String bbs_id) {
        DialogUtil.show(mActivity);
        OkHttpUtils
                .get()
                .url(Api.del_bbs)
                .tag("base")
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .addParams("bbs_id", bbs_id)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        DialogUtil.hide();
                        Ts.setText("删除成功!");
                        beanBaseXiaLaRvPresenter.Shuaxin();
                    }
                });
    }

    private RequestCall getBuild() {
        beanBaseXiaLaRvPresenter.p++;
        return OkHttpUtils
                .get()
                .url(url)
                .tag("base")
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .addParams("p", beanBaseXiaLaRvPresenter.p + "")
                .build();
    }

    @OnClick(R.id.layout_back)
    public void onClick() {
        finish();
    }

    @Override
    public void onRefresh() {
        beanBaseXiaLaRvPresenter.Shuaxin();
    }
}
