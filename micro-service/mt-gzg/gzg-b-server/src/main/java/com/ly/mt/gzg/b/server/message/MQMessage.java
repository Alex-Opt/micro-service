package com.ly.mt.gzg.b.server.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.common.constant.IdEnum;
import com.ly.mt.core.common.constant.gzg.GzgReplenishStatusEnum;
import com.ly.mt.core.common.entity.gzg.GzgHotelStock;
import com.ly.mt.core.common.entity.gzg.GzgInfo;
import com.ly.mt.core.common.entity.gzg.GzgOrderItemVo;
import com.ly.mt.core.common.entity.gzg.GzgReplenishOrder;
import com.ly.mt.core.common.util.IdUtil;
import com.ly.mt.gzg.b.server.constant.Constant;
import com.ly.mt.gzg.b.server.gzgb.mapper.GzgHotelStockMapper;
import com.ly.mt.gzg.b.server.gzgb.mapper.GzgInfoMapper;
import com.ly.mt.gzg.b.server.gzgb.mapper.GzgOrdersServiceMapper;
import com.ly.mt.gzg.b.server.gzgb.mapper.GzgReplenishOrderMapper;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;


@Component
@Slf4j
public class MQMessage {



    @Autowired
    private MqMessageHandler mqMessageHandler;

    @RabbitHandler
    @RabbitListener(queues = {"gzg_c_notify_b_monitor"})
    @Async
    public void msgMonitor(Message message, Channel channel){
        MessageProperties messageProperties = message.getMessageProperties();
        byte[] byteMessage = message.getBody();
        String msg = new String(byteMessage);
        log.info("monitor message is {}",msg);
        try {
            mqMessageHandler.msgHandler(msg);
            basicACK(message,channel);
        }catch (Exception e){
            log.error("消费消息出现异常情况,异常消息是:{}",e);
            basicNACK(message,channel);
        }

    }

    /**
     * 正常消费掉后通知mq服务器移除此条mq
     * @param message
     * @param channel
     */
    private void basicACK(Message message, Channel channel){
        try{

            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }catch(IOException e){
            log.error("通知服务器移除mq时异常，异常信息："+e);
        }
    }

    /**
     * 处理异常，mq重回队列
     */
    private void basicNACK(Message message, Channel channel){
        try{
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
        }catch(IOException e){
            log.error("mq重新进入服务器时出现异常，异常信息："+e.getMessage());
        }
    }
}
