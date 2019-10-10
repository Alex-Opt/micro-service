package com.ly.mt.mall.h5.mq.listener;

import com.ly.mt.mall.h5.mq.service.PayNotifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.ly.mt.core.mq.RabbitQueue.RABBIT_QUEUE_CALCULATE_PROFIT;
import static com.ly.mt.core.mq.RabbitQueue.RABBIT_QUEUE_PAY_NOTIFY_MALL;

/**
 * MQ监听
 */
@Component
public class MqListener {

    private final static Logger LOGGER = LoggerFactory.getLogger(MqListener.class);

    @Resource
    private PayNotifyService payNotifyService;

    /**
     * @Description 支付回调MQ
     * @Author taoye
     */
    @RabbitListener(queues = RABBIT_QUEUE_PAY_NOTIFY_MALL)
    public void payNotify(String message) {
        LOGGER.info("start-----------------mt-mall新接收到支付回调的mq，消费开始，入参信息:" + message);
        payNotifyService.payNotify(message);
        LOGGER.info("end-----------------mt-mall新接收到支付回调的mq，消费结束");
    }


    /**
     * 支付成功后，异步计算各层级收益
     */
    @RabbitListener(queues = RABBIT_QUEUE_CALCULATE_PROFIT)
    public void calculateProfit(String message) {
        LOGGER.info("start-----------------mt-mall新接收到计算各层级收益回调的mq，消费开始，入参信息:" + message);
        payNotifyService.calculateProfit(message);
        LOGGER.info("end-----------------mt-mall新接收到计算各层级收益回调的mq，消费结束");
    }
}