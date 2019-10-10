package com.ly.mt.blue.tooth.base.config;

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

    @Value("${wx.signatureUrl}")
    private String signatureUrl;

    public String getFilter() {
        return filter;
    }

    public String getSignatureUrl() {
        return signatureUrl;
    }

    public void setSignatureUrl(String signatureUrl) {
        this.signatureUrl = signatureUrl;
    }
}