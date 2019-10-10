package com.ly.mt.order.web.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @Description 属性配置
 * @Author taoye
 */
@Component
@RefreshScope
public class OrderWebYml {
    /**
     * @Description swagger
     */
   // @Value("${swagger.enable}")
    private boolean swaggerEnable;
    @Value("${swagger.order.path}")
    private String swaggerPath;
    @Value("${swagger.order.title}")
    private String swaggerTitle;
    @Value("${swagger.order.desc}")
    private String swaggerDesc;
    @Value("${swagger.order.url}")
    private String swaggerUrl;
    @Value("${swagger.order.version}")
    private String swaggerVersion;

    public boolean getSwaggerEnable() {
        return swaggerEnable;
    }

    public String getSwaggerPath() {
        return swaggerPath;
    }

    public String getSwaggerTitle() {
        return swaggerTitle;
    }

    public String getSwaggerDesc() {
        return swaggerDesc;
    }

    public String getSwaggerUrl() {
        return swaggerUrl;
    }

    public String getSwaggerVersion() {
        return swaggerVersion;
    }
}