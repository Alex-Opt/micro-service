package com.ly.mt.core.mq.service;

import com.alibaba.fastjson.JSONObject;
import com.ly.mt.core.mq.RabbitExchange;

public interface MqService {
    void sendMessage(RabbitExchange mq, JSONObject jsonObject);
}