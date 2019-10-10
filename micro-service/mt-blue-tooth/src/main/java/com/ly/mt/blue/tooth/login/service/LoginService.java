package com.ly.mt.blue.tooth.login.service;

import com.ly.mt.core.base.entity.ResponseJson;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {
    /**
     * 用户名登录
     * @throws Exception
     */
    ResponseJson nameLogin(String loginName, String password) throws Exception;

    /**
     * 验证码登录
     * @throws Exception
     */
    ResponseJson dynamicCodeLogin(String mobile,String dynamicCode) throws Exception;

    /**
     * 用户退出账号
     * @throws Exception
     */
    ResponseJson loginOut() throws Exception;


    /**
     * @Description 更新用户登录状态
     * @Author whl
     */
    ResponseJson updateFirstLogin() throws Exception;
}