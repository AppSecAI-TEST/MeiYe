package com.duma.liudong.meiye.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by liudong on 17/4/22.
 */

public class DinZhiXiangQinBean {

    /**
     * order_id : 43
     * order_sn : 201704221423286121
     * master_order_sn :
     * user_id : 9
     * order_status : 0
     * shipping_status : 0
     * pay_status : 1
     * consignee : 就得
     * country : 0
     * province : 北京市
     * city : 北京市
     * district : 东城区
     * twon : 0
     * address : 图
     * zipcode :
     * mobile : 18772397060
     * email :
     * shipping_code : 0
     * shipping_name :
     * pay_code :
     * pay_name :
     * invoice_title :
     * goods_price : 0.40
     * shipping_price : null
     * user_money : 0.40
     * coupon_price : 0.00
     * integral : 0
     * integral_money : 0.00
     * order_amount : 0.00
     * total_amount : 0.40
     * confirm_time : 0
     * pay_time : 1492842208
     * shipping_time : null
     * order_prom_id : 0
     * order_prom_amount : 0.00
     * discount : 0.00
     * user_note :
     * admin_note :
     * parent_sn : null
     * store_id : 4
     * is_comment : 0
     * deleted : 0
     * is_checkout : null
     * is_pick : 0
     * add_time : 1492842208
     * order_status_code : WAITSEND
     * order_status_desc : 待发货
     * pay_btn : 0
     * cancel_btn : 0
     * receive_btn : 0
     * comment_btn : 0
     * shipping_btn : 0
     * return_btn : 1
     * del_btn : 0
     * store_name : 旗舰店
     * store_qq : 123123123
     * store_phone : 123123123
     * goods_list : [{"rec_id":"42","order_id":"43","goods_id":"1","goods_name":"拼团商品","goods_sn":"拼团商品","goods_num":"20","market_price":"0.10","goods_price":"0.03","cost_price":"0.00","member_goods_price":"0.02","give_integral":"0","spec_key":"","spec_key_name":"","bar_code":"","is_comment":"0","prom_type":"0","prom_id":"0","is_send":"0","delivery_id":"0","sku":"","store_id":"4","commission":"0","is_checkout":"0","deleted":"0","original_img":"/Public/upload/goods/2017/04-21/58f9d3564d06c.jpg","type":"2"}]
     * support : {"user":[{"spell_id":"4","order_id":"43","user_id":"9","is_master":"1","goods_id":"1","goods_num":"20","add_time":"1492842208","end_time":"1492844008","nickname":"liudong ","head_pic":"/Public/upload/head_pic/2017-03-27/58d8701391de5.jpg","state":"2017-04-22 14:23:28 开团"}],"mark":{"short":0,"end_time":0,"content":"拼团已过期,商家正在发货中","tips":"10成团,数量不足自动退款"}}
     */

    private String order_id;
    private String order_sn;
    private String master_order_sn;
    private String user_id;
    private String order_status;
    private String shipping_status;
    private String pay_status;
    private String consignee;
    private String country;
    private String province;
    private String city;
    private String district;
    private String twon;
    private String address;
    private String zipcode;
    private String mobile;
    private String email;
    private String shipping_code;
    private String shipping_name;
    private String pay_code;
    private String pay_name;
    private String invoice_title;
    private String goods_price;
    private Object shipping_price;
    private String user_money;
    private String coupon_price;
    private String integral;
    private String integral_money;
    private String order_amount;
    private String total_amount;
    private String confirm_time;
    private String pay_time;
    private Object shipping_time;
    private String order_prom_id;
    private String order_prom_amount;
    private String discount;
    private String user_note;
    private String admin_note;
    private Object parent_sn;
    private String store_id;
    private String is_comment;
    private String deleted;
    private Object is_checkout;
    private String is_pick;
    private String add_time;
    private String order_status_code;
    private String order_status_desc;
    private int pay_btn;
    private int cancel_btn;
    private int receive_btn;
    private int comment_btn;
    private int shipping_btn;
    private int return_btn;
    private int del_btn;
    private String store_name;
    private String store_qq;
    private String store_phone;
    private SupportBean support;
    private List<GoodsListBean> goods_list;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public String getMaster_order_sn() {
        return master_order_sn;
    }

    public void setMaster_order_sn(String master_order_sn) {
        this.master_order_sn = master_order_sn;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getShipping_status() {
        return shipping_status;
    }

    public void setShipping_status(String shipping_status) {
        this.shipping_status = shipping_status;
    }

    public String getPay_status() {
        return pay_status;
    }

    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTwon() {
        return twon;
    }

    public void setTwon(String twon) {
        this.twon = twon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getShipping_code() {
        return shipping_code;
    }

    public void setShipping_code(String shipping_code) {
        this.shipping_code = shipping_code;
    }

    public String getShipping_name() {
        return shipping_name;
    }

    public void setShipping_name(String shipping_name) {
        this.shipping_name = shipping_name;
    }

    public String getPay_code() {
        return pay_code;
    }

    public void setPay_code(String pay_code) {
        this.pay_code = pay_code;
    }

    public String getPay_name() {
        return pay_name;
    }

    public void setPay_name(String pay_name) {
        this.pay_name = pay_name;
    }

    public String getInvoice_title() {
        return invoice_title;
    }

    public void setInvoice_title(String invoice_title) {
        this.invoice_title = invoice_title;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public Object getShipping_price() {
        return shipping_price;
    }

    public void setShipping_price(Object shipping_price) {
        this.shipping_price = shipping_price;
    }

    public String getUser_money() {
        return user_money;
    }

    public void setUser_money(String user_money) {
        this.user_money = user_money;
    }

    public String getCoupon_price() {
        return coupon_price;
    }

    public void setCoupon_price(String coupon_price) {
        this.coupon_price = coupon_price;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getIntegral_money() {
        return integral_money;
    }

    public void setIntegral_money(String integral_money) {
        this.integral_money = integral_money;
    }

    public String getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(String order_amount) {
        this.order_amount = order_amount;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getConfirm_time() {
        return confirm_time;
    }

    public void setConfirm_time(String confirm_time) {
        this.confirm_time = confirm_time;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public Object getShipping_time() {
        return shipping_time;
    }

    public void setShipping_time(Object shipping_time) {
        this.shipping_time = shipping_time;
    }

    public String getOrder_prom_id() {
        return order_prom_id;
    }

    public void setOrder_prom_id(String order_prom_id) {
        this.order_prom_id = order_prom_id;
    }

    public String getOrder_prom_amount() {
        return order_prom_amount;
    }

    public void setOrder_prom_amount(String order_prom_amount) {
        this.order_prom_amount = order_prom_amount;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getUser_note() {
        return user_note;
    }

    public void setUser_note(String user_note) {
        this.user_note = user_note;
    }

    public String getAdmin_note() {
        return admin_note;
    }

    public void setAdmin_note(String admin_note) {
        this.admin_note = admin_note;
    }

    public Object getParent_sn() {
        return parent_sn;
    }

    public void setParent_sn(Object parent_sn) {
        this.parent_sn = parent_sn;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getIs_comment() {
        return is_comment;
    }

    public void setIs_comment(String is_comment) {
        this.is_comment = is_comment;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public Object getIs_checkout() {
        return is_checkout;
    }

    public void setIs_checkout(Object is_checkout) {
        this.is_checkout = is_checkout;
    }

    public String getIs_pick() {
        return is_pick;
    }

    public void setIs_pick(String is_pick) {
        this.is_pick = is_pick;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getOrder_status_code() {
        return order_status_code;
    }

    public void setOrder_status_code(String order_status_code) {
        this.order_status_code = order_status_code;
    }

    public String getOrder_status_desc() {
        return order_status_desc;
    }

    public void setOrder_status_desc(String order_status_desc) {
        this.order_status_desc = order_status_desc;
    }

    public int getPay_btn() {
        return pay_btn;
    }

    public void setPay_btn(int pay_btn) {
        this.pay_btn = pay_btn;
    }

    public int getCancel_btn() {
        return cancel_btn;
    }

    public void setCancel_btn(int cancel_btn) {
        this.cancel_btn = cancel_btn;
    }

    public int getReceive_btn() {
        return receive_btn;
    }

    public void setReceive_btn(int receive_btn) {
        this.receive_btn = receive_btn;
    }

    public int getComment_btn() {
        return comment_btn;
    }

    public void setComment_btn(int comment_btn) {
        this.comment_btn = comment_btn;
    }

    public int getShipping_btn() {
        return shipping_btn;
    }

    public void setShipping_btn(int shipping_btn) {
        this.shipping_btn = shipping_btn;
    }

    public int getReturn_btn() {
        return return_btn;
    }

    public void setReturn_btn(int return_btn) {
        this.return_btn = return_btn;
    }

    public int getDel_btn() {
        return del_btn;
    }

    public void setDel_btn(int del_btn) {
        this.del_btn = del_btn;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getStore_qq() {
        return store_qq;
    }

    public void setStore_qq(String store_qq) {
        this.store_qq = store_qq;
    }

    public String getStore_phone() {
        return store_phone;
    }

    public void setStore_phone(String store_phone) {
        this.store_phone = store_phone;
    }

    public SupportBean getSupport() {
        return support;
    }

    public void setSupport(SupportBean support) {
        this.support = support;
    }

    public List<GoodsListBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsListBean> goods_list) {
        this.goods_list = goods_list;
    }

    public static class SupportBean {
        /**
         * user : [{"spell_id":"4","order_id":"43","user_id":"9","is_master":"1","goods_id":"1","goods_num":"20","add_time":"1492842208","end_time":"1492844008","nickname":"liudong ","head_pic":"/Public/upload/head_pic/2017-03-27/58d8701391de5.jpg","state":"2017-04-22 14:23:28 开团"}]
         * mark : {"short":0,"end_time":0,"content":"拼团已过期,商家正在发货中","tips":"10成团,数量不足自动退款"}
         */

        private MarkBean mark;
        private List<UserBean> user;

        public MarkBean getMark() {
            return mark;
        }

        public void setMark(MarkBean mark) {
            this.mark = mark;
        }

        public List<UserBean> getUser() {
            return user;
        }

        public void setUser(List<UserBean> user) {
            this.user = user;
        }

        public static class MarkBean {
            /**
             * short : 0
             * end_time : 0
             * content : 拼团已过期,商家正在发货中
             * tips : 10成团,数量不足自动退款
             */

            @SerializedName("short")
            private String shortX;
            private long end_time;
            private String content;
            private String tips;
            private String seller_up;

            public String getSeller_up() {
                return seller_up;
            }

            public void setSeller_up(String seller_up) {
                this.seller_up = seller_up;
            }

            public String getShortX() {
                return shortX;
            }

            public void setShortX(String shortX) {
                this.shortX = shortX;
            }

            public long getEnd_time() {
                return end_time;
            }

            public void setEnd_time(long end_time) {
                this.end_time = end_time;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getTips() {
                return tips;
            }

            public void setTips(String tips) {
                this.tips = tips;
            }
        }

        public static class UserBean {
            /**
             * spell_id : 4
             * order_id : 43
             * user_id : 9
             * is_master : 1
             * goods_id : 1
             * goods_num : 20
             * add_time : 1492842208
             * end_time : 1492844008
             * nickname : liudong
             * head_pic : /Public/upload/head_pic/2017-03-27/58d8701391de5.jpg
             * state : 2017-04-22 14:23:28 开团
             */

            private String spell_id;
            private String order_id;
            private String user_id;
            private String is_master;
            private String goods_id;
            private String goods_num;
            private String add_time;
            private String end_time;
            private String nickname;
            private String head_pic;
            private String state;

            public String getSpell_id() {
                return spell_id;
            }

            public void setSpell_id(String spell_id) {
                this.spell_id = spell_id;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getIs_master() {
                return is_master;
            }

            public void setIs_master(String is_master) {
                this.is_master = is_master;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(String goods_num) {
                this.goods_num = goods_num;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getHead_pic() {
                return head_pic;
            }

            public void setHead_pic(String head_pic) {
                this.head_pic = head_pic;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }
        }
    }

    public static class GoodsListBean {
        /**
         * rec_id : 42
         * order_id : 43
         * goods_id : 1
         * goods_name : 拼团商品
         * goods_sn : 拼团商品
         * goods_num : 20
         * market_price : 0.10
         * goods_price : 0.03
         * cost_price : 0.00
         * member_goods_price : 0.02
         * give_integral : 0
         * spec_key :
         * spec_key_name :
         * bar_code :
         * is_comment : 0
         * prom_type : 0
         * prom_id : 0
         * is_send : 0
         * delivery_id : 0
         * sku :
         * store_id : 4
         * commission : 0
         * is_checkout : 0
         * deleted : 0
         * original_img : /Public/upload/goods/2017/04-21/58f9d3564d06c.jpg
         * type : 2
         */

        private String rec_id;
        private String order_id;
        private String goods_id;
        private String goods_name;
        private String goods_sn;
        private String goods_num;
        private String market_price;
        private String goods_price;
        private String cost_price;
        private String member_goods_price;
        private String give_integral;
        private String spec_key;
        private String spec_key_name;
        private String bar_code;
        private String is_comment;
        private String prom_type;
        private String prom_id;
        private String is_send;
        private String delivery_id;
        private String sku;
        private String store_id;
        private String commission;
        private String is_checkout;
        private String deleted;
        private String original_img;
        private String type;

        public String getRec_id() {
            return rec_id;
        }

        public void setRec_id(String rec_id) {
            this.rec_id = rec_id;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
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

        public String getGoods_sn() {
            return goods_sn;
        }

        public void setGoods_sn(String goods_sn) {
            this.goods_sn = goods_sn;
        }

        public String getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(String goods_num) {
            this.goods_num = goods_num;
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

        public String getCost_price() {
            return cost_price;
        }

        public void setCost_price(String cost_price) {
            this.cost_price = cost_price;
        }

        public String getMember_goods_price() {
            return member_goods_price;
        }

        public void setMember_goods_price(String member_goods_price) {
            this.member_goods_price = member_goods_price;
        }

        public String getGive_integral() {
            return give_integral;
        }

        public void setGive_integral(String give_integral) {
            this.give_integral = give_integral;
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

        public String getIs_comment() {
            return is_comment;
        }

        public void setIs_comment(String is_comment) {
            this.is_comment = is_comment;
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

        public String getIs_send() {
            return is_send;
        }

        public void setIs_send(String is_send) {
            this.is_send = is_send;
        }

        public String getDelivery_id() {
            return delivery_id;
        }

        public void setDelivery_id(String delivery_id) {
            this.delivery_id = delivery_id;
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

        public String getCommission() {
            return commission;
        }

        public void setCommission(String commission) {
            this.commission = commission;
        }

        public String getIs_checkout() {
            return is_checkout;
        }

        public void setIs_checkout(String is_checkout) {
            this.is_checkout = is_checkout;
        }

        public String getDeleted() {
            return deleted;
        }

        public void setDeleted(String deleted) {
            this.deleted = deleted;
        }

        public String getOriginal_img() {
            return original_img;
        }

        public void setOriginal_img(String original_img) {
            this.original_img = original_img;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
