package com.ly.mt.cabinet.c.code.service;

import com.ly.mt.core.base.entity.ResponseJson;

public interface GzgDynamicCodeService {
    /**
     * @Description 发送动态验证到手机
     * @Author taoye
     */
    ResponseJson sendDynamicCode(String mobile, String dynamicCodeType);
}