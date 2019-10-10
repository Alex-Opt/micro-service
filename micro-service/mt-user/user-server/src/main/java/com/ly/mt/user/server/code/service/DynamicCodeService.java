package com.ly.mt.user.server.code.service;

import com.alibaba.fastjson.JSONObject;

public interface DynamicCodeService {
    /**
     * @Description 获取动态验证码发送到手机
     * @Author taoye
     */
    JSONObject getDynamicCode(String json) throws Exception;
}