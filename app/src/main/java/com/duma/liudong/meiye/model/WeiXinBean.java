package com.duma.liudong.meiye.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by liudong on 16/12/5.
 */

public class WeiXinBean {


    /**
     * appid : wxa8dce5196935ec34
     * noncestr : xet7n9e6eqnzof8yu0jqqwslkchpe7yn
     * package : Sign=WXPay
     * partnerid : 1410577202
     * prepayid : wx20161205123958f4e858975b0694564321
     * timestamp : 1480912804
     * paySign : 7C34D1A235A1A670C5D3BCA229EB0056
     */

    private String appid;
    private String noncestr;
    @SerializedName("package")
    private String packageX;
    private String partnerid;
    private String prepayid;
    private String timestamp;
    private String paySign;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }
}
