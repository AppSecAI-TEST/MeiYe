package com.duma.liudong.meiye.view.shoppingCart;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.DialogUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/10.
 */

public class QueRenDinDanActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_other)
    ImageView imgOther;
    @BindView(R.id.layout_other)
    LinearLayout layoutOther;
    @BindView(R.id.layout_kuaiDiKong)
    LinearLayout layoutKuaiDiKong;
    @BindView(R.id.tv_kuanDiType)
    TextView tvKuanDiType;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_dianhua)
    TextView tvDianhua;
    @BindView(R.id.tv_dizhi)
    TextView tvDizhi;
    @BindView(R.id.layout_kuaiDi)
    LinearLayout layoutKuaiDi;
    @BindView(R.id.layout_xuanZeKuaiDi)
    LinearLayout layoutXuanZeKuaiDi;
    @BindView(R.id.tv_store_name)
    TextView tvStoreName;
    @BindView(R.id.rv_shangping)
    RecyclerView rvShangping;
    @BindView(R.id.layout_kefu)
    LinearLayout layoutKefu;
    @BindView(R.id.edit_liuyan)
    EditText editLiuyan;
    @BindView(R.id.tv_youhuijuan)
    TextView tvYouhuijuan;
    @BindView(R.id.layout_youHuiJuan)
    LinearLayout layoutYouHuiJuan;
    @BindView(R.id.tv_hongbao)
    TextView tvHongbao;
    @BindView(R.id.layout_hongbao)
    LinearLayout layoutHongbao;
    @BindView(R.id.tv_jifen)
    TextView tvJifen;
    @BindView(R.id.swith_jifen)
    Switch swithJifen;
    @BindView(R.id.tv_yue)
    TextView tvYue;
    @BindView(R.id.swith_yue)
    Switch swithYue;
    @BindView(R.id.tv_zonge)
    TextView tvZonge;
    @BindView(R.id.tv_yunfei)
    TextView tvYunfei;
    @BindView(R.id.tv_shifu)
    TextView tvShifu;
    @BindView(R.id.tv_shijian)
    TextView tvShijian;
    @BindView(R.id.tv_zongji)
    TextView tvZongji;
    @BindView(R.id.tv_tijiaodindan)
    TextView tvTijiaodindan;

    private String store_id;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_querendindan);
    }

    @Override
    protected void initData() {
        tvTitle.setText("确认订单");
        store_id = getIntent().getStringExtra("store_id");
        DinDanHttp();
    }

    private void DinDanHttp() {
        DialogUtil.show(mActivity, false);
        OkHttpUtils.getInstance().cancelTag(this);
        OkHttpUtils
                .post()
                .url(Api.makeSure)
                .tag(this)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .addParams("store_id", store_id)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        DialogUtil.hide();
                        initRes();
                    }

                    @Override
                    protected void onError(String result) {
                        super.onError(result);
                        finish();
                    }
                });
    }

    private void initRes() {

    }

    @OnClick({R.id.layout_back, R.id.layout_xuanZeKuaiDi, R.id.layout_kefu, R.id.layout_youHuiJuan, R.id.layout_hongbao, R.id.swith_jifen, R.id.swith_yue})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.layout_xuanZeKuaiDi:
                break;
            case R.id.layout_kefu:
                break;
            case R.id.layout_youHuiJuan:
                break;
            case R.id.layout_hongbao:
                break;
            case R.id.swith_jifen:
                break;
            case R.id.swith_yue:
                break;
        }
    }
}
