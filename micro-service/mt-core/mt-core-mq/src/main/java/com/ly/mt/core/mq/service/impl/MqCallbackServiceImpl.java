package com.ly.mt.core.mq.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.mq.RabbitExchange;
import com.ly.mt.core.mq.config.RabbitConfirmCallback;
import com.ly.mt.core.mq.config.RabbitReturnCallback;
import com.ly.mt.core.mq.service.MqCallbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.UUID;

@Service
public class MqCallbackServiceImpl implements MqCallbackService {
    private final static Logger LOGGER = LoggerFactory.getLogger(MqCallbackServiceImpl.class);

    @Resource
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void initRabbitTemplate() {
        rabbitTemplate.setConfirmCallback(new RabbitConfirmCallback());
        rabbitTemplate.setReturnCallback(new RabbitReturnCallback());
    }

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
        LOGGER.info("发送消息并返回消息确认,exchange:{},routingKey:{},message:{}", mq.getExchangeName(), mq.getRoutingKey(), jsonObject.toJSONString());
    }
}