package com.duma.liudong.meiye.model;

import java.util.List;

/**
 * Created by liudong on 17/4/1.
 */

public class GouWuCheBean {

    /**
     * cart_list : [{"goods_list":[{"id":"8","user_id":"9","session_id":"0tud0la6snq0krbq4ib0te3ie3","goods_id":"20","goods_sn":"3","goods_name":"【送2个替换装】透真气垫BB霜裸妆遮瑕强隔离持久保湿cc霜粉底液","market_price":"980.00","goods_price":"78.00","member_goods_price":"78.00","goods_num":"2","spec_key":"","spec_key_name":"","bar_code":"","selected":"1","add_time":"1491015426","prom_type":"0","prom_id":"0","sku":"","store_id":"1","original_img":"/Public/upload/goods/2017/03-29/58db6ede94228.jpg","post_condition_id":"0","goods_fee":156,"store_count":"100"},{"id":"9","user_id":"9","session_id":"0tud0la6snq0krbq4ib0te3ie3","goods_id":"19","goods_sn":"2","goods_name":"【送2个替换装】透真气垫BB霜裸妆遮瑕强隔离持久保湿cc霜粉底液","market_price":"453.00","goods_price":"40.00","member_goods_price":"40.00","goods_num":"1","spec_key":"","spec_key_name":"","bar_code":"","selected":"1","add_time":"1491017488","prom_type":"0","prom_id":"0","sku":"","store_id":"1","original_img":"/Public/upload/goods/2017/03-29/58db6ede94228.jpg","post_condition_id":"0","goods_fee":40,"store_count":"100"}],"mark":{"store_name":"旗舰店","store_id":"1","total":196,"postage":"还差123122927元可享满123123123元包邮"}}]
     * total_price : {"total_fee":196,"cut_fee":2217,"num":3}
     */

    private TotalPriceBean total_price;
    private List<CartListBean> cart_list;

    public TotalPriceBean getTotal_price() {
        return total_price;
    }

    public void setTotal_price(TotalPriceBean total_price) {
        this.total_price = total_price;
    }

    public List<CartListBean> getCart_list() {
        return cart_list;
    }

    public void setCart_list(List<CartListBean> cart_list) {
        this.cart_list = cart_list;
    }

    public static class TotalPriceBean {
        /**
         * total_fee : 196
         * cut_fee : 2217
         * num : 3
         */

        private int total_fee;
        private int cut_fee;
        private int num;

        public int getTotal_fee() {
            return total_fee;
        }

        public void setTotal_fee(int total_fee) {
            this.total_fee = total_fee;
        }

        public int getCut_fee() {
            return cut_fee;
        }

        public void setCut_fee(int cut_fee) {
            this.cut_fee = cut_fee;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }

    public static class CartListBean {
        /**
         * goods_list : [{"id":"8","user_id":"9","session_id":"0tud0la6snq0krbq4ib0te3ie3","goods_id":"20","goods_sn":"3","goods_name":"【送2个替换装】透真气垫BB霜裸妆遮瑕强隔离持久保湿cc霜粉底液","market_price":"980.00","goods_price":"78.00","member_goods_price":"78.00","goods_num":"2","spec_key":"","spec_key_name":"","bar_code":"","selected":"1","add_time":"1491015426","prom_type":"0","prom_id":"0","sku":"","store_id":"1","original_img":"/Public/upload/goods/2017/03-29/58db6ede94228.jpg","post_condition_id":"0","goods_fee":156,"store_count":"100"},{"id":"9","user_id":"9","session_id":"0tud0la6snq0krbq4ib0te3ie3","goods_id":"19","goods_sn":"2","goods_name":"【送2个替换装】透真气垫BB霜裸妆遮瑕强隔离持久保湿cc霜粉底液","market_price":"453.00","goods_price":"40.00","member_goods_price":"40.00","goods_num":"1","spec_key":"","spec_key_name":"","bar_code":"","selected":"1","add_time":"1491017488","prom_type":"0","prom_id":"0","sku":"","store_id":"1","original_img":"/Public/upload/goods/2017/03-29/58db6ede94228.jpg","post_condition_id":"0","goods_fee":40,"store_count":"100"}]
         * mark : {"store_name":"旗舰店","store_id":"1","total":196,"postage":"还差123122927元可享满123123123元包邮"}
         */

        private MarkBean mark;
        private List<GoodsListBean> goods_list;

        public MarkBean getMark() {
            return mark;
        }

        public void setMark(MarkBean mark) {
            this.mark = mark;
        }

        public List<GoodsListBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }

        public static class MarkBean {
            /**
             * store_name : 旗舰店
             * store_id : 1
             * total : 196
             * postage : 还差123122927元可享满123123123元包邮
             */

            private String store_name;
            private String store_id;
            private int total;
            private String postage;

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

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public String getPostage() {
                return postage;
            }

            public void setPostage(String postage) {
                this.postage = postage;
            }
        }

        public static class GoodsListBean {
            /**
             * id : 8
             * user_id : 9
             * session_id : 0tud0la6snq0krbq4ib0te3ie3
             * goods_id : 20
             * goods_sn : 3
             * goods_name : 【送2个替换装】透真气垫BB霜裸妆遮瑕强隔离持久保湿cc霜粉底液
             * market_price : 980.00
             * goods_price : 78.00
             * member_goods_price : 78.00
             * goods_num : 2
             * spec_key :
             * spec_key_name :
             * bar_code :
             * selected : 1
             * add_time : 1491015426
             * prom_type : 0
             * prom_id : 0
             * sku :
             * store_id : 1
             * original_img : /Public/upload/goods/2017/03-29/58db6ede94228.jpg
             * post_condition_id : 0
             * goods_fee : 156
             * store_count : 100
             */

            private String id;
            private String user_id;
            private String session_id;
            private String goods_id;
            private String goods_sn;
            private String goods_name;
            private String market_price;
            private String goods_price;
            private String member_goods_price;
            private String goods_num;
            private String spec_key;
            private String spec_key_name;
            private String bar_code;
            private String selected;
            private String add_time;
            private String prom_type;
            private String prom_id;
            private String sku;
            private String store_id;
            private String original_img;
            private String post_condition_id;
            private int goods_fee;
            private String store_count;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getSession_id() {
                return session_id;
            }

            public void setSession_id(String session_id) {
                this.session_id = session_id;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_sn() {
                return goods_sn;
            }

            public void setGoods_sn(String goods_sn) {
                this.goods_sn = goods_sn;
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

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public String getMember_goods_price() {
                return member_goods_price;
            }

            public void setMember_goods_price(String member_goods_price) {
                this.member_goods_price = member_goods_price;
            }

            public String getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(String goods_num) {
                this.goods_num = goods_num;
            }

            public String getSpec_key() {
                return spec_key;
            }

            public void setSpec_key(String spec_key) {
                this.spec_key = spec_key;
            }

            public String getSpec_key_name() {
                return spec_key_name;
            }

            public void setSpec_key_name(String spec_key_name) {
                this.spec_key_name = spec_key_name;
            }

            public String getBar_code() {
                return bar_code;
            }

            public void setBar_code(String bar_code) {
                this.bar_code = bar_code;
            }

            public String getSelected() {
                return selected;
            }

            public void setSelected(String selected) {
                this.selected = selected;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getProm_type() {
                return prom_type;
            }

            public void setProm_type(String prom_type) {
                this.prom_type = prom_type;
            }

            public String getProm_id() {
                return prom_id;
            }

            public void setProm_id(String prom_id) {
                this.prom_id = prom_id;
            }

            public String getSku() {
                return sku;
            }

            public void setSku(String sku) {
                this.sku = sku;
            }

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public String getOriginal_img() {
                return original_img;
            }

            public void setOriginal_img(String original_img) {
                this.original_img = original_img;
            }

            public String getPost_condition_id() {
                return post_condition_id;
            }

            public void setPost_condition_id(String post_condition_id) {
                this.post_condition_id = post_condition_id;
            }

            public int getGoods_fee() {
                return goods_fee;
            }

            public void setGoods_fee(int goods_fee) {
                this.goods_fee = goods_fee;
            }

            public String getStore_count() {
                return store_count;
            }

            public void setStore_count(String store_count) {
                this.store_count = store_count;
            }
        }
    }
}
