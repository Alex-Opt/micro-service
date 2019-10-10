package com.ly.mt.activity.web.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description
 * 门店活动登录拦截path配置
 *
 * @author panjingtian
 * @date 2019/6/12 12:53 PM
 */

@Component
@ConfigurationProperties(prefix = "shop-auth-path")
public class ShopAuthPath {


    private List<String> path;

    public void setPath(List<String> path) {
        this.path = path;
    }

    public List<String> getPath() {
        return path;
    }
}
