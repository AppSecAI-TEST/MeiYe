package com.duma.liudong.meiye.view.me.dinDan;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.presenter.PhotoSelectUtil;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.DialogUtil;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.Ts;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/24.
 */

public class ShenQinTuiKuanActivity extends BaseActivity {
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
    @BindView(R.id.edit_text)
    EditText editText;
    @BindView(R.id.btn_tijiao)
    Button btnTijiao;
    @BindView(R.id.rv_photo)
    RecyclerView rvPhoto;

    private String order_id, order_sn, goods_id, storName, name, img, num, danjia, spec_key;
    private PhotoSelectUtil photoSelectUtil;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_shenqintuikuan);
    }

    @Override
    protected void initData() {
        tvTitle.setText("申请退款");
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
        ImageLoader.with(Api.url + img, imgHeadPic);
        photoSelectUtil = new PhotoSelectUtil(mActivity, rvPhoto);
    }

    @OnClick({R.id.layout_back, R.id.btn_tijiao})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.btn_tijiao:
                if (editText.getText().toString().equals("")) {
                    Ts.setText("请描述问题~");
                    return;
                }
                okHttp();
                break;
        }
    }


    private void okHttp() {
        DialogUtil.show(mActivity);
        OkHttpUtils.getInstance().cancelTag(this);
        PostFormBuilder postFormBuilder = OkHttpUtils
                .post()
                .tag(this)
                .url(Api.returnGoods)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .addParams("order_id", order_id)
                .addParams("goods_id", goods_id)
                .addParams("order_sn", order_sn)
                .addParams("type", "3")
                .addParams("spec_key", spec_key)
                .addParams("reason", editText.getText().toString());
        File file;
        for (int i = 0; i < photoSelectUtil.getmList().size(); i++) {
            file = new File(photoSelectUtil.getmList().get(i).getPath());
            postFormBuilder.addFile("img_file[" + i + "]", file.getName(), file);
        }
        postFormBuilder.build().execute(new MyStringCallback() {
            @Override
            public void onMySuccess(String result) {
                DialogUtil.hide();
                Ts.setText("提交成功!");
                finish();
            }
        });
    }
}
