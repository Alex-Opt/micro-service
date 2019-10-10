package com.ly.mt.cabinet.b.login.service;

import com.ly.mt.core.base.entity.ResponseJson;

/**
* @program: mt-blue-tooth
* @description: 用户登录service
* @author: wanghongliang
* @create: 2019/9/3 17:30
**/
public interface LoginService {
    /**
     * 验证码登录
     * @throws Exception
     */
    ResponseJson dynamicCodeLogin(String mobile, String dynamicCode,String clientId) throws Exception;

    /**
     * 用户退出账号
     * @throws Exception
     */
    ResponseJson loginOut() throws Exception;

}