package com.ly.mt.core.common.dict;

/**
 * rabbitmq定义交换机，routingkey的枚举类
 * @author zhanglifeng
 * @date 2019-06-26
 */
public enum RabbitMqEnum {
    RABBIT_MQ_HOME_B_PROVIDER("home_b_exchange","notify_home_b"),
    RABBIT_MQ_CALCULATE_PROFIT("calculate_profit_exchange","profit_routing_key"),;

    private final String exchange;

    private final String routingKey;

    RabbitMqEnum(String exchange, String routingKey) {
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    public String getExchange() {
        return exchange;
    }

    public String getRoutingKey() {
        return routingKey;
    }}
