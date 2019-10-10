package com.ly.mt.core.base.entity.user;


/**
 * @Description 用户信息修改vo类
 * @Author taoye
 *//** @deprecated */
public class UserInfoVo extends User {
    /**
     * @Description 动态验证码
     */
    private String dynamicCode;

    /**
     * //购物车sku数量
     */
    private String buyCarSkuNum;

    public String getDynamicCode() {
        return dynamicCode;
    }

    public void setDynamicCode(String dynamicCode) {
        this.dynamicCode = dynamicCode;
    }

    public String getBuyCarSkuNum() {
        return buyCarSkuNum;
    }

    public void setBuyCarSkuNum(String buyCarSkuNum) {
        this.buyCarSkuNum = buyCarSkuNum;
    }
}