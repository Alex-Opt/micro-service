package com.ly.mt.cabinet.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class YmlConfig {
    /**
     * @Description swagger
     */
    @Value("${filter}")
    private String filter;

    /**
     * @Description 动态验证码长度
     */
    @Value("${dynamic.code.length}")
    private int dynamicCodeLength;

    /**
     * 如今格子柜的的skey
     */
    @Value("${rujin.skey}")
    private String rujinSkey;

    /**
     * 亿联格子柜相关信息
     */
    @Value("${yl.mctId}")
    private String ylMctId;
    @Value("${yl.deviceImei}")
    private String ylDeviceImei;
    @Value("${yl.apiKey}")
    private String apiKey;

    //展柜商品总数量
    @Value("${goods.zgStockTotal}")
    private int zgStockTotal;
    //展柜套装数量
    @Value("${goods.zgSuitStockTotal}")
    private int zgSuitStockTotal;
    //当展柜低于此数量生成展柜补货订单
    @Value("${goods.zgReplenishStock}")
    private int zgReplenishStock;
    //top3商品skuId
    @Value("${goods.top3SkuId}")
    private String top3SkuId;

    public String getRujinSkey() {
        return rujinSkey;
    }

    public void setRujinSkey(String rujinSkey) {
        this.rujinSkey = rujinSkey;
    }

    public String getYlMctId() {
        return ylMctId;
    }

    public void setYlMctId(String ylMctId) {
        this.ylMctId = ylMctId;
    }

    public String getYlDeviceImei() {
        return ylDeviceImei;
    }

    public void setYlDeviceImei(String ylDeviceImei) {
        this.ylDeviceImei = ylDeviceImei;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * app微信支付的appId
     */
    @Value("${wx.appId.appAccount}")
    private String wxAppAccountId;
    @Value("${wx.appId.publicAccount}")
    private String wxPublicAccountId;
    @Value("${wx.appId.gzgbAccount}")
    private String wxGzgbAccount;

    public String getFilter() {
        return filter;
    }

    public int getDynamicCodeLength() {
        return dynamicCodeLength;
    }

    public String getWxAppAccountId() {
        return wxAppAccountId;
    }
    public String getWxPublicAccountId() {
        return wxPublicAccountId;
    }

    public int getZgStockTotal() {
        return zgStockTotal;
    }

    public void setZgStockTotal(int zgStockTotal) {
        this.zgStockTotal = zgStockTotal;
    }

    public int getZgSuitStockTotal() {
        return zgSuitStockTotal;
    }

    public void setZgSuitStockTotal(int zgSuitStockTotal) {
        this.zgSuitStockTotal = zgSuitStockTotal;
    }

    public String getTop3SkuId() {
        return top3SkuId;
    }

    public void setTop3SkuId(String top3SkuId) {
        this.top3SkuId = top3SkuId;
    }

    public int getZgReplenishStock() {
        return zgReplenishStock;
    }

    public void setZgReplenishStock(int zgReplenishStock) {
        this.zgReplenishStock = zgReplenishStock;
    }

    public String getWxGzgbAccount() {
        return wxGzgbAccount;
    }

    public void setWxGzgbAccount(String wxGzgbAccount) {
        this.wxGzgbAccount = wxGzgbAccount;
    }
}