package com.ly.mt.cabinet.c.user.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @Description 用户注册接口
 * @Author taoye
 */
public interface GzgUserService {
    /**
     * @Description 校验账号是否已注册
     * @Author taoye
     */
    JSONObject checkLoginName(String json) throws Exception;

    /**
     * @Description 校验手机号是否已注册
     * @Author taoye
     */
    JSONObject checkUserMobile(String json) throws Exception;

    /**
     * @Description 用户注册
     * @Author taoye
     */
    JSONObject gzgMobileRegist(String json) throws Exception;


}