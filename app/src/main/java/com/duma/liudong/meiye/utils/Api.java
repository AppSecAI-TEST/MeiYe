package com.duma.liudong.meiye.utils;

/**
 * Created by liudong on 17/3/21.
 */

public class Api {
    public static final String url = "http://meiye.duma-ivy.cn";

    /**
     * 个人用户
     */
    public static final String register = url + "/App/user/register";
    public static final String login = url + "/App/user/login";
    public static final String sendCode = url + "/App/user/sendCode";
    public static final String alterPwd = url + "/App/user/alterPwd";
    public static final String index = url + "/App/user/index";
    public static final String setting = url + "/App/user/setting";
    public static final String alterHead = url + "/App/user/alterHead";
    public static final String changePwd = url + "/App/user/changePwd";
    public static final String feedback = url + "/App/user/feedback";
    public static final String myClient = url + "/App/user/myClient";
    public static final String message = url + "/App/user/message";
    public static final String messageAction = url + "/App/user/messageAction";
}
