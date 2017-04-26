package com.duma.liudong.meiye.view.me;

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
import com.duma.liudong.meiye.model.AnQuanbean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.DialogUtil;
import com.duma.liudong.meiye.utils.Ts;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/24.
 */

public class AnQuanActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_other)
    ImageView imgOther;
    @BindView(R.id.layout_other)
    LinearLayout layoutOther;
    @BindView(R.id.edit_idcard)
    EditText editIdcard;
    @BindView(R.id.btn_ok)
    Button btnOk;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_anquan);
    }

    @Override
    protected void initData() {
        tvTitle.setText("安全设置");
        DialogUtil.show(mActivity, false);
        OkHttpUtils
                .get()
                .tag(this)
                .url(Api.isBindIdCard)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        DialogUtil.hide();
                        AnQuanbean anQuanbean = new Gson().fromJson(result, AnQuanbean.class);
                        if (anQuanbean == null) {
                            btnOk.setText("设置");
                            btnOk.setBackgroundColor(MyApplication.getInstance().getResources().getColor(R.color.button_rad));
                        } else {
                            editIdcard.setFocusable(false);
                            editIdcard.setFocusableInTouchMode(false);
                            editIdcard.setHint(anQuanbean.getCardNum());
                            btnOk.setText("已设置成功");
                            btnOk.setBackgroundColor(MyApplication.getInstance().getResources().getColor(R.color.text_hui));
                            btnOk.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.white));
                        }
                    }
                });
    }

    @OnClick({R.id.layout_back, R.id.btn_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.btn_ok:
                if (!btnOk.getText().toString().equals("设置")) {
                    return;
                }
                if (editIdcard.getText().toString().equals("")) {
                    Ts.setText("请输入身份证号");
                    return;
                }
                okHttp();
                break;
        }
    }

    private void okHttp() {
        DialogUtil.show(mActivity);
        OkHttpUtils
                .get()
                .tag(this)
                .url(Api.setting)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .addParams("id_card", editIdcard.getText().toString())
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        DialogUtil.hide();
                        Ts.setText("设置成功!");
                        finish();
                    }
                });
    }
}
