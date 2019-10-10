package com.ly.mt.gzg.b.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@RequestMapping("/")
@EnableHystrix
@EnableFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableSwagger2
@ComponentScan(basePackages = {"com.ly.mt.core.common", "com.ly.mt.core.rabbit", "com.ly.mt.core.web", "com.ly.mt.core.oss", "com.ly.mt.gzg.b.web"})
public class GzgBWebApplication extends SpringBootServletInitializer {

//    public static void main(String[] args) {
//        SpringApplication.run(GzgBWebApplication.class, args);
//    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GzgBWebApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(GzgBWebApplication.class, args);
    }

    @GetMapping("/valid")
    public String valid() {
        return "success";
    }

}
