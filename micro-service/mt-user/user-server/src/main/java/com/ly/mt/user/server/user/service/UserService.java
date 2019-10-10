package com.ly.mt.user.server.user.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @Description 用户注册接口
 * @Author taoye
 */
public interface UserService {
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
     * @Description M端H5商城帐号密码注册
     * @Author taoye
     */
    JSONObject mH5NameRegist(String json) throws Exception;

    /**
     * @Description M端H5商城手机号注册
     * @Author taoye
     */
    JSONObject mH5MobileRegist(String json) throws Exception;

    /**
     * @Description 活动H5页面注册
     * @Author taoye
     */
    JSONObject activityH5Regist(String json) throws Exception;
}