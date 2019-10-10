package com.ly.mt.blue.tooth.mq;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.blue.tooth.mq.service.RabbitMqService;
import com.ly.mt.blue.tooth.mq.vo.BluetoothLogMqVo;
import com.ly.mt.blue.tooth.mq.vo.BluetoothLogVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.ly.mt.core.mq.RabbitQueue.RABBIT_QUEUE_BLUETOOTH_LOG_QUEUE;

/**
 * 蓝牙APP-MQ消费端
 *
 * @author whl
 */
@Component
public class RabbitMqConsumer {
    private final static Logger LOGGER = LoggerFactory.getLogger(RabbitMqConsumer.class);
    @Resource
    private RabbitMqService rabbitMqService;

    /**
     * 处理蓝牙烟杆数据
     *
     */
    @RabbitListener(queues = RABBIT_QUEUE_BLUETOOTH_LOG_QUEUE)
    public void handlerHomeBMsg(String message) {
        try {
            LOGGER.info("蓝牙APP-消费方同步收到蓝牙烟杆抽烟数据为:" + message);
            BluetoothLogMqVo<BluetoothLogVo> bluetoothLogMqVo = JSONObject.parseObject(message, BluetoothLogMqVo.class);
            rabbitMqService.handlerBlueToothLogMq(bluetoothLogMqVo);
        } catch (Exception e) {
            LOGGER.error("处理消息失败，异常信息：" + e.getMessage());
        }
    }
}
