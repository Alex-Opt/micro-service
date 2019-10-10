package com.ly.mt.core.base.mq;

/**
 * rabbitmq定义交换机，routingkey的枚举类
 *
 * @author zhanglifeng
 * @date 2019-06-26
 */
public enum MqMethod {
    RABBIT_MQ_HOME_B_PROVIDER("home_b_exchange", "notify_home_b", ""),
    RABBIT_MQ_FN_CALLBACK("fn_callback_exchange", "fn_notify_b", ""),
    RABBIT_MQ_PAY_NOTIFY_ALI("alipay_callback_notify_exchange", "alipay_Routing_key", ""),
    RABBIT_MQ_PAY_NOTIFY_WX("wxpay_callback_notify_exchange", "wxpay_Routing_key", ""),
    MQ_PAY_NOTIFY_MALL("pay_notify_mall_exchange", "pay_notify_mall_key", "MT-MALL支付回调"),

    MQ_GZG_C_NOTICE_B("gzg_c_notice_b_exchange", "gzg_c_notice_b_routing_key", "格子柜C端通知B端订单信息"),
    RABBIT_MQ_BLUETOOTH_LOG("bluetooth_log_exchange", "bluetooth_log_routing_key", "蓝牙烟杆抽烟数据");


    private final String exchange;
    private final String routingKey;
    private final String desc;

    MqMethod(String exchange, String routingKey, String desc) {
        this.exchange = exchange;
        this.routingKey = routingKey;
        this.desc = desc;
    }

    public String getExchange() {
        return exchange;
    }

    public String getRoutingKey() {
        return routingKey;
    }
}
