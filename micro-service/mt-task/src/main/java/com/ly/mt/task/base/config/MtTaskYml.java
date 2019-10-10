package com.ly.mt.task.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @Description 属性配置
 * @Author taoye
 */
@Component
@RefreshScope
public class MtTaskYml {
    @Value("${gy.serverUrl}")
    private String serverUrl;
    @Value("${gy.appKey}")
    private String appKey;
    @Value("${gy.secret}")
    private String secret;
    @Value("${gy.sessionKey}")
    private String sessionKey;
    @Value("${gy.shopIdCommon}")
    private String shopIdCommon;
    @Value("${gy.shopIdNextDay}")
    private String shopIdNextDay;
    @Value("${gy.shopIdMerchant}")
    private String shopIdMerchant;
    @Value("${gy.warehouseId}")
    private String warehouseId;

    public String getServerUrl() {
        return serverUrl;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getSecret() {
        return secret;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public String getShopIdCommon() {
        return shopIdCommon;
    }

    public String getShopIdNextDay() {
        return shopIdNextDay;
    }

    public String getShopIdMerchant() {
        return shopIdMerchant;
    }

    public String getWarehouseId() {
        return warehouseId;
    }
}