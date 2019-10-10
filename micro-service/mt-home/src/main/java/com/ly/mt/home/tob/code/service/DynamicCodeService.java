package com.ly.mt.home.tob.code.service;

import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 短信验证码接口
 *
 * @author: linan
 * @date: 2019/9/10
 **/
public interface DynamicCodeService {
    /**
     * 发送短信验证码
     *
     * @param mobile 手机号
     * @param dynamicCodeType 验证码类型
     * @return ResponseJson
     */
    ResponseJson sendDynamicCode(String mobile, String dynamicCodeType);
}