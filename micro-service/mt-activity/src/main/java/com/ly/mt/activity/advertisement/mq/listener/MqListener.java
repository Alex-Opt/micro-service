package com.ly.mt.activity.advertisement.mq.listener;

import com.ly.mt.activity.advertisement.mq.service.PayNotifyService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import static com.ly.mt.core.mq.RabbitQueue.RABBIT_QUEUE_PAY_NOTIFY_AD_ACTIVITY;

/**
 * MQ监听
 */
@Component
public class MqListener {
    @Resource
    private PayNotifyService payNotifyService;

    /**
     * @Description 支付回调MQ
     * @Author wanglong
     */
    @RabbitListener(queues = RABBIT_QUEUE_PAY_NOTIFY_AD_ACTIVITY)
    public void payNotify(String message) {
        payNotifyService.payNotify(message);
    }
}