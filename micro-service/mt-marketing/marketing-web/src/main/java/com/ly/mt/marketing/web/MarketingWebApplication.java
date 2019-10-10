package com.ly.mt.marketing.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
/**
 * @创建人 zhuyh
 * @创建时间 2019/6/14
 * @描述
 */

@EnableHystrix
@EnableFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.ly.mt.core.common", "com.ly.mt.core.web", "com.ly.mt.core.oss", "com.ly.mt.marketing.web"})
public class MarketingWebApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MarketingWebApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MarketingWebApplication.class, args);
    }
}
