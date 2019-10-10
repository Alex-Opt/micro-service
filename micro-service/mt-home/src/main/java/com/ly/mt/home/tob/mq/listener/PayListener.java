package com.ly.mt.home.tob.mq.listener;

import com.ly.mt.home.tob.mq.service.PayNotifyService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.ly.mt.core.mq.RabbitQueue.RABBIT_QUEUE_PAY_NOTIFY_MT_HOME_B;

/**
 * @description: 支付宝支付回调
 * @author: linan
 * @date: 2019/7/17
 */
@Component
public class PayListener {

    @Resource
    PayNotifyService payNotifyService;

    /**
     * @Description 支付回调MQ
     * @Author taoye
     */
    @RabbitListener(queues = RABBIT_QUEUE_PAY_NOTIFY_MT_HOME_B)
    public void payNotify(String json, Channel channel, Message message) {
        payNotifyService.payNotify(json);
    }

    /**
     * 支付成功后，异步计算各层级收益
     * @param channel
     * @param message
     */
    // TODO: 2019/7/17
   /* @RabbitListener(queues = "calculate_profit_queue", containerFactory = "rabbitListenerContainerFactory")
    public void calculateProfitNotify(Channel channel, Message message) {
        payNotifyService.calculateProfit(channel, message);
    }*/
}
