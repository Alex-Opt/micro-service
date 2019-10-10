package com.ly.mt.center.third.fn.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * @Description 蜂鸟骑手接口
 * @Author taoye
 */
public interface FnCarrierService {
    /**
     * @Description 查询骑手位置
     * @Author taoye
     */
    ResponseJson carrierQuery(JSONObject jsonObject);
}