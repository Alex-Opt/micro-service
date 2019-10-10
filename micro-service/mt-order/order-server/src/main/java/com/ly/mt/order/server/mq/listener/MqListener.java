package com.ly.mt.order.server.mq.listener;

import com.ly.mt.order.server.mq.service.RabbitMqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

import static com.ly.mt.core.mq.RabbitQueue.*;

/**
 * MQ监听
 */
@Component
public class MqListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(MqListener.class);

    @Resource
    private RabbitMqService rabbitMqService;

    /**
     * @Description 处理一小时达订单的监听处理
     * @Author taoye
     */
    @RabbitListener(queues = RABBIT_QUEUE_HOME_B_PROVIDER)
    public void handlerHomeBMq(String message) {
        LOGGER.info("start-----------------新接收到家C发过来的一小时达mq消费开始，入参信息:" + message);
        List<Map<String, String>> maps = rabbitMqService.handlerHomeBMq(message);
        rabbitMqService.sendMsgAndFn(maps);
        LOGGER.info("end-----------------新接收到家C发过来的一小时达mq消费完毕！");
    }


    /**
     * 处理一小时达订单物流变化的监听处理
     */
    @RabbitListener(queues = RABBIT_QUEUE_FN_CALLBACK)
    public void handlerFnCallBack(String message) {
        LOGGER.info("start-----------------处理一小时达订单物流变化的监听处理开始，入参信息:" + message);
        rabbitMqService.handlerFnCallBack(message);
        LOGGER.info("end-----------------处理一小时达订单物流变化的监听处理完毕");
    }

}