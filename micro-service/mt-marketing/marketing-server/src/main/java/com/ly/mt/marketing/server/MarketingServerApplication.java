package com.ly.mt.marketing.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @创建人 zhuyh
 * @创建时间 2019/6/14
 * @描述
 */
@EnableAsync
@EnableHystrix
@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages = {"com.ly.mt.core.common", "com.ly.mt.core.server", "com.ly.mt.marketing.server", "com.ly.mt.core.db", "com.ly.mt.core.sms"})
public class MarketingServerApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MarketingServerApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MarketingServerApplication.class, args);
    }
}
