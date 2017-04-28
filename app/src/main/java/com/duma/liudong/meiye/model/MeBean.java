package com.duma.liudong.meiye.model;

import java.util.List;

/**
 * Created by liudong on 17/3/23.
 */

public class MeBean {
    /**
     * head_pic : /Public/upload/head_pic/2017-03-27/58d8701391de5.jpg
     * nickname : tout
     * level : 3
     * ref_num : 18888888888
     * distribut_money : 157168.23
     * new_money : 0.00
     * total_client : 4
     * new_client : 0
     * user_money : 929508.00
     * reward_points : 100
     * coupon : 4
     * packet : 3
     * goods : [{"goods_id":"15","goods_name":"KAI 贝印 细致安全修眉刀 刮眉刀片 日本化妆美容工具 修眉神器","market_price":"0.00","shop_price":"1000.00","original_img":"http://meiye.duma-ivy.cn/Public/upload/goods/2017/04-19/58f733030e62c.jpg","sales_sum":"0"},{"goods_id":"6","goods_name":"KAI 贝印 细致安全修眉刀 刮眉刀片 日本化妆美容工具 修眉神器","market_price":"0.00","shop_price":"1000.00","original_img":"http://meiye.duma-ivy.cn/Public/upload/goods/2017/04-19/58f733030e62c.jpg","sales_sum":"0"},{"goods_id":"13","goods_name":"KAI 贝印 细致安全修眉刀 刮眉刀片 日本化妆美容工具 修眉神器","market_price":"0.00","shop_price":"1000.00","original_img":"http://meiye.duma-ivy.cn/Public/upload/goods/2017/04-19/58f733030e62c.jpg","sales_sum":"0"},{"goods_id":"9","goods_name":"KAI 贝印 细致安全修眉刀 刮眉刀片 日本化妆美容工具 修眉神器","market_price":"0.00","shop_price":"1000.00","original_img":"http://meiye.duma-ivy.cn/Public/upload/goods/2017/04-19/58f733030e62c.jpg","sales_sum":"0"}]
     * ad : {"ad_link":"https://www.taobao.com","ad_name":"用户中心","ad_code":"http://meiye.duma-ivy.cn/Public/upload/ad/2017/04-21/58f97ab74f860.jpg"}
     */

    private String head_pic;
    private String nickname;
    private String level;
    private String ref_num;
    private String distribut_money;
    private String new_money;
    private String total_client;
    private String new_client;
    private String user_money;
    private String reward_points;
    private String coupon;
    private String packet;
    private AdBean ad;
    private List<GoodsBean> goods;
    private String level_img;

    public String getLevel_img() {
        return level_img;
    }

    public void setLevel_img(String level_img) {
        this.level_img = level_img;
    }

    public String getHead_pic() {
        return head_pic;
    }

    public void setHead_pic(String head_pic) {
        this.head_pic = head_pic;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRef_num() {
        return ref_num;
    }

    public void setRef_num(String ref_num) {
        this.ref_num = ref_num;
    }

    public String getDistribut_money() {
        return distribut_money;
    }

    public void setDistribut_money(String distribut_money) {
        this.distribut_money = distribut_money;
    }

    public String getNew_money() {
        return new_money;
    }

    public void setNew_money(String new_money) {
        this.new_money = new_money;
    }

    public String getTotal_client() {
        return total_client;
    }

    public void setTotal_client(String total_client) {
        this.total_client = total_client;
    }

    public String getNew_client() {
        return new_client;
    }

    public void setNew_client(String new_client) {
        this.new_client = new_client;
    }

    public String getUser_money() {
        return user_money;
    }

    public void setUser_money(String user_money) {
        this.user_money = user_money;
    }

    public String getReward_points() {
        return reward_points;
    }

    public void setReward_points(String reward_points) {
        this.reward_points = reward_points;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getPacket() {
        return packet;
    }

    public void setPacket(String packet) {
        this.packet = packet;
    }

    public AdBean getAd() {
        return ad;
    }

    public void setAd(AdBean ad) {
        this.ad = ad;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public static class AdBean {
        /**
         * ad_link : https://www.taobao.com
         * ad_name : 用户中心
         * ad_code : http://meiye.duma-ivy.cn/Public/upload/ad/2017/04-21/58f97ab74f860.jpg
         */

        private String ad_link;
        private String ad_name;
        private String ad_code;

        public String getAd_link() {
            return ad_link;
        }

        public void setAd_link(String ad_link) {
            this.ad_link = ad_link;
        }

        public String getAd_name() {
            return ad_name;
        }

        public void setAd_name(String ad_name) {
            this.ad_name = ad_name;
        }

        public String getAd_code() {
            return ad_code;
        }

        public void setAd_code(String ad_code) {
            this.ad_code = ad_code;
        }
    }

    public static class GoodsBean {
        /**
         * goods_id : 15
         * goods_name : KAI 贝印 细致安全修眉刀 刮眉刀片 日本化妆美容工具 修眉神器
         * market_price : 0.00
         * shop_price : 1000.00
         * original_img : http://meiye.duma-ivy.cn/Public/upload/goods/2017/04-19/58f733030e62c.jpg
         * sales_sum : 0
         */

        private String goods_id;
        private String goods_name;
        private String market_price;
        private String shop_price;
        private String original_img;
        private String sales_sum;

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getMarket_price() {
            return market_price;
        }

        public void setMarket_price(String market_price) {
            this.market_price = market_price;
        }

        public String getShop_price() {
            return shop_price;
        }

        public void setShop_price(String shop_price) {
            this.shop_price = shop_price;
        }

        public String getOriginal_img() {
            return original_img;
        }

        public void setOriginal_img(String original_img) {
            this.original_img = original_img;
        }

        public String getSales_sum() {
            return sales_sum;
        }

        public void setSales_sum(String sales_sum) {
            this.sales_sum = sales_sum;
        }
    }
}
