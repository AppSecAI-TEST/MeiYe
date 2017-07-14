package com.duma.liudong.meiye.view.me.dinDan;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.model.DinZhiXiangQinBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.DialogUtil;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.StartUtil;
import com.google.gson.Gson;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;

/**
 * Created by liudong on 17/4/21.
 */

public class DinZhiXiangQinActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.img_other)
    ImageView imgOther;
    @BindView(R.id.layout_other)
    LinearLayout layoutOther;
    @BindView(R.id.tv_store_name)
    TextView tvStoreName;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.img_head_pic)
    ImageView imgHeadPic;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_guige)
    TextView tvGuige;
    @BindView(R.id.tv_danjia)
    TextView tvDanjia;
    @BindView(R.id.tv_shuliang)
    TextView tvShuliang;
    @BindView(R.id.layout_jiesuan)
    LinearLayout layoutJiesuan;
    @BindView(R.id.img_dindan_type)
    ImageView imgDindanType;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_jiage)
    TextView tvJiage;
    @BindView(R.id.tv_cha_num)
    TextView tvChaNum;
    @BindView(R.id.time_daojishi)
    CountdownView timeDaojishi;
    @BindView(R.id.tv_shangping_title)
    TextView tvShangpingTitle;
    @BindView(R.id.rv_touxiang)
    RecyclerView rvTouxiang;
    @BindView(R.id.rv_user)
    RecyclerView rvUser;
    @BindView(R.id.tv_cont)
    TextView tvCont;
    @BindView(R.id.tv_text1)
    TextView tvText1;
    @BindView(R.id.tv_text2)
    TextView tvText2;

    private String order_id, DinZhitype;
    private DinZhiXiangQinBean bean;
    private CommonAdapter<DinZhiXiangQinBean.SupportBean.UserBean> mTouXiangAdapter;
    private CommonAdapter<DinZhiXiangQinBean.SupportBean.UserBean> mUserAdapter;
    private List<DinZhiXiangQinBean.SupportBean.UserBean> mList;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_dinzhixiangqin);
    }

    @Override
    protected void initData() {
        tvTitle.setText("拼团详情");
        order_id = getIntent().getStringExtra("id");
        DinZhitype = getIntent().getStringExtra("DinZhitype");
        if (DinZhitype.equals("拼团成功")) {
            imgDindanType.setImageDrawable(MyApplication.getInstance().getResources().getDrawable(R.drawable.img_58));
        } else if (DinZhitype.equals("拼团失败")) {
            imgDindanType.setImageDrawable(MyApplication.getInstance().getResources().getDrawable(R.drawable.img_64));
        } else {
            imgDindanType.setVisibility(View.GONE);
        }
        mList = new ArrayList<>();
        rvTouxiang.setLayoutManager(new GridLayoutManager(mActivity, 6));
        rvUser.setLayoutManager(new LinearLayoutManager(mActivity));
        rvUser.setFocusable(false);
        rvUser.setNestedScrollingEnabled(false);
        rvTouxiang.setFocusable(false);
        rvTouxiang.setNestedScrollingEnabled(false);
        initAdapter();
        timeDaojishi.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                onRefresh();
            }
        });
        onRefresh();
    }

    private void initAdapter() {
        mTouXiangAdapter = new CommonAdapter<DinZhiXiangQinBean.SupportBean.UserBean>(mActivity, R.layout.rv_touxiang, mList) {
            @Override
            protected void convert(ViewHolder holder, DinZhiXiangQinBean.SupportBean.UserBean userBean, int position) {
                ImageLoader.withYuan(Api.url + userBean.getHead_pic(), (ImageView) holder.getView(R.id.img_touxiang));
            }
        };
        rvTouxiang.setAdapter(mTouXiangAdapter);
        mUserAdapter = new CommonAdapter<DinZhiXiangQinBean.SupportBean.UserBean>(mActivity, R.layout.rv_user, mList) {
            @Override
            protected void convert(ViewHolder holder, DinZhiXiangQinBean.SupportBean.UserBean userBean, int position) {
                ImageLoader.withYuan(Api.url + userBean.getHead_pic(), (ImageView) holder.getView(R.id.img_touxiang));
                holder.setText(R.id.tv_name, userBean.getNickname())
                        .setText(R.id.tv_state, userBean.getState());
            }
        };
        rvUser.setAdapter(mUserAdapter);
        mUserAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                StartUtil.toShangPingWeb(mActivity, Api.H5Url() + mList.get(position).getGoods_id());
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    public void onRefresh() {
        DialogUtil.show(mActivity);
        OkHttpUtils.getInstance().cancelTag(this);
        OkHttpUtils
                .get()
                .url(Api.orderInfo)
                .tag(this)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .addParams("order_id", order_id)
                .addParams("order_type", "2")
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        DialogUtil.hide();
                        bean = new Gson().fromJson(result, DinZhiXiangQinBean.class);
                        initRes();
                    }
                });
    }

    private void initRes() {
        ImageLoader.with(Api.url + bean.getGoods_list().get(0).getOriginal_img(), imgHeadPic);
        tvStoreName.setText(bean.getStore_name());
        tvShangpingTitle.setText(bean.getGoods_list().get(0).getGoods_name());
        tvGuige.setText(bean.getGoods_list().get(0).getSpec_key_name());
        tvDanjia.setText("￥" + bean.getGoods_list().get(0).getMember_goods_price());
        tvShuliang.setText("x" + bean.getGoods_list().get(0).getGoods_num());
        double v = Integer.parseInt(bean.getGoods_list().get(0).getGoods_num()) * Double.parseDouble(bean.getGoods_list().get(0).getMember_goods_price());
        tvJiage.setText(StartUtil.setNumOr00(v) + "");
        tvNum.setText(bean.getSupport().getMark().getSeller_up());
        timeDaojishi.start(bean.getSupport().getMark().getEnd_time() * 1000);
        tvCont.setText(bean.getSupport().getMark().getTips());

        tvText1.setVisibility(View.GONE);
        tvText2.setVisibility(View.GONE);
        if (bean.getSupport().getMark().getEnd_time() == 0) {
            tvChaNum.setText(bean.getSupport().getMark().getContent());
        } else {
            tvText1.setVisibility(View.VISIBLE);
            tvText2.setVisibility(View.VISIBLE);
            tvChaNum.setText(bean.getSupport().getMark().getShortX() + "");
        }

        mList.clear();
        mList.addAll(bean.getSupport().getUser());
        mUserAdapter.notifyDataSetChanged();
        mTouXiangAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.layout_back)
    public void onClick() {
        finish();
    }

}
