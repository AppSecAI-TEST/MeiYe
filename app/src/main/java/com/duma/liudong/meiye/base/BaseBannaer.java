package com.duma.liudong.meiye.base;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.widget.NetworkImageHolderView;

import java.util.List;

/**
 * Created by 79953 on 2016/8/4.
 */
public class BaseBannaer {
    public static final int time = 3500;

    public void setBanner(ConvenientBanner banner, List<String> imageURL, OnItemClickListener jiantin) {
        //给viewpager加的切换动画 现在取消了
//        try {
//            banner.getViewPager().setPageTransformer(true, DefaultTransformer.class.newInstance());
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
        banner.setScrollDuration(1500);
        //网络加载例子
//        networkImages = Arrays.asList(images);
        banner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, imageURL)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.dot_blur, R.drawable.huidian})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                .setOnItemClickListener(jiantin);
//                .setOnPageChangeListener(this);//监听翻页事件
    }
}
