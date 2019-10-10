package com.ly.mt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 启动类
 *
 * @author taoye
 */
@EnableAsync
@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class MisApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MisApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MisApplication.class, args);
    }
}