package com.ly.mt.activity.advertisement.service;

import com.ly.mt.core.base.entity.ResponseJson;

public interface DynamicCodeService {
    /**
     * @Description 发送动态验证到手机
     * @Author taoye
     */
    ResponseJson sendDynamicCode(String mobile, String dynamicCodeType);
}