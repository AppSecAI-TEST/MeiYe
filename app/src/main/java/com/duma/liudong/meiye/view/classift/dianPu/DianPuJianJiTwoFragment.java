package com.duma.liudong.meiye.view.classift.dianPu;

import android.widget.ImageView;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseFragment;
import com.duma.liudong.meiye.model.DianPubean;
import com.duma.liudong.meiye.utils.ImageLoader;

import butterknife.BindView;

/**
 * Created by liudong on 17/4/7.
 */

public class DianPuJianJiTwoFragment extends BaseFragment {
    @BindView(R.id.img_business_licence_cert)
    ImageView imgBusinessLicenceCert;
    @BindView(R.id.tv_company_name)
    TextView tvCompanyName;
    @BindView(R.id.tv_addres)
    TextView tvAddres;
    @BindView(R.id.tv_business_date_start)
    TextView tvBusinessDateStart;
    @BindView(R.id.tv_business_scope)
    TextView tvBusinessScope;
    @BindView(R.id.tv_business_licence_number)
    TextView tvBusinessLicenceNumber;
    @BindView(R.id.tv_legal_person)
    TextView tvLegalPerson;
    @BindView(R.id.tv_company_type)
    TextView tvCompanyType;


    DianPuJianJieActivity activity;

    private DianPubean dianPubean;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_dianpujianjie_two;
    }

    @Override
    protected void initData() {
        activity = (DianPuJianJieActivity) mActivity;
        dianPubean = activity.dianPubean;

        ImageLoader.with(dianPubean.getBusiness_licence_cert(), imgBusinessLicenceCert);
        tvAddres.setText(dianPubean.getProvince_name() + dianPubean.getCity_name() + dianPubean.getDistrict_name() + dianPubean.getStore_address());
        tvBusinessDateStart.setText(dianPubean.getBusiness_date_start());
        tvBusinessLicenceNumber.setText(dianPubean.getBusiness_licence_number());
        tvBusinessScope.setText(dianPubean.getBusiness_scope());
        tvCompanyName.setText(dianPubean.getCompany_name());
        tvCompanyType.setText(dianPubean.getCompany_type());
        tvLegalPerson.setText(dianPubean.getLegal_person());
    }

}
