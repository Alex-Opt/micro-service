package com.ly.mt.user.server.user.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @Description 用户登录接口
 * @Author taoye
 */
public interface LoginService {
    /**
     * @Description 账号密码登录
     * @Author taoye
     */
    JSONObject nameLogin(String json) throws Exception;

    /**
     * @Description 手机号登录
     * @Author taoye
     */
    JSONObject mobileLogin(String json) throws Exception;
}