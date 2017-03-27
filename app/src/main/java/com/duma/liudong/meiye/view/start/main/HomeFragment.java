package com.duma.liudong.meiye.view.start.main;

import android.view.View;
import android.widget.LinearLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseBannaer;
import com.duma.liudong.meiye.base.BaseFragment;
import com.duma.liudong.meiye.model.AdDemo;
import com.duma.liudong.meiye.widget.SampleAdapter;
import com.duma.liudong.meiye.widget.VerticalBannerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.duma.liudong.meiye.R.id.banner_ad;

/**
 * Created by liudong on 17/3/14.
 */

public class HomeFragment extends BaseFragment implements OnItemClickListener {

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
    @BindView(banner_ad)
    VerticalBannerView bannerAd;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {

        List<AdDemo> adList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            adList.add(new AdDemo("头条头条头条头条头条头条头条上上" + i, "头条头条头条头条头条头条头条下下" + i));
        }
        SampleAdapter sampleAdapter = new SampleAdapter(adList);
        bannerAd.setAdapter(sampleAdapter);
        bannerAd.start();
        List<String> tupian = new ArrayList<>();
        tupian.add("https://img.alicdn.com/tfs/TB1.cY6PVXXXXaRaXXXXXXXXXXX-520-280.jpg_.webp");
        tupian.add("https://img.alicdn.com/simba/img/TB1b_dcQXXXXXcXapXXSutbFXXX.jpg");
        tupian.add("https://img.alicdn.com/simba/img/TB1YVzOPVXXXXXoXXXXSutbFXXX.jpg");
        tupian.add("https://img.alicdn.com/simba/img/TB1ypH1NXXXXXbsapXXSutbFXXX.jpg");
        tupian.add("https://img.alicdn.com/tfs/TB1Yw3pPVXXXXbqXFXXXXXXXXXX-520-280.jpg_.webp");
        new BaseBannaer().setBanner(bannerHome, tupian, this);
    }

    @OnClick({R.id.layout_seckill, R.id.layout_group, R.id.layout_customization, R.id.layout_train, R.id.layout_sign, R.id.layout_coupons, R.id.layout_makeMoney, R.id.layout_enter})
    public void onClick(View view) {
        switch (view.getId()) {
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
                break;
            case R.id.layout_makeMoney:
                break;
            case R.id.layout_enter:
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

    }
}
