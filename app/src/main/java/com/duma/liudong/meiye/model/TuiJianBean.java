package com.duma.liudong.meiye.model;

import java.util.List;

/**
 * Created by liudong on 17/4/21.
 */

public class TuiJianBean {

    /**
     * goods : [{"goods_id":"6","goods_name":"KAI 贝印 细致安全修眉刀 刮眉刀片 日本化妆美容工具 修眉神器","market_price":"0.00","shop_price":"1000.00","original_img":"http://meiye.duma-ivy.cn/Public/upload/goods/2017/04-19/58f733030e62c.jpg","sales_sum":"0"},{"goods_id":"17","goods_name":"KAI 贝印 细致安全修眉刀 刮眉刀片 日本化妆美容工具 修眉神器","market_price":"0.00","shop_price":"1000.00","original_img":"http://meiye.duma-ivy.cn/Public/upload/goods/2017/04-19/58f733030e62c.jpg","sales_sum":"0"},{"goods_id":"1","goods_name":"拼团商品","market_price":"1000.00","shop_price":"","original_img":"http://meiye.duma-ivy.cn/Public/upload/goods/2017/04-19/58f72166e9559.jpg","sales_sum":"0"},{"goods_id":"14","goods_name":"KAI 贝印 细致安全修眉刀 刮眉刀片 日本化妆美容工具 修眉神器","market_price":"0.00","shop_price":"1000.00","original_img":"http://meiye.duma-ivy.cn/Public/upload/goods/2017/04-19/58f733030e62c.jpg","sales_sum":"0"}]
     * ad : {"ad_link":"https://www.taobao.com","ad_name":"用户中心","ad_code":"http://meiye.duma-ivy.cn/Public/upload/ad/2017/04-21/58f97ab74f860.jpg"}
     */

    private IndexBean.AdBean ad;
    private List<IndexBean.ShiwuBean> goods;

    public IndexBean.AdBean getAd() {
        return ad;
    }

    public void setAd(IndexBean.AdBean ad) {
        this.ad = ad;
    }

    public List<IndexBean.ShiwuBean> getGoods() {
        return goods;
    }

    public void setGoods(List<IndexBean.ShiwuBean> goods) {
        this.goods = goods;
    }
}
