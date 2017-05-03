package com.duma.liudong.meiye.model;

/**
 * Created by liudong on 17/4/21.
 */

public class SelletBean {


    /**
     * store_id :
     * sc_id :
     * store_name :
     * store_head_img :
     * store_time : 2017
     * store_collect :
     * wait_confirm : 0
     * wait_pay : 0
     * wait_shipping : 0
     * refund_pay : 0
     */

    private String store_id;
    private String sc_id;
    private String store_name;
    private String store_head_img;
    private String store_time;
    private String store_collect;
    private String wait_confirm;
    private String wait_pay;
    private String wait_shipping;
    private String refund_pay;
    private DianPubean store_info;
    private DinDanNumBean order_count;

    public DinDanNumBean getOrder_count() {
        return order_count;
    }

    public void setOrder_count(DinDanNumBean order_count) {
        this.order_count = order_count;
    }

    public DianPubean getStore_info() {
        return store_info;
    }

    public void setStore_info(DianPubean store_info) {
        this.store_info = store_info;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getSc_id() {
        return sc_id;
    }

    public void setSc_id(String sc_id) {
        this.sc_id = sc_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getStore_head_img() {
        return store_head_img;
    }

    public void setStore_head_img(String store_head_img) {
        this.store_head_img = store_head_img;
    }

    public String getStore_time() {
        return store_time;
    }

    public void setStore_time(String store_time) {
        this.store_time = store_time;
    }

    public String getStore_collect() {
        return store_collect;
    }

    public void setStore_collect(String store_collect) {
        this.store_collect = store_collect;
    }

    public String getWait_confirm() {
        return wait_confirm;
    }

    public void setWait_confirm(String wait_confirm) {
        this.wait_confirm = wait_confirm;
    }

    public String getWait_pay() {
        return wait_pay;
    }

    public void setWait_pay(String wait_pay) {
        this.wait_pay = wait_pay;
    }

    public String getWait_shipping() {
        return wait_shipping;
    }

    public void setWait_shipping(String wait_shipping) {
        this.wait_shipping = wait_shipping;
    }

    public String getRefund_pay() {
        return refund_pay;
    }

    public void setRefund_pay(String refund_pay) {
        this.refund_pay = refund_pay;
    }
}
