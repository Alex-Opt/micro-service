package com.ly.mt.blue.tooth.base.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program:my-blue-tooth
 * @description:静态资源配置
 * @author:wanghongliang
 * @create:2019-08-0615:45
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    //在此处，将拦截器注册为一个 Bean
    @Bean
    public LoginInterceptor getLoginInterceptor() {
        return new LoginInterceptor();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/bluetooth/web/login").setViewName("index");
    }

    /**
     * 拦截所有请求，除了登录，首页，错误页面
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getLoginInterceptor())
                .addPathPatterns("/**")
                //排除的请求让可以直接访问
                .excludePathPatterns("/","/index.html","/shopManager.html","/shopDetail.html","/static/**")
                .excludePathPatterns("/resources/**").excludePathPatterns("/css/**").excludePathPatterns("/js/**")
                .excludePathPatterns("/image/**").excludePathPatterns("/bluetooth/h5/wxshop/**");
        //registry.addInterceptor(getLoginInterceptor()).addPathPatterns("/**");

    }

    /**
     * 静态资源目录放开
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resources/**")
//                .addResourceLocations("classpath:/resources/")
//                .addResourceLocations("classpath:/static/**");
        registry.addResourceHandler("/**")
.addResourceLocations("classpath:/META-INF/resources/").
                addResourceLocations("classpath:/resources/").
                addResourceLocations("classpath:/static/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}