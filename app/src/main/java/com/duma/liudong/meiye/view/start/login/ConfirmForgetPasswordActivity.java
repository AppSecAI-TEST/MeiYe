package com.duma.liudong.meiye.view.start.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.model.LoginBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.DialogUtil;
import com.duma.liudong.meiye.utils.Lg;
import com.duma.liudong.meiye.utils.StartUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/3/22.
 */

public class ConfirmForgetPasswordActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.img_password)
    ImageView imgPassword;
    @BindView(R.id.layout_hide_password)
    LinearLayout layoutHidePassword;
    @BindView(R.id.btn_ok)
    Button btnOk;
    private boolean hideOrShowPassword = false;//默认隐藏

    private String phone, code;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_confirm_forgetpassword);
    }

    @Override
    protected void initData() {
        tvTitle.setText("重设密码");
        phone = getIntent().getStringExtra("phone");
        code = getIntent().getStringExtra("code");
        Lg.e(phone + "-" + code);
    }

    @OnClick({R.id.layout_back, R.id.layout_hide_password, R.id.btn_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.layout_hide_password:
                //显示隐藏密码
                if (hideOrShowPassword) {
                    //显示
                    hideOrShowPassword = false;
                    showPassword(hideOrShowPassword, editPassword, imgPassword);
                } else {
                    hideOrShowPassword = true;
                    showPassword(hideOrShowPassword, editPassword, imgPassword);
                }
                break;
            case R.id.btn_ok:
                if (isPassword(editPassword)) return;
                forgetHttp();
                break;
        }
    }

    private void forgetHttp() {
        DialogUtil.show(mActivity);
        OkHttpUtils
                .post()
                .url(Api.alterPwd)
                .addParams("phone", phone)
                .addParams("code", code)
                .addParams("password", editPassword.getText().toString())
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        DialogUtil.hide();
                        LoginBean loginBean = new Gson().fromJson(result, LoginBean.class);
                        StartUtil.saveLogin(loginBean);
                        StartUtil.toLogin(ConfirmForgetPasswordActivity.this);
                    }
                });
    }
}
