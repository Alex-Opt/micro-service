package com.ly.mt.core.common.config;

import feign.Retryer;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
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