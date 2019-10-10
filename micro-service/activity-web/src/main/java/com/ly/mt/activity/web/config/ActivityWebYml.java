package com.ly.mt.activity.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @Description 属性配置
 */
@Component
@RefreshScope
public class ActivityWebYml {
    /**
     * @Description swagger
     */
    @Value("${swagger.enable}")
    private boolean swaggerEnable;
    @Value("${swagger.activity.path}")
    private String swaggerPath;
    @Value("${swagger.activity.title}")
    private String swaggerTitle;
    @Value("${swagger.activity.desc}")
    private String swaggerDesc;
    @Value("${swagger.activity.url}")
    private String swaggerUrl;
    @Value("${swagger.activity.version}")
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