package com.duma.liudong.meiye.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liudong on 17/4/5.
 */

public class DianPubean implements Serializable {

    /**
     * store_collect : 1
     * store_zy : 服饰
     * sc_name : 店家(服务卖家)
     * store_slide : /Public/upload/seller/2017/05-10/5912b31cf38c7.jpg,/Public/upload/seller/2017/05-10/5912b322f0ee3.jpg,/Public/upload/seller/2017/05-10/5912b3296c6da.jpg,/Public/upload/seller/2017/05-10/5912b33b9b529.jpeg,/Public/upload/seller/2017/05-10/5912b341b55e8.jpeg
     * province_name : 浙江省
     * city_name : 杭州市
     * district_name : 下城区
     * store_address : 庆春路87-1号锦和大厦5楼
     * store_name : 林俊的店铺
     * store_desccredit : 0
     * store_servicecredit : 0
     * store_deliverycredit : 0
     * store_logo : http://api.myjd.cc/Public/upload/seller/2017/05-10/591274b268d11.jpg
     * store_banner : http://api.myjd.cc/Public/images/meiye/group_2@2x.png
     * store_avatar :
     * store_phone : 15270001511
     * store_time : 2017-05-09
     * business_licence_number :
     * business_licence_cert : http://api.myjd.cc/Public/upload/store/cert/2017-05-09/5911a2157fc73.jpg
     * business_scope :
     * reg_capital :
     * legal_person :
     * business_date_start :
     * business_date_end :
     * company_name :
     * contacts_name : 林俊
     * contacts_mobile : 15270001512
     * company_type :
     * turn_back_rate : 48
     * store_score : 0
     * store_slide_url_com : [{"link_url":"http://jd.com","link_logo":"/Public/upload/seller/2017/05-10/5912b31cf38c7.jpg"},{"link_url":"http://baidu.com","link_logo":"/Public/upload/seller/2017/05-10/5912b322f0ee3.jpg"},{"link_url":"http://taobao.com","link_logo":"/Public/upload/seller/2017/05-10/5912b3296c6da.jpg"},{"link_url":"http://weibo.com","link_logo":"/Public/upload/seller/2017/05-10/5912b33b9b529.jpeg"},{"link_url":"http://qq.com","link_logo":"/Public/upload/seller/2017/05-10/5912b341b55e8.jpeg"}]
     * store_slide_url : ["http://api.myjd.cc/Public/upload/seller/2017/05-10/5912b31cf38c7.jpg","http://api.myjd.cc/Public/upload/seller/2017/05-10/5912b322f0ee3.jpg","http://api.myjd.cc/Public/upload/seller/2017/05-10/5912b3296c6da.jpg","http://api.myjd.cc/Public/upload/seller/2017/05-10/5912b33b9b529.jpeg","http://api.myjd.cc/Public/upload/seller/2017/05-10/5912b341b55e8.jpeg"]
     * store_time_y : 1
     * goods_num : 5
     * order_num : 21
     */

    private String store_collect;
    private String store_zy;
    private String sc_name;
    private String store_slide;
    private String province_name;
    private String city_name;
    private String district_name;
    private String store_address;
    private String store_name;
    private double store_desccredit;
    private double store_servicecredit;
    private double store_deliverycredit;
    private String store_logo;
    private String store_banner;
    private String store_avatar;
    private String store_phone;
    private String store_time;
    private String business_licence_number;
    private String business_licence_cert;
    private String business_scope;
    private String reg_capital;
    private String legal_person;
    private String business_date_start;
    private String business_date_end;
    private String company_name;
    private String contacts_name;
    private String contacts_mobile;
    private String company_type;
    private double turn_back_rate;
    private double store_score;
    private String store_time_y;
    private String goods_num;
    private String order_num;
    private List<StoreSlideUrlComBean> store_slide_url_com;
    private List<String> store_slide_url;

    public String getStore_collect() {
        return store_collect;
    }

    public void setStore_collect(String store_collect) {
        this.store_collect = store_collect;
    }

    public String getStore_zy() {
        return store_zy;
    }

    public void setStore_zy(String store_zy) {
        this.store_zy = store_zy;
    }

    public String getSc_name() {
        return sc_name;
    }

    public void setSc_name(String sc_name) {
        this.sc_name = sc_name;
    }

    public String getStore_slide() {
        return store_slide;
    }

    public void setStore_slide(String store_slide) {
        this.store_slide = store_slide;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getStore_address() {
        return store_address;
    }

    public void setStore_address(String store_address) {
        this.store_address = store_address;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public double getStore_desccredit() {
        return store_desccredit;
    }

    public void setStore_desccredit(double store_desccredit) {
        this.store_desccredit = store_desccredit;
    }

    public double getStore_servicecredit() {
        return store_servicecredit;
    }

    public void setStore_servicecredit(double store_servicecredit) {
        this.store_servicecredit = store_servicecredit;
    }

    public double getStore_deliverycredit() {
        return store_deliverycredit;
    }

    public void setStore_deliverycredit(double store_deliverycredit) {
        this.store_deliverycredit = store_deliverycredit;
    }

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

    public String getStore_avatar() {
        return store_avatar;
    }

    public void setStore_avatar(String store_avatar) {
        this.store_avatar = store_avatar;
    }

    public String getStore_phone() {
        return store_phone;
    }

    public void setStore_phone(String store_phone) {
        this.store_phone = store_phone;
    }

    public String getStore_time() {
        return store_time;
    }

    public void setStore_time(String store_time) {
        this.store_time = store_time;
    }

    public String getBusiness_licence_number() {
        return business_licence_number;
    }

    public void setBusiness_licence_number(String business_licence_number) {
        this.business_licence_number = business_licence_number;
    }

    public String getBusiness_licence_cert() {
        return business_licence_cert;
    }

    public void setBusiness_licence_cert(String business_licence_cert) {
        this.business_licence_cert = business_licence_cert;
    }

    public String getBusiness_scope() {
        return business_scope;
    }

    public void setBusiness_scope(String business_scope) {
        this.business_scope = business_scope;
    }

    public String getReg_capital() {
        return reg_capital;
    }

    public void setReg_capital(String reg_capital) {
        this.reg_capital = reg_capital;
    }

    public String getLegal_person() {
        return legal_person;
    }

    public void setLegal_person(String legal_person) {
        this.legal_person = legal_person;
    }

    public String getBusiness_date_start() {
        return business_date_start;
    }

    public void setBusiness_date_start(String business_date_start) {
        this.business_date_start = business_date_start;
    }

    public String getBusiness_date_end() {
        return business_date_end;
    }

    public void setBusiness_date_end(String business_date_end) {
        this.business_date_end = business_date_end;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getContacts_name() {
        return contacts_name;
    }

    public void setContacts_name(String contacts_name) {
        this.contacts_name = contacts_name;
    }

    public String getContacts_mobile() {
        return contacts_mobile;
    }

    public void setContacts_mobile(String contacts_mobile) {
        this.contacts_mobile = contacts_mobile;
    }

    public String getCompany_type() {
        return company_type;
    }

    public void setCompany_type(String company_type) {
        this.company_type = company_type;
    }

    public double getTurn_back_rate() {
        return turn_back_rate;
    }

    public void setTurn_back_rate(double turn_back_rate) {
        this.turn_back_rate = turn_back_rate;
    }

    public double getStore_score() {
        return store_score;
    }

    public void setStore_score(double store_score) {
        this.store_score = store_score;
    }

    public String getStore_time_y() {
        return store_time_y;
    }

    public void setStore_time_y(String store_time_y) {
        this.store_time_y = store_time_y;
    }

    public String getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(String goods_num) {
        this.goods_num = goods_num;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public List<StoreSlideUrlComBean> getStore_slide_url_com() {
        return store_slide_url_com;
    }

    public void setStore_slide_url_com(List<StoreSlideUrlComBean> store_slide_url_com) {
        this.store_slide_url_com = store_slide_url_com;
    }

    public List<String> getStore_slide_url() {
        return store_slide_url;
    }

    public void setStore_slide_url(List<String> store_slide_url) {
        this.store_slide_url = store_slide_url;
    }

    public static class StoreSlideUrlComBean implements Serializable {
        /**
         * link_url : http://jd.com
         * link_logo : /Public/upload/seller/2017/05-10/5912b31cf38c7.jpg
         */

        private String link_url;
        private String link_logo;

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
}
