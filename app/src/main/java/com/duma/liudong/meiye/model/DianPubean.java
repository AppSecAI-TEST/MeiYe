package com.duma.liudong.meiye.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liudong on 17/4/5.
 */

public class DianPubean implements Serializable {

    /**
     * store_collect : 0
     * store_zy : 店铺一店铺一店铺一
     * sc_name : 店家(服务卖家)
     * store_slide : /Public/upload/seller/2017/04-01/58df6756c5b33.jpg,/Public/upload/seller/2017/04-01/58df675f37e70.jpg,/Public/upload/seller/2017/04-01/58df6765942d3.jpg,,
     * province_name : 北京市
     * city_name : 北京市
     * district_name : 北京市
     * store_address : 12323
     * store_name : 旗舰店
     * turn_back_rate : 0
     * store_desccredit : 0
     * store_servicecredit : 0
     * store_deliverycredit : 0
     * store_logo : http://meiye.duma-ivy.cn/Public/upload/seller/2017/03-29/58db723588613.jpg
     * store_banner : http://meiye.duma-ivy.cn/Public/upload/seller/2017/03-28/58da0863b7ac1.jpg
     * store_avatar :
     * store_phone : 123123123
     * store_time : 2017-03-14
     * business_licence_number : null
     * business_licence_cert : null
     * business_scope : null
     * reg_capital : null
     * legal_person : null
     * business_date_start : null
     * business_date_end : null
     * company_name : null
     * contacts_name : null
     * contacts_mobile : null
     * company_type : null
     * store_score : 0
     * store_slide_url : ["http://meiye.duma-ivy.cn/Public/upload/seller/2017/04-01/58df6756c5b33.jpg","http://meiye.duma-ivy.cn/Public/upload/seller/2017/04-01/58df675f37e70.jpg","http://meiye.duma-ivy.cn/Public/upload/seller/2017/04-01/58df6765942d3.jpg"]
     * store_time_y : 1
     * goods_num : 30
     * order_num : 0
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
    private String turn_back_rate;
    private int store_desccredit;
    private int store_servicecredit;
    private int store_deliverycredit;
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
    private double store_score;
    private String store_time_y;
    private String goods_num;
    private String order_num;
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

    public String getTurn_back_rate() {
        return turn_back_rate;
    }

    public void setTurn_back_rate(String turn_back_rate) {
        this.turn_back_rate = turn_back_rate;
    }

    public int getStore_desccredit() {
        return store_desccredit;
    }

    public void setStore_desccredit(int store_desccredit) {
        this.store_desccredit = store_desccredit;
    }

    public int getStore_servicecredit() {
        return store_servicecredit;
    }

    public void setStore_servicecredit(int store_servicecredit) {
        this.store_servicecredit = store_servicecredit;
    }

    public int getStore_deliverycredit() {
        return store_deliverycredit;
    }

    public void setStore_deliverycredit(int store_deliverycredit) {
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

    public List<String> getStore_slide_url() {
        return store_slide_url;
    }

    public void setStore_slide_url(List<String> store_slide_url) {
        this.store_slide_url = store_slide_url;
    }
}
