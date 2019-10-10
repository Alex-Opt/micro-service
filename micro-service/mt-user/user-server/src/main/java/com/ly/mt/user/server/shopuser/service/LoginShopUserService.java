package com.ly.mt.user.server.shopuser.service;

import com.alibaba.fastjson.JSONObject;

/**
 * B端登录接口
 */
public interface LoginShopUserService {

    /**
     * B端用户名/手机号，密码登录
     * @param json
     * @return
     */
    JSONObject nameLogin(String json);


    /**
     * B端手机号，验证码登录
     * @param json
     * @return
     */
    JSONObject mobileLogin(String json);

    /**
     * h5活动注册
     * @param json
     * @return
     * @throws Exception
     */
    public JSONObject h5mobileLogin(String json) throws Exception;
}
