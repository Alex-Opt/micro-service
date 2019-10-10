package com.ly.mt.open.notify.service;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

public interface NotifyService {
    /**
     * @Description 阿里支付回调
     * @Author taoye
     */
    String alPayNotify(HttpServletRequest request);

    /**
     * @Description 微信支付回调
     * @Author taoye
     */
    String wxPayNotify(HttpServletRequest request);

    /**
     * 蜂鸟回调更新订单物流，状态信息
     *
     * @param jsonObject
     */
    void fnOrder(JSONObject jsonObject) throws Exception;
}