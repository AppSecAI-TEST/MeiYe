package com.duma.liudong.meiye.view.me.yuE;

import android.content.Intent;
import android.os.Bundle;
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
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.DialogUtil;
import com.duma.liudong.meiye.utils.Ts;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/19.
 */

public class TiXianYHActivity extends BaseActivity {

    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_other)
    ImageView imgOther;
    @BindView(R.id.layout_other)
    LinearLayout layoutOther;
    @BindView(R.id.edit_type)
    EditText editType;
    @BindView(R.id.edit_num)
    EditText editNum;
    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.edit_yh_name)
    EditText editYhName;
    @BindView(R.id.btn_queding)
    Button btnQueding;
    private String money;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_tixian_yh);
    }

    @Override
    protected void initData() {
        tvTitle.setText("提现账号");
        money = getIntent().getStringExtra("money");
    }

    @OnClick({R.id.layout_back, R.id.btn_queding})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.btn_queding:
                if (editName.getText().toString().equals("") || editNum.getText().toString().equals("") || editType.getText().toString().equals("") || editYhName.getText().toString().equals("")) {
                    Ts.setText("请填写银行卡信息!");
                    return;
                }
                queDinHttp();
                break;
        }
    }

    private void queDinHttp() {
        DialogUtil.show(mActivity);
        OkHttpUtils.getInstance().cancelTag(this);
        OkHttpUtils
                .post()
                .tag(this)
                .url(Api.withdraw)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .addParams("money", money)
                .addParams("bank_name", editYhName.getText().toString())
                .addParams("account_name", editName.getText().toString())
                .addParams("account_bank", editNum.getText().toString())
                .addParams("remark", editType.getText().toString())
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        DialogUtil.hide();
                        Ts.setText("提交成功!");
                        Intent intent = new Intent(mActivity, YuEActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                    }
                });
    }

}
