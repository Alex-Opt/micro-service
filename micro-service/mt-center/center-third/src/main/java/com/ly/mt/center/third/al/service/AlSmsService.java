package com.ly.mt.center.third.al.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * @Description 短信服务接口
 * @Author taoye
 */
public interface AlSmsService {
    /**
     * @Description 发送短信
     * @Author taoye
     */
    ResponseJson sendSms(JSONObject jsonObject);
}