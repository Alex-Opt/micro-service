package com.ly.mt.home.tob.shopuser.service;

import com.ly.mt.core.base.entity.ResponseJson;

/**
 * b端用户接口业务类
 */
public interface ShopUserService {
    /**
     * b端检测手机号码是否已注册
     *
     * @param
     * @return
     */
    ResponseJson bShopUserCheckMobile(String mobile);

    /**
     * b端用户注册
     *
     * @param
     * @return
     */
    ResponseJson bShopUserRegist(String inviteCode, String mobile, String sourceFlag, String quickType, String dynamicCode);

    /**
     * B端用户设置密码
     *
     * @param
     * @return
     */
    ResponseJson bShopUserSetPassword(String password, String userId);


    /**
     * B端用户名/手机号，密码登录
     *
     * @param
     * @return
     */
    ResponseJson nameLogin(String mobile, String password);


    /**
     * B端手机号，验证码登录
     *
     * @param
     * @return
     */
    ResponseJson mobileLogin(String dynamicCode, String mobile);

    /**
     * 重置密码
     *
     * @param password
     * @param mobile
     * @param dynamicCode
     * @return
     */
    ResponseJson resetPassword(String password, String mobile, String dynamicCode);

    /**
     * 退出登录
     *
     */
    void loginOut();

    /**
     *
     * @return
     */
    ResponseJson activityShopUserRegist(String mobile, String quickType, String dynamicCode);
}
