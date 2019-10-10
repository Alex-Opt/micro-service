package com.ly.mt.gzg.b.server;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@EnableHystrix
@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages = {"com.ly.mt.core.common", "com.ly.mt.core.server", "com.ly.mt.core.rabbit", "com.ly.mt.core.db", "com.ly.mt.core.sms", "com.ly.mt.core.oss", "com.ly.mt.gzg.b.server"})
@EnableAsync
@EnableRabbit
public class GzgBServerApplication extends SpringBootServletInitializer {

    //    public static void main(String[] args) {
//        SpringApplication.run(GzgBServerApplication.class, args);
//    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GzgBServerApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(GzgBServerApplication.class, args);
    }

    @GetMapping("/valid")
    public String valid() {
        return "success";
    }

}
