package com.ly.mt.core.mq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitReturnCallback implements RabbitTemplate.ReturnCallback {
    private final static Logger LOGGER = LoggerFactory.getLogger(RabbitReturnCallback.class);

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        LOGGER.info("MessageReturned message:{},replyCode:{},replyText:{},exchange:{},routingKey:{}", message, replyCode, replyText, exchange, routingKey);
    }
}