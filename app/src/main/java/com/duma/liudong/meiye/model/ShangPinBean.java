package com.duma.liudong.meiye.model;

/**
 * Created by liudong on 17/3/30.
 */

public class ShangPinBean {
    /**
     * goods_id : 2
     * goods_name : 实物商品
     * original_img : http://meiye.duma-ivy.cn/Public/upload/goods/2017/04-21/58f9d3564d06c.jpg
     * sales_sum : 0
     * store_id : 4
     * type : http://meiye.duma-ivy.cn/Public/images/meiye/type_1.png
     * price : 0.03
     * market_price : 0.10
     * store_name : 旗舰店
     * store_logo : http://meiye.duma-ivy.cn/Public/upload/seller/2017/04-20/58f7fd364d151.jpg
     * lng : 113.63755982348
     * lat : 40.856228406037
     * goods_url : http://www.meiye.com/goods.html?goods_id=2
     * distance : 1324660
     */

    private String goods_id;
    private String goods_name;
    private String original_img;
    private String sales_sum;
    private String store_id;
    private String type;
    private String price;
    private String market_price;
    private String store_name;
    private String store_logo;
    private String lng;
    private String lat;
    private String store_count;
    private int distance;
    private String goods_url;

    public String getStore_count() {
        return store_count;
    }

    public void setStore_count(String store_count) {
        this.store_count = store_count;
    }

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

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMarket_price() {
        return market_price;
    }

    public void setMarket_price(String market_price) {
        this.market_price = market_price;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getStore_logo() {
        return store_logo;
    }

    public void setStore_logo(String store_logo) {
        this.store_logo = store_logo;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getGoods_url() {
        return goods_url;
    }

    public void setGoods_url(String goods_url) {
        this.goods_url = goods_url;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
