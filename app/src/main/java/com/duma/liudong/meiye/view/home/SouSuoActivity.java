package com.duma.liudong.meiye.view.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.BasePopWindos;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.utils.Ts;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/19.
 */

public class SouSuoActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.edit_res)
    EditText editRes;
    @BindView(R.id.tv_sousuo)
    TextView tvSousuo;
    @BindView(R.id.btn_qingkong)
    Button btnQingkong;
    @BindView(R.id.layout_show)
    LinearLayout layoutShow;

    private BasePopWindos basePopWindos;
    private LinearLayout layout_baobei, layout_dianpu;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_sousuo);
    }

    @Override
    protected void initData() {
        basePopWindos = new BasePopWindos(mActivity, R.layout.pop_sousuo);
        layout_baobei = (LinearLayout) basePopWindos.getView().findViewById(R.id.layout_baobei);
        layout_dianpu = (LinearLayout) basePopWindos.getView().findViewById(R.id.layout_dianpu);
        layout_baobei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basePopWindos.disMiss();
                tvType.setText("宝贝");
            }
        });
        layout_dianpu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basePopWindos.disMiss();
                tvType.setText("店铺");
            }
        });
    }

    @OnClick({R.id.layout_back, R.id.tv_type, R.id.tv_sousuo, R.id.btn_qingkong})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.tv_type:
                basePopWindos.Show(layoutShow);
                break;
            case R.id.tv_sousuo:
                if (editRes.getText().toString().equals("")) {
                    Ts.setText("请输入搜索内容!");
                    return;
                }
                if (tvType.getText().toString().equals("宝贝")) {
                    StartUtil.toShangPingLieBiao(mActivity, Constants.keyword, editRes.getText().toString(), editRes.getText().toString(), "");
                } else {
                    //搜索店铺
                    StartUtil.toDianPuList(mActivity, editRes.getText().toString());
                }
                break;
            case R.id.btn_qingkong:
                break;
        }
    }


}
