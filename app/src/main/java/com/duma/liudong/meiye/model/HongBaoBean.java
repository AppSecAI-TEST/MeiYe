package com.duma.liudong.meiye.model;

/**
 * Created by liudong on 17/3/28.
 */

public class HongBaoBean {

    /**
     * id : 1
     * store_id : 2
     * money : 10.00
     * condition : 100.00
     * use_start_time : 1488297600
     * use_end_time : 1495468800
     */

    private String id;
    private String store_id;
    private String money;
    private String condition;
    private String use_start_time;
    private String use_end_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
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

    public String getUse_start_time() {
        return use_start_time;
    }

    public void setUse_start_time(String use_start_time) {
        this.use_start_time = use_start_time;
    }

    public String getUse_end_time() {
        return use_end_time;
    }

    public void setUse_end_time(String use_end_time) {
        this.use_end_time = use_end_time;
    }
}
