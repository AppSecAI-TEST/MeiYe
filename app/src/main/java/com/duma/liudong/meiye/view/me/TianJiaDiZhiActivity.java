package com.duma.liudong.meiye.view.me;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.model.DiZhiBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.DialogUtil;
import com.duma.liudong.meiye.utils.KeyBoardUtils;
import com.duma.liudong.meiye.utils.ShenShiQuUtil;
import com.duma.liudong.meiye.utils.Ts;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 79953 on 2016/10/28.
 */

public class TianJiaDiZhiActivity extends BaseActivity implements ShenShiQuUtil.OnGetDiZhi {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView layoutName;
    @BindView(R.id.edt_name)
    EditText edtName;
    @BindView(R.id.edt_phone)
    EditText edtPhone;
    @BindView(R.id.tv_diquTV)
    TextView tvDiquTV;
    @BindView(R.id.layout_diqu)
    LinearLayout layoutDiqu;
    @BindView(R.id.edt_addres)
    EditText edtAddres;
    @BindView(R.id.tv_baocun)
    TextView tvBaocun;

    private OptionsPickerView pvOptions;

    private String province = "";
    private String city = "";
    private String district = "";
    private String address_id = "";
    ShenShiQuUtil shenShiQuUtil;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_tianjiadizhi);
    }

    @Override
    protected void initData() {
        DialogUtil.show(this, false);
        shenShiQuUtil = new ShenShiQuUtil(this, pvOptions, mActivity);

        if (getIntent().getStringExtra("type").equals("add")) {
            layoutName.setText("新建收货地址");
        } else {
            layoutName.setText("编辑收货地址");
            DiZhiBean bean = (DiZhiBean) getIntent().getSerializableExtra("bean");
            edtName.setText(bean.getConsignee());
            edtPhone.setText(bean.getMobile());
            edtAddres.setText(bean.getAddress());
            tvDiquTV.setText(bean.getProvince() + bean.getCity() + bean.getDistrict());
            address_id = bean.getAddress_id();
            province = bean.getProvince();
            city = bean.getCity();
            district = bean.getDistrict();
        }

    }

    @OnClick({R.id.layout_back, R.id.layout_diqu, R.id.tv_baocun})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.layout_diqu:
                KeyBoardUtils.closeKeybord(edtAddres, this);
                KeyBoardUtils.closeKeybord(edtName, this);
                KeyBoardUtils.closeKeybord(edtPhone, this);
                shenShiQuUtil.Show();
                break;
            case R.id.tv_baocun:
                if (edtAddres.getText().toString().isEmpty() || edtPhone.getText().toString().isEmpty() || edtName.getText().toString().isEmpty() || tvDiquTV.getText().toString().isEmpty()) {
                    Ts.setText("请完善收货地址~");
                    return;
                }
                xiuGaiDiZhi();
                break;
        }
    }

    public void xiuGaiDiZhi() {
        DialogUtil.show(this);
        OkHttpUtils.getInstance().cancelTag(this);
        OkHttpUtils
                .get()
                .tag(this)
                .url(Api.editAddress)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .addParams("consignee", edtName.getText().toString())
                .addParams("province", province)
                .addParams("city", city)
                .addParams("district", district)
                .addParams("address", edtAddres.getText().toString())
                .addParams("mobile", edtPhone.getText().toString())
                .addParams("address_id", address_id)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        DialogUtil.hide();
                        setResult(RESULT_OK);
                        finish();
                    }

                    @Override
                    public void onError(String result) {
                        DialogUtil.hide();
                    }
                });
    }

    @Override
    public void getDiZhi(String province, String city, String district) {
        this.province = province;
        this.city = city;
        this.district = district;
        tvDiquTV.setText(province + city + district);
    }

    @Override
    public void hide() {
        DialogUtil.hide();
    }

    @Override
    protected void OnBack() {
        finish();
    }

}
