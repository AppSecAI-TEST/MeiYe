package com.duma.liudong.meiye.utils;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.model.LoginBean;
import com.duma.liudong.meiye.view.classift.ShangPingLieBiaoActivity;
import com.duma.liudong.meiye.view.classift.ShangPingXiangQinWeb;
import com.duma.liudong.meiye.view.classift.dianPu.DianPuActivity;
import com.duma.liudong.meiye.view.classift.dianPu.DianPuListActivity;
import com.duma.liudong.meiye.view.classift.dianPu.JuBaoActivity;
import com.duma.liudong.meiye.view.home.MessageContentActivity;
import com.duma.liudong.meiye.view.home.SouSuoActivity;
import com.duma.liudong.meiye.view.home.meiTuan.TuanGouActivity;
import com.duma.liudong.meiye.view.me.dinDan.FuWuXiangQinActivity;
import com.duma.liudong.meiye.view.me.dinDan.QuanBuDinDanActivity;
import com.duma.liudong.meiye.view.me.dinDan.ShenQinTuiKuanActivity;
import com.duma.liudong.meiye.view.me.dinDan.ShiWuDianDanXiangQingActivity;
import com.duma.liudong.meiye.view.me.dinDan.TuiKuanXiangQinActivity;
import com.duma.liudong.meiye.view.me.dinDan.WoDeDinZhiActivity;
import com.duma.liudong.meiye.view.shoppingCart.FuKuanChenGongActivity;
import com.duma.liudong.meiye.view.shoppingCart.QueRenDinDanActivity;
import com.duma.liudong.meiye.view.shoppingCart.ZhiFuActivity;
import com.duma.liudong.meiye.view.start.OrideService;
import com.duma.liudong.meiye.view.start.login.LoginActivity;
import com.duma.liudong.meiye.view.start.main.MainActivity;
import com.duma.liudong.meiye.view.start.main.WebViewActivity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by liudong on 17/3/20.
 */

public class StartUtil {
    //跳转登录页面
    public static void toLogin(Activity activity) {
        toLogin(activity, "no");
    }

    //跳转登录在跳注册页面
    public static void toLogin(Activity activity, String a) {
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("type", a);
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
    public static void toShangPingLieBiao(Activity activity, String key, String Value, String title, String goods_type) {
        Intent intent = new Intent(activity, ShangPingLieBiaoActivity.class);
        intent.putExtra("key", key);
        intent.putExtra("Value", Value);
        intent.putExtra("title", title);
        intent.putExtra("goods_type", goods_type);
        activity.startActivity(intent);
    }

    //跳转商品列表
    public static void toDianPuList(Activity activity, String keyword) {
        Intent intent = new Intent(activity, DianPuListActivity.class);
        intent.putExtra("keyword", keyword);
        activity.startActivity(intent);
    }

    //跳转团购
    public static void toTuanGou(Activity activity, String res) {
        Intent intent = new Intent(activity, TuanGouActivity.class);
        intent.putExtra("res", res);
        activity.startActivity(intent);
    }

    //跳转h5
    public static void toH5Web(Activity activity, String url, String title) {
        Intent intent = new Intent(activity, WebViewActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        activity.startActivity(intent);
    }

    //实物/定制 订单
    public static void toQuanBuDinDan(Activity activity, String type) {
        toQuanBuDinDan(activity, type, "");
    }

    //实物/定制 订单
    public static void toQuanBuDinDan(Activity activity, String type, String position) {
        toQuanBuDinDan(activity, type, position, "");
    }

    //实物/定制 订单
    public static void toQuanBuDinDan(Activity activity, String type, String position, String store_id) {
        toQuanBuDinDan(activity, type, position, store_id, "");
    }

    //实物/定制 订单
    public static void toQuanBuDinDan(Activity activity, String type, String position, String store_id, String isFinish) {
        Intent intent = new Intent(activity, QuanBuDinDanActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("position", position);
        intent.putExtra("store_id", store_id);
        intent.putExtra("isFinish", isFinish);//有值就是返回首页
        activity.startActivity(intent);
    }

    //我的定制
    public static void toWoDiDinZhi(Activity activity, String position) {
        Intent intent = new Intent(activity, WoDeDinZhiActivity.class);
        intent.putExtra("position", position);
        activity.startActivity(intent);
    }

    //我的定制
    public static void toWoDiDinZhi(Activity activity) {
        Intent intent = new Intent(activity, WoDeDinZhiActivity.class);
        intent.putExtra("position", "0");
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
    public static void toQueRenDinDan(Activity activity, String key, String value, String type) {
        toQueRenDinDan(activity, key, value, type, "", "");
    }

    public static void toQueRenDinDan(Activity activity, String key, String value, String type, String spell_id, String cart_id) {
        Intent intent = new Intent(activity, QueRenDinDanActivity.class);
        intent.putExtra("key", key);
        intent.putExtra("value", value);
        intent.putExtra("type", type);//是否开团 1:是
        intent.putExtra("spell_id", spell_id);
        intent.putExtra("cart_id", cart_id);
        activity.startActivity(intent);
    }

//    //跳转论坛
//    public static void toLunTanWeb(Activity activity, String bbs_id) {
//        Intent intent = new Intent(activity, LunTanXiangQinWeb.class);
//        intent.putExtra("url", Api.LunTanH5Url + bbs_id);
//        activity.startActivity(intent);
//    }

    //跳转支付
    public static void toZhiFu(Activity activity, String id, String money, String type) {
        Intent intent = new Intent(activity, ZhiFuActivity.class);
        intent.putExtra("money", money);
        intent.putExtra("id", id);
        intent.putExtra("type", type);//判断是那种商品//1:实物,2:定制,3:团购
        activity.startActivity(intent);
    }

    //搜索
    public static void toSousuo(Activity activity) {
        toSousuo(activity, "宝贝");
    }

    //搜索
    public static void toSousuo(Activity activity, String type) {
        Intent intent = new Intent(activity, SouSuoActivity.class);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }

    //跳转支付成功
    public static void toZhiFuSuccess(Activity activity, String type, String order_id) {
        Intent intent = new Intent(activity, FuKuanChenGongActivity.class);
        intent.putExtra("order_id", order_id);
        intent.putExtra("type", type);//判断是那种商品//1:实物,2:定制,3:团购
        activity.startActivity(intent);
    }

    //跳转分享
    public static void toShare(Activity activity, String title, String url) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, StartUtil.getShare(title, url));
        activity.startActivity(Intent.createChooser(intent, "分享到"));
    }

    //跳转举报
    public static void toJubao_sp(Activity activity, String id) {
        toJubao(activity, "商品", id);
    }

    //跳转举报
    public static void toJubao_dp(Activity activity, String id) {
        toJubao(activity, "店铺", id);
    }

    //跳转举报
    public static void toJubao(Activity activity, String type, String id) {
        Intent intent = new Intent(activity, JuBaoActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("id", id);
        activity.startActivity(intent);
    }

    //跳转退款
    public static void totuikuan(Activity activity, String order_id, String order_sn,
                                 String goods_id, String storName, String name, String img, String num, String danjia, String spec_key) {
        Intent intent = new Intent(activity, ShenQinTuiKuanActivity.class);
        intent.putExtra("order_id", order_id);
        intent.putExtra("order_sn", order_sn);
        intent.putExtra("goods_id", goods_id);
        intent.putExtra("storName", storName);
        intent.putExtra("name", name);
        intent.putExtra("img", img);
        intent.putExtra("num", num);
        intent.putExtra("danjia", danjia);
        intent.putExtra("spec_key", spec_key);
        activity.startActivity(intent);
    }

    //跳转退款详情
    public static void totuikuanXiangQin(Activity activity, String order_id, String order_sn, String goods_id,
                                         String storName, String name, String img, String num, String danjia, String spec_key) {
        Intent intent = new Intent(activity, TuiKuanXiangQinActivity.class);
        intent.putExtra("order_id", order_id);
        intent.putExtra("order_sn", order_sn);
        intent.putExtra("goods_id", goods_id);
        intent.putExtra("storName", storName);
        intent.putExtra("name", name);
        intent.putExtra("img", img);
        intent.putExtra("num", num);
        intent.putExtra("danjia", danjia);
        intent.putExtra("spec_key", spec_key);
        activity.startActivity(intent);
    }

    //跳转详情页
    public static void toXiangQin(Activity activity, String type, String id, String fenlei_type, String store_id) {
        //判断是那种商品//1:实物,2:定制,3:团购
        Intent intent;
        switch (type) {
            case "1":
                //1:实物
                intent = new Intent(activity, ShiWuDianDanXiangQingActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("fenlei_type", fenlei_type);
                intent.putExtra("store_id", store_id);
                activity.startActivity(intent);
                break;
            case "2":
                //定制  ->就是开团
                intent = new Intent(activity, ShiWuDianDanXiangQingActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("fenlei_type", fenlei_type);
                intent.putExtra("store_id", store_id);
                activity.startActivity(intent);
//                intent = new Intent(activity, DinZhiXiangQinActivity.class);
//                intent.putExtra("id", id);
//                activity.startActivity(intent);
                break;
            case "3":
                //团购->就是服务 带卷码的
                intent = new Intent(activity, FuWuXiangQinActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("fenlei_type", fenlei_type);
                intent.putExtra("store_id", store_id);
                activity.startActivity(intent);
                break;
        }

    }

    public static void setSw(SwipeRefreshLayout swLoading, SwipeRefreshLayout.OnRefreshListener onRefreshListener) {
        swLoading.setOnRefreshListener(onRefreshListener);
        swLoading.setColorSchemeColors(MyApplication.getInstance().getResources().getColor(R.color.main_red));
    }

    public static void setBirthday(String Birthday) {
        if (Birthday.equals("") || Birthday == null) {
            Birthday = "0";
        }
        long l = Long.parseLong(Birthday);
        MyApplication.getSpUtils().put(Constants.birthday, getTime(l * 1000));
    }

    public static String setNum(double Birthday) {
        DecimalFormat df = new DecimalFormat("0.0");
        return df.format(Birthday);
    }

    public static String setNumOr00(double Birthday) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(Birthday);
    }

    public static String getShare(String title, String url) {
        return "【" + title + "】" + url + "  (分享自美帝基地)";
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

    public static String getShiJian(long time) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time * 1000);
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

    public static void sendNotification(Context activity) {
        Intent intent = new Intent(activity, MessageContentActivity.class);
        intent.putExtra("type", "3");
        intent.putExtra("title", "订单提醒");
        PendingIntent resultPendingIntent = PendingIntent.getActivity(
                activity, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification build = new NotificationCompat.Builder(activity)
                .setSmallIcon(R.mipmap.icon)
                .setContentTitle("美帝购物")
                .setContentText("尊敬的商家,您有新的订单通知!")
                .setContentIntent(resultPendingIntent)
                .setPriority(2)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true)
                .build();
        NotificationManager mNotifyMgr =
                (NotificationManager) activity.getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(001, build);
    }

    /**
     * 开始一个服务
     */
    public static void goOridService(Activity activity) {
        activity.startService(new Intent(activity, OrideService.class));
    }

    public static String setNumOr00(float Birthday) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(Birthday);
    }
}
