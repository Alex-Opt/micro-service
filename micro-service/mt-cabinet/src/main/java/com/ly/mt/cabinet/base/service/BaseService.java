package com.ly.mt.cabinet.base.service;

import com.ly.mt.cabinet.b.login.vo.TokenInfo;

public interface BaseService {
    /**
     * 从token中获取用户信息
     * @return
     */
    TokenInfo getTokenInfo();
}