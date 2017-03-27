package com.duma.liudong.meiye.base;

/**
 * Created by liudong on 17/3/27.
 */

public class MessageBean {
    /**
     * message_id : 4
     * message_body : 你的号被封啦
     * message_time : 1489485007
     * message_type : 0
     * no_read : 1
     */
    private String message_id;
    private String message_body;
    private String message_time;
    private String message_type;
    private String no_read;

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getMessage_body() {
        return message_body;
    }

    public void setMessage_body(String message_body) {
        this.message_body = message_body;
    }

    public String getMessage_time() {
        return message_time;
    }

    public void setMessage_time(String message_time) {
        this.message_time = message_time;
    }

    public String getMessage_type() {
        return message_type;
    }

    public void setMessage_type(String message_type) {
        this.message_type = message_type;
    }

    public String getNo_read() {
        return no_read;
    }

    public void setNo_read(String no_read) {
        this.no_read = no_read;
    }

}
