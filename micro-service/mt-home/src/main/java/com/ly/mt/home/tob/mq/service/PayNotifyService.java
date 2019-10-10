package com.ly.mt.home.tob.mq.service;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

public interface PayNotifyService {
    /**
     * @Description 支付回调MQ
     * @Author taoye
     */
    void payNotify(String json);

    /**
     * 计算收益
     */
    void calculateProfit(Channel channel, Message message);
}
