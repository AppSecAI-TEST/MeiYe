package com.duma.liudong.meiye.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.MyApplication;

/**
 * Created by 79953 on 2016/7/29.
 */
public class ImageLoader {
    public static void with(Object url, ImageView imageView) {
        Glide.with(MyApplication.getInstance())
                .load(url)
                .error(R.drawable.zu)//load失敗的Drawable
                .placeholder(R.drawable.zu)//loading時候的Drawable

//                .an//設置load完的動畫
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()//中心切圖, 會填滿
                .dontAnimate()
//                .fitCenter()//中心fit, 以原本圖片的長寬為主

                .into(imageView);
    }

    public static void with_p(Object url, ImageView imageView) {
        Glide.with(MyApplication.getInstance())
                .load(url)
                .error(R.drawable.zu)//load失敗的Drawable
                .placeholder(R.drawable.zu)//loading時候的Drawable
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .an//設置load完的動畫

//                .centerCrop()//中心切圖, 會填滿
                .dontAnimate()
//                .fitCenter()//中心fit, 以原本圖片的長寬為主

                .into(imageView);
    }

    public static void withYuan(Object url, ImageView imageView) {
        Glide.with(MyApplication.getInstance())
                .load(url)
                .error(R.drawable.touxiang)//load失敗的Drawable
                .placeholder(R.drawable.touxiang)//loading時候的Drawable
                .transform(new GlideCircleTransform(MyApplication.getInstance()))
                .into(imageView);
    }
}
