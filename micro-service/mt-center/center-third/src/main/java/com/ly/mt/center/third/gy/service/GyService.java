package com.ly.mt.center.third.gy.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

/**
 * @description: 管易接口
 * @author: linan
 * @date: 2019/7/22
 */
public interface GyService {
    ResponseJson gyDeliverInfo(JSONObject jsonObject);
}
