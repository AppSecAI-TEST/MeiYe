package com.duma.liudong.meiye.view.start.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.model.LoginBean;
import com.duma.liudong.meiye.model.SlideBus;
import com.duma.liudong.meiye.presenter.PublicPresenter;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.CodeTimeUtil;
import com.duma.liudong.meiye.utils.DialogUtil;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.utils.Ts;
import com.duma.liudong.meiye.view.dialog.ServiceDialog;
import com.duma.liudong.meiye.view.start.main.SlideActivity;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/3/20.
 */

public class RigisterActivity extends BaseActivity implements PublicPresenter.GetCodeListener {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.edit_invitation)
    EditText editInvitation;
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.edit_code)
    EditText editCode;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.img_password)
    ImageView imgPassword;
    @BindView(R.id.layout_hide_password)
    LinearLayout layoutHidePassword;
    @BindView(R.id.checkbox_agreement)
    CheckBox checkboxAgreement;
    @BindView(R.id.tv_agreement)
    TextView tvAgreement;
    @BindView(R.id.btn_rigister)
    Button btnRigister;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_issue)
    TextView tvIssue;

    private boolean hideOrShowPassword = false;//默认隐藏
    private CodeTimeUtil codeTimeUtil;
    private PublicPresenter publicPresenter;
    private ServiceDialog serviceDialog;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_rigister);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {
        tvTitle.setText("注册");
        serviceDialog = new ServiceDialog(mActivity);
        codeTimeUtil = new CodeTimeUtil(tvCode);
        publicPresenter = new PublicPresenter();
        publicPresenter.setGetCodeListener(this);
        StartUtil.tvHui(btnRigister);
    }

    @Subscribe
    public void sendCode(SlideBus slideBus) {
        DialogUtil.show(mActivity);
        publicPresenter.getCode(editPhone.getText().toString());
    }

    @OnClick({R.id.layout_back, R.id.tv_code, R.id.layout_hide_password, R.id.checkbox_agreement, R.id.tv_agreement, R.id.btn_rigister, R.id.tv_login, R.id.tv_issue})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.tv_code:
                //发送验证码
                if (isPhone(editPhone)) return;
                if (tvCode.getText().toString().equals(this.getString(R.string.code))) {
                    startActivity(new Intent(this, SlideActivity.class));
                }
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
            case R.id.checkbox_agreement:
                if (checkboxAgreement.isChecked()) {
                    StartUtil.tvRed(btnRigister);
                } else {
                    StartUtil.tvHui(btnRigister);
                }
                break;
            case R.id.tv_agreement:
                // TODO: 17/3/22 跳转到美业协议
                break;
            case R.id.btn_rigister:
                if (!checkboxAgreement.isChecked()) {
                    Ts.setText("请同意美业协议!");
                    return;
                }
                //注册
                if (isPhone(editPhone)) return;
                if (isPassword(editPassword)) return;
                if (isCode(editCode)) return;
                if (editInvitation.getText().toString().isEmpty()) {
                    Ts.setText("邀请码不能为空!");
                    return;
                }
                rigisterHttp();
                break;
            case R.id.tv_login:
                StartUtil.toLogin(mActivity);
                break;
            case R.id.tv_issue:
                serviceDialog.Show();
                break;
        }
    }

    private void rigisterHttp() {
        DialogUtil.show(mActivity);
        OkHttpUtils
                .post()
                .url(Api.register)
                .addParams("phone", editPhone.getText().toString())
                .addParams("code", editCode.getText().toString())
                .addParams("password", editPassword.getText().toString())
                .addParams("ref_phone", editInvitation.getText().toString())
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        DialogUtil.hide();
                        LoginBean loginBean = new Gson().fromJson(result, LoginBean.class);
                        StartUtil.saveLogin(loginBean);
                        StartUtil.toLogin(RigisterActivity.this);
                    }
                });
    }

    //获取验证码成功
    @Override
    public void GetCodeSuccess() {
        DialogUtil.hide();
        codeTimeUtil.startTime();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        codeTimeUtil.cancel();
        EventBus.getDefault().unregister(this);
    }
}
