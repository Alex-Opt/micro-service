package com.ly.mt.mall.h5.mq.service;

public interface PayNotifyService {
    /**
     * @Description 支付回调MQ
     * @Author taoye
     */
    void payNotify(String message);

    /**
     * 计算收益
     */
    void calculateProfit(String message);
}
