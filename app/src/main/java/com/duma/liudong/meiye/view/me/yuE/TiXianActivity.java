package com.duma.liudong.meiye.view.me.yuE;

import android.content.Intent;
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
import com.duma.liudong.meiye.utils.Ts;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.liudong.meiye.R.id.rdoBtn_yinhangka;
import static com.duma.liudong.meiye.R.id.rdoBtn_zhifubao;

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
    @BindView(rdoBtn_zhifubao)
    RadioButton rdoBtnZhifubao;
    @BindView(rdoBtn_yinhangka)
    RadioButton rdoBtnYinhangka;
    @BindView(R.id.layout_yinlian)
    LinearLayout layoutYinlian;
    @BindView(R.id.btn_shenqi)
    Button btnShenqi;

    private double money;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_tixian);
    }

    @Override
    protected void initData() {
        tvTitle.setText("提现");
        String m = getIntent().getStringExtra("money");
        money = Double.parseDouble(m);
        tvKetixianJine.setText(m + "元");
    }

    @OnClick({R.id.layout_back, rdoBtn_zhifubao, rdoBtn_yinhangka, R.id.btn_shenqi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case rdoBtn_zhifubao:
                rdoBtnZhifubao.setChecked(true);
                rdoBtnYinhangka.setChecked(false);
                break;
            case rdoBtn_yinhangka:
                rdoBtnZhifubao.setChecked(false);
                rdoBtnYinhangka.setChecked(true);
                break;
            case R.id.btn_shenqi:
                if (editJine.getText().toString().equals("")) {
                    Ts.setText("请输入提现金额!");
                    return;
                }
                if (money < Double.parseDouble(editJine.getText().toString())) {
                    Ts.setText("可提现金额不足!");
                    return;
                }
                if (rdoBtnZhifubao.isChecked()) {
                    Intent intent = new Intent(mActivity, TiXianZFBActivity.class);
                    intent.putExtra("money", editJine.getText().toString());
                    startActivity(intent);
                    return;
                }
                if (rdoBtnYinhangka.isChecked()) {
                    Intent intent = new Intent(mActivity, TiXianYHActivity.class);
                    intent.putExtra("money", editJine.getText().toString());
                    startActivity(intent);
                    return;
                }
                break;
        }
    }
}
