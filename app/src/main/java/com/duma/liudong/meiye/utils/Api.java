package com.duma.liudong.meiye.utils;

/**
 * Created by liudong on 17/3/21.
 */

public class Api {
    public static final String url = "http://meiye.duma-ivy.cn";

    public static final String H5Url = Api.url + "/index.php/Mobile/Goods/goodsdetail?goods_id=";

    public static final String LunTanH5Url = Api.url + "/index.php/Mobile/Forum/detail?bbs_id=";
    /**
     * 个人用户
     */
    public static final String register = url + "/App/user/register";
    public static final String login = url + "/App/user/login";
    public static final String sendCode = url + "/App/user/sendCode";
    public static final String alterPwd = url + "/App/user/alterPwd";
    public static final String index = url + "/App/user/index";
    public static final String setting = url + "/App/user/setting";
    public static final String alterHead = url + "/App/user/alterHead";
    public static final String changePwd = url + "/App/user/changePwd";
    public static final String feedback = url + "/App/user/feedback";
    public static final String myClient = url + "/App/user/myClient";
    public static final String message = url + "/App/user/message";
    public static final String messageAction = url + "/App/user/messageAction";
    public static final String moneyDetail = url + "/App/user/moneyDetail";
    public static final String pointsDetail = url + "/App/user/pointsDetail";
    public static final String coupon = url + "/App/user/coupon";
    public static final String packet = url + "/App/user/packet";
    public static final String collectList = url + "/App/user/collectList";
    public static final String editAddress = url + "/App/user/editAddress";
    public static final String addressList = url + "/App/user/addressList";
    public static final String delAddress = url + "/App/user/delAddress";
    public static final String orderList = url + "/App/user/orderList";
    /**
     * Index
     */
    public static final String cate = url + "/App/Index/cate";
    public static final String mainindex = url + "/App/Index/index";
    public static final String goodindex = url + "/App/Goods/index";

    /**
     * coupon - 领取优惠券
     */
    public static final String couponindex = url + "/App/coupon/index";
    public static final String getCoupon = url + "/App/coupon/getCoupon";

    /**
     * order
     */
    public static final String cartList = url + "/App/order/cartList";
    public static final String delcart = url + "/App/order/delcart";
    public static final String makeSure = url + "/App/order/makeSure";
    public static final String makeOrder = url + "/App/order/makeOrder";
    /**
     * 头条
     */
    public static final String Toutiaoindex = url + "/App/Toutiao/index";

    /**
     * store
     */
    public static final String Storeindex = url + "/App/Store/index";
    public static final String Storegoods = url + "/App/Store/goods";
    public static final String dynamic = url + "/App/Store/dynamic";
    public static final String store_class = url + "/App/Store/store_class";

    /**
     * Market - 秒杀活动接口
     */
    public static final String miaosha = url + "/App/Market/miaosha";

    /**
     * Bbs - 论坛分类
     */
    public static final String cat = url + "/App/Bbs/cat";
    public static final String top = url + "/App/Bbs/top";
    public static final String Bbsindex = url + "/App/Bbs/index";
    public static final String upload = url + "/App/Bbs/upload";
    public static final String add = url + "/App/Bbs/add";
    public static final String like = url + "/App/Bbs/like";
}
