package com.ly.mt.core.wechat.dict;

/**
 * 红包发放结果枚举
 *
 * @author taoye
 */
public enum SendStatus {
    SEND_STATUS_SENDING("SENDING", "发放中"),
    SEND_STATUS_SENT("SENT", "已发放待领取"),
    SEND_STATUS_FAILED("FAILED", "发放失败"),
    SEND_STATUS_RECEIVED("RECEIVED", "已领取"),
    SEND_STATUS_RFUND_ING("RFUND_ING", "退款中"),
    SEND_STATUS_REFUND("REFUND", "已退款");

    private String code;
    private String name;

    SendStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
