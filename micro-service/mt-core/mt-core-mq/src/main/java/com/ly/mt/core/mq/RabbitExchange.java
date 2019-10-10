package com.ly.mt.core.mq;

import static com.ly.mt.core.mq.RabbitQueue.*;

/**
 * rabbit mq 配置枚举
 *
 * @author taoye
 */
public enum RabbitExchange {
    /**
     * 蓝牙烟杆抽烟数据
     */
    RABBIT_MQ_BLUETOOTH_LOG("bluetooth_log_exchange", "bluetooth_log_routing_key", RABBIT_QUEUE_BLUETOOTH_LOG_QUEUE),
    /**
     * 发送mq到一小时达消息队列
     */
    RABBIT_MQ_HOME_B_PROVIDER("home_b_provider_exchange", "home_b_provider_routing_key", RABBIT_QUEUE_HOME_B_PROVIDER),
    /**
     * 计算收益
     */
    RABBIT_MQ_CALCULATE_PROFIT("calculate_profit_exchange", "calculate_profit_routing_key", RABBIT_QUEUE_CALCULATE_PROFIT),
    /**
     * 蜂鸟推送订单状态回调
     */
    RABBIT_MQ_FN_CALLBACK("fn_callback_exchange", "fn_callback_routing_key", RABBIT_QUEUE_FN_CALLBACK),
    /**
     * 支付回调-MALL
     */
    RABBIT_MQ_PAY_NOTIFY_MALL("pay_notify_mall_exchange", "pay_notify_mall_routing_key", RABBIT_QUEUE_PAY_NOTIFY_MALL),
    /**
     * 支付回调-ACTIVITY
     */
    RABBIT_MQ_PAY_NOTIFY_AD_ACTIVITY("pay_notify_ad_activity_exchange", "pay_notify_ad_activity_routing_key", RABBIT_QUEUE_PAY_NOTIFY_AD_ACTIVITY),
    /**
     * 支付回调-HOME
     */
    RABBIT_MQ_PAY_NOTIFY_MT_HOME_B("pay_notify_mt_home_b_exchange", "pay_notify_mt_home_b_routing_key", RABBIT_QUEUE_PAY_NOTIFY_MT_HOME_B),
    /**
     * 格子柜C端通知B端订单信息
     */
    RABBIT_MQ_GZG_C_NOTICE_B("gzg_c_notice_b_exchange", "gzg_c_notice_b_routing_key", RABBIT_QUEUE_GZG_CNOTICE_B);


    private final String exchangeName;
    private final String routingKey;
    private final String queueName;

    RabbitExchange(String exchangeName, String routingKey, String queueName) {
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
        this.queueName = queueName;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public String getRoutingKey() {
        return routingKey;
    }
}