package com.ly.mt.open.notify.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.base.entity.ResponseJson;

import javax.servlet.http.HttpServletRequest;

public interface ProvideApiService {

    /**
     * 查询订单数据
     * @param startTime：起始时间
     * @return
     */
    ResponseJson queryOrder(String  startTime);

}