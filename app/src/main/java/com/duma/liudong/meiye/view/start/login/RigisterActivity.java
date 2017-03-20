package com.duma.liudong.meiye.view.start.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.utils.StartUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/3/20.
 */

public class RigisterActivity extends BaseActivity {
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
    @BindView(R.id.radio_agreement)
    RadioButton radioAgreement;
    @BindView(R.id.tv_agreement)
    TextView tvAgreement;
    @BindView(R.id.btn_rigister)
    Button btnRigister;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_issue)
    TextView tvIssue;

    private boolean hideOrShowPassword = false;//默认隐藏

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_rigister);
    }

    @Override
    protected void initData() {
        tvTitle.setText("注册");
    }

    @OnClick({R.id.layout_back, R.id.tv_code, R.id.layout_hide_password, R.id.radio_agreement, R.id.tv_agreement, R.id.btn_rigister, R.id.tv_login, R.id.tv_issue})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.tv_code:
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
            case R.id.radio_agreement:
                break;
            case R.id.tv_agreement:
                break;
            case R.id.btn_rigister:
                break;
            case R.id.tv_login:
                StartUtil.toLogin(mActivity);
                break;
            case R.id.tv_issue:
                break;
        }
    }
}
