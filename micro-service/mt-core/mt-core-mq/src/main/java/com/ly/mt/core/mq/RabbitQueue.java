package com.ly.mt.core.mq;

/**
 * rabbit mq queue 枚举
 *
 * @author taoye
 */
public class RabbitQueue {
    /**
     * 蓝牙烟杆抽烟数据
     */
    public static final String RABBIT_QUEUE_BLUETOOTH_LOG_QUEUE = "bluetooth_log_queue";
    /**
     * 发送mq到一小时达消息队列
     */
    public static final String RABBIT_QUEUE_HOME_B_PROVIDER = "home_b_provider_queue";
    /**
     * 计算收益
     */
    public static final String RABBIT_QUEUE_CALCULATE_PROFIT = "calculate_profit_queue";
    /**
     * 蜂鸟推送订单状态回调
     */
    public static final String RABBIT_QUEUE_FN_CALLBACK = "fn_callback_queue";
    /**
     * 支付回调-MALL
     */
    public static final String RABBIT_QUEUE_PAY_NOTIFY_MALL = "pay_notify_mall_queue";
    /**
     * 支付回调-ACTIVITY
     */
    public static final String RABBIT_QUEUE_PAY_NOTIFY_AD_ACTIVITY = "pay_notify_ad_activity_queue";
    /**
     * 支付回调-HOME
     */
    public static final String RABBIT_QUEUE_PAY_NOTIFY_MT_HOME_B = "pay_notify_mt_home_b_queue";
    /**
     * 格子柜C端通知B端订单信息
     */
    public static final String RABBIT_QUEUE_GZG_CNOTICE_B = "gzg_c_notice_b_queue";
}