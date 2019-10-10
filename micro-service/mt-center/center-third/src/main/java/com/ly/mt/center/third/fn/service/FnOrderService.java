package com.ly.mt.center.third.fn.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * @Description 蜂鸟订单接口
 * @Author taoye
 */
public interface FnOrderService {
    /**
     * @Description 推送订单
     * @Author taoye
     */
    ResponseJson orderCreate(JSONObject jsonObject);

    /**
     * @Description 取消订单
     * @Author taoye
     */
    ResponseJson orderCancel(JSONObject jsonObject);

    /**
     * @Description 查询订单
     * @Author taoye
     */
    ResponseJson orderQuery(JSONObject jsonObject);
}