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
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.view.me.UserDataActivity;

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

    @OnClick({R.id.img_head_pic, R.id.layout_account, R.id.layout_ref_num, R.id.layout_red_packet, R.id.layout_integral, R.id.layout_balance, R.id.layout_discount_coupon, R.id.layout_asset})
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
                break;
            case R.id.layout_integral:
                break;
            case R.id.layout_balance:
                break;
            case R.id.layout_discount_coupon:
                break;
            case R.id.layout_asset:
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
            if (StartUtil.isEmpty(meBean.getNick_name())) {
                tvNickName.setText("用户" + MyApplication.getSpUtils().getString(Constants.phone));
            } else {
                tvNickName.setText(meBean.getNick_name());
            }
            tvRefNum.setText("我的推广码:" + meBean.getRef_num());
            tvBalance.setText(meBean.getUser_money());//余额
            tvDistributMoney.setText("总收益:" + meBean.getDistribut_money());//总收益
            tvIntegral.setText(meBean.getReward_points());//积分
            tvNewClient.setText("今日新增:" + meBean.getNew_client());
            tvNewMoney.setText("今日收益:" + meBean.getNew_money());
            tvTotalClient.setText("我的客户:" + meBean.getTotal_client());
            // TODO: 17/3/23 这里有些字段服务器没有给出
            tvRedPacket.setText("0");//红包
            tvDiscountCoupon.setText("0");//优惠券
        }
    }
}
