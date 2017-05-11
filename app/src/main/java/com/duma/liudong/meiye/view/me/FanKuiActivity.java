package com.duma.liudong.meiye.view.me;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/3/27.
 */

public class FanKuiActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.edi_res)
    EditText ediRes;
    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.edit_qq)
    EditText editQq;
    @BindView(R.id.checkBox)
    CheckBox checkBox;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_fankui);
    }

    @Override
    protected void initData() {
        tvTitle.setText("反馈");
    }

    @OnClick({R.id.layout_back, R.id.btn_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.btn_ok:
                if (isText(ediRes, "请填写要反馈的内容!")) return;
                if (isText(editQq, "请填写您的联系方式!")) return;
                sendResHttp();
                break;
        }
    }

    private void sendResHttp() {
        DialogUtil.show(mActivity);
        OkHttpUtils.getInstance().cancelTag("sendResHttp");
        OkHttpUtils
                .post()
                .tag("sendResHttp")
                .url(Api.feedback)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .addParams("content", ediRes.getText().toString())
                .addParams("relation", editQq.getText().toString())
                .addParams("is_niming", checkBox.isChecked() == true ? "0" : "1")
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        DialogUtil.hide();
                        finish();
                    }
                });
    }

}
