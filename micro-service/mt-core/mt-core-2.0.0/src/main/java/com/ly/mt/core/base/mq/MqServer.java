package com.ly.mt.core.base.mq;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * rabbitmq生产者公共方法
 *
 * @author zhanglifeng
 * @date 2019-06-26
 */
@Component
public class MqServer implements RabbitTemplate.ReturnCallback {
    private final static Logger LOGGER = LoggerFactory.getLogger(MqServer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(MqMethod method, JSONObject jsonObject) {
        String exchange = method.getExchange();
        String routingKey = method.getRoutingKey();
        rabbitTemplate.convertAndSend(exchange, routingKey, jsonObject);
    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        LOGGER.info("sender return success" + message.toString() + "===" + i + "===" + s1 + "===" + s2);
    }
}
