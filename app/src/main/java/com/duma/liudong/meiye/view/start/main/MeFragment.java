package com.duma.liudong.meiye.view.start.main;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseFragment;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.model.MeBean;
import com.duma.liudong.meiye.presenter.PublicPresenter;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.view.me.FanKuiActivity;
import com.duma.liudong.meiye.view.me.HongBaoActivity;
import com.duma.liudong.meiye.view.me.JiFenActivity;
import com.duma.liudong.meiye.view.me.shiWuDinDan.ShiWuDinDanQuanBuActivity;
import com.duma.liudong.meiye.view.me.ShouCangActivity;
import com.duma.liudong.meiye.view.me.UserDataActivity;
import com.duma.liudong.meiye.view.me.WoDekeHuActivity;
import com.duma.liudong.meiye.view.me.YouHuiJuanActivity;
import com.duma.liudong.meiye.view.me.yuE.YuEActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/3/14.
 */

public class MeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, PublicPresenter.GetMeListener {
    @BindView(R.id.img_head_pic)
    ImageView imgHeadPic;
    @BindView(R.id.tv_nick_name)
    TextView tvNickName;
    @BindView(R.id.layout_account)
    LinearLayout layoutAccount;
    @BindView(R.id.tv_level)
    TextView tvLevel;
    @BindView(R.id.tv_level_up)
    TextView tvLevelUp;
    @BindView(R.id.tv_ref_num)
    TextView tvRefNum;
    @BindView(R.id.layout_ref_num)
    LinearLayout layoutRefNum;
    @BindView(R.id.tv_distribut_money)
    TextView tvDistributMoney;
    @BindView(R.id.tv_new_money)
    TextView tvNewMoney;
    @BindView(R.id.tv_total_client)
    TextView tvTotalClient;
    @BindView(R.id.tv_new_client)
    TextView tvNewClient;
    @BindView(R.id.tv_red_packet)
    TextView tvRedPacket;
    @BindView(R.id.layout_red_packet)
    LinearLayout layoutRedPacket;
    @BindView(R.id.tv_integral)
    TextView tvIntegral;
    @BindView(R.id.layout_integral)
    LinearLayout layoutIntegral;
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.layout_balance)
    LinearLayout layoutBalance;
    @BindView(R.id.tv_discount_coupon)
    TextView tvDiscountCoupon;
    @BindView(R.id.layout_discount_coupon)
    LinearLayout layoutDiscountCoupon;
    @BindView(R.id.layout_asset)
    LinearLayout layoutAsset;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;

    PublicPresenter publicPresenter;
    @BindView(R.id.layout_shiwu_dindan)
    LinearLayout layoutShiwuDindan;
    @BindView(R.id.layout_tuangou_dindan)
    LinearLayout layoutTuangouDindan;
    @BindView(R.id.layout_dinzhi_dindan)
    LinearLayout layoutDinzhiDindan;
    @BindView(R.id.layout_wode_dinzhi)
    LinearLayout layoutWodeDinzhi;
    @BindView(R.id.layout_wode_dindan)
    LinearLayout layoutWodeDindan;
    @BindView(R.id.img_ad)
    ImageView imgAd;
    @BindView(R.id.layout_qiandao)
    LinearLayout layoutQiandao;
    @BindView(R.id.layout_guanzhuan)
    LinearLayout layoutGuanzhuan;
    @BindView(R.id.layout_tiezi)
    LinearLayout layoutTiezi;
    @BindView(R.id.layout_fensi)
    LinearLayout layoutFensi;
    @BindView(R.id.layout_luntan)
    LinearLayout layoutLuntan;
    @BindView(R.id.layout_tequan)
    LinearLayout layoutTequan;
    @BindView(R.id.layout_fankui)
    LinearLayout layoutFankui;
    @BindView(R.id.layout_zuji)
    LinearLayout layoutZuji;
    @BindView(R.id.layout_shoucang)
    LinearLayout layoutShoucang;
    @BindView(R.id.layout_wode_kehu)
    LinearLayout layoutWodeKehu;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initData() {
        swLoading.setOnRefreshListener(this);
        swLoading.setColorSchemeColors(getResources().getColor(R.color.yellow));

        publicPresenter = new PublicPresenter();
        publicPresenter.setGetMeListener(this);
    }


    //切换到当前页面的时候
    @Override
    protected void onLazyLoad() {
        refresh();
    }

    //用户下拉刷新的时候
    @Override
    public void onRefresh() {
        refresh();
    }

    @OnClick({R.id.layout_wode_kehu, R.id.img_head_pic, R.id.layout_account, R.id.layout_ref_num, R.id.layout_red_packet, R.id.layout_integral, R.id.layout_balance, R.id.layout_discount_coupon, R.id.layout_asset, R.id.layout_shiwu_dindan, R.id.layout_tuangou_dindan, R.id.layout_dinzhi_dindan, R.id.layout_wode_dinzhi, R.id.layout_wode_dindan, R.id.img_ad, R.id.layout_qiandao, R.id.layout_guanzhuan, R.id.layout_tiezi, R.id.layout_fensi, R.id.layout_luntan, R.id.layout_tequan, R.id.layout_fankui, R.id.layout_zuji, R.id.layout_shoucang})
    public void onClick(View view) {
        if (!StartUtil.isLogin()) {
            StartUtil.toLogin(mActivity);
            return;
        }
        switch (view.getId()) {
            case R.id.img_head_pic:
                break;
            case R.id.layout_account:
                //个人资料or账户管理
                startActivity(new Intent(mActivity, UserDataActivity.class));
                break;
            case R.id.layout_ref_num:
                break;
            case R.id.layout_red_packet:
                //红包
                startActivity(new Intent(mActivity, HongBaoActivity.class));
                break;
            case R.id.layout_integral:
                //积分
                startActivity(new Intent(mActivity, JiFenActivity.class));
                break;
            case R.id.layout_balance:
                //余额
                startActivity(new Intent(mActivity, YuEActivity.class));
                break;
            case R.id.layout_discount_coupon:
                //优惠券
                startActivity(new Intent(mActivity, YouHuiJuanActivity.class));
                break;
            case R.id.layout_asset:
                break;
            case R.id.layout_shiwu_dindan:
                //实物订单
                startActivity(new Intent(mActivity, ShiWuDinDanQuanBuActivity.class));
                break;
            case R.id.layout_tuangou_dindan:
                break;
            case R.id.layout_dinzhi_dindan:
                break;
            case R.id.layout_wode_dinzhi:
                break;
            case R.id.layout_wode_dindan:
                break;
            case R.id.img_ad:
                break;
            case R.id.layout_qiandao:
                break;
            case R.id.layout_guanzhuan:
                break;
            case R.id.layout_tiezi:
                break;
            case R.id.layout_fensi:
                break;
            case R.id.layout_luntan:
                break;
            case R.id.layout_tequan:
                break;
            case R.id.layout_fankui:
                //反馈
                startActivity(new Intent(mActivity, FanKuiActivity.class));
                break;
            case R.id.layout_zuji:
                //足迹
                break;
            case R.id.layout_shoucang:
                //收藏
                startActivity(new Intent(mActivity, ShouCangActivity.class));
                break;
            case R.id.layout_wode_kehu:
                //客户
                startActivity(new Intent(mActivity, WoDekeHuActivity.class));
                break;
        }
    }

    public void refresh() {
        if (!StartUtil.isLogin()) {
            setData(null);
            return;
        }
        swLoading.setRefreshing(true);
        publicPresenter.getMe();
    }

    //获取数据后的回调
    @Override
    public void GetMeSuccess(MeBean meBean) {
        setData(meBean);
    }

    //数据请求错误的回调
    @Override
    public void GetMeError() {
        swLoading.setRefreshing(false);
    }

    //添加数据
    private void setData(MeBean meBean) {
        swLoading.setRefreshing(false);
        if (meBean == null) {
            ImageLoader.withYuan(R.drawable.touxiang, imgHeadPic);
            tvNickName.setText("登录/注册");
            tvRefNum.setText("我的推广码:...");//推广码
            tvBalance.setText("...");//余额
            tvDiscountCoupon.setText("...");//优惠券
            tvDistributMoney.setText("总收益:...");//总收益
            tvIntegral.setText("...");//积分
            tvNewClient.setText("今日新增:...");
            tvNewMoney.setText("今日收益:...");
            tvTotalClient.setText("我的客户:...");
            tvRedPacket.setText("...");//红包
        } else {
            if (StartUtil.isEmpty(meBean.getNickname())) {
                tvNickName.setText("用户" + MyApplication.getSpUtils().getString(Constants.phone));
            } else {
                tvNickName.setText(meBean.getNickname());
            }
            if (StartUtil.isEmpty(meBean.getHead_pic())) {
                ImageLoader.withYuan(R.drawable.touxiang, imgHeadPic);
            } else {
                ImageLoader.withYuan(Api.url + meBean.getHead_pic(), imgHeadPic);
            }
            tvRefNum.setText("我的推广码:" + meBean.getRef_num());
            tvBalance.setText(meBean.getUser_money());//余额
            tvDistributMoney.setText("总收益:" + meBean.getDistribut_money());//总收益
            tvIntegral.setText(meBean.getReward_points());//积分
            tvNewClient.setText("今日新增:" + meBean.getNew_client());
            tvNewMoney.setText("今日收益:" + meBean.getNew_money());
            tvTotalClient.setText("我的客户:" + meBean.getTotal_client());
            tvRedPacket.setText(meBean.getCoupon());//红包
            tvDiscountCoupon.setText(meBean.getPacket());//优惠券
        }
    }

}
