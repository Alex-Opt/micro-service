package com.ly.mt.core.base.entity.hd.model;

/**
 * @description
 * 活动发送短信状态类
 * @author panjingtian
 * @date 2019/6/15 4:05 PM
 *//** @deprecated */
public class HdSmsMsg {

    /**
     * true 发送成功
     * false 发送失败
     */
    private  Boolean flag;

    /**
     * 短信验证码
     */
    private String code;

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public HdSmsMsg() {
    }

    public HdSmsMsg(Boolean flag, String code) {
        this.flag = flag;
        this.code = code;
    }
}
