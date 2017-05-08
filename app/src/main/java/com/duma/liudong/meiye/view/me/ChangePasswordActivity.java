package com.duma.liudong.meiye.view.me;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.DialogUtil;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.utils.Ts;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/3/27.
 */

public class ChangePasswordActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.edit_new_password)
    EditText editNewPassword;
    @BindView(R.id.edit_new_two_password)
    EditText editNewTwoPassword;
    @BindView(R.id.btn_ok)
    Button btnOk;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_change_password);
    }

    @Override
    protected void initData() {
        tvTitle.setText("密码修改");
    }

    @OnClick({R.id.layout_back, R.id.btn_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.btn_ok:
                if (isPassword(editPassword)) return;
                if (isPassword(editNewPassword)) return;
                if (isPassword(editNewTwoPassword)) return;
                if (!editNewPassword.getText().toString().equals(editNewTwoPassword.getText().toString())) {
                    Ts.setText("两次新密码输入不一致!");
                    return;
                }
                changeHttp();
                break;
        }
    }

    private void changeHttp() {
        DialogUtil.show(mActivity);
        OkHttpUtils.getInstance().cancelTag("changeHttp");
        OkHttpUtils
                .post()
                .tag("changeHttp")
                .url(Api.changePwd)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .addParams("old_pwd", editPassword.getText().toString())
                .addParams("new_pwd1", editNewPassword.getText().toString())
                .addParams("new_pwd2", editNewTwoPassword.getText().toString())
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        DialogUtil.hide();
                        Ts.setText("修改成功!请重新登录!");
                        StartUtil.cancleLogin();
                        StartUtil.toMain(mActivity);
                    }
                });
    }
}
