package com.duma.liudong.meiye.utils;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.model.LoginBean;
import com.duma.liudong.meiye.view.classift.ShangPingLieBiaoActivity;
import com.duma.liudong.meiye.view.classift.ShangPingXiangQinWeb;
import com.duma.liudong.meiye.view.classift.dianPu.DianPuActivity;
import com.duma.liudong.meiye.view.shoppingCart.QueRenDinDanActivity;
import com.duma.liudong.meiye.view.start.login.LoginActivity;
import com.duma.liudong.meiye.view.start.main.MainActivity;

import java.text.SimpleDateFormat;

/**
 * Created by liudong on 17/3/20.
 */

public class StartUtil {
    //跳转登录页面
    public static void toLogin(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.out_to_left2, R.anim.in_from_right);
    }

    //保存登录状态
    public static void saveLogin(LoginBean bean) {
        MyApplication.getSpUtils().put(Constants.user_id, bean.getUser_id());
        MyApplication.getSpUtils().put(Constants.phone, bean.getPhone());
        MyApplication.getSpUtils().put(Constants.token, bean.getToken());
        MyApplication.getSpUtils().put(Constants.head_pic, bean.getHead_pic());
        setBirthday(bean.getBirthday());
        setNickName(bean.getNickname());
        setSex(bean.getSex());
    }

    //跳转首页
    public static void toMain(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);
    }

    //跳转商品列表
    public static void toShangPingLieBiao(Activity activity, String key, String Value, String title) {
        Intent intent = new Intent(activity, ShangPingLieBiaoActivity.class);
        intent.putExtra("key", key);
        intent.putExtra("Value", Value);
        intent.putExtra("title", title);
        activity.startActivity(intent);
    }

    //跳转店铺
    public static void toDianPu(Activity activity, String id) {
        Intent intent = new Intent(activity, DianPuActivity.class);
        intent.putExtra("id", id);
        activity.startActivity(intent);
    }

    //跳转商品详情页
    public static void toShangPingWeb(Activity activity, String url) {
        Intent intent = new Intent(activity, ShangPingXiangQinWeb.class);
        intent.putExtra("url", url);
        activity.startActivity(intent);
    }

    //跳转确认订单
    public static void toQueRenDinDan(Activity activity, String store_id) {
        Intent intent = new Intent(activity, QueRenDinDanActivity.class);
        intent.putExtra("store_id", store_id);
        activity.startActivity(intent);
    }

    public static void setSw(SwipeRefreshLayout swLoading, SwipeRefreshLayout.OnRefreshListener onRefreshListener) {
        swLoading.setOnRefreshListener(onRefreshListener);
        swLoading.setColorSchemeColors(MyApplication.getInstance().getResources().getColor(R.color.main_red));
    }

    public static void setBirthday(String Birthday) {
        MyApplication.getSpUtils().put(Constants.birthday, getTime(Long.parseLong(Birthday) * 1000));
    }


    public static void setNickName(String nickName) {
        //如果昵称为空的话 给个默认的
        if (isEmpty(nickName)) {
            MyApplication.getSpUtils().put(Constants.nickname, "用户:" + MyApplication.getSpUtils().getString(Constants.phone));
        } else {
            MyApplication.getSpUtils().put(Constants.nickname, nickName);
        }
    }

    public static void setSex(String sex) {
        switch (sex) {
            case "0":
                MyApplication.getSpUtils().put(Constants.sex, "保密");
                break;
            case "1":
                MyApplication.getSpUtils().put(Constants.sex, "男");
                break;
            case "2":
                MyApplication.getSpUtils().put(Constants.sex, "女");
                break;
        }
    }

    public static boolean isLogin() {
        return MyApplication.getSpUtils().getString(Constants.user_id) == null || MyApplication.getSpUtils().getString(Constants.user_id).equals("") ? false : true;
    }

    public static void cancleLogin() {
        MyApplication.getSpUtils().clear();
    }

    public static String getTime(long l) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(l);
    }

    //textView变颜色
    public static void tvHui(TextView textView) {
        textView.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.text_hui));
        textView.setBackgroundColor(MyApplication.getInstance().getResources().getColor(R.color.bg_hui));
    }

    public static void tvRed(TextView textView) {
        textView.setTextColor(MyApplication.getInstance().getResources().getColor(R.color.white));
        textView.setBackgroundColor(MyApplication.getInstance().getResources().getColor(R.color.button_rad));
    }

    //判断为空
    public static boolean isEmpty(String s) {
        if (s == null || s.equals("")) {
            return true;
        }
        return false;
    }
}
