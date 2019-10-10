package com.ly.mt.core.base.service.impl;

import com.ly.mt.core.base.mq.MqServer;
import com.ly.mt.core.base.redis.RedisServer;
import com.ly.mt.core.base.service.CoreService;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Primary
@Service
public class CoreServiceImpl implements CoreService {
    private final static Logger LOGGER = LoggerFactory.getLogger(CoreServiceImpl.class);
    @Resource
    public RedisServer redisServer;
    @Resource
    public MqServer mqServer;

    /**
     * @Description MQ移除
     * @Author taoye
     */
    @Override
    public void basicACK(Message message, Channel channel) {
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            LOGGER.error("MQ移除出错:", e);
        }
    }

    /**
     * @Description MQ重回队列
     * @Author taoye
     */
    @Override
    public void basicNACK(Message message, Channel channel) {
        try {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        } catch (IOException e) {
            LOGGER.error("mMQ重回队列出错:", e);
        }
    }

    /**
     * @Description 获取登录用户id
     * @Author taoye
     */
    @Override
    public String getLoginUserId() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        HttpSession session = request.getSession();
        return String.valueOf(session.getAttribute("userId"));
    }

    /**
     * @Description 获取登录用户姓名
     * @Author taoye
     */
    @Override
    public String getLoginUserName() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        HttpSession session = request.getSession();
        return String.valueOf(session.getAttribute("userName"));
    }

    /**
     * @Description 获取登录用户手机
     * @Author taoye
     */
    @Override
    public String getLoginUserMobile() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        HttpSession session = request.getSession();
        return String.valueOf(session.getAttribute("mobile"));
    }

    /**
     * @Description 获取登录用户Ip
     * @Author taoye
     */
    @Override
    public String getLoginIpAddress() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        HttpSession session = request.getSession();
        return String.valueOf(session.getAttribute("ipAddress"));
    }

    /**
     * @Description 获取登录用户店铺id
     * @Author taoye
     */
    @Override
    public String getLoginShopId() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        HttpSession session = request.getSession();
        return String.valueOf(session.getAttribute("shopId"));
    }
}