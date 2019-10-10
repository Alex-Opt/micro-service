package com.ly.mt.user.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableHystrix
@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages = {"com.ly.mt.core.common", "com.ly.mt.core.server", "com.ly.mt.user.server", "com.ly.mt.core.db", "com.ly.mt.core.sms","com.ly.mt.core.rabbit"})
public class UserServerApplication extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(UserServerApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(UserServerApplication.class, args);
    }
}