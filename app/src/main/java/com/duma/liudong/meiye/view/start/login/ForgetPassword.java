package com.duma.liudong.meiye.view.start.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.model.SlideBus;
import com.duma.liudong.meiye.presenter.PublicPresenter;
import com.duma.liudong.meiye.utils.CodeTimeUtil;
import com.duma.liudong.meiye.utils.DialogUtil;
import com.duma.liudong.meiye.view.dialog.ServiceDialog;
import com.duma.liudong.meiye.view.start.main.SlideActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/3/22.
 */

public class ForgetPassword extends BaseActivity implements PublicPresenter.GetCodeListener {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.edit_code)
    EditText editCode;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.tv_issue)
    TextView tvIssue;

    private CodeTimeUtil codeTimeUtil;
    private PublicPresenter publicPresenter;
    private ServiceDialog serviceDialog;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_forget_password);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {
        tvTitle.setText("忘记密码");
        codeTimeUtil = new CodeTimeUtil(tvCode);
        publicPresenter = new PublicPresenter();
        publicPresenter.setGetCodeListener(this);
        serviceDialog = new ServiceDialog(mActivity);
    }

    @Subscribe
    public void sendCode(SlideBus slideBus) {
        DialogUtil.show(mActivity);
        publicPresenter.getCode(editPhone.getText().toString());
    }

    @OnClick({R.id.layout_back, R.id.tv_code, R.id.btn_next, R.id.tv_issue})
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
            case R.id.btn_next:
                if (isPhone(editPhone)) return;
                if (isCode(editCode)) return;
                Intent intent = new Intent(ForgetPassword.this, ConfirmForgetPasswordActivity.class);
                intent.putExtra("phone", editPhone.getText().toString());
                intent.putExtra("code", editCode.getText().toString());
                startActivity(intent);
                break;
            case R.id.tv_issue:
                serviceDialog.Show();
                break;
        }
    }

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
