package com.ly.mt.core.server.service;

import java.util.Map;

/**
 * @Description 公共接口类
 * @Author taoye
 */
public interface CoreService {
    /**
     * @Description 调用外部接口——post
     * @Author taoye
     */
    String postExternalServer(String serverUrl, String param);

    /**
     * @Description 调用外部接口——get
     * @Author taoye
     */
    String getExternalServer(String serverUrl, Map<String, Object> map);

    /**
     * @Description 获取用户登录用户id
     * @Author taoye
     */
    String getLoginUserId();

    /**
     * @Description 获取用户登录用户name
     * @Author taoye
     */
    String getLoginUserName();

    /**
     * @Description 获取用户登录用户手机
     * @Author taoye
     */
    String getLoginUserMobile();

    /**
     * @Description 获取用户登录Ip
     * @Author taoye
     */
    String getLoginIpAddress();

    /**
     * @return
     * @Description 获取小B的shopId
     * @Author zhanglifeng
     */
    String getLoginShopId();
}