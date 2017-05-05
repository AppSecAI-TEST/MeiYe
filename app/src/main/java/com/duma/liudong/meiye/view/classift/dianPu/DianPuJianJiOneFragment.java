package com.duma.liudong.meiye.view.classift.dianPu;

import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseFragment;
import com.duma.liudong.meiye.model.DianPubean;
import com.hedgehog.ratingbar.RatingBar;

import butterknife.BindView;

/**
 * Created by liudong on 17/4/7.
 */

public class DianPuJianJiOneFragment extends BaseFragment {
    @BindView(R.id.tv_company_name)
    TextView tvCompanyName;
    @BindView(R.id.tv_store_time)
    TextView tvStoreTime;
    @BindView(R.id.tv_district_name)
    TextView tvDistrictName;
    @BindView(R.id.tv_store_zy)
    TextView tvStoreZy;
    @BindView(R.id.tv_goods_num)
    TextView tvGoodsNum;
    @BindView(R.id.tv_order_num)
    TextView tvOrderNum;
    @BindView(R.id.xx_store_score)
    RatingBar xxStoreScore;
    @BindView(R.id.xx_store_desccredit)
    RatingBar xxStoreDesccredit;
    @BindView(R.id.xx_store_servicecredit)
    RatingBar xxStoreServicecredit;
    @BindView(R.id.xx_store_deliverycredit)
    RatingBar xxStoreDeliverycredit;
    @BindView(R.id.xx_turn_back_rate)
    TextView xxTurnBackRate;
    @BindView(R.id.tv_contacts_name)
    TextView tvContactsName;
    @BindView(R.id.tv_contacts_mobile)
    TextView tvContactsMobile;
    @BindView(R.id.tv_addres)
    TextView tvAddres;
    @BindView(R.id.tv_sc_name)
    TextView tvScName;

    private DianPuJianJieActivity activity;
    private DianPubean dianPubean;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_dianpujianjie_one;
    }

    @Override
    protected void initData() {
        activity = (DianPuJianJieActivity) mActivity;
        dianPubean = activity.dianPubean;

        tvAddres.setText(dianPubean.getProvince_name() + dianPubean.getCity_name() + dianPubean.getDistrict_name() + dianPubean.getStore_address());
//        tvBusinessScope.setText(dianPubean.getBusiness_scope());
        tvCompanyName.setText(dianPubean.getCompany_name());
        tvContactsMobile.setText(dianPubean.getContacts_mobile());
        tvDistrictName.setText(dianPubean.getDistrict_name());
        tvGoodsNum.setText(dianPubean.getGoods_num());
        tvOrderNum.setText(dianPubean.getOrder_num());
        tvStoreTime.setText(dianPubean.getStore_time());
        tvStoreZy.setText(dianPubean.getStore_zy());
        tvContactsName.setText(dianPubean.getContacts_name());
        tvScName.setText(dianPubean.getSc_name());

        xxTurnBackRate.setText(dianPubean.getTurn_back_rate() + "%");
        xxStoreDeliverycredit.setStarCount(dianPubean.getStore_deliverycredit());
        xxStoreDesccredit.setStarCount(dianPubean.getStore_desccredit());
        xxStoreScore.setStarCount((int) dianPubean.getStore_score());
        xxStoreServicecredit.setStarCount(dianPubean.getStore_servicecredit());
    }
}
