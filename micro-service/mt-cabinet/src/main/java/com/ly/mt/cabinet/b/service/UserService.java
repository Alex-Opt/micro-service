package com.ly.mt.cabinet.b.service;

import com.ly.mt.cabinet.b.util.Resp;

public interface UserService {
    /**
     * 登录
     * @param phoneNo
     * @param dynamicCode
     * @return
     */
    Resp login(String phoneNo,String dynamicCode);
}
