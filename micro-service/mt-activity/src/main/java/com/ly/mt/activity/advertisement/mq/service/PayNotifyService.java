package com.ly.mt.activity.advertisement.mq.service;

public interface PayNotifyService {
    /**
     * @Description 支付回调MQ
     * @Author taoye
     */
    void payNotify(String message);
}
