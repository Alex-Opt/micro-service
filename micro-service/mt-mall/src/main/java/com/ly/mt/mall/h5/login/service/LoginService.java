package com.ly.mt.mall.h5.login.service;

import com.ly.mt.core.base.entity.ResponseJson;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 用户登录
 * @Author taoye
 */
public interface LoginService {
    /**
     * @Description 账号密码登录
     * @Author taoye
     */
    ResponseJson nameLogin(String loginName, String password, HttpServletRequest request);

    /**
     * @Description 到家C h5或者活动页手机号登录
     * @Author taoye
     */
    ResponseJson mobileLogin(String mobile, String dynamicCode, String quickType, String data_source, String material, String channel, HttpServletRequest request);

    /**
     * @Description 到家C app或者微信小程序手机号登录
     * @Author zhanglifeng
     */
    ResponseJson appletMobileLogin(String mobile, String dynamicCode, String quickType, HttpServletRequest request);

    /**
     * @Description 用户信息补全
     * @Author taoye
     */
    ResponseJson perfectInfo(String loginName, String password, HttpServletRequest request);

    /**
     * 微信小程序：登陆授权
     *
     * @param request
     * @return
     */
    ResponseJson weChatAppletLoginAuth(String code, HttpServletRequest request);

    /**
     * 微信小程序：微信手机号登陆，通过手机号进行用户账号的信息同步等。
     *
     * @param mobile
     * @param userWxInfoCacheKey
     * @return
     */
    ResponseJson weChatAppletMobileLogin(String mobile, String userWxInfoCacheKey);

    /**
     * 微信小程序：获取用户绑定微信的手机号
     *
     * @param encryptedData
     * @param iv
     * @param userWxInfoCacheKey
     * @return
     */
    ResponseJson wxAppletGetBindMobileNumber(String encryptedData, String iv, String userWxInfoCacheKey);

    /**
     * 获取、刷新到家C小程序的access_token值
     *
     * @return
     */
    ResponseJson wxAppletAccessToken();


    /**
     * [批量保存formIds]
     * @param formIds
     * @param openId
     * @return
     */
    ResponseJson saveFormId(String formIds, String openId);

}