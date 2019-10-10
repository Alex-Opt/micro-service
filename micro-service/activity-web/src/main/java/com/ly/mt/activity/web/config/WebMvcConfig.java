package com.ly.mt.activity.web.config;

import com.ly.mt.activity.web.Interceotors.ShopAuthInterceotor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @description webmvc 配置
 * 此处目前只添加了一个方法拦截器
 *
 * @author panjingtian
 * @date 2019/6/12 3:39 PM
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private ShopAuthInterceotor shopAuthInterceotor;
   // @Autowired
    //private ShopAuthPath path;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //List<String> path = this.path.getPath();
        //    - /my/*
        //    - /login/*
        String[] paths;
        paths = new String[]{"/activity/**"};
        //String[] paths = new String[path.size()];
        //TODO 1.5版本不支持传入集合，只能传数组，很坑很辣鸡  升级2.0时候可以用下面这个
        //registry.addInterceptor(shopAuthInterceotor).addPathPatterns(path);
        //registry.addInterceptor(shopAuthInterceotor).addPathPatterns(path.toArray(paths));
        registry.addInterceptor(shopAuthInterceotor).addPathPatterns(paths);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowCredentials(true);
    }
}
