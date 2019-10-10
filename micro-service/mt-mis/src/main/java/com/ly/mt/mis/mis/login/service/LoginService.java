package com.ly.mt.mis.mis.login.service;

import com.ly.mt.core.base.entity.ResponseJson;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录
 *
 * @author taoye
 */
public interface LoginService {
    /**
     * 获取登录错误次数
     *
     * @author taoye
     */
    String getLoginErrorCount(HttpServletRequest request) throws Exception;

    /**
     * 登录
     *
     * @author taoye
     */
    ResponseJson loginIn(HttpServletRequest request) throws Exception;

    /**
     * 登出
     *
     * @author taoye
     */
    ResponseJson loginOut(HttpServletRequest request) throws Exception;
}