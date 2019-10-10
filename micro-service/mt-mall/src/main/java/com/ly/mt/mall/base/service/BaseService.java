package com.ly.mt.mall.base.service;

/**
 * 模块公共接口，处理本模块公共方法
 *
 * @author taoye
 */
public interface BaseService {
    /**
     * 从session中获取登录用户id，ServletRequestAttributes为空时抛出异常
     *
     * @return 登录用户的id
     * @author taoye
     */
    String getLoginUserId();

    /**
     * 从session中获获取登录用户姓名，ServletRequestAttributes为空时抛出异常
     *
     * @return 登录用户的姓名
     * @author taoye
     */
    String getLoginUserName();

    /**
     * 从session中获取登录用户ip，ServletRequestAttributes为空时抛出异常
     *
     * @return 登录用户的ip
     * @author taoye
     */
    String getLoginUserIp();
}