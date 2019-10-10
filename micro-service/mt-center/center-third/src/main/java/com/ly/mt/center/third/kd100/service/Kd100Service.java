package com.ly.mt.center.third.kd100.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * 快递100服务
 *
 * @author zhanglifeng
 * @date 2019-06-26
 */
public interface Kd100Service {
    /**
     * 根据运单号查询对应快递公司码的物流追踪信息
     *
     * @param jsonObject 入参
     * @return
     * @throws Exception
     */
    ResponseJson getDeliveryInfo(JSONObject jsonObject);
}