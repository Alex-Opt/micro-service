package com.ly.mt.home.tob.wx.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * wx
 *
 * @author: linan
 * @date: 2019/9/4
 */
public interface WxService {
    /**
     * 账号密码登录
     * @param mobile 手机号
     * @param password 密码
     * @param code wx授权码
     * @return ResponseJson
     */
    ResponseJson nameLogin(String mobile, String password, String code);

    /**
     * 手机动态码登录
     * @param mobile 手机号
     * @param dynamicCode 动态验证码
     * @param code wx授权码
     * @return ResponseJson
     */
    ResponseJson mobileLogin(String mobile, String dynamicCode, String code);

    /**
     * wx授权
     * @param code wx授权码
     * @return JSONObject
     */
    JSONObject authorization(String code);

    /**
     * wx token登录
     * @param token wx access_token
     * @param refreshToken wx refresh_token
     * @param openId wx user openid
     * @return ResponseJson
     */
    ResponseJson loginByToken(String token, String refreshToken, String openId);

}
