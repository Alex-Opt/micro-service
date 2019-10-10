package com.ly.mt.task.order.service;

import com.alibaba.fastjson.JSONObject;

public interface TradeOrderService {
    /**
     * 定时更新达到自动确认收货的待收货的【到家C】单子状态为完成
     */
    void updCompleteStatus();

    /**
     * 定时自动更新【到家C】订单状态为取消的业务处理
     */
    void updCancelStatus();

    /**
     * 一小时达订单被定时任务捞取到抢单表。作为抢单的单源，如果15分钟状态任然是1：未抢单。则要进入兜底流程。
     * 1.获取订单地址最近区域的兜底商家。
     * 2.更新进入兜底商家的抢单状态为2：已被抢单
     * @throws Exception
     */
    void processingTimeOutOrders() throws  Exception;

    /**
     * 小B抢单后，要对商品进行校验。如果超过五分钟单子状态没有变成校验成功。则系统自动取消小B的抢单。
     * @throws Exception
     */
    void productVerificationExpired() throws Exception;

    /**
     * 调用三方发送短信
     * @param smsJson
     * @throws Exception
     */
    void sendMsg(JSONObject smsJson) throws  Exception;

    /**
     * 未支付的用户在倒计时还有15分钟时，发送微信小程序提醒。
     * @throws Exception
     */
    void notPayUserSendWxMsg() throws Exception;
}