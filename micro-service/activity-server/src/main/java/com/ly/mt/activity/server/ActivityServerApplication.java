package com.ly.mt.activity.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;


@EnableHystrix
@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages = {"com.ly.mt.core.common", "com.ly.mt.core.db","com.ly.mt.core.rabbit", "com.ly.mt.core.server", "com.ly.mt.core.sms", "com.ly.mt.core.oss" ,"com.ly.mt.activity.server"})
public class ActivityServerApplication extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ActivityServerApplication.class);
    }

    public static void main(String[] args) {
         SpringApplication.run(ActivityServerApplication.class, args);
    }
}
