package com.ly.mt.cabinet.b.common.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginsInterceptor loginsInterceptor;

    //在此处，将拦截器注册为一个 Bean
    @Bean
    public LoginsInterceptor getLoginInterceptor() {
        return new LoginsInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginsInterceptor).addPathPatterns("/cabinet/b/**");
        //registry.addInterceptor(getLoginInterceptor()).addPathPatterns("/cabinet/b/**");
    }
}