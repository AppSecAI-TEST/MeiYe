package com.duma.liudong.meiye.view.start.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.model.LoginBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.DialogUtil;
import com.duma.liudong.meiye.utils.Lg;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.view.dialog.ServiceDialog;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/3/15.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.layout_issue)
    FrameLayout layoutIssue;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.layout_back)
    FrameLayout layoutBack;
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.img_password)
    ImageView imgPassword;
    @BindView(R.id.layout_hide_password)
    LinearLayout layoutHidePassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_rigister)
    TextView tvRigister;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;

    private boolean hideOrShowPassword = false;//默认隐藏
    private ServiceDialog serviceDialog;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initData() {
        serviceDialog = new ServiceDialog(mActivity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (StartUtil.isLogin()) {
            Lg.e(MyApplication.getSpUtils().getString(Constants.user_id));
            finish();
        }
    }

    @OnClick({R.id.layout_issue, R.id.layout_back, R.id.layout_hide_password, R.id.btn_login, R.id.tv_rigister, R.id.tv_forget_password})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_issue:
                serviceDialog.Show();
                break;
            case R.id.layout_back:
                finish();
                break;
            case R.id.layout_hide_password:
                if (hideOrShowPassword) {
                    //显示
                    hideOrShowPassword = false;
                    showPassword(hideOrShowPassword, editPassword, imgPassword);
                } else {
                    hideOrShowPassword = true;
                    showPassword(hideOrShowPassword, editPassword, imgPassword);
                }
                break;
            case R.id.btn_login:
                if (isPhone(editPhone)) return;
                if (isPassword(editPassword)) return;
                loginHttp();
                break;
            case R.id.tv_rigister:
                startActivity(new Intent(mActivity, RigisterActivity.class));
                break;
            case R.id.tv_forget_password:
                startActivity(new Intent(this, ForgetPassword.class));
                break;
        }
    }

    private void loginHttp() {
        DialogUtil.show(mActivity);
        OkHttpUtils
                .post()
                .url(Api.login)
                .addParams("phone", editPhone.getText().toString())
                .addParams("password", editPassword.getText().toString())
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        DialogUtil.hide();
                        LoginBean loginBean = new Gson().fromJson(result, LoginBean.class);
                        StartUtil.saveLogin(loginBean);
                        finish();
                    }
                });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }
}
