package com.duma.liudong.meiye.widget;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.duma.liudong.meiye.model.DianPubean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.ImageLoader;

/**
 * Created by Sai on 15/8/4.
 * 网络图片加载例子
 */
public class NetworkImageDianPuHolderView implements Holder<DianPubean.StoreSlideUrlComBean> {
    private ImageView imageView;

    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, DianPubean.StoreSlideUrlComBean data) {
        ImageLoader.with(Api.url + data.getLink_logo(), imageView);
    }
}