package com.duma.liudong.meiye.view.start.main;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseBannaer;
import com.duma.liudong.meiye.base.BaseFragment;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.model.IndexBean;
import com.duma.liudong.meiye.presenter.BaseAdAdapter;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.view.home.LinJuanActivity;
import com.duma.liudong.meiye.widget.SampleAdapter;
import com.duma.liudong.meiye.widget.VerticalBannerView;
import com.google.gson.Gson;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/3/14.
 */

public class HomeFragment extends BaseFragment implements OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.banner_home)
    ConvenientBanner bannerHome;
    @BindView(R.id.layout_seckill)
    LinearLayout layoutSeckill;
    @BindView(R.id.layout_group)
    LinearLayout layoutGroup;
    @BindView(R.id.layout_customization)
    LinearLayout layoutCustomization;
    @BindView(R.id.layout_train)
    LinearLayout layoutTrain;
    @BindView(R.id.layout_sign)
    LinearLayout layoutSign;
    @BindView(R.id.layout_coupons)
    LinearLayout layoutCoupons;
    @BindView(R.id.layout_makeMoney)
    LinearLayout layoutMakeMoney;
    @BindView(R.id.layout_enter)
    LinearLayout layoutEnter;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;
    @BindView(R.id.banner_ad)
    VerticalBannerView bannerAd;
    @BindView(R.id.img_ad)
    ImageView imgAd;
    @BindView(R.id.img_miaosha)
    ImageView imgMiaosha;
    @BindView(R.id.img_xinpin)
    ImageView imgXinpin;
    @BindView(R.id.img_weihuo)
    ImageView imgWeihuo;
    @BindView(R.id.img_youpin)
    ImageView imgYoupin;
    @BindView(R.id.img_rexiao)
    ImageView imgRexiao;
    @BindView(R.id.img_haohuo)
    ImageView imgHaohuo;
    @BindView(R.id.layout_shangping)
    LinearLayout layoutShangping;
    @BindView(R.id.rv_shangping)
    RecyclerView rvShangping;
    @BindView(R.id.layout_tuangou)
    LinearLayout layoutTuangou;
    @BindView(R.id.rv_tuangou)
    RecyclerView rvTuangou;
    @BindView(R.id.layout_dingzhi)
    LinearLayout layoutDingzhi;
    @BindView(R.id.rv_dingzhi)
    RecyclerView rvDingzhi;
    @BindView(R.id.layout_tuijian)
    LinearLayout layoutTuijian;
    @BindView(R.id.rv_tuijian)
    RecyclerView rvDianpu;
    @BindView(R.id.layout_guanlian)
    LinearLayout layoutGuanlian;
    @BindView(R.id.rv_guanlian)
    RecyclerView rvTuijian;

    private IndexBean bean;
    private boolean one = true;

    private BaseAdAdapter<IndexBean.StoreBean> dianPuAdapter;
    private BaseAdAdapter<IndexBean.DingzhiBean> dingzhiAdapter;
    private BaseAdAdapter<IndexBean.ShiwuBean> shangpingAdapter;
    private BaseAdAdapter<IndexBean.TuangouBean> tuangouAdapter;
    private BaseAdAdapter<IndexBean.TuijianBean> tuijianAdapter;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        StartUtil.setSw(swLoading, this);
        dianPuAdapter = new BaseAdAdapter<IndexBean.StoreBean>(mActivity, rvDianpu, Constants.dianPu_AD) {
            @Override
            protected void getView(ViewHolder holder, IndexBean.StoreBean storeBean, int position) {
                holder.setText(R.id.tv_store_name, storeBean.getStore_name());
                ImageView img_store_banner = holder.getView(R.id.img_store_banner);
                ImageView img_store_logo = holder.getView(R.id.img_store_logo);
                ImageLoader.with(storeBean.getStore_banner(), img_store_banner);
                ImageLoader.with(storeBean.getStore_logo(), img_store_logo);
            }
        };
        dingzhiAdapter = new BaseAdAdapter<IndexBean.DingzhiBean>(mActivity, rvDingzhi, Constants.dingZhi_AD) {
            @Override
            protected void getView(ViewHolder holder, IndexBean.DingzhiBean dingzhiBean, int position) {
                holder.setText(R.id.tv_goods_name, dingzhiBean.getGoods_name());
                holder.setText(R.id.tv_market_price, dingzhiBean.getMarket_price());
                holder.setText(R.id.tv_sales_sum, dingzhiBean.getSales_sum());
                holder.setText(R.id.tv_sell_up, dingzhiBean.getSell_up() + "件起批");
                ImageView imageView = holder.getView(R.id.img_original_img);
                ImageLoader.with(dingzhiBean.getOriginal_img(), imageView);

                TextView tv = holder.getView(R.id.tv_shop_price);
                tv.setText("¥" + dingzhiBean.getShop_price());
                tv.setVisibility(View.VISIBLE);
                if (dingzhiBean.getShop_price().equals("")) {
                    tv.setVisibility(View.GONE);
                }

            }
        };
        shangpingAdapter = new BaseAdAdapter<IndexBean.ShiwuBean>(mActivity, rvShangping) {
            @Override
            protected void getView(ViewHolder holder, IndexBean.ShiwuBean shiwuBean, int position) {
                holder.setText(R.id.tv_goods_name, shiwuBean.getGoods_name());
                holder.setText(R.id.tv_market_price, shiwuBean.getMarket_price());
                holder.setText(R.id.tv_sales_sum, shiwuBean.getSales_sum() + "人付款");
                ImageView imageView = holder.getView(R.id.img_original_img);
                ImageLoader.with(shiwuBean.getOriginal_img(), imageView);

                TextView tv = holder.getView(R.id.tv_shop_price);
                tv.setText("¥" + shiwuBean.getShop_price());
                tv.setVisibility(View.VISIBLE);
                if (shiwuBean.getShop_price().equals("")) {
                    tv.setVisibility(View.GONE);
                }
            }
        };
        tuangouAdapter = new BaseAdAdapter<IndexBean.TuangouBean>(mActivity, rvTuangou) {
            @Override
            protected void getView(ViewHolder holder, IndexBean.TuangouBean tuangouBean, int position) {
                holder.setText(R.id.tv_goods_name, tuangouBean.getGoods_name());
                holder.setText(R.id.tv_market_price, tuangouBean.getMarket_price());
                holder.setText(R.id.tv_sales_sum, tuangouBean.getSales_sum() + "人付款");
                ImageView imageView = holder.getView(R.id.img_original_img);
                ImageLoader.with(tuangouBean.getOriginal_img(), imageView);

                TextView tv = holder.getView(R.id.tv_shop_price);
                tv.setText("¥" + tuangouBean.getShop_price());
                tv.setVisibility(View.VISIBLE);
                if (tuangouBean.getShop_price().equals("")) {
                    tv.setVisibility(View.GONE);
                }
            }
        };
        tuijianAdapter = new BaseAdAdapter<IndexBean.TuijianBean>(mActivity, rvTuijian) {
            @Override
            protected void getView(ViewHolder holder, IndexBean.TuijianBean tuijianBean, int position) {
                holder.setText(R.id.tv_goods_name, tuijianBean.getGoods_name());
                holder.setText(R.id.tv_market_price, tuijianBean.getMarket_price());
                holder.setText(R.id.tv_sales_sum, tuijianBean.getSales_sum() + "人付款");
                ImageView imageView = holder.getView(R.id.img_original_img);
                ImageLoader.with(tuijianBean.getOriginal_img(), imageView);

                TextView tv = holder.getView(R.id.tv_shop_price);
                tv.setText("¥" + tuijianBean.getShop_price());
                tv.setVisibility(View.VISIBLE);
                if (tuijianBean.getShop_price().equals("")) {
                    tv.setVisibility(View.GONE);
                }
            }
        };

        getIndexHttp();
    }

    @Override
    protected void onLazyLoad() {
        if (one)
            getIndexHttp();
    }

    public void getIndexHttp() {
        swLoading.setRefreshing(true);
        OkHttpUtils.getInstance().cancelTag("getIndexHttp");
        OkHttpUtils
                .get()
                .url(Api.mainindex)
                .tag("getIndexHttp")
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        one = false;
                        swLoading.setRefreshing(false);
                        bean = new Gson().fromJson(result, IndexBean.class);
                        initIndex();
                    }

                    @Override
                    protected void onError(String result) {
                        super.onError(result);
                        swLoading.setRefreshing(false);
                    }
                });
    }

    private void initIndex() {
        // TODO: 17/3/29 头条数据有问题
        SampleAdapter sampleAdapter = new SampleAdapter(bean.getArticle());
        bannerAd.setAdapter(sampleAdapter);
        bannerAd.start();
        new BaseBannaer().setBanner(bannerHome, bean.getFriend_link(), this);
        bannerHome.startTurning(BaseBannaer.time);
        ImageLoader.with(bean.getAd().getAd_code(), imgAd);
        ImageLoader.with(bean.getGoods_cat().get(0).getPic_url(), imgMiaosha);
        ImageLoader.with(bean.getGoods_cat().get(1).getPic_url(), imgXinpin);
        ImageLoader.with(bean.getGoods_cat().get(2).getPic_url(), imgWeihuo);
        ImageLoader.with(bean.getGoods_cat().get(3).getPic_url(), imgYoupin);
        ImageLoader.with(bean.getGoods_cat().get(4).getPic_url(), imgRexiao);
        ImageLoader.with(bean.getGoods_cat().get(5).getPic_url(), imgHaohuo);
        dianPuAdapter.setmList(bean.getStore());
        dingzhiAdapter.setmList(bean.getDingzhi());
        shangpingAdapter.setmList(bean.getShiwu());
        tuangouAdapter.setmList(bean.getTuangou());
        tuijianAdapter.setmList(bean.getTuijian());
    }

    @OnClick({R.id.layout_shangping, R.id.layout_tuangou, R.id.layout_dingzhi, R.id.layout_tuijian, R.id.layout_guanlian, R.id.img_ad, R.id.img_miaosha, R.id.img_xinpin, R.id.img_weihuo, R.id.img_youpin, R.id.img_rexiao, R.id.img_haohuo, R.id.layout_seckill, R.id.layout_group, R.id.layout_customization, R.id.layout_train, R.id.layout_sign, R.id.layout_coupons, R.id.layout_makeMoney, R.id.layout_enter})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_shangping:
                break;
            case R.id.layout_tuangou:
                break;
            case R.id.layout_dingzhi:
                break;
            case R.id.layout_tuijian:
                break;
            case R.id.layout_guanlian:
                break;
            case R.id.layout_seckill:
                break;
            case R.id.layout_group:
                break;
            case R.id.layout_customization:
                break;
            case R.id.layout_train:
                break;
            case R.id.layout_sign:
                break;
            case R.id.layout_coupons:
                //领卷
                startActivity(new Intent(mActivity, LinJuanActivity.class));
                break;
            case R.id.layout_makeMoney:
                break;
            case R.id.layout_enter:
                break;
            case R.id.img_ad:
                break;
            case R.id.img_miaosha:
                break;
            case R.id.img_xinpin:
                break;
            case R.id.img_weihuo:
                break;
            case R.id.img_youpin:
                break;
            case R.id.img_rexiao:
                break;
            case R.id.img_haohuo:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        bannerHome.startTurning(BaseBannaer.time);
    }

    @Override
    public void onPause() {
        super.onPause();
        bannerHome.stopTurning();
    }

    /**
     * 首页banner的点击事件
     *
     * @param position
     */
    @Override
    public void onItemClick(int position) {
        // TODO: 17/3/29 跳转商品详情
    }

    @Override
    public void onRefresh() {
        getIndexHttp();
    }

}
