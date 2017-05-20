package com.duma.liudong.meiye.model;

/**
 * Created by liudong on 17/3/30.
 */

public class LinJuanBean {

    /**
     * id : 1
     * name : 测试
     * money : 10.00
     * condition : 100.00
     * head_img : /Public/upload/coupon/2017-03-27/58d8db7423581.jpeg
     * createnum : 100
     * send_num : 0
     * store_id : 1
     */

    private String id;
    private String name;
    private String money;
    private String condition;
    private String head_img;
    private String createnum;
    private String send_num;
    private String store_id;
    private String is_use;

    public String getIs_use() {
        return is_use;
    }

    public void setIs_use(String is_use) {
        this.is_use = is_use;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }

    public String getCreatenum() {
        return createnum;
    }

    public void setCreatenum(String createnum) {
        this.createnum = createnum;
    }

    public String getSend_num() {
        return send_num;
    }

    public void setSend_num(String send_num) {
        this.send_num = send_num;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }
}
