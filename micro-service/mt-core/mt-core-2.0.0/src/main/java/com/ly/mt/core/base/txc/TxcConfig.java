package com.ly.mt.core.base.txc;

import com.taobao.txc.client.aop.TxcTransactionScaner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(value = "open.txc")
@Configuration
public class TxcConfig {
    @Bean(name = "txcScanner")
    @ConfigurationProperties(prefix = "moti")
    public TxcTransactionScaner txcTransactionScaner() {
        return new TxcTransactionScaner("moti.1898541498834417.BJ");
    }
}
