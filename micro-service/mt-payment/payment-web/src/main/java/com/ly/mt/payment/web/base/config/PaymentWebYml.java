package com.ly.mt.payment.web.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @Description 属性配置
 * @Author taoye
 */
@Component
@RefreshScope
public class PaymentWebYml {
    /**
     * @Description swagger
     */
    @Value("${swagger.enable}")
    private boolean swaggerEnable;
    @Value("${swagger.payment.path}")
    private String swaggerPath;
    @Value("${swagger.payment.title}")
    private String swaggerTitle;
    @Value("${swagger.payment.desc}")
    private String swaggerDesc;
    @Value("${swagger.payment.url}")
    private String swaggerUrl;
    @Value("${swagger.payment.version}")
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