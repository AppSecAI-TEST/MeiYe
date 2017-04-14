package com.duma.liudong.meiye.view.me;

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

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/13.
 */

public class TiXianActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_other)
    ImageView imgOther;
    @BindView(R.id.layout_other)
    LinearLayout layoutOther;
    @BindView(R.id.tv_ketixian_jine)
    TextView tvKetixianJine;
    @BindView(R.id.edit_jine)
    EditText editJine;
    @BindView(R.id.rdoBtn_zhifubao)
    RadioButton rdoBtnZhifubao;
    @BindView(R.id.rdoBtn_yinhangka)
    RadioButton rdoBtnYinhangka;
    @BindView(R.id.layout_yinlian)
    LinearLayout layoutYinlian;
    @BindView(R.id.btn_shenqi)
    Button btnShenqi;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_tixian);
    }


    @OnClick({R.id.layout_back, R.id.rdoBtn_zhifubao, R.id.rdoBtn_yinhangka, R.id.btn_shenqi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.rdoBtn_zhifubao:

                break;
            case R.id.rdoBtn_yinhangka:
                break;
            case R.id.btn_shenqi:
                break;
        }
    }
}
