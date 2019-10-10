package com.ly.mt.core.mq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitConfirmCallback implements RabbitTemplate.ConfirmCallback {
    private final static Logger LOGGER = LoggerFactory.getLogger(RabbitConfirmCallback.class);

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        LOGGER.info("MessageConfirm correlationData:{},ack:{},cause:{}", correlationData, ack, cause);
    }
}