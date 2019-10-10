package com.ly.mt.center.data;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.mq.service.MqService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.ly.mt.core.mq.RabbitExchange.RABBIT_MQ_PAY_NOTIFY_MALL;

public class MqTest extends MallApplicationTest {
    @Autowired
    MqService mqService;

    @Test
    public void testSendMq() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("test1", "test1");
        jsonObject.put("test2", "test2");
        jsonObject.put("test3", "test3");
        mqService.sendMessage(RABBIT_MQ_PAY_NOTIFY_MALL, jsonObject);
    }
}