package com.ly.mt.core.mq.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.mq.RabbitExchange;
import com.ly.mt.core.mq.service.MqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class MqServiceImpl implements MqService {
    private final static Logger LOGGER = LoggerFactory.getLogger(MqServiceImpl.class);
    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     *
     * @author taoye
     */
    @Override
    public void sendMessage(RabbitExchange mq, JSONObject jsonObject) {
        rabbitTemplate.convertAndSend(
                mq.getExchangeName(),
                mq.getRoutingKey(),
                jsonObject.toJSONString(),
                new CorrelationData("unRouting-" + UUID.randomUUID().toString())
        );
        LOGGER.info("发送消息,exchange:{},routingKey:{},message:{}", mq.getExchangeName(), mq.getRoutingKey(), jsonObject.toJSONString());
    }
}