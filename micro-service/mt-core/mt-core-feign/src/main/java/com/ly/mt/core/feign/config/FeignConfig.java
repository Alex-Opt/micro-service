package com.ly.mt.core.feign.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * feign配置
 *
 * @author taoye
 */
@Configuration
public class FeignConfig {
    @Bean
    Retryer feignRetryer() {
        // feign取消重试
        return Retryer.NEVER_RETRY;
    }
}