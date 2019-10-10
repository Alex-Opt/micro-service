package com.ly.mt.open.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class YmlConfig {
    /**
     * @Description filter
     */
    @Value("${filter}")
    private String filter;

    public String getFilter() {
        return filter;
    }
}