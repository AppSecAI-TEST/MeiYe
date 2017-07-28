package com.duma.liudong.meiye.view.me.dinDan;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.model.TuiKuanXiangQinBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.DialogUtil;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.StartUtil;
import com.google.gson.Gson;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/24.
 */

public class TuiKuanXiangQinActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
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
    @BindView(R.id.tv_shangping_title)
    TextView tvShangpingTitle;
    @BindView(R.id.tv_guige)
    TextView tvGuige;
    @BindView(R.id.tv_danjia)
    TextView tvDanjia;
    @BindView(R.id.tv_shuliang)
    TextView tvShuliang;
    @BindView(R.id.layout_jiesuan)
    LinearLayout layoutJiesuan;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_jiage)
    TextView tvJiage;
    @BindView(R.id.tv_sn)
    TextView tvSn;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_yuanyin)
    TextView tvYuanyin;
    @BindView(R.id.tv_shouhou)
    TextView tvShouhou;
    @BindView(R.id.rv_img)
    RecyclerView rvImg;
    @BindView(R.id.tv_xinxi)
    TextView tvXinxi;

    private String order_id, order_sn, goods_id, storName, name, img, num, danjia, spec_key;
    private TuiKuanXiangQinBean bean;
    private List<String> mlist;
    private CommonAdapter<String> adapter;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activiaty_tuikuanxiangqin);
    }

    @Override
    protected void initData() {
        tvTitle.setText("退款详情");
        mlist = new ArrayList<>();
        order_id = getIntent().getStringExtra("order_id");
        order_sn = getIntent().getStringExtra("order_sn");
        goods_id = getIntent().getStringExtra("goods_id");
        storName = getIntent().getStringExtra("storName");
        name = getIntent().getStringExtra("name");
        img = getIntent().getStringExtra("img");
        num = getIntent().getStringExtra("num");
        danjia = getIntent().getStringExtra("danjia");
        spec_key = getIntent().getStringExtra("spec_key");

        tvDanjia.setText("￥" + danjia);
        tvShuliang.setText("x" + num);
        tvNum.setText(num);
        tvJiage.setText(Double.parseDouble(num) * Double.parseDouble(danjia) + "");
        tvShangpingTitle.setText(name);
        tvStoreName.setText(storName);
        tvSn.setText("订单编号:" + order_sn);
        ImageLoader.with(Api.url + img, imgHeadPic);

        DialogUtil.show(mActivity);
        OkHttpUtils
                .get()
                .tag(this)
                .url(Api.orderInfo)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .addParams("order_id", order_id)
                .addParams("goods_id", goods_id)
                .addParams("order_sn", order_sn)
                .addParams("spec_key", spec_key)
                .addParams("order_type", "3")
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        DialogUtil.hide();
                        bean = new Gson().fromJson(result, TuiKuanXiangQinBean.class);
                        initRes();
                    }

                    @Override
                    protected void onError(String result) {
                        finish();
                    }
                });

        rvImg.setLayoutManager(new GridLayoutManager(mActivity, 3));
        rvImg.setFocusable(false);
        rvImg.setNestedScrollingEnabled(false);
        adapter = new CommonAdapter<String>(mActivity, R.layout.rv_img, mlist) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                ImageLoader.with(Api.url + s, (ImageView) holder.getView(R.id.img_img));
            }
        };
        rvImg.setAdapter(adapter);
    }

    private void initRes() {
        tvXinxi.setText(bean.getSupport().getRemark() + "");
        tvTime.setText("申请时间:" + StartUtil.getShiJian(Long.parseLong(bean.getSupport().getAddtime())));
        tvYuanyin.setText("退款原因:" + bean.getSupport().getAddtime());
        //0:待确认 1：审核中 2：已完成
        switch (bean.getSupport().getStatus()) {
            case "0":
                tvShouhou.setText("申请中");
                break;
            case "1":
                tvShouhou.setText("退款拒绝");
                break;
            case "2":
                tvShouhou.setText("退款完成");
                break;

        }
        mlist.clear();
        mlist.addAll(bean.getSupport().getImgs());
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.layout_back)
    public void onClick() {
        finish();
    }

}
