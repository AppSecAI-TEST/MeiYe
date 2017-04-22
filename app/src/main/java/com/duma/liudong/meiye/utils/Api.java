package com.duma.liudong.meiye.utils;

import com.duma.liudong.meiye.base.MyApplication;

/**
 * Created by liudong on 17/3/21.
 */

public class Api {
    public static final String url = "http://meiye.duma-ivy.cn";

    //商品详情
    public static final String H5Url = Api.url + "/index.php/Mobile/Goods/goodsdetail?goods_id=";
    //论坛详情
    public static final String LunTanH5Url = Api.url + "/index.php/Mobile/Forum/detail?bbs_id=";
    //签到
    public static final String QianDaoH5Url = Api.url + "/index.php/Mobile/User/sign.html";
    //头条
    public static final String TouTiaoH5Url = Api.url + "/index.php/Mobile/Toutiao/detail.html?article_id=";
    //二维码
    public static final String erweima = Api.url + "/index.php/Mobile/User/qrcode?user_id=" + MyApplication.getSpUtils().getString(Constants.user_id) + "&token=" + MyApplication.getSpUtils().getString(Constants.token);
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
    public static final String cancelOrder = url + "/App/user/cancelOrder";
    public static final String delOrder = url + "/App/user/delOrder";
    public static final String confirmOrder = url + "/App/user/confirmOrder";
    public static final String comment = url + "/App/user/comment";
    public static final String withdraw = url + "/App/user/withdraw";
    public static final String footList = url + "/App/user/footList";
    public static final String delFoot = url + "/App/user/delFoot";
    public static final String delCollect = url + "/App/user/delCollect";
    public static final String storeCollect = url + "/App/user/storeCollect";
    public static final String orderInfo = url + "/App/user/orderInfo";
    /**
     * seller-个人中心
     */
    public static final String sellerindex = url + "/App/seller/index";
    public static final String my_list = url + "/App/Goods/my_list";
    public static final String is_warehouse = url + "/App/Goods/is_warehouse";
    public static final String is_onsale = url + "/App/Goods/is_onsale";
    public static final String del = url + "/App/Goods/del";
    /**
     * Index
     */
    public static final String cate = url + "/App/Index/cate";
    public static final String mainindex = url + "/App/Index/index";
    public static final String goodindex = url + "/App/Goods/index";
    public static final String store_search = url + "/App/Goods/store_search";
    public static final String recommend = url + "/App/Index/recommend";

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
    public static final String serverOrder = url + "/App/order/serverOrder";
    public static final String serverSure = url + "/App/order/serverSure";

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
    public static final String follow_list = url + "/App/Bbs/follow_list";
    public static final String follow_bbs = url + "/App/Bbs/follow_bbs";
    public static final String my_add_bbs = url + "/App/Bbs/my_add_bbs";

    /**
     * Tuangou - 团购列表
     */
    public static final String Tuangouindex = url + "/App/Tuangou/index";
    public static final String address = url + "/App/Tuangou/address";

    /**
     * pay
     */
    public static final String PayserverOrder = url + "/App/Pay/makeAlipay";
}
