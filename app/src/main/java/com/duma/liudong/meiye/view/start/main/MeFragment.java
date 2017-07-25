package com.duma.liudong.meiye.view.start.main;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseFragment;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.model.IndexBean;
import com.duma.liudong.meiye.model.MeBean;
import com.duma.liudong.meiye.model.PhoneBean;
import com.duma.liudong.meiye.model.TuiJianBean;
import com.duma.liudong.meiye.presenter.BaseAdAdapter;
import com.duma.liudong.meiye.presenter.PublicPresenter;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.DialogUtil;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.utils.Ts;
import com.duma.liudong.meiye.view.me.DinDanZhongXinActivity;
import com.duma.liudong.meiye.view.me.FanKuiActivity;
import com.duma.liudong.meiye.view.me.FenSiActivity;
import com.duma.liudong.meiye.view.me.GuanZhuActivity;
import com.duma.liudong.meiye.view.me.HongBaoActivity;
import com.duma.liudong.meiye.view.me.JiFenActivity;
import com.duma.liudong.meiye.view.me.ShouCangActivity;
import com.duma.liudong.meiye.view.me.UserDataActivity;
import com.duma.liudong.meiye.view.me.WoDeZuJiActivity;
import com.duma.liudong.meiye.view.me.WoDekeHuActivity;
import com.duma.liudong.meiye.view.me.YouHuiJuanActivity;
import com.duma.liudong.meiye.view.me.yuE.YuEActivity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;

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
    @BindView(R.id.rv_shangping)
    RecyclerView rvShangping;
    @BindView(R.id.img_huiyuan)
    ImageView imgHuiyuan;
    @BindView(R.id.tv_denglu)
    TextView tvDenglu;
    @BindView(R.id.tv_dianhua)
    TextView tvDianhua;
    private Intent intent;
    private BaseAdAdapter<IndexBean.ShiwuBean> shangpingAdapter;
    private TuiJianBean bean;
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
        shangpingAdapter = new BaseAdAdapter<IndexBean.ShiwuBean>(mActivity, rvShangping) {
            @Override
            protected void getView(ViewHolder holder, final IndexBean.ShiwuBean shiwuBean, int position) {
                holder.setText(R.id.tv_goods_name, shiwuBean.getGoods_name());
                holder.setText(R.id.tv_market_price, "¥" + shiwuBean.getMarket_price());
                holder.setText(R.id.tv_sales_sum, shiwuBean.getSales_sum() + "人付款");
                ImageView imageView = holder.getView(R.id.img_original_img);
                ImageLoader.with(shiwuBean.getOriginal_img(), imageView);

                TextView tv = holder.getView(R.id.tv_shop_price);
                tv.setText(shiwuBean.getShop_price());
                tv.setVisibility(View.VISIBLE);
                if (shiwuBean.getShop_price().equals("")) {
                    tv.setVisibility(View.GONE);
                }
                holder.setOnClickListener(R.id.layout_onClick, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StartUtil.toShangPingWeb(mActivity, Api.H5Url() + shiwuBean.getGoods_id());
                    }
                });

            }
        };

        tvDianhua.setText("加载中...");
        OkHttpUtils
                .get()
                .url(Api.phone)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        DialogUtil.hide();
                        final PhoneBean phoneBean = new Gson().fromJson(result, PhoneBean.class);
                        tvDianhua.setText(phoneBean.getPhone());
                    }
                });
        tvDianhua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvDianhua.getText().toString().equals("加载中...")) {
                    Ts.setText("加载中...");
                } else {
                    mActivity.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tvDianhua.getText().toString())));
                }

            }
        });
    }

    private void tuiJianHttp() {
        OkHttpUtils
                .get()
                .tag(this)
                .url(Api.recommend)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        try {
                            bean = new Gson().fromJson(result, TuiJianBean.class);
                            shangpingAdapter.setmList(bean.getGoods());
                            ImageLoader.with(bean.getAd().getAd_code(), imgAd);
                        } catch (JsonSyntaxException e) {
                            Ts.JsonErroy();
                        }
                    }
                });
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

    @OnClick({R.id.tv_denglu, R.id.tv_level_up, R.id.layout_wode_kehu, R.id.img_head_pic, R.id.layout_account, R.id.layout_ref_num, R.id.layout_red_packet, R.id.layout_integral, R.id.layout_balance, R.id.layout_discount_coupon, R.id.layout_asset, R.id.layout_shiwu_dindan, R.id.layout_tuangou_dindan, R.id.layout_dinzhi_dindan, R.id.layout_wode_dinzhi, R.id.layout_wode_dindan, R.id.img_ad, R.id.layout_qiandao, R.id.layout_guanzhuan, R.id.layout_tiezi, R.id.layout_fensi, R.id.layout_luntan, R.id.layout_tequan, R.id.layout_fankui, R.id.layout_zuji, R.id.layout_shoucang})
    public void onClick(View view) {
        if (!StartUtil.isLogin()) {
            StartUtil.toLogin(mActivity);
            return;
        }
        switch (view.getId()) {
            case R.id.img_head_pic:
                //个人资料or账户管理
                startActivity(new Intent(mActivity, UserDataActivity.class));
                break;
            case R.id.layout_account:
                //个人资料or账户管理
                startActivity(new Intent(mActivity, UserDataActivity.class));
                break;
            case R.id.layout_ref_num:
                //二维码
                StartUtil.toH5Web(mActivity, Api.erweima(), "我的推广码");
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
                //我的资产->跳余额
                startActivity(new Intent(mActivity, YuEActivity.class));
                break;
            case R.id.layout_shiwu_dindan:
                //实物订单
                StartUtil.toQuanBuDinDan(mActivity, "1");
                break;
            case R.id.layout_tuangou_dindan:
                //团购订单
                StartUtil.toQuanBuDinDan(mActivity, "3");
                break;
            case R.id.layout_dinzhi_dindan:
                //定制订单
                StartUtil.toQuanBuDinDan(mActivity, "2");
                break;
            case R.id.layout_wode_dinzhi:
                //我的定制
                StartUtil.toWoDiDinZhi(mActivity);
                break;
            case R.id.layout_wode_dindan:
                //我的订单
                startActivity(new Intent(mActivity, DinDanZhongXinActivity.class));
                break;
            case R.id.img_ad:
                //中间的广告
                if (bean != null) {
                    StartUtil.toH5Web(mActivity, bean.getAd().getAd_link(), bean.getAd().getAd_name());
                }
                break;
            case R.id.layout_qiandao:
                StartUtil.toH5Web(mActivity, Api.QianDaoH5Url, "签到");
                break;
            case R.id.layout_guanzhuan:
                //关注
                intent = new Intent(mActivity, FenSiActivity.class);
                intent.putExtra("type", "0");
                startActivity(intent);
                break;
            case R.id.layout_tiezi:
                //贴子
//                this.intent = new Intent(mActivity, GuanZhuActivity.class);
//                this.intent.putExtra("url", Api.my_add_bbs);
//                startActivity(this.intent);
                this.intent = new Intent(mActivity, GuanZhuActivity.class);
                this.intent.putExtra("url", Api.follow_bbs);
                startActivity(this.intent);
                break;
            case R.id.layout_fensi:
                //粉丝
                intent = new Intent(mActivity, FenSiActivity.class);
                intent.putExtra("type", "1");
                startActivity(intent);
                break;
            case R.id.layout_luntan:
                //我的论坛
                this.intent = new Intent(mActivity, GuanZhuActivity.class);
                this.intent.putExtra("url", Api.my_add_bbs);
                startActivity(this.intent);
                break;
            case R.id.layout_tequan:
                //特权
                StartUtil.toH5Web(mActivity, Api.TeQuanH5, "新人特权");
                break;
            case R.id.tv_level_up:
                //会员升级
                StartUtil.toH5Web(mActivity, Api.HuiYuanH5, "会员升级");
                break;
            case R.id.layout_fankui:
                //反馈
                startActivity(new Intent(mActivity, FanKuiActivity.class));
                break;
            case R.id.layout_zuji:
                //足迹
                startActivity(new Intent(mActivity, WoDeZuJiActivity.class));
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
        tuiJianHttp();
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
            tvNickName.setText("注册");
            tvRefNum.setText("我的推广码:xxxxxxxxxxx");//推广码
            tvBalance.setText("0");//余额
            tvDiscountCoupon.setText("0");//优惠券
            tvDistributMoney.setText("总收益:0");//总收益
            tvIntegral.setText("0");//积分
            tvNewClient.setText("今日新增:0");
            tvNewMoney.setText("今日收益:0");
            tvTotalClient.setText("我的客户:0");
            tvRedPacket.setText("0");//红包
            imgHuiyuan.setVisibility(View.VISIBLE);
            ImageLoader.with_p(R.drawable.huiyuan, imgHuiyuan);
            tvDenglu.setVisibility(View.VISIBLE);
        } else {
            tvDenglu.setVisibility(View.GONE);
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
            tvRedPacket.setText(meBean.getPacket());//红包
            tvDiscountCoupon.setText(meBean.getCoupon());//优惠券
            imgHuiyuan.setVisibility(View.VISIBLE);
            ImageLoader.with_p(Api.url + meBean.getLevel_img(), imgHuiyuan);
        }
    }


    @OnClick(R.id.tv_nick_name)
    public void onClick() {
        //昵称 注册的那个字
        if (!StartUtil.isLogin()) {
            StartUtil.toLogin(mActivity, "ok");
            return;
        }

    }
}
