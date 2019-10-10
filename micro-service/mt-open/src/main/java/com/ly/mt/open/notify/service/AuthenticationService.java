package com.ly.mt.open.notify.service;

import com.ly.mt.core.base.entity.ResponseJson;

import javax.servlet.http.HttpServletRequest;

public interface AuthenticationService {

    /**
     *   登录
     * @param account
     * @param password
     * @return
     */
    ResponseJson login(String  account, String password, HttpServletRequest request) throws Exception;
}
