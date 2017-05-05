package com.duma.liudong.meiye.model;

import java.util.List;

/**
 * Created by liudong on 17/4/12.
 */

public class QueRenDinDanBean {

    /**
     * address : {"address_id":"1","user_id":"2","consignee":"王的的","email":"","country":"0","province":"浙江省","city":"杭州市","district":"拱墅区","twon":null,"address":"华丰新村","zipcode":"","mobile":"13888888888","is_default":"1"}
     * cart_list : [{"goods_list":[{"id":"11","user_id":"2","session_id":"n6tse29au19fmifb71ldqn7bpq","goods_id":"32","goods_sn":"15","goods_name":"【送2个替换装】透真气垫BB霜裸妆遮瑕强隔离持久保湿cc霜粉底液","market_price":"941.00","goods_price":"53.00","member_goods_price":"69.00","goods_num":"1","spec_key":"","spec_key_name":"","bar_code":"","selected":"1","add_time":"1491360399","prom_type":"0","prom_id":"0","sku":"","store_id":"1","original_img":"/Public/upload/goods/2017/03-29/58db6ede94228.jpg","post_condition_id":"0","is_free_shipping":"0","weight":"10000","goods_fee":69,"store_count":"10000"}],"mark":{"store_name":"旗舰店","store_id":"1","goods_nums":1,"total":59,"coupon":"已够满10.00元,已减10.00元","coupon_price":"10.00","coupon_name":"今天有活动","freight":22,"user_money":"8888588.00","reward_points":"0"}}]
     * total_price : {"total_fee":81,"cut_fee":872,"num":2}
     */

    private DiZhiBean address;
    private TotalPriceBean total_price;
    private List<CartListBean> cart_list;

    public DiZhiBean getAddress() {
        return address;
    }

    public void setAddress(DiZhiBean address) {
        this.address = address;
    }

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
         * total_fee : 81
         * cut_fee : 872
         * num : 2
         */

        private String total_fee;
        private String cut_fee;
        private String num;

        public String getTotal_fee() {
            return total_fee;
        }

        public void setTotal_fee(String total_fee) {
            this.total_fee = total_fee;
        }

        public String getCut_fee() {
            return cut_fee;
        }

        public void setCut_fee(String cut_fee) {
            this.cut_fee = cut_fee;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }

    public static class CartListBean {
        /**
         * goods_list : [{"id":"11","user_id":"2","session_id":"n6tse29au19fmifb71ldqn7bpq","goods_id":"32","goods_sn":"15","goods_name":"【送2个替换装】透真气垫BB霜裸妆遮瑕强隔离持久保湿cc霜粉底液","market_price":"941.00","goods_price":"53.00","member_goods_price":"69.00","goods_num":"1","spec_key":"","spec_key_name":"","bar_code":"","selected":"1","add_time":"1491360399","prom_type":"0","prom_id":"0","sku":"","store_id":"1","original_img":"/Public/upload/goods/2017/03-29/58db6ede94228.jpg","post_condition_id":"0","is_free_shipping":"0","weight":"10000","goods_fee":69,"store_count":"10000"}]
         * mark : {"store_name":"旗舰店","store_id":"1","goods_nums":1,"total":59,"coupon":"已够满10.00元,已减10.00元","coupon_price":"10.00","coupon_name":"今天有活动","freight":22,"user_money":"8888588.00","reward_points":"0"}
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
             * goods_nums : 1
             * total : 59
             * coupon : 已够满10.00元,已减10.00元
             * coupon_price : 10.00
             * coupon_name : 今天有活动
             * freight : 22
             * user_money : 8888588.00
             * reward_points : 0
             */

            private String store_name;
            private String store_id;
            private String goods_nums;
            private String total;
            private String goods_total;
            private String postage;
            private String user_money;
            private String reward_points;
            private String freight;
            private String store_phone;

            public String getStore_phone() {
                return store_phone;
            }

            public void setStore_phone(String store_phone) {
                this.store_phone = store_phone;
            }

            public String getFreight() {
                return freight;
            }

            public void setFreight(String freight) {
                this.freight = freight;
            }

            public String getGoods_total() {
                return goods_total;
            }

            public void setGoods_total(String goods_total) {
                this.goods_total = goods_total;
            }

            public String getPostage() {
                return postage;
            }

            public void setPostage(String postage) {
                this.postage = postage;
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

            public String getGoods_nums() {
                return goods_nums;
            }

            public void setGoods_nums(String goods_nums) {
                this.goods_nums = goods_nums;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
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
        }

        public static class GoodsListBean {
            /**
             * id : 11
             * user_id : 2
             * session_id : n6tse29au19fmifb71ldqn7bpq
             * goods_id : 32
             * goods_sn : 15
             * goods_name : 【送2个替换装】透真气垫BB霜裸妆遮瑕强隔离持久保湿cc霜粉底液
             * market_price : 941.00
             * goods_price : 53.00
             * member_goods_price : 69.00
             * goods_num : 1
             * spec_key :
             * spec_key_name :
             * bar_code :
             * selected : 1
             * add_time : 1491360399
             * prom_type : 0
             * prom_id : 0
             * sku :
             * store_id : 1
             * original_img : /Public/upload/goods/2017/03-29/58db6ede94228.jpg
             * post_condition_id : 0
             * is_free_shipping : 0
             * weight : 10000
             * goods_fee : 69
             * store_count : 10000
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
            private String is_free_shipping;
            private String weight;
            private double goods_fee;
            private String store_count;


            private String pickup_id;
            private String pickup_name;
            private String pickup_address;
            private String pickup_phone;
            private String is_send;

            public String getIs_send() {
                return is_send;
            }

            public void setIs_send(String is_send) {
                this.is_send = is_send;
            }

            public String getPickup_id() {
                return pickup_id;
            }

            public void setPickup_id(String pickup_id) {
                this.pickup_id = pickup_id;
            }

            public String getPickup_name() {
                return pickup_name;
            }

            public void setPickup_name(String pickup_name) {
                this.pickup_name = pickup_name;
            }

            public String getPickup_address() {
                return pickup_address;
            }

            public void setPickup_address(String pickup_address) {
                this.pickup_address = pickup_address;
            }

            public String getPickup_phone() {
                return pickup_phone;
            }

            public void setPickup_phone(String pickup_phone) {
                this.pickup_phone = pickup_phone;
            }

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

            public String getIs_free_shipping() {
                return is_free_shipping;
            }

            public void setIs_free_shipping(String is_free_shipping) {
                this.is_free_shipping = is_free_shipping;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public double getGoods_fee() {
                return goods_fee;
            }

            public void setGoods_fee(double goods_fee) {
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
