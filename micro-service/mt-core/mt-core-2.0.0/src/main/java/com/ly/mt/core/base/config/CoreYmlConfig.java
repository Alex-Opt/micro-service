package com.ly.mt.core.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class CoreYmlConfig {
    /**
     * @Description mq
     */
    @Value("${spring.rabbitmq.host}")
    private String mqHost;
    @Value("${spring.rabbitmq.port}")
    private int mqPort;
    @Value("${spring.rabbitmq.username}")
    private String mqUserName;
    @Value("${spring.rabbitmq.password}")
    private String mqPassword;

    public String getMqHost() {
        return mqHost;
    }

    public int getMqPort() {
        return mqPort;
    }

    public String getMqUserName() {
        return mqUserName;
    }

    public String getMqPassword() {
        return mqPassword;
    }
}