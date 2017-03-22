package com.duma.liudong.meiye.model;

/**
 * Created by liudong on 17/3/22.
 */

public class ObjectBean<T> {

    /**
     * status : 1
     * msg : 注册成功
     * result : {"user_id":"2","phone":"15088653868","token":"mrknoc1489542142"}
     */

    private String status;
    private String msg;
    private T result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
