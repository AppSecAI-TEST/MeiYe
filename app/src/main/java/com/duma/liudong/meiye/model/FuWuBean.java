package com.duma.liudong.meiye.model;

import java.util.List;

/**
 * Created by liudong on 17/4/22.
 */

public class FuWuBean {

    /**
     * order_id : 28
     * order_sn : 201704221216367731
     * master_order_sn :
     * user_id : 9
     * order_status : 0
     * shipping_status : 0
     * pay_status : 1
     * consignee :
     * country : 0
     * province : 0
     * city : 0
     * district : 0
     * twon : 0
     * address :
     * zipcode :
     * mobile : 18772397060
     * email :
     * shipping_code : 0
     * shipping_name :
     * pay_code :
     * pay_name :
     * invoice_title :
     * goods_price : 0.02
     * shipping_price : 0.00
     * user_money : 0.06
     * coupon_price : 0.00
     * integral : 0
     * integral_money : 0.00
     * order_amount : 0.00
     * total_amount : 0.02
     * confirm_time : 0
     * pay_time : 1492834596
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
     * add_time : 1492834596
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
     * goods_list : [{"rec_id":"27","order_id":"28","goods_id":"34","goods_name":"服务商品","goods_sn":"服务商品","goods_num":"3","market_price":"0.10","goods_price":"0.03","cost_price":"0.00","member_goods_price":"0.02","give_integral":"0","spec_key":"","spec_key_name":"","bar_code":"","is_comment":"0","prom_type":"0","prom_id":"0","is_send":"0","delivery_id":"0","sku":"","store_id":"4","commission":"0","is_checkout":"0","deleted":"0","original_img":"/Public/upload/goods/2017/04-21/58f9d3564d06c.jpg","type":"3"}]
     * support : [{"id":"16","card_num":"200198733793","order_id":"28","is_use":"0"},{"id":"17","card_num":"177086661170","order_id":"28","is_use":"0"},{"id":"18","card_num":"585458471470","order_id":"28","is_use":"0"}]
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
    private String shipping_price;
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
    private List<QueRenDinDanBean.CartListBean.GoodsListBean> goods_list;
    private List<SupportBean> support;

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

    public String getShipping_price() {
        return shipping_price;
    }

    public void setShipping_price(String shipping_price) {
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

    public List<QueRenDinDanBean.CartListBean.GoodsListBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<QueRenDinDanBean.CartListBean.GoodsListBean> goods_list) {
        this.goods_list = goods_list;
    }

    public List<SupportBean> getSupport() {
        return support;
    }

    public void setSupport(List<SupportBean> support) {
        this.support = support;
    }

    public static class SupportBean {
        /**
         * id : 16
         * card_num : 200198733793
         * order_id : 28
         * is_use : 0
         */

        private String id;
        private String card_num;
        private String order_id;
        private String is_use;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCard_num() {
            return card_num;
        }

        public void setCard_num(String card_num) {
            this.card_num = card_num;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getIs_use() {
            return is_use;
        }

        public void setIs_use(String is_use) {
            this.is_use = is_use;
        }
    }
}
