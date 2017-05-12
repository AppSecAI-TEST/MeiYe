package com.duma.liudong.meiye.utils;

import com.duma.liudong.meiye.base.MyApplication;

/**
 * Created by liudong on 17/3/21.
 */

public class Api {
    public static String url = "http://api.myjd.cc";
//    public static String url = "http://meiye.duma-ivy.cn";

    /**
     * h5
     */
    //商品详情
    public static String H5Url() {
        return Api.url + "/index.php/Mobile/Goods/goodsdetail?" + Constants.user_id + "=" + MyApplication.getSpUtils().getString(Constants.user_id) + "&goods_id=";
    }

    //论坛详情
    public static String LunTanH5Url = Api.url + "/index.php/Mobile/Forum/detail?bbs_id=";
    //签到
    public static String QianDaoH5Url = Api.url + "/index.php/Mobile/User/sign.html";
    //头条
    public static String TouTiaoH5Url = Api.url + "/index.php/Mobile/Toutiao/detail.html?article_id=";

    //二维码
    public static String erweima() {
        return Api.url + "/index.php/Mobile/User/qrcode?user_id=" + MyApplication.getSpUtils().getString(Constants.user_id) + "&token=" + MyApplication.getSpUtils().getString(Constants.token);
    }

    //赚钱
    public static String zhuanQianH5 = Api.url + "/index.php/Mobile/User/makeMoney";
    //入驻
    public static String ruzhuH5 = Api.url + "/index.php/Mobile/User/settled";
    //版权
    public static String banquanH5 = Api.url + "/index.php/Mobile/User/copyright";
    //会员升级
    public static String HuiYuanH5 = Api.url + "/index.php/Mobile/User/upgrade";
    //问卷调查
    public static String WenJuanH5 = Api.url + "/index.php/Mobile/Question/index";
    //查看物流
    public static String WuLiuH5 = Api.url + "/index.php/Mobile/Goods/logistics?order_id=";
    //新人特权
    public static String TeQuanH5 = Api.url + "/index.php/Mobile/User/privilege";
    /**
     * 个人用户
     */
    public static String register = url + "/App/user/register";
    public static String login = url + "/App/user/login";
    public static String sendCode = url + "/App/user/sendCode";
    public static String alterPwd = url + "/App/user/alterPwd";
    public static String index = url + "/App/user/index";
    public static String setting = url + "/App/user/setting";
    public static String alterHead = url + "/App/user/alterHead";
    public static String changePwd = url + "/App/user/changePwd";
    public static String feedback = url + "/App/user/feedback";
    public static String myClient = url + "/App/user/myClient";
    public static String message = url + "/App/user/message";
    public static String messageAction = url + "/App/user/messageAction";
    public static String moneyDetail = url + "/App/user/moneyDetail";
    public static String pointsDetail = url + "/App/user/pointsDetail";
    public static String coupon = url + "/App/user/coupon";
    public static String packet = url + "/App/user/packet";
    public static String red_pack = url + "/App/user/red_pack";
    public static String collectList = url + "/App/user/collectList";
    public static String editAddress = url + "/App/user/editAddress";
    public static String setDefaultAddr = url + "/App/user/setDefaultAddr";
    public static String addressList = url + "/App/user/addressList";
    public static String delAddress = url + "/App/user/delAddress";
    public static String orderList = url + "/App/user/orderList";
    public static String cancelOrder = url + "/App/user/cancelOrder";
    public static String delOrder = url + "/App/user/delOrder";
    public static String confirmOrder = url + "/App/user/confirmOrder";
    public static String comment = url + "/App/user/comment";
    public static String withdraw = url + "/App/user/withdraw";
    public static String footList = url + "/App/user/footList";
    public static String delFoot = url + "/App/user/delFoot";
    public static String delCollect = url + "/App/user/delCollect";
    public static String storeCollect = url + "/App/user/storeCollect";
    public static String orderInfo = url + "/App/user/orderInfo";
    public static String mySpell = url + "/App/user/mySpell";
    public static String returnGoods = url + "/App/user/returnGoods";
    public static String isBindIdCard = url + "/App/user/isBindIdCard";
    public static String sellerorderList = url + "/App/seller/orderList";

    public static String isComment = url + "/App/user/isComment";
    public static String isReturnGoods = url + "/App/user/isReturnGoods";
    public static String orderNum = url + "/App/user/orderNum";
    /**
     * seller-个人中心
     */
    public static String sellerindex = url + "/App/seller/index";
    public static String my_list = url + "/App/Goods/my_list";
    public static String is_warehouse = url + "/App/Goods/is_warehouse";
    public static String is_onsale = url + "/App/Goods/is_onsale";
    public static String del = url + "/App/Goods/del";
    public static String sellStatement = url + "/App/seller/sellStatement";
    public static String sellerorderInfo = url + "/App/seller/orderInfo";

    public static String phone = url + "/App/Index/phone";
    /**
     * Index
     */
    public static String cate = url + "/App/Index/cate";
    public static String mainindex = url + "/App/Index/index";
    public static String goodindex = url + "/App/Goods/index";
    public static String store_search = url + "/App/Goods/store_search";
    public static String recommend = url + "/App/Index/recommend";

    /**
     * coupon - 领取优惠券
     */
    public static String couponindex = url + "/App/coupon/index";
    public static String getCoupon = url + "/App/coupon/getCoupon";

    /**
     * order
     */
    public static String cartList = url + "/App/order/cartList";
    public static String delcart = url + "/App/order/delcart";
    public static String makeSure = url + "/App/order/makeSure";
    public static String makeOrder = url + "/App/order/makeOrder";
    public static String serverOrder = url + "/App/order/serverOrder";
    public static String serverSure = url + "/App/order/serverSure";

    /**
     * 头条
     */
    public static String Toutiaoindex = url + "/App/Toutiao/index";

    /**
     * store
     */
    public static String Storeindex = url + "/App/Store/index";
    public static String Storegoods = url + "/App/Store/goods";
    public static String dynamic = url + "/App/Store/dynamic";
    public static String store_class = url + "/App/Store/store_class";

    /**
     * Market - 秒杀活动接口
     */
    public static String miaosha = url + "/App/Market/miaosha";

    /**
     * Bbs - 论坛分类
     */
    public static String cat = url + "/App/Bbs/cat";
    public static String top = url + "/App/Bbs/top";
    public static String Bbsindex = url + "/App/Bbs/index";
    public static String upload = url + "/App/Bbs/upload";
    public static String add = url + "/App/Bbs/add";
    public static String like = url + "/App/Bbs/like";
    public static String follow_list = url + "/App/Bbs/follow_list";
    public static String follow_bbs = url + "/App/Bbs/follow_bbs";
    public static String my_add_bbs = url + "/App/Bbs/my_add_bbs";
    public static String follow = url + "/App/Bbs/follow";
    public static String del_bbs = url + "/App/Bbs/del_bbs";
    public static String user_follow = url + "/App/Bbs/user_follow";
    public static String my_follow_list = url + "/App/Bbs/my_follow_list";
    /**
     * Tuangou - 团购列表
     */
    public static String Tuangouindex = url + "/App/Tuangou/index";
    public static String address = url + "/App/Tuangou/address";

    /**
     * pay
     */
    public static String huoqudindanwx = url + "/index.php?m=Api&c=Wxpay&a=get_code";
    public static String PayserverOrder = url + "/index.php?m=Api&c=Alipay&a=get_code1";
    public static String yinlian = url + "/index.php/Api/Union/get_code";
}
