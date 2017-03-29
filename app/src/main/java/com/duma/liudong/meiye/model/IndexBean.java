package com.duma.liudong.meiye.model;

import java.util.List;

/**
 * Created by liudong on 17/3/29.
 */

public class IndexBean {

    /**
     * friend_link : [{"link_name":"轮播图","link_url":"http://www.meiye.com","link_logo":"http://meiye.duma-ivy.cn/Public/upload/link/2017/03-29/58db72b51f5e0.jpg"},{"link_name":"轮播图","link_url":"http://meiye.duma-ivy.cn","link_logo":"http://meiye.duma-ivy.cn/Public/upload/link/2017/03-29/58db72a9da07c.jpg"},{"link_name":"测试轮图","link_url":"http://meiye.duma-ivy.cn","link_logo":"http://meiye.duma-ivy.cn/Public/upload/link/2017/03-29/58db72fb472c2.jpg"}]
     * article : [{"cat_name":"分类一","title":"123","article_id":"1","url":"http://www.meiye.com/article.html?id=1"},{"cat_name":"分类一","title":"1231","article_id":"2","url":"http://www.meiye.com/article.html?id=2"}]
     * goods_cat : [{"name":"周日秒杀","pic_url":"http://meiye.duma-ivy.cn/Public/images/meiye/img_01.png"},{"name":"新品拿样","pic_url":"http://meiye.duma-ivy.cn/Public/images/meiye/img_02.png"},{"name":"尾货清仓","pic_url":"http://meiye.duma-ivy.cn/Public/images/meiye/img_03.png"},{"name":"优品推荐","pic_url":"http://meiye.duma-ivy.cn/Public/images/meiye/img_04.png"},{"name":"热销爆款","pic_url":"http://meiye.duma-ivy.cn/Public/images/meiye/img_05.png"},{"name":"附近好货","pic_url":"http://meiye.duma-ivy.cn/Public/images/meiye/img_06.png"}]
     * ad : {"ad_link":"123","ad_name":"自定义广告名称","ad_code":"http://meiye.duma-ivy.cn/Public/upload/ad/2017/03-27/58d8c0eaa984d.jpeg"}
     * store : [{"store_logo":"http://meiye.duma-ivy.cn/Public/upload/seller/2017/03-29/58db71dd20473.jpg","store_banner":"http://meiye.duma-ivy.cn/Public/upload/seller/2017/03-29/58db720a09f30.jpg","store_name":"逍遥当铺","store_id":"3","grade_id":"1"},{"store_logo":"http://meiye.duma-ivy.cn/Public/upload/seller/2017/03-29/58db723588613.jpg","store_banner":"http://meiye.duma-ivy.cn/Public/upload/seller/2017/03-28/58da0863b7ac1.jpg","store_name":"旗舰店","store_id":"1","grade_id":"0"},{"store_logo":"http://meiye.duma-ivy.cn/Public/upload/seller/2017/03-29/58db71ac7b12c.jpg","store_banner":"http://meiye.duma-ivy.cn/Public/upload/seller/2017/03-29/58db71ba25027.jpg","store_name":" 透真旗舰店","store_id":"2","grade_id":"1"}]
     * shiwu : [{"goods_id":"22","goods_name":"【送2个替换装】透真气垫BB霜裸妆遮瑕强隔离持久保湿cc霜粉底液","market_price":"219.00","shop_price":"89.00","original_img":"http://meiye.duma-ivy.cn/Public/upload/goods/2017/03-29/58db6ede94228.jpg","sales_sum":"0"},{"goods_id":"24","goods_name":"【送2个替换装】透真气垫BB霜裸妆遮瑕强隔离持久保湿cc霜粉底液","market_price":"219.00","shop_price":"89.00","original_img":"http://meiye.duma-ivy.cn/Public/upload/goods/2017/03-29/58db6ede94228.jpg","sales_sum":"0"},{"goods_id":"23","goods_name":"【送2个替换装】透真气垫BB霜裸妆遮瑕强隔离持久保湿cc霜粉底液","market_price":"219.00","shop_price":"89.00","original_img":"http://meiye.duma-ivy.cn/Public/upload/goods/2017/03-29/58db6ede94228.jpg","sales_sum":"0"},{"goods_id":"19","goods_name":"【送2个替换装】透真气垫BB霜裸妆遮瑕强隔离持久保湿cc霜粉底液","market_price":"219.00","shop_price":"89.00","original_img":"http://meiye.duma-ivy.cn/Public/upload/goods/2017/03-29/58db6ede94228.jpg","sales_sum":"0"}]
     * tuangou : [{"goods_id":"37","goods_name":"【送2个替换装】透真气垫BB霜裸妆遮瑕强隔离持久保湿cc霜粉底液","market_price":"219.00","goods_remark":"送2个替换装 轻透服帖 无瑕美肌","shop_price":"","original_img":"http://meiye.duma-ivy.cn/Public/upload/goods/2017/03-29/58db6ede94228.jpg","sales_sum":"0"},{"goods_id":"36","goods_name":"【送2个替换装】透真气垫BB霜裸妆遮瑕强隔离持久保湿cc霜粉底液","market_price":"219.00","goods_remark":"送2个替换装 轻透服帖 无瑕美肌","shop_price":"","original_img":"http://meiye.duma-ivy.cn/Public/upload/goods/2017/03-29/58db6ede94228.jpg","sales_sum":"0"},{"goods_id":"31","goods_name":"【送2个替换装】透真气垫BB霜裸妆遮瑕强隔离持久保湿cc霜粉底液","market_price":"219.00","goods_remark":"送2个替换装 轻透服帖 无瑕美肌","shop_price":"","original_img":"http://meiye.duma-ivy.cn/Public/upload/goods/2017/03-29/58db6ede94228.jpg","sales_sum":"0"},{"goods_id":"35","goods_name":"【送2个替换装】透真气垫BB霜裸妆遮瑕强隔离持久保湿cc霜粉底液","market_price":"219.00","goods_remark":"送2个替换装 轻透服帖 无瑕美肌","shop_price":"","original_img":"http://meiye.duma-ivy.cn/Public/upload/goods/2017/03-29/58db6ede94228.jpg","sales_sum":"0"}]
     * dingzhi : [{"goods_id":"46","goods_name":"【送2个替换装】透真气垫BB霜裸妆遮瑕强隔离持久保湿cc霜粉底液","market_price":"219.00","sell_up":"20","shop_price":"","original_img":"http://meiye.duma-ivy.cn/Public/upload/goods/2017/03-29/58db6ede94228.jpg","sales_sum":"0"},{"goods_id":"43","goods_name":"【送2个替换装】透真气垫BB霜裸妆遮瑕强隔离持久保湿cc霜粉底液","market_price":"219.00","sell_up":"20","shop_price":"","original_img":"http://meiye.duma-ivy.cn/Public/upload/goods/2017/03-29/58db6ede94228.jpg","sales_sum":"0"},{"goods_id":"39","goods_name":"【送2个替换装】透真气垫BB霜裸妆遮瑕强隔离持久保湿cc霜粉底液","market_price":"219.00","sell_up":"20","shop_price":"","original_img":"http://meiye.duma-ivy.cn/Public/upload/goods/2017/03-29/58db6ede94228.jpg","sales_sum":"0"},{"goods_id":"45","goods_name":"【送2个替换装】透真气垫BB霜裸妆遮瑕强隔离持久保湿cc霜粉底液","market_price":"219.00","sell_up":"20","shop_price":"","original_img":"http://meiye.duma-ivy.cn/Public/upload/goods/2017/03-29/58db6ede94228.jpg","sales_sum":"0"}]
     * tuijian : [{"goods_id":"44","goods_name":"【送2个替换装】透真气垫BB霜裸妆遮瑕强隔离持久保湿cc霜粉底液","market_price":"219.00","shop_price":"","original_img":"http://meiye.duma-ivy.cn/Public/upload/goods/2017/03-29/58db6ede94228.jpg","sales_sum":"0"},{"goods_id":"30","goods_name":"【送2个替换装】透真气垫BB霜裸妆遮瑕强隔离持久保湿cc霜粉底液","market_price":"219.00","shop_price":"","original_img":"http://meiye.duma-ivy.cn/Public/upload/goods/2017/03-29/58db6ede94228.jpg","sales_sum":"0"},{"goods_id":"37","goods_name":"【送2个替换装】透真气垫BB霜裸妆遮瑕强隔离持久保湿cc霜粉底液","market_price":"219.00","shop_price":"","original_img":"http://meiye.duma-ivy.cn/Public/upload/goods/2017/03-29/58db6ede94228.jpg","sales_sum":"0"},{"goods_id":"31","goods_name":"【送2个替换装】透真气垫BB霜裸妆遮瑕强隔离持久保湿cc霜粉底液","market_price":"219.00","shop_price":"","original_img":"http://meiye.duma-ivy.cn/Public/upload/goods/2017/03-29/58db6ede94228.jpg","sales_sum":"0"}]
     */

    private AdBean ad;
    private List<FriendLinkBean> friend_link;
    private List<ArticleBean> article;
    private List<GoodsCatBean> goods_cat;
    private List<StoreBean> store;
    private List<ShiwuBean> shiwu;
    private List<TuangouBean> tuangou;
    private List<DingzhiBean> dingzhi;
    private List<TuijianBean> tuijian;

    public AdBean getAd() {
        return ad;
    }

    public void setAd(AdBean ad) {
        this.ad = ad;
    }

    public List<FriendLinkBean> getFriend_link() {
        return friend_link;
    }

    public void setFriend_link(List<FriendLinkBean> friend_link) {
        this.friend_link = friend_link;
    }

    public List<ArticleBean> getArticle() {
        return article;
    }

    public void setArticle(List<ArticleBean> article) {
        this.article = article;
    }

    public List<GoodsCatBean> getGoods_cat() {
        return goods_cat;
    }

    public void setGoods_cat(List<GoodsCatBean> goods_cat) {
        this.goods_cat = goods_cat;
    }

    public List<StoreBean> getStore() {
        return store;
    }

    public void setStore(List<StoreBean> store) {
        this.store = store;
    }

    public List<ShiwuBean> getShiwu() {
        return shiwu;
    }

    public void setShiwu(List<ShiwuBean> shiwu) {
        this.shiwu = shiwu;
    }

    public List<TuangouBean> getTuangou() {
        return tuangou;
    }

    public void setTuangou(List<TuangouBean> tuangou) {
        this.tuangou = tuangou;
    }

    public List<DingzhiBean> getDingzhi() {
        return dingzhi;
    }

    public void setDingzhi(List<DingzhiBean> dingzhi) {
        this.dingzhi = dingzhi;
    }

    public List<TuijianBean> getTuijian() {
        return tuijian;
    }

    public void setTuijian(List<TuijianBean> tuijian) {
        this.tuijian = tuijian;
    }

    public static class AdBean {
        /**
         * ad_link : 123
         * ad_name : 自定义广告名称
         * ad_code : http://meiye.duma-ivy.cn/Public/upload/ad/2017/03-27/58d8c0eaa984d.jpeg
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

    public static class FriendLinkBean {
        /**
         * link_name : 轮播图
         * link_url : http://www.meiye.com
         * link_logo : http://meiye.duma-ivy.cn/Public/upload/link/2017/03-29/58db72b51f5e0.jpg
         */

        private String link_name;
        private String link_url;
        private String link_logo;

        public String getLink_name() {
            return link_name;
        }

        public void setLink_name(String link_name) {
            this.link_name = link_name;
        }

        public String getLink_url() {
            return link_url;
        }

        public void setLink_url(String link_url) {
            this.link_url = link_url;
        }

        public String getLink_logo() {
            return link_logo;
        }

        public void setLink_logo(String link_logo) {
            this.link_logo = link_logo;
        }
    }

    public static class ArticleBean {
        /**
         * cat_name : 分类一
         * title : 123
         * article_id : 1
         * url : http://www.meiye.com/article.html?id=1
         */

        private String cat_name;
        private String title;
        private String article_id;
        private String url;

        public String getCat_name() {
            return cat_name;
        }

        public void setCat_name(String cat_name) {
            this.cat_name = cat_name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getArticle_id() {
            return article_id;
        }

        public void setArticle_id(String article_id) {
            this.article_id = article_id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class GoodsCatBean {
        /**
         * name : 周日秒杀
         * pic_url : http://meiye.duma-ivy.cn/Public/images/meiye/img_01.png
         */

        private String name;
        private String pic_url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }
    }

    public static class StoreBean {
        /**
         * store_logo : http://meiye.duma-ivy.cn/Public/upload/seller/2017/03-29/58db71dd20473.jpg
         * store_banner : http://meiye.duma-ivy.cn/Public/upload/seller/2017/03-29/58db720a09f30.jpg
         * store_name : 逍遥当铺
         * store_id : 3
         * grade_id : 1
         */

        private String store_logo;
        private String store_banner;
        private String store_name;
        private String store_id;
        private String grade_id;

        public String getStore_logo() {
            return store_logo;
        }

        public void setStore_logo(String store_logo) {
            this.store_logo = store_logo;
        }

        public String getStore_banner() {
            return store_banner;
        }

        public void setStore_banner(String store_banner) {
            this.store_banner = store_banner;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getGrade_id() {
            return grade_id;
        }

        public void setGrade_id(String grade_id) {
            this.grade_id = grade_id;
        }
    }

    public static class ShiwuBean {
        /**
         * goods_id : 22
         * goods_name : 【送2个替换装】透真气垫BB霜裸妆遮瑕强隔离持久保湿cc霜粉底液
         * market_price : 219.00
         * shop_price : 89.00
         * original_img : http://meiye.duma-ivy.cn/Public/upload/goods/2017/03-29/58db6ede94228.jpg
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

    public static class TuangouBean {
        /**
         * goods_id : 37
         * goods_name : 【送2个替换装】透真气垫BB霜裸妆遮瑕强隔离持久保湿cc霜粉底液
         * market_price : 219.00
         * goods_remark : 送2个替换装 轻透服帖 无瑕美肌
         * shop_price :
         * original_img : http://meiye.duma-ivy.cn/Public/upload/goods/2017/03-29/58db6ede94228.jpg
         * sales_sum : 0
         */

        private String goods_id;
        private String goods_name;
        private String market_price;
        private String goods_remark;
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

        public String getGoods_remark() {
            return goods_remark;
        }

        public void setGoods_remark(String goods_remark) {
            this.goods_remark = goods_remark;
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

    public static class DingzhiBean {
        /**
         * goods_id : 46
         * goods_name : 【送2个替换装】透真气垫BB霜裸妆遮瑕强隔离持久保湿cc霜粉底液
         * market_price : 219.00
         * sell_up : 20
         * shop_price :
         * original_img : http://meiye.duma-ivy.cn/Public/upload/goods/2017/03-29/58db6ede94228.jpg
         * sales_sum : 0
         */

        private String goods_id;
        private String goods_name;
        private String market_price;
        private String sell_up;
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

        public String getSell_up() {
            return sell_up;
        }

        public void setSell_up(String sell_up) {
            this.sell_up = sell_up;
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

    public static class TuijianBean {
        /**
         * goods_id : 44
         * goods_name : 【送2个替换装】透真气垫BB霜裸妆遮瑕强隔离持久保湿cc霜粉底液
         * market_price : 219.00
         * shop_price :
         * original_img : http://meiye.duma-ivy.cn/Public/upload/goods/2017/03-29/58db6ede94228.jpg
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
