package com.ly.mt.core.base.service;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

public interface CoreService {
    /**
     * @Description MQ移除
     * @Author taoye
     */
    void basicACK(Message message, Channel channel);

    /**
     * @Description MQ重回队列
     * @Author taoye
     */
    void basicNACK(Message message, Channel channel);

    /**
     * @Description 获取登录用户id
     * @Author taoye
     */
    String getLoginUserId();

    /**
     * @Description 获取登录用户姓名
     * @Author taoye
     */
    String getLoginUserName();

    /**
     * @Description 获取登录用户手机
     * @Author taoye
     */
    String getLoginUserMobile();

    /**
     * @Description 获取登录用户Ip
     * @Author taoye
     */
    String getLoginIpAddress();

    /**
     * @Description 获取登录用户店铺id
     * @Author zhanglifeng
     */
    String getLoginShopId();
}