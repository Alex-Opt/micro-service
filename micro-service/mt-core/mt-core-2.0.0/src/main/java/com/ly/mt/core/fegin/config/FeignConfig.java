package com.ly.mt.core.fegin.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description Feign配置
 * @Author taoye
 */
@Configuration
public class FeignConfig {
    @Bean
    Retryer feignRetryer() {
        // feign取消重试
        return Retryer.NEVER_RETRY;
    }
}