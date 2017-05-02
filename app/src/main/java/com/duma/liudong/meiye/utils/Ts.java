package com.duma.liudong.meiye.utils;


import android.widget.Toast;

import com.duma.liudong.meiye.base.MyApplication;


/**
 * Created by Thinkpad on 2016/4/19.
 */
public class Ts {
    public static void setText(String res) {
        Toast.makeText(MyApplication.getInstance(), res, Toast.LENGTH_SHORT).show();
    }

    public static void erroy() {
        Toast.makeText(MyApplication.getInstance(), "服务器错误!", Toast.LENGTH_SHORT).show();
    }

    public static void JsonErroy() {
        Toast.makeText(MyApplication.getInstance(), "服务器错误!json解析错误!", Toast.LENGTH_SHORT).show();
    }

    public static void HttpErroy() {
        Toast.makeText(MyApplication.getInstance(), "网络连接错误!", Toast.LENGTH_SHORT).show();
    }

    public static void databaseErroy() {
        Toast.makeText(MyApplication.getInstance(), "数据库异常！", Toast.LENGTH_SHORT).show();
    }
//
//    public static void tstupianUtil(String res) {
//        Toast toast = Toast.makeText(MyApplication.getInstance(), res, Toast.LENGTH_SHORT);
//        toast.setGravity(Gravity.CENTER, 0, 0);
//        LinearLayout linearLayout = (LinearLayout) toast.getView();
//        LinearLayout.LayoutParams layoutParamsImageMain = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        layoutParamsImageMain.bottomMargin = 15;
//        layoutParamsImageMain.gravity = Gravity.CENTER;
//        ImageView imageView = new ImageView(MyApplication.getInstance());
//        imageView.setImageResource(R.drawable.gwcgg);
//        linearLayout.addView(imageView, 0, layoutParamsImageMain);
//        toast.show();
//    }
}
