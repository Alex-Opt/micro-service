package com.ly.mt.center.third.wx.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * @Description 微信登陆
 * @Author zhanglifeng
 */
public interface WxLoginService {

    /**
     * 微信小程序登陆凭证校验
     *
     * @param jsonObject
     * @return
     */
    ResponseJson appletAuthCode2Session(JSONObject jsonObject);

    /**
     * 获取小程序全局唯一后台接口调用凭据（access_token）
     * @param jsonObject
     * @return
     */
    ResponseJson getWxAppletAccessToken(JSONObject jsonObject);

    /**
     * 微信网页授权
     * @param jsonObject
     * @return
     */
    ResponseJson auth(JSONObject jsonObject);

    /**
     * 微信刷新token
     * @param jsonObject
     * @return
     */
    ResponseJson refreshToken(JSONObject jsonObject);
}