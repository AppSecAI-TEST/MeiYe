package com.duma.liudong.meiye.utils;

/**
 * Created by liudong on 17/3/21.
 */

public class Api {
    public static final String url = "http://meiye.duma-ivy.cn";

    /**
     * 登录注册
     */
    public static final String register = url + "/App/user/register";
    public static final String login = url + "/App/user/login";
    public static final String sendCode = url + "/App/user/sendCode";
    public static final String alterPwd = url + "/App/user/alterPwd";
    public static final String index = url + "/App/user/index";
}
