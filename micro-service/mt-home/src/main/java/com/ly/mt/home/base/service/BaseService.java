package com.ly.mt.home.base.service;

public interface BaseService {
    /**
     * 获取登录用户id
     *
     * @return
     */
    String getLoginUserId();

    /**
     * 获取登录用户姓名
     *
     * @return
     */
    String getLoginUserName();

    /**
     * 获取登录用户shopId
     *
     * @return
     */
    String getLoginShopId();

    /**
     * 获取登录用户ip
     *
     * @return
     */
    String getLoginUserIp();

    /**
     * 获取token
     *
     * @return
     */
    String getToken();
}