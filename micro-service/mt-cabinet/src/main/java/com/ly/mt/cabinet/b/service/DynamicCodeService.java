package com.ly.mt.cabinet.b.service;

import com.ly.mt.cabinet.b.util.Resp;

public interface DynamicCodeService {
    /**
     * 发送短信验证码
     * @param phoneNo
     * @return
     */
    Resp sendDynamicCode(String phoneNo);
}
