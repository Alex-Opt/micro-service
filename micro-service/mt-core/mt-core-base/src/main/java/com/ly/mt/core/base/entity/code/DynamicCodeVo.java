package com.ly.mt.core.base.entity.code;
/** @deprecated */
public class DynamicCodeVo {
    /**
     * @Description 手机号
     * @Author taoye
     */
    private String mobile;
    /**
     * @Description 动态验证码类型
     * @Author taoye
     */
    private String dynamicCodeType;


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDynamicCodeType() {
        return dynamicCodeType;
    }

    public void setDynamicCodeType(String dynamicCodeType) {
        this.dynamicCodeType = dynamicCodeType;
    }
}